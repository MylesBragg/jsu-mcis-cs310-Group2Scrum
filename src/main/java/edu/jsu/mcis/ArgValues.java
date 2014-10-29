package edu.jsu.mcis;

import java.util.*;

public class ArgValues
{
	private Object argValue;
	private String name;
	private String help;
	private dataTypeDefinitions values;

	public ArgValues(String name,String help,dataTypeDefinitions dataType) {
		this.name = name;
		this.help = help;
		values = dataType;

	}

	public String getHelpArg() {
		return help;
	}
	
	public <T extends Object> T getValueArg() {
		return (T) argValue;
	}
	
	public void addValueArg(String v){
		switch(values){
		case INT:
			argValue = Integer.parseInt(v);
			break;
		case FLOAT:
			argValue = Float.parseFloat(v);
			break;
		case BOOLEAN:
			if(v.equals("true") || v.equals("false")){
				argValue = Boolean.parseBoolean(v);
			break;
		default:
			argValue = v;
		}
	}

	public dataTypeDefinitions getDataTypeArg() {
		return values;
	}
}