public interface RateLimitStrategy {
    boolean isAllowed(String key, RateLimitConfig config);
}