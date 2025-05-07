import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class EnhancedTask implements Callable<String> {
    private static final Logger logger = Logger.getLogger(EnhancedTask.class.getName());
    private final String name;
    private final String input;
    private final int maxRetries;

    public EnhancedTask(String name, String input) {
        this.name = name;
        this.input = input;
        this.maxRetries = 1; // one retry on failure
    }

    @Override
    public String call() throws Exception {
        int attempt = 0;
        while (attempt <= maxRetries) {
            long start = System.currentTimeMillis();
            try {
                logger.info("Starting Task " + name + " (Attempt " + (attempt + 1) + ")");
                Thread.sleep(1000); // simulate work

                if (Math.random() < 0.1) { // 10% chance to fail
                    throw new Exception("Task " + name + " failed on attempt " + (attempt + 1));
                }

                String result = "Result of " + name + (input != null ? " with input [" + input + "]" : "");
                long timeTaken = System.currentTimeMillis() - start;
                logger.info("Finished Task " + name + " in " + timeTaken + "ms: " + result);
                return result;

            } catch (Exception e) {
                logger.warning(e.getMessage());
                attempt++;
                if (attempt > maxRetries) {
                    throw new Exception(" Task " + name + " failed after " + (attempt) + " attempts");
                }
            }
        }
        return null;
    }
}
