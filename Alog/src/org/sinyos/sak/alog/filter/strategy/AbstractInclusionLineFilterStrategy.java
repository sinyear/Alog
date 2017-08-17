package org.sinyos.sak.alog.filter.strategy;

import java.io.IOException;
import java.io.Writer;
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
	public LineFilterActive filter(Writer writer, String line) throws IOException {
		boolean conditon = matchCondition(line);
		if (includeOrExclude()) {
			return includeFilter(writer, line, conditon);
		} else {			
			return excludeFilter(writer, line, conditon);
		}
	}
	
	protected LineFilterActive includeFilter(Writer writer, String line, boolean condition) throws IOException {
		if (condition) {
			// 包含关系则继续处理下一个，最后才写入
			LineFilterStrategy nextStrategy = next();
			if (nextStrategy != null) {
				return nextStrategy.filter(writer, line);
			}
			
			writer.write(line);
			return LineFilterActive.CONTINUE;					
		}
		return LineFilterActive.NONE;
	}
	
	protected LineFilterActive excludeFilter(Writer writer, String line, boolean condition) throws IOException {
		if (condition) {
			// 不包含关系则继续处理下一个，最后才忽略
			LineFilterStrategy nextStrategy = next();
			if (nextStrategy != null) {
				return nextStrategy.filter(writer, line);
			}
						
			return LineFilterActive.NONE;					
		}
		return LineFilterActive.CONTINUE;
	}
	
	/**
	 * 包含或不包含（即处理或忽略）
	 * @return true 包含，false不包含
	 */
	public abstract boolean includeOrExclude();
}
