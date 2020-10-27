package org.ict.algorithm.fsm;


public class TLStateEventMachine implements StateEventListener {
	
	TLState currentState;
	
	public TLStateEventMachine() {}
	
	public TLStateEventMachine(TLState currentState) {
		this.currentState = currentState;
	}
	
	@Override
	public TLState onEventT(TLEvents t, ReplyEvent r) {
		return currentState.onEventT(t, r);
	}

	@Override
	public TLState onEventL(TLEvents l) {
		return currentState.onEventL(l);
	}

	public TLState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(TLState currentState) {
		this.currentState = currentState;
	}

	@Override
	public TLState onEventY(ReplyEvent r) {
		return currentState.onEventY(r);
	}

}
