package edu.jsu.mcis;

import java.util.*;

public class ArgumentValues
{
	private HashMap<String, String> namedValues;
	private HashMap<String, String> namedHelp;
	private HashMap<String, String> namedDataType;
	private List<String> name;
	private List<String> value;
	
	public ArgumentValues() {
		namedValues = new HashMap<String, String>();
		namedHelp = new HashMap<String, String>();
		namedDataType = new HashMap<String, String>();
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
	
	public void addHelpArgument(String name, String helpMessage) {
		namedHelp.put(name, helpMessage);
	}
	
	public void addValueArgument(String name, String value) {
		namedValues.put(name, value);
	}
	
	public String getHelpArgument(String name) {
		return namedHelp.get(name);
	}
	
	public String getValueArgument(String name) {
		return namedValues.get(name);
	}
	
	public void addDataTypeArgument(String name, String dataType) {
		namedDataType.put(name, dataType);
	}
	
	public String getDataTypeArgument(String name) {
		return namedDataType.get(name);
	}
}