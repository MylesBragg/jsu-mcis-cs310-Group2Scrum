package edu.jsu.mcis;

import java.util.*;

public class Argument 
{
	public enum Type{STRING, INT, BOOLEAN, FLOAT}
	protected Object value;
	protected String name;
	protected String description;
	protected Type type;

	public Argument(String name, Type dataType) 
	{
		this.name = name;
		description = "";
		type = dataType;
		if(Type.BOOLEAN  == dataType)
		{
			value = false;
		}
		else
		{
			value = null;
		}
	}
	
	public String getName() 
	{
		return name;
	}
	
	public Type getType() 
	{
		return type;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public <T extends Object> T getValue() 
	{
		return (T)value;
	}
	
	public void setValue(String newValue) 
	{
		switch(type)
		{
			case INT:
				value = Integer.parseInt(newValue);
				break;
			case FLOAT:
				value = Float.parseFloat(newValue);
				break;
			case BOOLEAN:
				setBooleanValue(newValue);
				break;
			default:
				value = newValue;
		}
	}
	private void setBooleanValue(String newValue)
	{
		if(newValue.toLowerCase().equals("true") || newValue.toLowerCase().equals("false")) 
		{
			value = Boolean.parseBoolean(newValue);
		}
		else throw new NumberFormatException(newValue + " is not true or false.");
	}
}