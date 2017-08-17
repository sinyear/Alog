package org.sinyos.sak.alog.filter.strategy;

public class LineFilterResult {
	private LineFilterActive filterActive;
	private String line;
	
	public LineFilterResult(LineFilterActive filterActive, String line) {
		super();
		this.filterActive = filterActive;
		this.line = line;
	}
	
	public LineFilterResult(String line) {
		super();
		this.line = line;
	}

	public LineFilterActive getFilterActive() {
		return filterActive;
	}
	
	public void setFilterActive(LineFilterActive filterActive) {
		this.filterActive = filterActive;
	}
	
	public String getLine() {
		return line;
	}
	
	public void setLine(String line) {
		this.line = line;
	}
}
