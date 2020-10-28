package org.ict.algorithm.fsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Task and Listener State graph implementation
 *
 */
public enum TLState implements StateEventListener {
	
	NegativeOneZero(-1, 0) {
		@Override
		public TLState onEventT(TLEvents t, ReplyEvent r) {
			TLState state = null;
			if (t == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (t == TLEvents.T && r == ReplyEvent.N) {
				state = TLState.NegativeOneZero;
			} else if (t == TLEvents.TR && r == ReplyEvent.N) {
				state = TLState.NegativeOneZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
			
		}
		
		@Override
		public TLState onEventL(TLEvents l) {
			TLState state = null;
			if (l == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (l == TLEvents.L) {
				state = TLState.NegativeOneZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
		}
		
		@Override
		public TLState onEventY(ReplyEvent r) {
			TLState state = null;
			if (r == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (r == ReplyEvent.Y) {
				state = TLState.NegativeOneZero;
			} else {
				throw new IllegalArgumentException("ReplyEvent is not illegal!");
			}
			return state;
		}
	},
	ZeroZero(0, 0){
		@Override
		public TLState onEventT(TLEvents t, ReplyEvent r) {
			TLState state = null;
			if (t == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (t == TLEvents.T && r == ReplyEvent.N) {
				state = TLState.OneZero;
			} else if (t == TLEvents.TR && r == ReplyEvent.N) {
				state = TLState.NegativeOneZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
			
		}
		
		@Override
		public TLState onEventL(TLEvents l) {
			TLState state = null;
			if (l == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (l == TLEvents.L) {
				state = TLState.ZeroZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
		}
		
		@Override
		public TLState onEventY(ReplyEvent r) {
			TLState state = null;
			if (r == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (r == ReplyEvent.Y) {
				state = TLState.ZeroZero;
			} else {
				throw new IllegalArgumentException("ReplyEvent is not illegal!");
			}
			return state;
		}
	},
	OneZero(1, 0){
		@Override
		public TLState onEventT(TLEvents t, ReplyEvent r) {
			TLState state = null;
			if (t == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (t == TLEvents.T && r == ReplyEvent.N) {
				state = TLState.TwoZero;
			} else if (t == TLEvents.TR && r == ReplyEvent.N) {
				state = TLState.NegativeOneZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
			
		}
		
		@Override
		public TLState onEventL(TLEvents l) {
			TLState state = null;
			if (l == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (l == TLEvents.L) {
				state = TLState.OneZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
		}
		
		@Override
		public TLState onEventY(ReplyEvent r) {
			TLState state = null;
			if (r == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (r == ReplyEvent.Y) {
				state = TLState.OneOne;
			} else {
				throw new IllegalArgumentException("ReplyEvent is not illegal!");
			}
			return state;
		}
	},
	OneOne(1, 1){
		@Override
		public TLState onEventT(TLEvents t, ReplyEvent r) {
			TLState state = null;
			if (t == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			LOG.info("t={}, r={}", t, r);
			if (t == TLEvents.T && r == ReplyEvent.N) {
				state = TLState.OneOne;
			} else if (t == TLEvents.TR && r == ReplyEvent.N) {
				state = TLState.OneOne;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
			
		}
		
		@Override
		public TLState onEventL(TLEvents l) {
			TLState state = null;
			if (l == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (l == TLEvents.L) {
				state = TLState.ZeroZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
		}
		
		@Override
		public TLState onEventY(ReplyEvent r) {
			TLState state = null;
			if (r == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (r == ReplyEvent.Y) {
				state = TLState.OneOne;
			} else {
				throw new IllegalArgumentException("ReplyEvent is not illegal!");
			}
			return state;
		}
	},
	TwoZero(2, 0){
		@Override
		public TLState onEventT(TLEvents t, ReplyEvent r) {
			TLState state = null;
			if (t == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			LOG.info("t={}, r={}", t, r);
			if (t == TLEvents.T && r == ReplyEvent.N) {
				state = TLState.TwoZero;
			} else if (t == TLEvents.TR && r == ReplyEvent.N) {
				state = TLState.NegativeOneZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
			
		}
		
		@Override
		public TLState onEventL(TLEvents l) {
			TLState state = null;
			if (l == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (l == TLEvents.L) {
				state = TLState.ZeroZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
		}
		
		@Override
		public TLState onEventY(ReplyEvent r) {
			TLState state = null;
			if (r == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (r == ReplyEvent.Y) {
				state = TLState.TwoOne;
			} else {
				throw new IllegalArgumentException("ReplyEvent is not illegal!");
			}
			return state;
		}
	},
	TwoOne(2, 1){
		@Override
		public TLState onEventT(TLEvents t, ReplyEvent r) {
			TLState state = null;
			if (t == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			LOG.info("t={}, r={}", t, r);
			if (t == TLEvents.T && r == ReplyEvent.N) {
				state = TLState.TwoOne;
			} else if (t == TLEvents.TR && r == ReplyEvent.N) {
				state = TLState.TwoOne;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
			
		}
		
		@Override
		public TLState onEventL(TLEvents l) {
			TLState state = null;
			if (l == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (l == TLEvents.L) {
				state = TLState.ZeroZero;
			} else {
				throw new IllegalArgumentException("TLEvents is not illegal!");
			}
			return state;
		}
		
		@Override
		public TLState onEventY(ReplyEvent r) {
			TLState state = null;
			if (r == null) {
				throw new IllegalArgumentException("parameter can not be null!");
			}
			if (r == ReplyEvent.Y) {
				state = TLState.TwoOne;
			} else {
				throw new IllegalArgumentException("ReplyEvent is not illegal!");
			}
			return state;
		}
	};

	public TLState onEventT(TLEvents t, ReplyEvent r) {
		return TLState.ZeroZero;
	}

	public TLState onEventL(TLEvents l) {
		return TLState.ZeroZero;
	}
	
	public TLState onEventY(ReplyEvent r) {
		return TLState.ZeroZero;
	}
	
	private final int first;
	
	private final int second;
	
	private static final Logger LOG = LoggerFactory.getLogger(TLState.class);
	
	private TLState(int first, int second) {
		this.first = first;
		this.second = second;
	}
	
	public static TLState codeOf(int first, int second) {
		for (TLState state : values()) {
			if ((state.first == first) && (state.second == second)) {
				return state;
			}
		}
		throw new IllegalArgumentException("Invalid TLState: <first, second>:<" + first +", " + second +">");
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

}
