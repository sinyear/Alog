package org.sinyos.sak.alog.filter.strategy;

import org.sinyos.framework.common.annotation.Alias;

@Alias("过滤策略-忽略前缀")
public class SimplePrefixIgnoreLineFilterStrategy extends SimplePrefixLineFilterStrategy {
	
	public SimplePrefixIgnoreLineFilterStrategy(String prefix) {
		super(prefix);
	}

	@Override
	public boolean includeOrExclude() {
		return false;
	}
}
