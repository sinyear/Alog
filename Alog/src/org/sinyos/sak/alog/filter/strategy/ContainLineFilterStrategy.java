package org.sinyos.sak.alog.filter.strategy;

import org.sinyos.framework.common.annotation.Alias;

@Alias("过滤策略-包含")
public class ContainLineFilterStrategy extends InclusionLineFilterStrategy {
	private String contain;
	public ContainLineFilterStrategy(String contain) {
		super();
		this.contain = contain;
	}

	public ContainLineFilterStrategy(String contain, boolean includeOrExclude) {
		super(includeOrExclude);
		this.contain = contain;
	}

	@Override
	public boolean matchCondition(String line) {
		return line.contains(contain);
	}
}
