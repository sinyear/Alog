package org.sinyos.sak.alog.filter.strategy;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import org.sinyos.framework.common.annotation.Alias;

@Alias("过滤策略-统计重复")
public class SimpleRepeatCountLineFilterStrategy implements LineFilterStrategy {
	private TreeMap<String, Integer> repeatCountMap = new TreeMap<String, Integer>();
	
	@Override
	public LineFilterResult filter(LineFilterResult result) throws IOException {
		String line = result.getLine();
		Integer count = 1;
		if (repeatCountMap.containsKey(line)) {
			count += repeatCountMap.get(line);
		}
		repeatCountMap.put(line, count);
		if (count == 1) {
			result.setFilterActive(LineFilterActive.CONTINUE);
			return result;
		} else {
			result.setLine(null);
			result.setFilterActive(LineFilterActive.NONE);
			return result;
		}
	}

	public String getDescription() {
		final String LINE_SEPARATOR = "\n";//AccessController.doPrivileged(new GetPropertyAction("line.separator"));
		StringBuffer sb = new StringBuffer();
		String key = null;
		Integer repeatCount = null;
		for (Iterator<String> iterator = repeatCountMap.keySet().iterator(); iterator.hasNext();) {
			key = (String) iterator.next();
			repeatCount = repeatCountMap.get(key); 
			if (repeatCount > 1) {
				sb.append("( ");
				sb.append(repeatCountMap.get(key));
				sb.append(" ): ");
				sb.append(key);
				sb.append(LINE_SEPARATOR);
			}
		}
		return sb.toString();
	}
}
