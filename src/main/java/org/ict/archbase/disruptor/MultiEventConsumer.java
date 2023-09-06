package org.ict.archbase.disruptor;


import com.lmax.disruptor.EventHandler;

public class MultiEventConsumer implements EventConsumer {

    private int expectedValue = -1;
    private int otherExpectedValue = -1;

    @Override
    @SuppressWarnings("unchecked")
    public EventHandler<ValueEvent>[] getEventHandler() {
        final EventHandler<ValueEvent> eventHandler = (event, sequence, endOfBatch) -> assertExpectedValue(event.getValue());
        final EventHandler<ValueEvent> otherEventHandler = (event, sequence, endOfBatch) -> assertOtherExpectedValue(event.getValue());
        return new EventHandler[] { eventHandler, otherEventHandler };
    }

    private void assertExpectedValue(final int id) {
        ++expectedValue;
        System.out.println(expectedValue == id);
    }

    private void assertOtherExpectedValue(final int id) {
        ++otherExpectedValue;
        System.out.println(expectedValue == id);
    }
}
