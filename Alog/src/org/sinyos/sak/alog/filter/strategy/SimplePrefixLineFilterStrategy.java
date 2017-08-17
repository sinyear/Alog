package org.sinyos.sak.alog.filter.strategy;

import org.sinyos.framework.common.annotation.Alias;

@Alias("过滤策略-包含前缀")
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
