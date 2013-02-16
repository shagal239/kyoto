import kyoto.AbstractDB;
import kyoto.DBPool;
import kyotocabinet.DB;
import utils.ByteUtils;
import utils.IConnector;

import static utils.TestGen.getBytes;

/**
 * Arkady Shagal
 * 22:07
 */
public class TestCase1 extends TestCase {
    TestCase1(IConnector connector) {
        super(connector);
    }

    @Override
    public void test() throws Exception {
        AbstractDB connector = (AbstractDB) getConnector();
        connector.open(DB.OCREATE | DB.OWRITER);
        for(int i = 0; i < DBPool.GIGO; i++){
            connector.insertData(ByteUtils.toBytes(i), getBytes(2));
        }
        connector.close();
    }
}
