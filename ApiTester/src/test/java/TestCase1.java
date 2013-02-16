import kyoto.KyotoConnector;
import utils.ByteUtils;
import utils.IConnector;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Arkady Shagal
 * 22:07
 */
public class TestCase1 extends TestCase {
    int size = (int) 10e6;

    TestCase1(IConnector connector, int size) {
        super(connector);
        this.size = size;
    }

    @Override
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        IConnector connector = super.getConnector();
        for(int i = 0; i < size; i++){
            connector.insertData(ByteUtils.toBytes(i), ByteUtils.toBytes(i));
        }
        long cur1 = System.currentTimeMillis();
        assertTrue(connector.getCount() == (size));

        for(int i = 300; i < size; i++){
            assertTrue(Arrays.equals(connector.getData(ByteUtils.toBytes(i)), ByteUtils.toBytes(i)));
        }
        long cur2 = System.currentTimeMillis();
        for(int i = 0; i < size/2; i++){
            assertTrue(connector.deleteById(ByteUtils.toBytes(i)));
        }

        long cur3 = System.currentTimeMillis();
        assertEquals(connector.getCount(), (size - (size/2)));
        assertTrue(connector.close());
        long fin = System.currentTimeMillis();
        PrintWriter out = new PrintWriter("testcase1 " + size + " " + ((KyotoConnector)super.getConnector()).getDBName() + ((KyotoConnector)super.getConnector()).getInit());
        out.println(cur1 - start);
        out.println(cur2 - start);
        out.println(cur3 - start);
        out.println(fin - start);
        out.close();
    }
}
