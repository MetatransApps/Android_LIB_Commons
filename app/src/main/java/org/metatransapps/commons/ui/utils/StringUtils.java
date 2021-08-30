package org.metatransapps.commons.ui.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class StringUtils {

	
	public static String[] split(String Subject, String Delimiters) {
		StringTokenizer StrTkn = new StringTokenizer(Subject, Delimiters);
		List<String> ArrLis = new ArrayList<String>(Subject.length());
		while (StrTkn.hasMoreTokens()) {
			String cur = StrTkn.nextToken();
			if (cur.trim().equals("")) {
				continue;
			}
			ArrLis.add(cur);
		}
		return ArrLis.toArray(new String[0]);
	}
	
	
}
