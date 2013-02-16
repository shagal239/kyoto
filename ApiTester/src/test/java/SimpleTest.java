import kyoto.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.IConnector;


/**
 * Arkady Shagal
 * 10:16
 */

public class SimpleTest {
    private IConnector connector1;
    private IConnector connector2;
    private IConnector connector3;

    @Before
    public void before() throws Exception {
        connector1 = new HashDB();
        connector2 = new HashDB("test2.kch#" + DBPool.tune_buckets1 + "#" + DBPool.page_cache + "#" + DBPool.tune_map2);
        connector3 = new CashDB();
    }

    @Test
    public void test() throws Exception {
        new TestCase1(connector1).run();
        new TestCase1(connector2).run();
        new TestCase1(connector3).run();
    }


    @After
    public void after() throws Exception {

    }
}

















































