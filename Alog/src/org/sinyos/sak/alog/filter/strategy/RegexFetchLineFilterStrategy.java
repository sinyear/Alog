package org.sinyos.sak.alog.filter.strategy;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegexFetchLineFilterStrategy extends AbstractChainLineFilterStrategy {
	private String regex;

	public RegexFetchLineFilterStrategy(String regex) {
		super();
		this.regex = regex;
	}
	
	@Override
	public LineFilterResult filter(LineFilterResult result) {
		String line = result.getLine();
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			MatchResult matchResult = matcher.toMatchResult();
			if (matchResult.groupCount() > 0) {
				line = handleMatchResult(matchResult, line);
			}
		}
		
		// 处理结果为抛弃，则不继续往下走
		if (line == null) {
			result.setLine(line);
			result.setFilterActive(LineFilterActive.NONE);
			return result;
		}
		// 正常处理，则继续下一个过滤
		result.setLine(line);
		result.setFilterActive(LineFilterActive.CONTINUE);
		return super.filter(result);
	}
	
	public abstract String handleMatchResult(MatchResult matchResult, String line);
}
