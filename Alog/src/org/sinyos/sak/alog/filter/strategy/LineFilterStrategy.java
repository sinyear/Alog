package org.sinyos.sak.alog.filter.strategy;

import java.io.IOException;

/**
 * 行过滤策略
 * @author zw
 *
 */
public interface LineFilterStrategy {
	public LineFilterResult filter(LineFilterResult result) throws IOException;
}
