package org.sinyos.sak.alog.filter;

import java.io.ByteArrayInputStream;

public class StringLogFilter extends LogFilter {
	
	public StringLogFilter(String string) {
		super(new ByteArrayInputStream(string.getBytes()));
	}	
}
