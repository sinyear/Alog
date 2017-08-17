package org.sinyos.sak.alog.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.sinyos.framework.common.annotation.Alias;

@Alias("文件日志过滤器")
public class FileLogFilter extends LogFilter {
	public FileLogFilter(String logFilePath) throws FileNotFoundException {
		this(new File(logFilePath));
	}
	
	public FileLogFilter(File logFile) throws FileNotFoundException {
		super(new FileInputStream(logFile));
	}	
}
