package org.sinyos.sak.alog.filter.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.MatchResult;

public abstract class DateFetchLineFilterStrategy extends RegexFetchLineFilterStrategy {
	private String datePattern;
	
	public DateFetchLineFilterStrategy(String regex, String datePattern) {
		super(regex);
		this.datePattern = datePattern;
	}

	@Override
	public String handleMatchResult(MatchResult matchResult, String line) {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		String firstGroup = matchResult.group();
		try {
			Date date = sdf.parse(firstGroup);
			if (date != null) {
				return handleMatchDate(matchResult, line, date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return line;
	}

	public abstract String handleMatchDate(MatchResult matchResult, String line, Date date);
}
