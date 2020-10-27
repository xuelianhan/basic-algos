package org.ict.algorithm.fsm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Dynamic proxy version for state machine
 */
public class TLStateEventMachineV2 {
	
	private TLState currentState;
	
	final StateEventListener stateEventPublisher = buildStateEventForwarder();
	
	private StateEventListener buildStateEventForwarder() {
		Class<?>[] interfaces = {StateEventListener.class};
		return (StateEventListener) Proxy.newProxyInstance(getClass().getClassLoader(), interfaces, new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				try {
					return method.invoke(currentState, args);
				} catch(Exception e) {
					throw e.getCause();
				}
			}
			
		});
	}
	
	public static void main(String[] args) {
		TLStateEventMachineV2 sm = new TLStateEventMachineV2();
		
		
		sm.setCurrentState(TLState.TwoZero);
		System.out.println("before transfer: <" + sm.getCurrentState().getFirst() + ", " + sm.getCurrentState().getSecond() +">");
		TLState t1 = sm.stateEventPublisher.onEventL(TLEvents.L);
		System.out.println("after transfer: <" + t1.getFirst() + ", " + t1.getSecond() +">");
		
		System.out.println("before transfer: <" + sm.getCurrentState().getFirst() + ", " + sm.getCurrentState().getSecond() +">");
		TLState t2 = sm.stateEventPublisher.onEventT(TLEvents.TR, ReplyEvent.N);
		System.out.println("after transfer: <" + t2.getFirst() + ", " + t2.getSecond() +">");
		
		System.out.println("before transfer: <" + sm.getCurrentState().getFirst() + ", " + sm.getCurrentState().getSecond() +">");
		TLState t3 =sm.stateEventPublisher.onEventY(ReplyEvent.Y);
		System.out.println("after transfer: <" + t3.getFirst() + ", " + t3.getSecond() +">");
	}

	public TLState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(TLState currentState) {
		this.currentState = currentState;
	}

	public StateEventListener getStateEventPublisher() {
		return stateEventPublisher;
	}
}
