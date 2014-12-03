package edu.jsu.mcis;

import java.util.*;

public class Argument 
{
	public enum Type{STRING, INT, BOOLEAN, FLOAT}
	protected Object value;
	protected String name;
	protected String description;
	protected int multipleValuesListSize;
	protected Type type;
	protected List<Object> restrictedValues;
	protected List<Object> multipleValues;

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
		multipleValuesListSize = 0;
		restrictedValues = new ArrayList<Object>();
		multipleValues = new ArrayList<Object>();
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
		if (multipleValuesListSize == 0)
		{
			setSingleValue(newValue);
		}
		else
		{
			setMultipleValue(newValue);
		}
		
	}
	
	private void setSingleValue(String newValue)
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
	
	private void setMultipleValue(String newValue)
	{
		switch(type)
		{
			case INT:
				appendMultipleValue(Integer.parseInt(newValue));
				break;
			case FLOAT:
				appendMultipleValue(Float.parseFloat(newValue));
				break;
			case BOOLEAN:
				appendMultipleValue(getBooleanValue(newValue));
				break;
			default:
				appendMultipleValue(newValue);
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
	private boolean getBooleanValue(String newValue)
	{
		if(newValue.toLowerCase().equals("true") || newValue.toLowerCase().equals("false")) 
		{
			return Boolean.parseBoolean(newValue);
		}
		else throw new NumberFormatException(newValue + " is not true or false.");
	}
	
	public void appendRestrictedValues(List<Object> values)
	{
		restrictedValues = values;
	}
	
	public boolean checkIfRestrictedValue(String value)
	{
		if (restrictedValues.isEmpty())
		{
			return true;
		}
		else
		{
			return checkValue(value);
		}
		
	}
	private boolean checkValue(String value)
	{
		Object tempValue;
		switch(type)
		{
			case INT:
				tempValue = Integer.parseInt(value);
				return restrictedValues.contains(tempValue);
			case FLOAT:
				tempValue = Float.parseFloat(value);
				return restrictedValues.contains(tempValue);
			case BOOLEAN:
				if(value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) 
				{
					tempValue = Boolean.parseBoolean(value);
					return restrictedValues.contains(tempValue);
				}
				else throw new NumberFormatException(value + " is not true or false.");
			case STRING:
				tempValue = value;
				return restrictedValues.contains(tempValue);
			default:
				return false;
		}
	}
	
	public void setMultipleValuesListSize(int size)
	{
		if (type != Type.BOOLEAN) 
		{
			multipleValuesListSize = size;
		}
		
	}
	
	public int getMultipleValuesListSize()
	{
		return multipleValuesListSize;
	}
	public <T> List<T> getMultipleValuesList()
	{
		return parseToGenericList();
	}
	private <T> List<T> parseToGenericList()
	{
		List<T> newList = new ArrayList<T>();
		for (int i = 0; i < multipleValues.size(); i++)
		{
			newList.add((T)multipleValues.get(i));
		}
		return newList;
	}
	private void appendMultipleValue(Object value)
	{
		multipleValues.add(value);
	}
}