package fn;

import java.util.function.Supplier;

public class ConditionalExecution {

    public static void main(String[] args) {
//        log(LogLevel.ERROR, msg);
//        log(LogLevel.INFO, msg);
        logConditionally(LogLevel.ERROR, () -> "this is a compute intensive log message");
    }

    public enum LogLevel {
        INFO, WARN, ERROR
    }

    private static void log(LogLevel level, String msg) {
        if (level == LogLevel.ERROR) {
            System.out.println(msg);
        }
    }

    private static void logConditionally(LogLevel level, Supplier<String> msg) {
        if (level == LogLevel.ERROR) {
            System.out.println(msg.get());
        }
    }
}
