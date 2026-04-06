public class Main {
    public static void main(String[] args) {
        System.out.println("=== FIXED WINDOW ===");
        runScenario("T1", "FIXED_WINDOW");

        System.out.println();
        System.out.println("=== SLIDING WINDOW ===");
        runScenario("T1", "SLIDING_WINDOW");
    }

    private static void runScenario(String tenantId, String algorithm) {
        RateLimiter.getInstance(StrategyFactory.create(algorithm));

        RateLimitConfig config = new RateLimitConfig.Builder()
                .limit(5)
                .windowInSeconds(60)
                .key("tenant:" + tenantId)
                .algorithm(algorithm)
                .build();

        InternalService internalService = new InternalService(config);

        for (int i = 1; i <= 6; i++) {
            System.out.println("Call " + i + ":");
            internalService.handleRequest(tenantId, true);
        }
    }
}