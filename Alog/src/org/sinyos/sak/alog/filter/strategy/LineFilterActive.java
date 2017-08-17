package org.sinyos.sak.alog.filter.strategy;

/**
 * 行过滤响应活动指令
 * @author zw
 *
 */
public enum LineFilterActive {
	/**
	 * 不做任何处理，跳过。跳过后续所有过滤策略。
	 */
	NONE,
	/**
	 * 继续。进入下一个过滤策略。
	 */
	CONTINUE,
	/**
	 * 中断。停止所有过滤策略，退出所有过滤循环
	 */
	BREAK;
}
