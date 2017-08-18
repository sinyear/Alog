package org.sinyos.sak.alog.filter.strategy;

/**
 * 行过滤策略
 * @author zw
 *
 */
public interface LineFilterStrategy {
	public LineFilterResult filter(LineFilterResult result, long lineNumber);
}
