import kyoto.*;

import kyotocabinet.DB;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.ByteUtils;
import utils.IConnector;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static utils.TestGen.getKBBytes;


/**
 * Arkady Shagal
 * 10:16
 */

public class SimpleTest {
    private IConnector connector1;
    private IConnector connector2;
    private IConnector connector3;
    private IConnector connector4;
    private IConnector connector5;
    private IConnector connector6;
    private IConnector connector7;
    private IConnector connector8;
    volatile byte[] dataset = getKBBytes(1);
    volatile byte[] dataset2 = getKBBytes(2);
    private int size = (int) 10e5;
    private int reads = size / 4;
    private int updates = size / 4;
    private int deletes = size / 4;
    Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void before() throws Exception {
        connector1 = new HashDB();
        connector2 = new HashDB("test2.kch#" + DBPool.tune_buckets1 + "#" + DBPool.tune_map1);
        connector3 = new TreeDB();
        connector4 = new TreeDB("test3.kct#" + DBPool.tune_buckets1 + "#" + DBPool.tune_page_cache + "#" + DBPool.tune_map2);
        connector5 = new HashDB("test4.kch#" + DBPool.tune_buckets2 + "#" + DBPool.tune_map1);
        connector6 = new TreeDB("test5.kct#" + DBPool.tune_buckets1 + "#" + DBPool.tune_page_cache + "#" + DBPool.tune_map1);
        connector7 = new TreeDB("test6.kct#" + DBPool.tune_buckets2 + "#" + DBPool.tune_page_cache + "#" + DBPool.tune_map2);
        connector8 = new TreeDB("test7.kct#" + DBPool.tune_buckets2 + "#" + DBPool.tune_page_cache2 + "#" + DBPool.tune_map1);
    }

    @Test
    public void test1() throws Exception {
        abstarcttest((AbstractDB) connector1);
    }
    @Test
    public void test2() throws Exception {
        abstarcttest((AbstractDB) connector2);
    }
    @Test
    public void test3() throws Exception {
        abstarcttest((AbstractDB) connector3);
    }
    @Test
    public void test4() throws Exception {
        abstarcttest((AbstractDB) connector4);
    }
    @Test
    public void test5() throws Exception {
        abstarcttest((AbstractDB) connector5);
    }
    @Test
    public void test6() throws Exception {
        abstarcttest((AbstractDB) connector6);
    }
    @Test
    public void test7() throws Exception {
        abstarcttest((AbstractDB) connector7);
    }

    @Test
    public void test8() throws Exception {
        abstarcttest((AbstractDB) connector8);
    }


    public synchronized void abstarcttest(final AbstractDB connector) throws Exception {
        //System.err.println(System.currentTimeMillis());
        logger.info("size " + size);
        logger.info("reads " + reads);
        logger.info("updates " + updates);
        logger.info("deletes " + deletes);
        logger.info(connector.getInit());

        connector.open(DB.OCREATE | DB.OWRITER | DB.OAUTOTRAN);

        long ttime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            connector.insertData(ByteUtils.toBytes(i), dataset);
        }

        logger.info(System.currentTimeMillis() - ttime);

        assertTrue(connector.getCount() == size);
        logger.info("required " + size);
        Random r = new Random();
        for (int i = 0; i < 239; i++) {
            int cur = r.nextInt(size);
            assertArrayEquals(dataset, connector.getData(ByteUtils.toBytes(cur)));
        }


        class ReadRunnable implements Callable<Long> {
            final int size = reads;

            @Override
            public Long call() throws Exception {
                logger.info("reads started " + size);
                long start = System.currentTimeMillis();
                for (int i = 0; i < size; i++) {
                        if (i % 2 == 0) {
                            byte[] test = connector.getData(ByteUtils.toBytes(i));
                            assertTrue(Arrays.equals(test, dataset2) || Arrays.equals(test, dataset));
                            if (!(Arrays.equals(test, dataset2) || Arrays.equals(test, dataset))) {
                                System.err.println(test + " " + ByteUtils.toLong(test) + " " + i + " " + dataset + " " + dataset2);
                            }
                        }
                }
                return System.currentTimeMillis() - start;
            }
        }
        class DeleteRunnable implements Callable<Long> {
            final int size = deletes;

            @Override
            public Long call() throws Exception {
                logger.info("deletes started" + size);
                long start = System.currentTimeMillis();
                for (int i = 0; i < size; i++) {
                    if (i % 2 == 1) {
                        assertTrue(connector.deleteById(ByteUtils.toBytes(i)));
                    }
                }
                return System.currentTimeMillis() - start;
            }
        }
        class UpdateRunnable implements Callable<Long> {
            final int size = reads;

            @Override
            public Long call() throws Exception {
                logger.info("updates started" + size);
                long start = System.currentTimeMillis();
                for (int i = 0; i < size; i++) {
                    connector.insertData(ByteUtils.toBytes(i), dataset2);
                }
                return System.currentTimeMillis() - start;
            }
        }

        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
        tasks.add(new ReadRunnable());
        tasks.add(new UpdateRunnable());
        tasks.add(new DeleteRunnable());

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Long>> futures = executor.invokeAll(tasks);
        //System.err.println(System.currentTimeMillis());
        connector.close();

        for (Future<Long> f : futures)  {
            try {
                logger.info(f.get() + " " + f.isDone());
            } catch (InterruptedException e) {
                logger.error(e);
                e.printStackTrace();
            } catch (ExecutionException e) {
                logger.error(e);
                e.printStackTrace();
            }
        }
        logger.info(connector.getInit() + " finished");

    }


    @After
    public void after() throws Exception {
        try {
            Path path1 = FileSystems.getDefault().getPath("test.kct");
            Path path2 = FileSystems.getDefault().getPath("test1.kct");
            Path path3 = FileSystems.getDefault().getPath("test2.kct");
            Path path4 = FileSystems.getDefault().getPath("test3.kct");
            Path path5 = FileSystems.getDefault().getPath("test4.kct");
            Path path6 = FileSystems.getDefault().getPath("test5.kct");
            Path path7 = FileSystems.getDefault().getPath("test6.kct");
            Path path8 = FileSystems.getDefault().getPath("test7.kct");

            Path hpath1 = FileSystems.getDefault().getPath("test.kch");
            Path hpath2 = FileSystems.getDefault().getPath("test1.kch");
            Path hpath3 = FileSystems.getDefault().getPath("test2.kch");
            Path hpath4 = FileSystems.getDefault().getPath("test3.kch");
            Path hpath5 = FileSystems.getDefault().getPath("test4.kch");
            Path hpath6 = FileSystems.getDefault().getPath("test5.kch");
            Path hpath7 = FileSystems.getDefault().getPath("test6.kch");
            Path hpath8 = FileSystems.getDefault().getPath("test7.kch");

            Files.delete(path1);
            Files.delete(path2);
            Files.delete(path3);
            Files.delete(path4);
            Files.delete(path5);
            Files.delete(path6);
            Files.delete(path7);
            Files.delete(path8);
            Files.delete(hpath1);
            Files.delete(hpath2);
            Files.delete(hpath3);
            Files.delete(hpath4);
            Files.delete(hpath5);
            Files.delete(hpath6);
            Files.delete(hpath7);
            Files.delete(hpath8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }
}

















































