package edu.jsu.mcis;

import java.util.*;

public class ArgumentValues
{
	private List<String> name;
	private List<String> value;
	
	public ArgumentValues() {
		name = new ArrayList<String>();
		value = new ArrayList<String>();
	}
	public void setName(String name) {
		this.name.add(name);
	}
	
	public String getName(int position){
		return name.get(position);
	}
	
	public void setValue(String name, String value) {
		
		this.value.add(value);
	}
	
	public String getValue(String name){
		return value.get(this.name.indexOf(name));
	}
}