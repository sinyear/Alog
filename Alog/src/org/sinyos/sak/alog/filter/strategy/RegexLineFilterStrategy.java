package org.sinyos.sak.alog.filter.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexLineFilterStrategy extends AbstractInclusionLineFilterStrategy {
	private String regex;

	public RegexLineFilterStrategy(String regex) {
		super();
		this.regex = regex;
	}

	@Override
	public boolean matchCondition(String line) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		return matcher.find();
	}

	@Override
	public boolean includeOrExclude() {
		return true;
	}
}
