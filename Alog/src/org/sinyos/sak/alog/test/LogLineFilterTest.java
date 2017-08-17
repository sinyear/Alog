package org.sinyos.sak.alog.test;

import org.sinyos.sak.alog.common.RegexPattern;
import org.sinyos.sak.alog.filter.FileLogFilter;
import org.sinyos.sak.alog.filter.LogFilter;
import org.sinyos.sak.alog.filter.strategy.ContainLineFilterStrategy;
import org.sinyos.sak.alog.filter.strategy.DeleteWordLineFilterStategy;
import org.sinyos.sak.alog.filter.strategy.ReplaceLineFilterStrategy;
import org.sinyos.sak.alog.filter.strategy.SimplePrefixLineFilterStrategy;

public class LogLineFilterTest {
	
	public static void main(String[] args) throws Exception {
		String userHomePath = System.getProperty("user.home");
		LogFilter logFilter = new FileLogFilter(userHomePath + "/Downloads/sf-ext-posp.log");
		ContainLineFilterStrategy errorStrategy = new ContainLineFilterStrategy("[ERROR]");
//		RegexLineFilterStrategy regexStrategy = new RegexLineFilterStrategy("^\\w{2,1320}$");
		String timeRegex = RegexPattern.REGEX_MATCH_LOG4J_TIMESTAMP;
		ReplaceLineFilterStrategy replaceStrategy = new ReplaceLineFilterStrategy(timeRegex, "");
		DeleteWordLineFilterStategy deleteStrategy = new DeleteWordLineFilterStategy(RegexPattern.REGEX_MATCH_JAVA_LINE);
		DeleteWordLineFilterStategy deleteThreadStrategy = new DeleteWordLineFilterStategy(RegexPattern.REGEX_MATCH_JAVA_THREAD_INFO);
		DeleteWordLineFilterStategy deleteNioThreadStrategy = new DeleteWordLineFilterStategy(RegexPattern.REGEX_MATCH_JAVA_THREAD_HTTP_NIO);
		logFilter.addFilterStrategy(new SimplePrefixLineFilterStrategy("2017-07-17 16:").chain(errorStrategy.chain(replaceStrategy.chain(deleteStrategy.chain(deleteThreadStrategy.chain(deleteNioThreadStrategy))))));
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
