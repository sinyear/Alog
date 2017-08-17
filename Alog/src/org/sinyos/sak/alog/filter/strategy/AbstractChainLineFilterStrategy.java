package org.sinyos.sak.alog.filter.strategy;

public abstract class AbstractChainLineFilterStrategy implements LineFilterStrategy, ILineFilterStrategyChain {
	protected LineFilterStrategy next;
	
	public LineFilterStrategy chain(LineFilterStrategy next) {
		this.next = next;
		return this;
	}
	
	public LineFilterStrategy next() {
		return next;
	}
}
