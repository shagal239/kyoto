package kyoto;

import kyotocabinet.DB;

/**
 * Arkady Shagal
 * 20:15
 */
public class ProtoHashDB extends AbstractDB {
    public ProtoHashDB() {
        init = "-";
    }

    public ProtoHashDB(String s){
        init = s;
    }
}
