import kyoto.AbstractDB;
import org.apache.log4j.Logger;
import utils.IConnector;

/**
 * Arkady Shagal
 * 22:06
 */



abstract class  TestCase implements Runnable{
    Logger logger = Logger.getLogger(this.getClass());
    private final IConnector connector;

    TestCase(IConnector connector) {
        this.connector = connector;
    }

    @Override
    public void run(){
        try {
            logger.info("started " + this.getClass().getSimpleName() + " " + connector.getClass().getSimpleName() + " " + ((AbstractDB)connector).getInit());
            long time = System.currentTimeMillis();
            test();
            logger.info("finished " + (System.currentTimeMillis() - time));
        } catch (Exception e) {
            System.err.println("exception" + this.getClass().getSimpleName());
        }
    }

    public abstract void test() throws Exception;

    public IConnector getConnector() {
        return connector;
    }
}