package org.sinyos.sak.alog.filter.strategy;

public class SimplePrefixIgnoreLineFilterStrategy extends SimplePrefixLineFilterStrategy {
	
	public SimplePrefixIgnoreLineFilterStrategy(String prefix) {
		super(prefix);
	}

	@Override
	public boolean includeOrExclude() {
		return false;
	}
}
