package org.sinyos.sak.alog.filter.strategy;

public abstract class AbstractChainLineFilterStrategy extends WrapLineFilterStrategy implements ILineFilterStrategyChain {
	protected WrapLineFilterStrategy next;
	
	@Override
	public LineFilterResult filter(LineFilterResult result) {
		LineFilterResult rslt = result;
		if (next() != null) {
			rslt = next().filter(rslt);
		}
		return rslt;
	}
	
	public WrapLineFilterStrategy chain(WrapLineFilterStrategy next) {
		this.next = next;
		return this;
	}
	
	public WrapLineFilterStrategy next() {
		return next;
	}
}
