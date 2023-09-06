package org.ict.archbase.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * 
 * @see https://github.com/eugenp/tutorials/tree/master/disruptor/src/main/java/com/baeldung/disruptor
 * @see https://www.codeproject.com/Tips/1225874/Java-Flow-API-vs-LMAX-Disruptor
 * @see https://lmax-exchange.github.io/disruptor/
 *
 */
public class SingleEventPrintConsumer implements EventConsumer {
	

	@Override
	public EventHandler<ValueEvent>[] getEventHandler() {
        EventHandler<ValueEvent> eventHandler = (event, sequence, endOfBatch) -> print(event.getValue(), sequence);
        return new EventHandler[] { eventHandler };
    }
  
    private void print(int id, long sequenceId) {
        System.out.println("Id is " + id + " sequence id that was used is " + sequenceId);
    }
}
