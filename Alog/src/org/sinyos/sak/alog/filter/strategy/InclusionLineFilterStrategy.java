package org.sinyos.sak.alog.filter.strategy;

public class InclusionLineFilterStrategy extends AbstractInclusionLineFilterStrategy {
	private boolean includeOrExclude = true;
	private IMatchCondition matchCondition;
	
	public InclusionLineFilterStrategy() {
		super();
	}
	
	public InclusionLineFilterStrategy(boolean includeOrExclude) {
		super();
		this.includeOrExclude = includeOrExclude;
	}
	
	public InclusionLineFilterStrategy(boolean includeOrExclude, IMatchCondition matchCondition) {
		super();
		this.includeOrExclude = includeOrExclude;
		this.matchCondition = matchCondition;
	}

	@Override
	public boolean matchCondition(String line) {
		return matchCondition != null ? matchCondition.matchCondition(line) : false;
	}

	@Override
	public boolean includeOrExclude() {
		return includeOrExclude;
	}

	public void setIncludeOrExclude(boolean includeOrExclude) {
		this.includeOrExclude = includeOrExclude;
	}

	public void setMatchCondition(IMatchCondition matchCondition) {
		this.matchCondition = matchCondition;
	}
}
