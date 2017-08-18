package org.sinyos.sak.alog.filter.strategy;

import org.sinyos.framework.common.annotation.Alias;

@Alias("过滤策略-正则替换")
public class ReplaceLineFilterStrategy extends AbstractChainLineFilterStrategy {
	protected boolean firstOrAll = true;
	protected String regex;
	protected String replacement;
	
	public ReplaceLineFilterStrategy(String regex, String replacement) {
		super();
		this.regex = regex;
		this.replacement = replacement;
	}

	public ReplaceLineFilterStrategy(boolean firstOrAll, String regex, String replacement) {
		super();
		this.firstOrAll = firstOrAll;
		this.regex = regex;
		this.replacement = replacement;
	}


	@Override
	public LineFilterResult filter(LineFilterResult result) {
		String line = result.getLine();
		if (firstOrAll) {
			line = line.replaceFirst(regex, replacement);
		} else {
			line = line.replaceAll(regex, replacement);
		}
		result.setLine(line);
		result.setFilterActive(LineFilterActive.CONTINUE);
		return super.filter(result);
	}
}
