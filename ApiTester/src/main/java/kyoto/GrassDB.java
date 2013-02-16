package kyoto;

import kyotocabinet.DB;

/**
 * Arkady Shagal
 * 20:16
 */
public class GrassDB extends AbstractDB {
    public GrassDB() {
        init = "%";
    }

    public GrassDB(String s){
        init = s;
    }
}
