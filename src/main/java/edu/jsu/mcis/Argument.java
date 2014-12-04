package edu.jsu.mcis;

import java.util.*;
/**
 * Class that is specifically used to identify arguments that can be stored, described,
 * named, and set into lists depending upon the type of information being parsed.
 * Overall, it should be able to contain multiple values while also allowing the user to set 
 * a list of restricted values depending on what they specify.
 */
public class Argument 
{
	/**
	* Enumerator class used to identify the types of arguments being used.
	*/
	public enum Type{STRING, INT, BOOLEAN, FLOAT}
	protected Object value;
	protected String name;
	protected String description;
	protected int multipleValuesListSize;
	protected Type type;
	protected List<Object> restrictedValues;
	protected List<Object> multipleValues;

	/**
     * The constructor takes in two parameters of String & Enumerator Type
     * & depending on the type of enumerator being passed in it will set the 
     * value field to either false or null. Note this will also create two empty
	 * lists for multiple & restricted values.
     * @param name Sets the name field.
     * @param dataType Sets the type field. 
     */
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
	   /**
     * Returns the name of the string argument.
     * @return String representation of name. 
     */
	public String getName() 
	{
		return name;
	}
	    /**
     * Returns an enumerator expression.
     * @return Enumerator type {STRING,INT,BOOLEAN,FLOAT}.
     */
	public Type getType() 
	{
		return type;
	}
	    /**
     * Sets the description as a string value.
     * @param description Sets the description field.
     */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	    /**
     * Returns the description as a string.
     * @return String description.
     */
	public String getDescription() 
	{
		return description;
	}
	    /**
     * Returns the value of the Object depending on its primitive type.
     * @param <T> Returns a value of any type.
     * @return Gives a value of either String,Int,Float,or Boolean. 
     */
	public <T extends Object> T getValue() 
	{
		return (T)value;
	}
	/**
     * Sets the string value by deciding if the list size is equal to 0.
     * @param newValue Sets the value depending on the list size.
     */
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
	
	/**
     * Adds the given list to a set of restricted values.
     * @param values Restricts the lists values given.
     */ 
	public void appendRestrictedValues(List<Object> values)
	{
		restrictedValues = values;
	}
	/**
     * Returns the set of restricted values from a list.
     * @return Gives the restricted values.
     */
	public List<Object> getRestrictedValues()
	{
		return restrictedValues;
	}
	/**
     * Returns true if the restricted values list is empty, otherwise the value is 
	 * checked and returned if it is contained in the restricted list.
     * @return Gives the restricted value.
     */
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
	
	/**
     * Sets the multiple value list size.
	 * @param size Uses size to set the lists length.
     */
	public void setMultipleValuesListSize(int size)
	{
		multipleValuesListSize = size;
	}
	
	/**
     * Returns the multiple values current list size.
     * @return Gives back an int value of the list size.
     */
	public int getMultipleValuesListSize()
	{
		return multipleValuesListSize;
	}
	
	/**
     * Returns the set of multiple values within the list through generic parsing.
     * @return Gives each multiple value through the list.
     */
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