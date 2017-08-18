package org.sinyos.sak.alog.filter.strategy;

import java.util.Date;
import java.util.regex.MatchResult;

import org.sinyos.framework.common.annotation.Alias;

@Alias("过滤策略-时间选择")
public class DatePeriodLineFilterStrategy extends DateFetchLineFilterStrategy {
	private Date startDate;
	private Date endDate;
	
	public DatePeriodLineFilterStrategy(String regex, String datePattern, Date startDate, Date endDate) {
		super(regex, datePattern);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public String handleMatchDate(MatchResult matchResult, String line, Date date) {
		if (startDate != null && startDate.after(date)) {
			return null;
		}
		if (endDate != null && endDate.before(date)) {
			return null;
		}
		return line;
	}

}
