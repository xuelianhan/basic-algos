package org.ict.disruptor;

import java.util.concurrent.ThreadFactory;

import org.junit.Before;
import org.junit.Test;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * In the constructor of Disruptor, the following are defined:
 * 1.Event Factory – Responsible for generating objects which will be stored in ring buffer during initialization
 * 2.The size of Ring Buffer – We have defined 16 as the size of the ring buffer. 
 * It has to be a power of 2 else it would throw an exception while initialization. 
 * This is important because it is easy to perform most of the operations using logical binary operators e.g. mod operation
 * 3.Thread Factory – Factory to create threads for event processors
 * 4.Producer Type – Specifies whether we will have single or multiple producers
 * 5.Waiting strategy – Defines how we would like to handle slow subscriber who doesn't keep up with producer's pace
 * 
 * @see https://www.baeldung.com/lmax-disruptor-concurrency
 * @see https://lmax-exchange.github.io/disruptor/
 * @see https://github.com/LMAX-Exchange/disruptor
 * @see https://www.codeproject.com/Tips/1225874/Java-Flow-API-vs-LMAX-Disruptor
 *
 */
public class DisruptorIntegrationTest {
	
	private Disruptor<ValueEvent> disruptor;
	
    private WaitStrategy waitStrategy;

    @Before
    public void setUp() throws Exception {
        waitStrategy = new BusySpinWaitStrategy();
    }

    private void createDisruptor(final ProducerType producerType, final EventConsumer eventConsumer) {
        final ThreadFactory threadFactory = DaemonThreadFactory.INSTANCE;
        disruptor = new Disruptor<>(ValueEvent.EVENT_FACTORY, 16, threadFactory, producerType, waitStrategy);
        // Connect the consumer handler
        disruptor.handleEventsWith(eventConsumer.getEventHandler());
        // It is possible to supply multiple consumers with Disruptor to handle the data that is produced by producer. 
        // In the example above, we have just one consumer a.k.a. event handler.
    }

    private void startProducing(final RingBuffer<ValueEvent> ringBuffer, final int count, final EventProducer eventProducer) {
        eventProducer.startProducing(ringBuffer, count);
    }

    @Test
    public void whenMultipleProducerSingleConsumer_thenOutputInFifoOrder() {
        final EventConsumer eventConsumer = new SingleEventPrintConsumer();
        final EventProducer eventProducer = new DelayedMultiEventProducer();
        createDisruptor(ProducerType.MULTI, eventConsumer);
        // To start the Disruptor
        final RingBuffer<ValueEvent> ringBuffer = disruptor.start();

        startProducing(ringBuffer, 32, eventProducer);

        disruptor.halt();
        disruptor.shutdown();
    }

    @Test
    public void whenSingleProducerSingleConsumer_thenOutputInFifoOrder() {
        final EventConsumer eventConsumer = new SingleEventConsumer();
        final EventProducer eventProducer = new SingleEventProducer();
        createDisruptor(ProducerType.SINGLE, eventConsumer);
        // To start the Disruptor
        final RingBuffer<ValueEvent> ringBuffer = disruptor.start();

        startProducing(ringBuffer, 32, eventProducer);

        disruptor.halt();
        disruptor.shutdown();
    }

    @Test
    public void whenSingleProducerMultipleConsumer_thenOutputInFifoOrder() {
        final EventConsumer eventConsumer = new MultiEventConsumer();
        final EventProducer eventProducer = new SingleEventProducer();
        createDisruptor(ProducerType.SINGLE, eventConsumer);
        // To start the Disruptor
        final RingBuffer<ValueEvent> ringBuffer = disruptor.start();

        startProducing(ringBuffer, 32, eventProducer);

        disruptor.halt();
        disruptor.shutdown();
    }

    @Test
    public void whenMultipleProducerMultipleConsumer_thenOutputInFifoOrder() {
        final EventConsumer eventConsumer = new MultiEventPrintConsumer();
        final EventProducer eventProducer = new DelayedMultiEventProducer();
        createDisruptor(ProducerType.MULTI, eventConsumer);
        // To start the Disruptor
        final RingBuffer<ValueEvent> ringBuffer = disruptor.start();

        startProducing(ringBuffer, 32, eventProducer);

        disruptor.halt();
        disruptor.shutdown();
    }
}
