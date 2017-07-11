package com.boomhope.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 字符串分割操作
 * 
 * @version 1.0 2017-03-22
 * @author zhangxx
 *
 */
public class StrSplitUtil {

	private final static Logger logger = LoggerFactory.getLogger(StrSplitUtil.class);

	public static List<String> strSplite(String str) {

		String[] strs = str.split("\\|"); // 如果以竖线为分隔符，则split的时候需要加上两个斜杠【\\】进行转
		if (strs == null || strs.length <= 0) {
			logger.info("strs错误！");
			return null;
		}

		ArrayList<String> resultList = new ArrayList<String>();
		for (int i = 0; i < strs.length; i++) {
			// 遍历用|分割的字符串数组的第一个元素，再次使用[分割，并将分割出的字符串中的]用""替换掉；并添加入resultList中；
			// 将其余的第一次分割的字符串添加到resultList中
			if (i == 0) {
				String myStr = strs[i];
				String[] myStrs = myStr.split("\\[");
				if (myStrs == null || myStrs.length <= 0) {
					logger.info("myStrs错误！");
					return null;
				}
				for (String string : myStrs) {
					resultList.add(string.replace("]", ""));
				}
			} else {
				resultList.add(strs[i]);
			}
		}

		if (resultList == null || resultList.size() <= 0) {
			return null;
		}

		ArrayList<String> customeList = new ArrayList<String>();
		customeList.add(resultList.get(1));
		customeList.add(resultList.get(resultList.size() - 1));

		return customeList;
	}

}
