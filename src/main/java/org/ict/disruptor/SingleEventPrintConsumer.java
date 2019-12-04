package org.ict.disruptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.EventHandler;

/**
 * 
 * @see https://github.com/eugenp/tutorials/tree/master/disruptor/src/main/java/com/baeldung/disruptor
 * @see https://www.codeproject.com/Tips/1225874/Java-Flow-API-vs-LMAX-Disruptor
 * @see https://lmax-exchange.github.io/disruptor/
 *
 */
public class SingleEventPrintConsumer implements EventConsumer {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public EventHandler<ValueEvent>[] getEventHandler() {
        EventHandler<ValueEvent> eventHandler = (event, sequence, endOfBatch) -> print(event.getValue(), sequence);
        return new EventHandler[] { eventHandler };
    }
  
    private void print(int id, long sequenceId) {
        System.out.println("Id is " + id + " sequence id that was used is " + sequenceId);
        logger.info("Id is " + id + " sequence id that was used is " + sequenceId);
    }
}
