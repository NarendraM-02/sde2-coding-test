import java.util.concurrent.*;
import java.util.logging.Logger;

public class TaskRunnerE {
    private static final Logger logger = Logger.getLogger(TaskRunner.class.getName());

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down executor service...");
            executor.shutdownNow();
        }));

        try {
            Future<String> aResult = executor.submit(new EnhancedTask("A", null));
            Future<String> bResult = executor.submit(new EnhancedTask("B", null));
            String aOutput = aResult.get();
            String bOutput = bResult.get();

            Future<String> cResult = executor.submit(new EnhancedTask("C", aOutput + " + " + bOutput));
            Future<String> dResult = executor.submit(new EnhancedTask("D", aOutput + " + " + bOutput));
            String cOutput = cResult.get();
            String dOutput = dResult.get();

            Future<String> fResult = executor.submit(new EnhancedTask("F", cOutput + " + " + dOutput));
            String fOutput = fResult.get();

            logger.info("Final Output from Task F: " + fOutput);

        } catch (Exception e) {
            logger.severe("Workflow Failed: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
