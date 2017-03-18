package me.paul.yiblog_ssm.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.ui.Model;

public class ModelContent {
	
	Map<String,Object> subjects = new HashMap<>();
	
	public void save(String key,Object value){
		subjects.put(key, value);
	}
	
	public void remove(String key){
		subjects.remove(key);
	}
	
	public Object get(String key){
		return subjects.get(key);
	}
	
	public Set<String> keySet(){
		return subjects.keySet();
	}
	
	public Set<Entry<String,Object>> entrySet(){
		return subjects.entrySet();
	}
	
	public void fillInModel(Model model){
		Set<Entry<String,Object>> set = subjects.entrySet();
		for(Entry<String,Object> entry : set){
			model.addAttribute(entry.getKey(),entry.getValue());
		}
	}

}
