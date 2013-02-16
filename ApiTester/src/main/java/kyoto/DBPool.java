package kyoto;

import kyotocabinet.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * Arkady Shagal
 * 10:02
 */
public class DBPool {
    static long tmp1 = (10l * 1000 * 1000);
    static long tmp2 = 60L * 1000 * 1000;
    static long tmp3 = (8L << 30);

    static long tmp4 = (500L * 1000);
    static long tmp5 = (32768);
    static long tmp6 = (long) 10e8;
    static public List<DB> dbs = new ArrayList<DB>();
    static public DB hashdb = new TreeDB("test.kct#tune_buckets=" + tmp4 + "#tune_page=" + tmp5 + "#tune_page_cache" + tmp6);

    static {

        DB cashdbtun = new CashDB("*#tune_buckets=" + tmp1 + "#cap_count=" + tmp2 + "#cap_size=" + tmp3);
        DB cashdb = new CashDB();
        DB grassdbtun = new GrassDB("%#tune_buckets=" + tmp4 + "#tune_page=" + tmp5 + "#tune_page_cache" + tmp6);
        DB grassdb = new GrassDB();
        DB stashdb = new StashDB();
        dbs.add(cashdb);
        dbs.add(cashdbtun);
        dbs.add(grassdb);
        dbs.add(stashdb);
        dbs.add(hashdb);
    }

}
