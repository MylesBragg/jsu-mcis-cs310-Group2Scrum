package edu.jsu.mcis;

import java.util.*;
@SuppressWarnings("unchecked")
public class Arguments 
{
	public enum Type{STRING, INT, BOOLEAN, FLOAT, FLAG}
	private Object value;
	private String name;
	private String description
	private Type type;

	public type(String name,String help,Type dataType) {
		this.name = name;
		this.help = help;
		type = dataType;
	}

	public String getDescription() {
		return help;
	}
	
	public <T extends Object> T getValue() {
		return (T)value;
	}
	
	public void setValue(String v) throws NumberFormatException {
		switch(type){
		case INT:
			value = Integer.parseInt(v);
			break;
		case FLOAT:
			value = Float.parseFloat(v);
			break;
		case BOOLEAN:FLAG:
			if(v.equals("true") || v.equals("false")){
				value = Boolean.parseBoolean(v);
			}
			else throw new NumberFormatException("Invalid Argument");
			break;
		default:
			value = v;
		}
	}

	public Type getType() {
		return type;
	}
}