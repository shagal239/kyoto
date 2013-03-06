package kyoto;

/**
 * Arkady Shagal
 * 10:02
 */
public class DBPool {
    public static final long GIGO = 1073741824;
    public static final long MEGO = 1048576;
    public static final long KILO = 1024;

    private static final long tmp1 = 104857600;
    private static final long tmp2 = (long)(7*GIGO);

    public static final String tune_map1 = "tune_map=" + tmp2;
    public static final String tune_map2 = "tune_map=" + 100 * MEGO;
    public static final String tune_buckets1 = "tune_buckets=100000000";
    public static final String tune_buckets2 = "tune_buckets=1000000000";
    public static final String tune_page_cache = "tune_page_cache=" + tmp1;
    public static final String tune_page_cache2 = "tune_page_cache=" + tmp2;
}
