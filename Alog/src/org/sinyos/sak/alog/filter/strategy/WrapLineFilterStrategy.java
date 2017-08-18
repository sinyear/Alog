package org.sinyos.sak.alog.filter.strategy;

public abstract class WrapLineFilterStrategy implements LineFilterStrategy {

	@Override
	public LineFilterResult filter(LineFilterResult result, long lineNumber) {
		return filter(result);
	}

	public abstract LineFilterResult filter(LineFilterResult result);
}
