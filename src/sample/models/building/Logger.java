package sample.models.building;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class Logger {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getGlobal();

    public static void ConfigLogger() {
        try {
            FileHandler handler = new FileHandler("Log.log");
            handler.setFormatter(new LogFormatter());
            logger.setUseParentHandlers(false);
            logger.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Log(Level level, String message) {
        logger.log(level, message);
    }
}
