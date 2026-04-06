# Rate-Limiter-LLD

Pluggable Rate Limiting System

How to run:
1. Compile all files under `src`.
2. Run `Main`.

Design note:
Strategy Pattern fits here because the rate limiting algorithm can change without changing the service flow. The internal service depends on the strategy interface, so the same request path can use either fixed window or sliding window.

Fixed Window is simpler and cheaper to store because it keeps only one counter and one window start time. Sliding Window is more accurate because it counts only calls inside the last full window, but it needs a deque of timestamps and more memory.

Burst-at-boundary problem: with a 5-per-minute fixed window, a tenant can send 5 calls at 12:00:59 and another 5 calls at 12:01:00, so 10 calls happen in about 1 second. Sliding Window reduces this burst because it looks back across the last 60 seconds.

Singleton makes sense here because the application should have one shared rate-limiter entry point managing the active strategy.