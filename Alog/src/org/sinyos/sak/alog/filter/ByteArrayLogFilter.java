package org.sinyos.sak.alog.filter;

import java.io.ByteArrayInputStream;

public class ByteArrayLogFilter extends LogFilter {
	
	public ByteArrayLogFilter(byte[] bytes) {
		super(new ByteArrayInputStream(bytes));
	}	
}
