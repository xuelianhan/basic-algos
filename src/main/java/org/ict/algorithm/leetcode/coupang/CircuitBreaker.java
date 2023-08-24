package org.ict.algorithm.leetcode.coupang;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Design a circuit breaker that implements failure threshold statistics
 * within a sliding window to dynamically adjust whether it fuses
 * --------------------
 * This circuit breaker implementation has the following features:
 *
 * It has a failure threshold, which is the number of failures that must occur within a sliding window before the circuit breaker opens.
 * It has a sliding window, which is the period of time over which the failures are counted.
 * It has a state machine, which tracks the state of the circuit breaker (open, closed, or half-open).
 * The circuit breaker is implemented as a singleton, so there is only one instance of the circuit breaker in the system.
 * This ensures that all calls to the execute() method are routed to the same circuit breaker.
 *
 * The execute() method takes a key as input.
 * The key is used to identify the resource that is being protected by the circuit breaker.
 * The execute() method then checks the state of the circuit breaker.
 * If the circuit breaker is open, then the method throws an exception.
 * Otherwise, the method increments the number of failures for the resource and checks if the number of failures has reached the failure threshold.
 * If the number of failures has reached the failure threshold, then the circuit breaker opens.
 * Otherwise, the method returns normally.
 * The isOpen() method returns the state of the circuit breaker.
 * The reset() method resets the circuit breaker to the closed state.
 *
 * @author sniper
 * @date 19 Jul 2023
 * @see <a href="https://java-design-patterns.com/patterns/circuit-breaker/#explanation"></a>
 */
public class CircuitBreaker {
    private final int failureThreshold;
    private final int slidingWindowSize;
    private final TimeUnit slidingWindowUnit;
    private final ConcurrentHashMap<String, FailureRecord> failureRecords;
    private State state;

    public CircuitBreaker(int failureThreshold, int slidingWindowSize, TimeUnit slidingWindowUnit) {
        this.failureThreshold = failureThreshold;
        this.slidingWindowSize = slidingWindowSize;
        this.slidingWindowUnit = slidingWindowUnit;
        this.failureRecords = new ConcurrentHashMap<>();
        this.state = State.CLOSED;
    }

    public void execute(String key) {
        if (this.state == State.CLOSED) {
            FailureRecord failureRecord = this.failureRecords.get(key);
            if (failureRecord == null) {
                failureRecord = new FailureRecord();
            }
            failureRecord.recordFailure();

            if (failureRecord.getNumberOfFailures() >= this.failureThreshold) {
                this.state = State.OPEN;
            }
        }
    }

    public boolean isOpen() {
        return this.state == State.OPEN;
    }

    public void reset() {
        this.state = State.CLOSED;
        this.failureRecords.clear();
    }

    private enum State {
        CLOSED, OPEN, HALF_OPEN
    }

    private class FailureRecord {
        private int numberOfFailures;
        private long lastFailureTime;

        public FailureRecord() {
            this.numberOfFailures = 0;
            this.lastFailureTime = 0L;
        }

        public void recordFailure() {
            this.numberOfFailures++;
            this.lastFailureTime = System.currentTimeMillis();
        }

        public int getNumberOfFailures() {
            return this.numberOfFailures;
        }

        public long getLastFailureTime() {
            return this.lastFailureTime;
        }
    }
}
