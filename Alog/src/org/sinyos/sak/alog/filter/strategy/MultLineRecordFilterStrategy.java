package org.sinyos.sak.alog.filter.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sinyos.framework.common.annotation.Alias;

@Alias("过滤策略-多行联动")
public class MultLineRecordFilterStrategy implements LineFilterStrategy {
	private List<Long> targetLineList = new ArrayList<Long>();
	// 往回找几行
	private int backwardLineNumber;
	// 往前继续找几行
	private int forwardLineNumber;
	
	public MultLineRecordFilterStrategy(int backwardLineNumber) {
		super();
		this.backwardLineNumber = backwardLineNumber;
	}
	
	public MultLineRecordFilterStrategy(int backwardLineNumber, int forwardLineNumber) {
		super();
		this.backwardLineNumber = backwardLineNumber;
		this.forwardLineNumber = forwardLineNumber;
	}

	@Override
	public LineFilterResult filter(LineFilterResult result, long lineNumber) {
		targetLineList.add(lineNumber);
		return result;
	}

	public List<Long> getRecordTargetLines() {
		Set<Long> tmpLines = new HashSet<Long>(targetLineList.size());
		// TODO to optimize high performance code
		long lineStart = 0;
		long lineEnd = 0;
		for (long lineNumber : targetLineList) {
			lineStart = lineNumber - backwardLineNumber;
			if (lineStart <= 0) {
				lineStart = 1;
			}
			lineEnd = lineNumber + forwardLineNumber;
			do {
				tmpLines.add(lineStart);
			} while (lineStart++ < lineEnd);
		}
		List<Long> targetLines = new ArrayList<>(tmpLines);
		Collections.sort(targetLines);
		return targetLines;
	}
	
//	public static void main(String[] args) {
//		MultLineRecordFilterStrategy strategy = new MultLineRecordFilterStrategy(10, 2);
//		strategy.targetLineList.add(8L);
//		strategy.targetLineList.add(15L);
//		strategy.targetLineList.add(40L);
//		List<Long> targetLines = strategy.getRecordTargetLines();
//		for (long line : targetLines) {
//			System.out.println(line);
//		}
//	}
}
