package Utilities;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbGlobalMap;

public class CacheUtil {

	public static String getValue(String globalMapName, String key) {
		String value = null;
		MbGlobalMap globalMap = null;
		
		try {
			globalMap = MbGlobalMap.getGlobalMap(globalMapName);
			value = (String) globalMap.get(key);
		} catch(MbException mbe) {
			System.out.println(mbe.getMessage());
			mbe.printStackTrace();
		}
		
		return value;
	}

	public static Boolean addMap(MbElement elmMap) {
		String strValue = null;
		String strKey = null;
		String strMapName = "ratesGlobalMap";
		MbGlobalMap globalMap = null;
		
		try {
			strKey = elmMap.getFirstChild().getValueAsString();
			strValue = elmMap.getLastChild().getValueAsString();
			
			globalMap = MbGlobalMap.getGlobalMap(strMapName);
			
			if(globalMap.containsKey(strKey)) {
				globalMap.update(strKey,strValue);
			} else {
				globalMap.put(strKey, strValue);
			}
		} catch(MbException mbe) {
			System.out.println(mbe.getMessage());
			mbe.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}	
	
	public static Boolean addToCache(String globalMapName, String key, String value) {
		MbGlobalMap globalMap = null;
		
		try {
			globalMap = MbGlobalMap.getGlobalMap(globalMapName);
			
			if(globalMap.containsKey(key)) {
				globalMap.update(key, value);
			} else {
				globalMap.put(key, value);
			}
		} catch(MbException mbe) {
			System.out.println(mbe.getMessage());
			mbe.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public static Boolean removeFromCache(String globalMapName, String key) {
		MbGlobalMap globalMap = null;
		try {
			globalMap = MbGlobalMap.getGlobalMap(globalMapName);
			globalMap.remove(key);
		} catch(MbException mbe) {
			System.out.println(mbe.getMessage());
			mbe.printStackTrace();
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
}
