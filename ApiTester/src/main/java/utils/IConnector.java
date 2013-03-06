package utils; /**
 * Arkady Shagal
 * 23:12
 */

import java.util.Set;

public interface IConnector {

    long getCount();

    void insertData(String id, byte[] data) throws Exception;

    void insertData(byte[] id, byte[] data) throws Exception;

    byte[] getData(String id) throws Exception;

    byte[] getData(byte[] id) throws Exception;

    boolean deleteById(String id) throws Exception;

    boolean deleteById(byte[] id) throws Exception;

    Set<byte[]> getAllData(byte[][] ids) throws Exception;

    Set<byte[]> selectRange(byte[] start, byte[] finish) throws Exception;

    void truncate() throws Exception;

    boolean close() throws Exception;
}
