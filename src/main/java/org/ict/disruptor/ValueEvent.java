package org.ict.disruptor;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lmax.disruptor.EventFactory;

import lombok.Getter;
import lombok.Setter;

/**
 * Define the event that carries the data
 */
@Setter
@Getter
public class ValueEvent {
	
	public final static EventFactory<ValueEvent> EVENT_FACTORY = () -> new ValueEvent();

	private int value;
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
   
}
