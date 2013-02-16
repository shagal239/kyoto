package kyoto;

import kyotocabinet.DB;

/**
 * Arkady Shagal
 * 20:16
 */
public class TreeDB extends AbstractDB {

    String s;
    public TreeDB(String s) {
        s = s;
    }

    @Override
    public boolean open(String s, int i) {
        if(s == null){
            return false;
        }
        return super.open(s, i);
    }
}
