package org.sinyos.sak.alog.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.sinyos.framework.common.annotation.Alias;
import org.sinyos.sak.alog.filter.strategy.LineFilterActive;
import org.sinyos.sak.alog.filter.strategy.LineFilterResult;
import org.sinyos.sak.alog.filter.strategy.LineFilterStrategy;

@Alias("通用日志过滤器")
public class LogFilter {
	private InputStream logStream;
	private OutputStream outputStream;
	private List<LineFilterStrategy> filterStrategyies = new ArrayList<LineFilterStrategy>();
	
	public LogFilter() {
		super();
	}

	public LogFilter(InputStream logStream) {
		super();
		this.logStream = logStream;
	}

	public void addFilterStrategy(LineFilterStrategy filterStrategy) {
		this.filterStrategyies.add(filterStrategy);
	}
	
	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void reset() {
		this.filterStrategyies.clear();
	}
	
	public void filter(InputStream is) {
		logStream = is;
		filter();
	}
	
	public void filter(OutputStream os) {
		outputStream = os;
		filter();
	}
	
	public void filter() {
		filter(logStream, outputStream);
	}
	
	public void filter(InputStream is, OutputStream os) {
		if (!canFilter()) return ;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(is/*, "utf-8"*/));
			if (os != null) {
				bw = new BufferedWriter(new OutputStreamWriter(os));
			}
			String line = null;
			LineFilterResult result = null;
			LineFilterActive active = null;
			long lineNumber = 0;
			while ((line = br.readLine()) != null) {
				lineNumber++;
				result = new LineFilterResult(line);
				for (LineFilterStrategy filterStrategy : filterStrategyies) {
					if (result.getLine() == null) {
						break ;
					}
					result = filterStrategy.filter(result, lineNumber); 
					active = result.getFilterActive();
					if (LineFilterActive.CONTINUE != active) {
						break ;
					}
				}
				if (null ==  active || LineFilterActive.NONE == active || result.getLine() == null) {
					continue ;
				}
				if (LineFilterActive.BREAK == active) {
					break ;
				}
				if (bw != null) {
					bw.write(result.getLine());
					bw.newLine();	
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSafety(br);
			closeSafety(bw);
		}
	}
	
	public void filter(List<Long> targetLines) {
		if (!canFilter()) return ;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(logStream));
			bw = new BufferedWriter(new OutputStreamWriter(outputStream));
			String line = null;
			
			long lineNumber = 0;
			while ((line = br.readLine()) != null) {
				lineNumber++;
				if (!targetLines.contains(lineNumber)) {
					continue;
				}
				bw.write(line);
				bw.newLine();				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSafety(br);
			closeSafety(bw);
		}
	}
	
	public void closeSafety(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public byte[] filt2ByteArray() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		filter(os);
		return os.toByteArray();
	}
	
	public String filt2String() {
		byte[] bytes = filt2ByteArray();
		if (bytes == null) return null;
		return new String(bytes);
	}
	
	public boolean canFilter() {
		if (logStream == null) return false;
		return true;
	}
}
