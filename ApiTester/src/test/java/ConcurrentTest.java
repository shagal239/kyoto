import kyoto.StashDB;
import org.junit.Before;
import org.junit.Test;
import utils.ByteUtils;
import utils.IConnector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Arkady Shagal
 * 10:19
 */
public class ConcurrentTest {
    IConnector connector;
    @Before
    public void before() throws Exception {
        connector = new StashDB();
    }

    @Test
    public void test() throws Exception {
        final int size = 1000000;
        ExecutorService executor = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            final int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                        try {
                                connector.insertData(ByteUtils.toBytes(finalI), ByteUtils.toBytes(finalI));
                                assertEquals(ByteUtils.toLong(connector.getData(ByteUtils.toBytes(finalI))), ByteUtils.toLong(ByteUtils.toBytes(finalI)));
                        } catch (Exception e) {
                                assertTrue(false);
                        }
                    }


            });
        }
        if(executor.isTerminated()){

            assertEquals(size, connector.getCount());
        }
        long fin = System.currentTimeMillis();
        System.err.println(fin - start);
    }
}
