package kyoto;

import kyotocabinet.DB;

/**
 * Arkady Shagal
 * 12:57
 */
public class AbstractDB extends KyotoConnector {
    public AbstractDB(DB db) throws Exception {
        super(db);
    }

    public AbstractDB() {
        super();
    }

    public void setInit(String init) {
        this.init = init;
    }

    public String init;


    public boolean open(int i) {
        return super.getDb().open(init, i);
    }

    public String getInit() {
        return init;
    }
}
