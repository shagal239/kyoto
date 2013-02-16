package kyoto;

import kyotocabinet.DB;
import utils.IConnector;

import java.util.HashSet;
import java.util.Set;

/**
 * Arkady Shagal
 * 9:52
 */
public class KyotoConnector extends AbstractDB implements IConnector {
    private String init;

    public String getDBName() {
        return db.getClass().getSimpleName();
    }

    private DB db;

    public KyotoConnector(DB db) throws Exception {
        this.db = db;

        if(!db.open("%", DB.OWRITER | DB.OCREATE | DB.OAUTOTRAN)){
            throw new Exception("not opened");
        }
    }


    public long getCount(){
        return db.count();
    }
    @Override
    public void insertData(String id, byte[] data) throws InterruptedException, Exception {
        if(db.set(id.getBytes(), data)){
            throw new Exception("not inserted");
        }
    }

    @Override
    public void insertData(byte[] id, byte[] data) throws InterruptedException, Exception {
        boolean res = db.begin_transaction(false);
        if(!db.set(id, data)){
            throw new Exception("not inserted");
        }
        db.end_transaction(res);
    }

    @Override
    public byte[] getData(String id) throws InterruptedException, Exception {
        byte[] result = db.get(id.getBytes());
        if(result != null){
            return result;
        } else {
            throw new Exception("not returned");
        }
    }

    @Override
    public byte[] getData(byte[] id) throws InterruptedException, Exception {
        byte[] result = db.get(id);
        if(result != null){
            return result;
        } else {
            throw new Exception("not returned");
        }
    }

    @Override
    public boolean deleteById(String id) throws InterruptedException, Exception {
        if(!db.remove(id.getBytes())){
            throw new Exception("not removed");
        }
        return true;
    }

    @Override
    public boolean deleteById(byte[] id) throws InterruptedException, Exception {
        boolean start = db.begin_transaction(true);
        db.remove(id);
        return db.end_transaction(start);
    }

    @Override
    public Set<byte[]> getAllData(byte[][] ids) throws Exception, InterruptedException {
        byte[][] result = db.get_bulk(ids, true);
        if (result != null) {
            Set<byte[]> set = new HashSet<byte[]>();
            for(byte[] bytes : result){
                set.add(bytes);
            }
            return set;
        } else {
            throw new Exception("fail");
        }
    }

    @Override
    public void truncate() throws InterruptedException, Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean open(String s, int i) {
        return super.open(s, i);
    }

    @Override
    public boolean close() {
       return db.close();
    }
}
