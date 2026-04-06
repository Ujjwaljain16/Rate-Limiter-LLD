public class RateLimiter {
    private static RateLimiter instance;
    private RateLimitStrategy strategy;

    private RateLimiter(RateLimitStrategy strategy) {
        this.strategy = strategy;
    }

    public static synchronized RateLimiter getInstance(RateLimitStrategy strategy) {
        if (instance == null) {
            if (strategy == null) {
                throw new IllegalStateException("RateLimiter must be initialized with a strategy first.");
            }
            instance = new RateLimiter(strategy);
        } else if (strategy != null) {
            instance.strategy = strategy;
        }
        return instance;
    }

    public static synchronized RateLimiter getInstance() {
        if (instance == null) {
            throw new IllegalStateException("RateLimiter must be initialized with a strategy first.");
        }
        return instance;
    }

    public boolean isAllowed(RateLimitConfig config) {
        return strategy.isAllowed(config.getKey(), config);
    }
}