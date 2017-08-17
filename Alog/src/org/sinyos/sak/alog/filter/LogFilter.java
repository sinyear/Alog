package org.sinyos.sak.alog.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.sinyos.sak.alog.filter.strategy.LineFilterActive;
import org.sinyos.sak.alog.filter.strategy.LineFilterStrategy;

public class LogFilter {
	private InputStream logStream;
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
	
	public void reset() {
		this.filterStrategyies.clear();
	}

	public void filter(InputStream is, OutputStream os) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(is/*, "utf-8"*/));
			bw = new BufferedWriter(new OutputStreamWriter(os));
			String line = null;
			LineFilterActive active = null;
			while ((line = br.readLine()) != null) {
				for (LineFilterStrategy filterStrategy : filterStrategyies) {
					active = filterStrategy.filter(bw, line); 
					if (LineFilterActive.CONTINUE != active) {
						break ;
					}
				}
				if (null ==  active || LineFilterActive.NONE == active) {
					continue ;
				}
				if (LineFilterActive.BREAK == active) {
					break ;
				}
				bw.newLine();				
			}
			br.close();
			bw.close();
		} catch (Exception e) {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}
	
	public byte[] filt2ByteArray() {
		if (logStream == null) return null;
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		filter(logStream, os);
		return os.toByteArray();
	}
	
	public String filt2String() {
		byte[] bytes = filt2ByteArray();
		if (bytes == null) return null;
		return new String(bytes);
	}
}
