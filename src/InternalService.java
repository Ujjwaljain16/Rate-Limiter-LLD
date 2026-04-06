public class InternalService {
    private final RateLimitConfig config;

    public InternalService(RateLimitConfig config) {
        this.config = config;
    }

    public void handleRequest(String tenantId, boolean needsExternalCall) {
        if (!needsExternalCall) {
            System.out.println("Tenant " + tenantId + ": processed internally, no external call needed.");
            return;
        }

        boolean allowed = RateLimiter.getInstance().isAllowed(config);
        if (allowed) {
            System.out.println("Tenant " + tenantId + ": external call allowed.");
        } else {
            System.out.println("Tenant " + tenantId + ": external call rejected by rate limiter.");
        }
    }
}