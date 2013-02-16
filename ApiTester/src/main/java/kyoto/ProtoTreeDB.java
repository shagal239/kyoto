package kyoto;

import kyotocabinet.DB;

/**
 * Arkady Shagal
 * 20:16
 */
public class ProtoTreeDB extends AbstractDB {
    public ProtoTreeDB() {
        init = "+";
    }

    public ProtoTreeDB(String s){
        init = s;
    }
}
