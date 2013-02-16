package kyoto;

import kyotocabinet.DB;

/**
 * Arkady Shagal
 * 20:17
 */
public class HashDB extends AbstractDB {
    public HashDB() {
        init = "test.kch";
    }

    public HashDB(String s){
        init = s;
    }
}
