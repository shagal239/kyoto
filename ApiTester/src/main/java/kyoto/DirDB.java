package kyoto;

/**
 * Arkady Shagal
 * 17:06
 */
public class DirDB extends AbstractDB {
    public DirDB() {
        init = "test.kcd";
    }

    public DirDB(String s) {
        init = s;
    }
}
