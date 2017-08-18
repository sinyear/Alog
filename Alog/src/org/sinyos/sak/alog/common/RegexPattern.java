package org.sinyos.sak.alog.common;

import org.sinyos.framework.common.annotation.Alias;

@Alias("正则匹配库")
public final class RegexPattern {
	@Alias("正则匹配-日期（yyyy-MM-dd）")
	public final static String REGEX_MATCH_DATE = "((?:19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
	@Alias("正则匹配-时间（HH:mm:ss）")
	public final static String REGEX_MATCH_TIME = "(?:[01]\\d|2[0-3])(?::[0-5]\\d){2}";
	@Alias("正则匹配-日期时间（yyyy-MM-dd HH:mm:ss）")
	public final static String REGEX_MATCH_DATE_TIME = REGEX_MATCH_DATE + " " + REGEX_MATCH_TIME;
	@Alias("正则匹配-LOG4J时间戳（yyyy-MM-dd HH:mm:ss,fff）")
	public final static String REGEX_MATCH_LOG4J_TIMESTAMP = REGEX_MATCH_DATE_TIME + ",[0-9]\\d{0,2}";
	@Alias("正则匹配-JAVA代码行")
	public final static String REGEX_MATCH_JAVA_LINE = "\\((?:\\w+.java,[0-9]\\d{0,5})\\)";
	@Alias("正则匹配-JAVA线程信息")
	public final static String REGEX_MATCH_JAVA_THREAD_INFO = " \\[\\] (?:\\w+-{1,}[0-9]\\d{0,4}) ";
	@Alias("正则匹配-JAVA线程-HTTP-NIO")
	public final static String REGEX_MATCH_JAVA_THREAD_HTTP_NIO = " \\[\\] http-nio-8080-exec(?:-[0-9]\\d{0,4}) ";
}
