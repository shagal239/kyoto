package utils;

import java.nio.ByteBuffer;

/**
 * Arkady Shagal
 * 11:19
 */
public class TestGen {
    private static int KBSIZE = 1024;
    private static final byte testbyte = Byte.MAX_VALUE;
    public static byte[] getBytes(int size){
        ByteBuffer buffer = ByteBuffer.allocate(size);
        for(int i = 0; i < size; i++){
            buffer.put(i, testbyte);
        }
        return buffer.array();
    }

    public static byte[] getKBBytes(int count){
        return getBytes(count * KBSIZE);
    }
}
