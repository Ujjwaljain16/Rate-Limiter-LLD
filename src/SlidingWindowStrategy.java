import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindowStrategy implements RateLimitStrategy {
    private final Map<String, Deque<Long>> calls = new HashMap<String, Deque<Long>>();

    public synchronized boolean isAllowed(String key, RateLimitConfig config) {
        long now = System.currentTimeMillis();
        long windowStart = now - (config.getWindowInSeconds() * 1000L);

        Deque<Long> timestamps = calls.get(key);
        if (timestamps == null) {
            timestamps = new ArrayDeque<Long>();
            calls.put(key, timestamps);
        }

        while (!timestamps.isEmpty() && timestamps.peekFirst() < windowStart) {
            timestamps.removeFirst();
        }

        if (timestamps.size() >= config.getLimit()) {
            return false;
        }

        timestamps.addLast(now);
        return true;
    }
}