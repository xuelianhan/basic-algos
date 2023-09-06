package org.ict.archbase.disruptor;

import com.lmax.disruptor.RingBuffer;

public class SingleEventProducer implements EventProducer {

	@Override
	public void startProducing(RingBuffer<ValueEvent> ringBuffer, int count) {
		final Runnable producer = () -> produce(ringBuffer, count);
	    new Thread(producer).start();
	}
	
	/**
	 * Producers place the data in the ring buffer in a sequence. 
	 * Producers have to be aware of the next available slot so that they don't overwrite data that is not yet consumed.
	 * Use the RingBuffer from Disruptor for publishing
	 * @param ringBuffer
	 * @param count
	 */
	private void produce(final RingBuffer<ValueEvent> ringBuffer, final int count) {
        for (int i = 0; i < count; i++) {
            final long seq = ringBuffer.next();
            System.out.println(this.getClass().getSimpleName() + ":current count:" + i + ", next sequence:" + seq);
            try {
            	final ValueEvent valueEvent = ringBuffer.get(seq);
            	// Do some work with the event
                valueEvent.setValue(i);
                System.out.println(this.getClass().getSimpleName() + ":value event set value:" + i);
            } finally {
            	System.out.println(this.getClass().getSimpleName() + ":ringBuffer publish sequence:" + seq);
            	ringBuffer.publish(seq);
            }
           
        }
    }

}
