package kyoto;

import kyotocabinet.DB;

/**
 * Arkady Shagal
 * 20:05
 */
public class CashDB extends AbstractDB {
    public CashDB() {
        init = "*";
    }

    public CashDB(String s){
        init = s;
    }
}
