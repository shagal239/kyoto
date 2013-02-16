import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Arkady Shagal
 * 9:21
 */
public class ConcTest {
    @Test
    public void test(){
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < 50; i++){
            final int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {

                    for(int j = 0; j < 5; j++){
                            System.out.println(finalI + " " + j);
                    }
                }
            });
        }
        executor.shutdown();
    }
}
