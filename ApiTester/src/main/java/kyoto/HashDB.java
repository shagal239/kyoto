package kyoto;

import kyotocabinet.DB;

/**
 * Arkady Shagal
 * 20:17
 */
public class HashDB extends AbstractDB {
    @Override
    public boolean open(String s, int i) {
        if(s == null){
            return false;
        }
        return super.open(s + ".kch", i);
    }
}
