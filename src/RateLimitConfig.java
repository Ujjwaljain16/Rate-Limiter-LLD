public class RateLimitConfig {
    private final int limit;
    private final int windowInSeconds;
    private final String key;
    private final String algorithm;

    private RateLimitConfig(Builder builder) {
        this.limit = builder.limit;
        this.windowInSeconds = builder.windowInSeconds;
        this.key = builder.key;
        this.algorithm = builder.algorithm;
    }

    public int getLimit() {
        return limit;
    }

    public int getWindowInSeconds() {
        return windowInSeconds;
    }

    public String getKey() {
        return key;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public static class Builder {
        private int limit;
        private int windowInSeconds;
        private String key;
        private String algorithm;

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder windowInSeconds(int windowInSeconds) {
            this.windowInSeconds = windowInSeconds;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder algorithm(String algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public RateLimitConfig build() {
            if (limit <= 0) {
                throw new IllegalArgumentException("limit must be positive");
            }
            if (windowInSeconds <= 0) {
                throw new IllegalArgumentException("windowInSeconds must be positive");
            }
            if (key == null || key.length() == 0) {
                throw new IllegalArgumentException("key must not be empty");
            }
            if (algorithm == null || algorithm.length() == 0) {
                throw new IllegalArgumentException("algorithm must not be empty");
            }
            return new RateLimitConfig(this);
        }
    }
}