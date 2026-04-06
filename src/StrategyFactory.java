public class StrategyFactory {
    public static RateLimitStrategy create(String algorithmType) {
        if ("SLIDING_WINDOW".equalsIgnoreCase(algorithmType)) {
            return new SlidingWindowStrategy();
        }
        return new FixedWindowStrategy();
    }
}