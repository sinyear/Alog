package org.sinyos.sak.alog.filter.strategy;

public class SimpleRegexLineFilterStrategy extends AbstractInclusionLineFilterStrategy {
	private boolean includeOrExclude;
	protected String regex;
	
	public SimpleRegexLineFilterStrategy(String regex) {
		this(regex, true);
	}
	
	public SimpleRegexLineFilterStrategy(String regex, boolean includeOrExclude) {
		super();
		this.regex = regex;
		this.includeOrExclude = includeOrExclude;
	}

	@Override
	public boolean includeOrExclude() {
		return includeOrExclude;
	}

	@Override
	public boolean matchCondition(String line) {
		return line.matches(regex);
	}
}
