package kyoto;

import kyotocabinet.DB;

/**
 * Arkady Shagal
 * 12:57
 */
public class AbstractDB extends DB {
    public static String init;


    @Override
    public boolean open(String s, int i) {
        return super.open(init , i);
    }

    public String getInit() {
        return init;
    }
}
