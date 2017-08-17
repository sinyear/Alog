package org.sinyos.sak.alog.filter.strategy;

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
