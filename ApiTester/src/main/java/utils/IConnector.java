package utils; /**
 * Arkady Shagal
 * 23:12
 */

import java.util.Set;

public interface IConnector {

    long getCount();
    void insertData(String id, byte[] data) throws InterruptedException, Exception;

    void insertData(byte[] id, byte[] data) throws InterruptedException, Exception;

    byte[] getData(String id) throws InterruptedException, Exception;

    byte[] getData(byte[] id) throws InterruptedException, Exception;

    boolean deleteById(String id) throws InterruptedException, Exception;

    boolean deleteById(byte[] id) throws InterruptedException, Exception;

    Set<byte[]> getAllData(byte[][] ids) throws Exception, InterruptedException;

    void truncate() throws InterruptedException, Exception;

    boolean close() throws Exception;
}
