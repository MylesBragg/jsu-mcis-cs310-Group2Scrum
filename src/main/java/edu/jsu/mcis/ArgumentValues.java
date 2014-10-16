package edu.jsu.mcis;

import java.util.*;

public class ArgumentValues
{
	private HashMap<String, Object> namedValues;
	private HashMap<String, String> namedHelp;
	private HashMap<String, String> namedDataType;
	private List<String> name;
	private List<String> value;
	
	public ArgumentValues() {
		namedValues = new HashMap<String, Object>();
		namedHelp = new HashMap<String, String>();
		namedDataType = new HashMap<String, String>();
		name = new ArrayList<String>();
		value = new ArrayList<String>();
	}
		
	public void addHelpArgument(String name, String helpMessage) {
		namedHelp.put(name, helpMessage);
	}
	
	public void addValueArgument(String name, String value) {
		namedValues.put(name, value);
	}
	public void addValueArgument(String name, int value) {
		namedValues.put(name, value);
	}
	public void addValueArgument(String name, boolean value) {
		namedValues.put(name, value);
	}
	public void addValueArgument(String name, float value) {
		namedValues.put(name, value);
	}
	
	public String getHelpArgument(String name) {
		return namedHelp.get(name);
	}
	
	public Object getValueArgument(String name, String dataType) {
		switch(dataType) {
			case "optional":
				namedValues.put(name, namedValues.get(name).toString());
				break;
			case "integer":
				int intValue = Integer.parseInt(namedValues.get(name).toString());
				namedValues.put(name, intValue);
				break;
			case "string":
				namedValues.put(name, namedValues.get(name).toString());
				break;
			case "boolean":
				boolean boolValue = Boolean.parseBoolean(namedValues.get(name).toString());
				namedValues.put(name, boolValue);
				break;
			case "float":
				float floatValue =  Float.parseFloat(namedValues.get(name).toString());
				namedValues.put(name, floatValue);
				break;
		}
		return namedValues.get(name);
	}
	
	
	public void addDataTypeArgument(String name, String dataType) {
		namedDataType.put(name, dataType);
	}
	
	public String getDataTypeArgument(String name) {
		return namedDataType.get(name);
	}
	
	public int size() {
		return namedValues.size();
	}
}