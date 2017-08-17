package org.sinyos.sak.alog.filter.strategy;

import java.io.IOException;
import java.io.Writer;

/**
 * 行过滤策略
 * @author zw
 *
 */
public interface LineFilterStrategy {
	public LineFilterActive filter(Writer writer, String line) throws IOException;
}
