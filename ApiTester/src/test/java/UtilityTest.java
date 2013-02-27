import org.junit.Test;
import utils.ByteUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static utils.TestGen.getBytes;
import static utils.TestGen.getKBBytes;

/**
 * Arkady Shagal
 * 6:26
 */
public class UtilityTest {
    @Test
    public void test()  {
        List<byte[]> lust = new ArrayList<byte[]>();
        for (int i = 0; i < 10e5; i++) {
            lust.add(ByteUtils.toBytes(239));
        }






        for (int i = 0; i < 10e4; i++) {
            for (int j = 0; j < 10e4; j++) {
                assertTrue(Arrays.equals(lust.get(i), lust.get(j)));
            }
        }
        for(int i = 2; i < 234325; i++){
            assertTrue(Arrays.equals(ByteUtils.toBytes(i), ByteUtils.toBytes(i)));
            assertEquals(ByteUtils.toLong(ByteUtils.toBytes(i)), i);
        }
    }

    @Test
    public void test1() throws FileNotFoundException {
        byte[] kbBytes = getKBBytes(10);
        System.err.println(kbBytes);
        System.err.println(kbBytes.length);
        PrintWriter out = new PrintWriter("out.txt");
        for(byte b : kbBytes){
            out.print(b);
        }
        out.close();
    }


}
