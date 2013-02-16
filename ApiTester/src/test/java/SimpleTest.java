import kyoto.*;
import kyotocabinet.DB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.ByteUtils;
import utils.IConnector;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Arkady Shagal
 * 10:16
 */

public class SimpleTest {
    private IConnector connector;
    @Before
    public void before() throws Exception {
        connector = new KyotoConnector(new CashDB());

    }

    @Test
    public void test() throws Exception {
        int[] sizes = {(int) 10e10};
        for (int i : sizes) {
                new TestCase1(new KyotoConnector(DBPool.hashdb), i).run();
        }
    }


    @After
    public void after() throws Exception {
        connector.close();
    }


}

















































