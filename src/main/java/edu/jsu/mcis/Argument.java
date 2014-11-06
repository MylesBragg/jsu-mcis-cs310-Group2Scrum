package edu.jsu.mcis;

import java.util.*;
@SuppressWarnings("unchecked")
public class Argument
{
	public enum Type{STRING, INT, BOOLEAN, FLOAT, FLAG}
	protected Object value;
	protected String name;
	protected String description;
	protected Type type;

	public Argument(String name, String description, Type dataType) {
		this.name = name;
		this.description = description;
		type = dataType;
	}

	public String getDescription() {
		return description;
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
		case BOOLEAN: case FLAG:
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