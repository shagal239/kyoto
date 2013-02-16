import utils.IConnector;

/**
 * Arkady Shagal
 * 22:06
 */



abstract class  TestCase implements Runnable{
    private final IConnector connector;

    TestCase(IConnector connector) {
        this.connector = connector;
    }

    @Override
    public void run(){
        try {
            test();
        } catch (Exception e) {
            System.err.println("exception" + this.getClass().getSimpleName());
        }
    }

    public abstract void test() throws Exception;

    public IConnector getConnector() {
        return connector;
    }
}