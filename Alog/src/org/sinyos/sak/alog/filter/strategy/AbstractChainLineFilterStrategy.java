package org.sinyos.sak.alog.filter.strategy;

public abstract class AbstractChainLineFilterStrategy implements LineFilterStrategy, ILineFilterStrategyChain {
	protected LineFilterStrategy next;
	
	@Override
	public LineFilterResult filter(LineFilterResult result) {
		LineFilterResult rslt = result;
		if (next() != null) {
			rslt = next().filter(rslt);
		}
		return rslt;
	}
	
	public LineFilterStrategy chain(LineFilterStrategy next) {
		this.next = next;
		return this;
	}
	
	public LineFilterStrategy next() {
		return next;
	}
}
