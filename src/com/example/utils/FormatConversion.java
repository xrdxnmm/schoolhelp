package com.example.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.domain.Mission;

public class FormatConversion {
	
	//map转换json 
	public String getJsonMap(Map<String,Object> map){
	        StringBuffer sbf = new StringBuffer();
	        Set<String> keys = map.keySet();
	        sbf.append("{");
	        for(String str : keys){
	            sbf.append("\"");
	            sbf.append(str);
	            sbf.append("\":");
	            if(map.get(str) instanceof Map){
	                sbf.append(getJsonMap((Map)map.get(str)));
	            }else if(map.get(str) instanceof List){
	                sbf.append(getJsonList((List) map.get(str)));
	            }else{
	                sbf.append("\"");
	                sbf.append(map.get(str));
	                sbf.append("\"");
	            }
	            sbf.append(",");
	        }
	        sbf = new StringBuffer(sbf.substring(0,sbf.length()-1));
	        sbf.append("}");
	        return sbf.toString();
	    }

	//list转换json

	public String  getJsonList(List<Mission> list){
	        StringBuffer sbf = new StringBuffer("[");
	        for (Object object : list) {
	            if(object instanceof List){
	                sbf.append(getJsonList((List) object));
	            }else if(object instanceof Map){
	                sbf.append(getJsonMap((Map)object));
	            }else{
	                sbf.append(object.toString());
	            }
	            sbf.append(",");
	        }
	        sbf = new StringBuffer(sbf.substring(0,sbf.length()-1));
	        sbf.append("]");
	        return sbf.toString();
	    }
	
	public static String test(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "小白");
        map.put("age", 2);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(int i=0; i<3; i++){
            Map<String, Object> mapc = new HashMap<String, Object>();
            mapc.put("a"+i, i);
            mapc.put("b"+i, i);
            mapc.put("c"+i, i);
            list.add(mapc);
        }
        map.put("list", list);
 //       String str = getJsonMap(map);
 //       System.out.println(str);
        return "";
    }
}
