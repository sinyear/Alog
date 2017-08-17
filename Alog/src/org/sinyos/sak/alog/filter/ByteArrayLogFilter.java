package org.sinyos.sak.alog.filter;

import java.io.ByteArrayInputStream;

import org.sinyos.framework.common.annotation.Alias;

@Alias("字节数组日志过滤器")
public class ByteArrayLogFilter extends LogFilter {
	
	public ByteArrayLogFilter(byte[] bytes) {
		super(new ByteArrayInputStream(bytes));
	}	
}
