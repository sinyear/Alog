package org.sinyos.sak.alog.filter.strategy;

public interface ILineFilterStrategyChain {
	public LineFilterStrategy next();
}
