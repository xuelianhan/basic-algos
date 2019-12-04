package org.ict.disruptor;

import com.lmax.disruptor.RingBuffer;

/**
 * 
 * @see https://github.com/eugenp/tutorials/blob/master/disruptor/src/main/java/com/baeldung/disruptor/DelayedMultiEventProducer.java
 *
 */
public class DelayedMultiEventProducer implements EventProducer {

	@Override
	public void startProducing(RingBuffer<ValueEvent> ringBuffer, int count) {
		final Runnable simpleProducer = () -> produce(ringBuffer, count, false);
        final Runnable delayedProducer = () -> produce(ringBuffer, count, true);
        new Thread(simpleProducer).start();
        new Thread(delayedProducer).start();
	}
	
	/**
	 * Here, the producer is producing and publishing items in sequence. 
	 * It is important to note here that Disruptor works similar to 2 phase commit protocol. 
	 * It reads a new sequenceId and publishes. 
	 * The next time it should get sequenceId + 1 as the next sequenceId.
	 * 
	 * @param ringBuffer
	 * @param count
	 * @param addDelay
	 */
	private void produce(final RingBuffer<ValueEvent> ringBuffer, final int count, final boolean addDelay) {
        for (int i = 0; i < count; i++) {
            final long seq = ringBuffer.next();
            System.out.println(this.getClass().getSimpleName() + ":current count:" + i + ", next sequence:" + seq);
            try {
            	final ValueEvent valueEvent = ringBuffer.get(seq);
                valueEvent.setValue(i);
                System.out.println(this.getClass().getSimpleName() + ":value event set value:" + i);
            } finally {
            	System.out.println(this.getClass().getSimpleName() + ":ringBuffer publish sequence:" + seq);
            	ringBuffer.publish(seq);
            }
            if (addDelay) {
                addDelay();
                System.out.println(this.getClass().getSimpleName() + " delay finished");
            }
        }
    }
	
	private void addDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            // No-Op lets swallow it
        }
    }

}
