package org.ict.algorithm.fsm;

public interface StateEventListener {
	TLState onEventT(TLEvents t, ReplyEvent r);
	TLState onEventL(TLEvents l);
	TLState onEventY(ReplyEvent r);
}
