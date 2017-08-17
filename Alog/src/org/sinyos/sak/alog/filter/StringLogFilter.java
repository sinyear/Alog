package org.sinyos.sak.alog.filter;

import java.io.ByteArrayInputStream;

import org.sinyos.framework.common.annotation.Alias;

@Alias("字符串日志过滤器")
public class StringLogFilter extends LogFilter {
	
	public StringLogFilter(String string) {
		super(new ByteArrayInputStream(string.getBytes()));
	}	
}
