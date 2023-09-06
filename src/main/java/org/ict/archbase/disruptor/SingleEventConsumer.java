package org.ict.archbase.disruptor;


import com.lmax.disruptor.EventHandler;

public class SingleEventConsumer implements EventConsumer {

	private int expectedValue = -1;

	@Override
	@SuppressWarnings("unchecked")
	public EventHandler<ValueEvent>[] getEventHandler() {
		final EventHandler<ValueEvent> eventHandler = (event, sequence, endOfBatch) -> assertExpectedValue(event.getValue());
		return new EventHandler[] { eventHandler };
	}

	private void assertExpectedValue(final int id) {
		++expectedValue;
		System.out.println(expectedValue == id);
	}

}
