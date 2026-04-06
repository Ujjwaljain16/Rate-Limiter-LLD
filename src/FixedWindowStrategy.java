import java.util.HashMap;
import java.util.Map;

public class FixedWindowStrategy implements RateLimitStrategy {
    private final Map<String, int[]> counters = new HashMap<String, int[]>();

    public synchronized boolean isAllowed(String key, RateLimitConfig config) {
        long currentSecond = System.currentTimeMillis() / 1000;
        int[] state = counters.get(key);

        if (state == null) {
            state = new int[]{0, (int) currentSecond};
            counters.put(key, state);
        }

        int windowStart = state[1];
        if (currentSecond - windowStart >= config.getWindowInSeconds()) {
            state[0] = 0;
            state[1] = (int) currentSecond;
        }

        state[0]++;
        return state[0] <= config.getLimit();
    }
}