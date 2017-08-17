package org.sinyos.sak.alog.filter.strategy;

public class SimplePrefixLineFilterStrategy extends AbstractInclusionLineFilterStrategy {
	protected String prefix;
	
	public SimplePrefixLineFilterStrategy(String prefix) {
		super();
		this.prefix = prefix;
	}

	@Override
	public boolean includeOrExclude() {
		return true;
	}

	@Override
	public boolean matchCondition(String line) {
		return line.startsWith(prefix);
	}
}
