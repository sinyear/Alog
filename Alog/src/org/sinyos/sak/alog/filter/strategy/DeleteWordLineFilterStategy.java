package org.sinyos.sak.alog.filter.strategy;

import org.sinyos.framework.common.annotation.Alias;

@Alias("过滤策略-删除")
public class DeleteWordLineFilterStategy extends ReplaceLineFilterStrategy {
	private final static String BLANK_WORD = "";
	
	public DeleteWordLineFilterStategy(boolean firstOrAll, String regex) {
		super(firstOrAll, regex, BLANK_WORD);
	}
	
	public DeleteWordLineFilterStategy(String regex) {
		super(regex, BLANK_WORD);
	}
}
