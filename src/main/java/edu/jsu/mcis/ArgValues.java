package edu.jsu.mcis;

import java.util.*;

public class ArgValues
{
	private Object Xvalue;
	//private String name;
	private String Xhelp;
	private String XdataType;
	private int XintValue;
	
	
	private HashMap<String, Object> namedValues;
	private HashMap<String, String> namedHelp;
	private HashMap<String, String> namedDataType;
	private List<String> name;
	private List<String> value;
	
	public ArgValues() {
		namedValues = new HashMap<String, Object>();
		namedHelp = new HashMap<String, String>();
		namedDataType = new HashMap<String, String>();
		name = new ArrayList<String>();
		value = new ArrayList<String>();
	}
		
	public void addHelpArg(String name, String helpMessage) {
		namedHelp.put(name, helpMessage);
	}
	
	public void addValueArg(String name, String value) {
		namedValues.put(name, value);
	}
	public void addValueArg(String name, int value) {
		namedValues.put(name, value);
	}
	public void addValueArg(String name, boolean value) {
		namedValues.put(name, value);
	}
	public void addValueArg(String name, float value) {
		namedValues.put(name, value);
	}
	
	public String getHelpArg(String name) {
		return namedHelp.get(name);
	}
	
	public Object getValueArg(String name, String dataType) {
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
			case "boolean":case "flag":
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
	
	
	public void addDataTypeArg(String name, String dataType) {
		namedDataType.put(name, dataType);
	}
	
	public String getDataTypeArg(String name) {
		return namedDataType.get(name);
	}
	
	public int size() {
		return namedValues.size();
	}
}