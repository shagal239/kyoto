package kyoto;

import kyotocabinet.Cursor;
import kyotocabinet.DB;
import utils.ByteUtils;
import utils.IConnector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Arkady Shagal
 * 9:52
 */
public class KyotoConnector implements IConnector {
    public KyotoConnector() {
        db = new DB();
    }

    public DB getDb() {
        return db;
    }

    private DB db;

    public KyotoConnector(DB db) throws Exception {
        this.db = db;
    }


    public long getCount() {
        return db.count();
    }

    @Override
    public void insertData(String id, byte[] data) throws Exception {
        if (db.set(id.getBytes(), data)) {
            throw new Exception("not inserted");
        }
    }

    @Override
    public void insertData(byte[] id, byte[] data) throws Exception {
        boolean res = db.begin_transaction(false);
        boolean res2 = db.set(id, data);
        db.end_transaction(res && res2);
    }

    @Override
    public byte[] getData(String id) throws Exception {
        byte[] result = db.get(id.getBytes());
        if (result != null) {
            return result;
        } else {
            throw new Exception("not returned");
        }
    }

    @Override
    public byte[] getData(byte[] id) throws Exception {
        return db.get(id);
    }

    @Override
    public boolean deleteById(String id) throws Exception {
        boolean res = db.begin_transaction(false);
        db.remove(id.getBytes());
        return db.end_transaction(res);
    }

    @Override
    public boolean deleteById(byte[] id) throws Exception {
        boolean start = db.begin_transaction(false);
        db.seize(id);
        return db.end_transaction(start);
    }

    @Override
    public Set<byte[]> getAllData(byte[][] ids) throws Exception {
        byte[][] result = db.get_bulk(ids, true);
        if (result != null) {
            Set<byte[]> set = new HashSet<byte[]>();
            for (byte[] bytes : result) {
                set.add(bytes);
            }
            return set;
        } else {
            throw new Exception("fail");
        }
    }

    @Override
    public Set<byte[]> selectRange(byte[] start, byte[] finish) throws Exception {
        Cursor cursor = new Cursor(db);
        cursor.jump(start);
        Set<byte[]> res = new HashSet<byte[]>();
        res.add(cursor.get_value(true));
        while(true){

            if(Arrays.equals(cursor.get_key(false), finish)){
                break;
            }
            res.add(cursor.get_value(true));
        }
        return res;
    }

    @Override
    public void truncate() throws Exception {
        return;
    }

    @Override
    public boolean close() {
        return db.close();
    }
}
