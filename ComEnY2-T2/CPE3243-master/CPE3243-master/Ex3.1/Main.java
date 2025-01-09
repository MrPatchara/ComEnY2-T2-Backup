// Main.java
import java.util.logging.Logger;

class Main
{
    public static void main(String[] args)
    {
        StringValue string = new StringValue();
        string.logString();
    }
}

class StringValue {
    Logger logger = Logger.getLogger(getClass().getName());
    public void logString() {
        logger.info("Hello Java");
    }
}