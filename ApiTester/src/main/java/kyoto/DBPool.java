package kyoto;

/**
 * Arkady Shagal
 * 10:02
 */
public class DBPool {
    public static final int GIGO = 1073741824;
    public static final int MEGO = 1048576;
    public static final int KILO = 1024;

    private static long tmp1 = 4*GIGO;
    private static long tmp2 = GIGO;

    public static final String tune_map1 = "tune_map=" + GIGO;
    public static final String tune_map2 = "tune_map=" + 100 * MEGO;
    public static final String tune_buckets1 = "tune_buckets=100000000";
    public static final String tune_buckets2 = "tune_buckets=1000000000";
    public static final String tune_page_cache = "tune_page_cache=" + tmp1;
    public static final String tune_page_cache2 = "tune_page_cache=" + tmp2;
}
