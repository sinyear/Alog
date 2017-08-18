package org.sinyos.sak.alog.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.MatchResult;

import org.sinyos.sak.alog.common.DatePattern;
import org.sinyos.sak.alog.common.RegexPattern;
import org.sinyos.sak.alog.filter.FileLogFilter;
import org.sinyos.sak.alog.filter.LogFilter;
import org.sinyos.sak.alog.filter.strategy.ContainLineFilterStrategy;
import org.sinyos.sak.alog.filter.strategy.DateFetchLineFilterStrategy;
import org.sinyos.sak.alog.filter.strategy.DatePeriodLineFilterStrategy;
import org.sinyos.sak.alog.filter.strategy.DeleteWordLineFilterStategy;
import org.sinyos.sak.alog.filter.strategy.RegexFetchLineFilterStrategy;
import org.sinyos.sak.alog.filter.strategy.RegexLineFilterStrategy;
import org.sinyos.sak.alog.filter.strategy.ReplaceLineFilterStrategy;
import org.sinyos.sak.alog.filter.strategy.SimplePrefixLineFilterStrategy;

public class LogLineFilterTest {
	
	public static void main(String[] args) throws Exception {
		String userHomePath = System.getProperty("user.home");
		LogFilter logFilter = new FileLogFilter(userHomePath + "/Downloads/sf-ext-posp.log");
		ContainLineFilterStrategy errorStrategy = new ContainLineFilterStrategy("[ERROR]");
		RegexLineFilterStrategy regexStrategy = new RegexLineFilterStrategy(RegexPattern.REGEX_MATCH_JAVA_THREAD_HTTP_NIO);
		String timeRegex = RegexPattern.REGEX_MATCH_LOG4J_TIMESTAMP;
		ReplaceLineFilterStrategy replaceStrategy = new ReplaceLineFilterStrategy(timeRegex, "");
		DeleteWordLineFilterStategy deleteStrategy = new DeleteWordLineFilterStategy(RegexPattern.REGEX_MATCH_JAVA_LINE);
		DeleteWordLineFilterStategy deleteThreadStrategy = new DeleteWordLineFilterStategy(RegexPattern.REGEX_MATCH_JAVA_THREAD_INFO);
//		DeleteWordLineFilterStategy deleteNioThreadStrategy = new DeleteWordLineFilterStategy(RegexPattern.REGEX_MATCH_JAVA_THREAD_HTTP_NIO);
//		logFilter.addFilterStrategy(new SimplePrefixLineFilterStrategy("2017-07-17 16:").chain(errorStrategy.chain(replaceStrategy.chain(deleteStrategy.chain(deleteThreadStrategy)))));
//		logFilter.addFilterStrategy(deleteNioThreadStrategy);
//		logFilter.addFilterStrategy(regexStrategy);
//		logFilter.addFilterStrategy(new RegexFetchLineFilterStrategy(RegexPattern.REGEX_MATCH_LOG4J_TIMESTAMP) {
//			@Override
//			public String handleMatchResult(MatchResult matchResult, String line) {
//				System.out.println("MATCH: " + matchResult.group() + " =======  " + line);
//				for (int i = 0; i < matchResult.groupCount(); i++) {
//					System.out.println("MATCH[ " + (i+1) + " ] : " + matchResult.group(i));
//				}
//				return line;
//			}
//		});
		
		
		SimpleDateFormat sdf = new SimpleDateFormat(DatePattern.TIMESTAMP);
		final Date startDate = sdf.parse("2017-07-17 16:30:00,000");
		final Date endDate = sdf.parse("2017-07-17 17:30:00,000");
//		logFilter.addFilterStrategy(new DateFetchLineFilterStrategy(RegexPattern.REGEX_MATCH_LOG4J_TIMESTAMP, DatePattern.TIMESTAMP) {
//			@Override
//			public String handleMatchDate(MatchResult matchResult, String line, Date date) {
////				System.out.println("MATCH DATE: " + date);
////				return line;
//				if (startDate.before(date) && endDate.after(date)) {
//					return line;
//				} else {
//					return null;
//				}
//			}
//		});
		logFilter.addFilterStrategy(new DatePeriodLineFilterStrategy(RegexPattern.REGEX_MATCH_LOG4J_TIMESTAMP, DatePattern.TIMESTAMP, startDate, endDate));
		
		System.out.println(logFilter.filt2String());
		
//		LogFilter logFilter = new FileLogFilter(userHomePath + "/05-backup/清结算日志.txt");
//		logFilter.addFilterStrategy(new SimplePrefixIgnoreLineFilterStrategy("Hibernate: select dictionary0_"));
//		logFilter.addFilterStrategy(new SimplePrefixIgnoreLineFilterStrategy("Hibernate: select sequencere0_"));
//		logFilter.addFilterStrategy(new SimplePrefixLineFilterStrategy("Hibernate: "));
//		byte[] bytes = logFilter.filt2ByteArray();
//		
//		logFilter = new ByteArrayLogFilter(bytes);
//		SimpleRepeatCountLineFilterStrategy repeatCountStrategy = new SimpleRepeatCountLineFilterStrategy();
//		logFilter.addFilterStrategy(repeatCountStrategy);
//		logFilter.addFilterStrategy(new SimplePrefixLineFilterStrategy("Hibernate: "));
//		String rslt = logFilter.filt2String();
//		System.out.println(rslt);
//		System.out.println("==============================");
//		System.out.println(repeatCountStrategy.getDescription());
	}
}
