package org.metatrans.commons.model;


import java.io.Serializable;
import java.util.HashMap;


public class LevelsResults implements Serializable {
	
	
	private static final long serialVersionUID = 1767865576161616365L;
	
	
	public static final String FILE_NAME_LEVELS_RESULTS	= "levels_results";
	
	
	private HashMap<Integer, LevelResult_Base> map;


	public LevelsResults() {
		map = new HashMap<Integer, LevelResult_Base>();
	}
	
	
	public LevelResult_Base getResult(int level) {
		LevelResult_Base cur = map.get(level);
		if (cur == null) {
			cur = new LevelResult_Base();
			map.put(level, cur);
		}
		return cur;
	}
}
