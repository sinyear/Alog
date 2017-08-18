package org.sinyos.sak.alog.filter.strategy;

/**
 * 包含/不包含（处理与忽略）过滤策略
 * @author zw
 *
 */
public abstract class AbstractInclusionLineFilterStrategy extends AbstractChainLineFilterStrategy implements IMatchCondition {
	public AbstractInclusionLineFilterStrategy() {
		super();
	}

	@Override
	public LineFilterResult filter(LineFilterResult result) {
		String line = result.getLine();
		boolean conditon = matchCondition(line);
		if (includeOrExclude()) {
			return includeFilter(result, line, conditon);
		} else {			
			return excludeFilter(result, line, conditon);
		}
	}
	
	protected LineFilterResult includeFilter(LineFilterResult result, String line, boolean condition) {
		if (condition) {
			// 包含关系则继续处理下一个，最后才写入
			LineFilterStrategy nextStrategy = next();
			if (nextStrategy != null) {
				return nextStrategy.filter(result);
			}
			
			result.setFilterActive(LineFilterActive.CONTINUE);
			return result;
		}
		result.setLine(null);
		result.setFilterActive(LineFilterActive.NONE);
		return result;
	}
	
	protected LineFilterResult excludeFilter(LineFilterResult result, String line, boolean condition) {
		if (condition) {
			// 不包含关系则继续处理下一个，最后才忽略
			LineFilterStrategy nextStrategy = next();
			if (nextStrategy != null) {
				return nextStrategy.filter(result);
			}
						
			result.setLine(null);
			result.setFilterActive(LineFilterActive.NONE);					
		}
		result.setFilterActive(LineFilterActive.CONTINUE);
		return result;
	}
	
	/**
	 * 包含或不包含（即处理或忽略）
	 * @return true 包含，false不包含
	 */
	public abstract boolean includeOrExclude();
}
