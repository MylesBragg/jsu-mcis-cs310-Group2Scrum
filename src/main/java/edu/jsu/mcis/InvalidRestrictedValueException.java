package edu.jsu.mcis;

import java.util.*;

public class InvalidRestrictedValueException extends RuntimeException
{
	private String usage, program, name, type, selectionSet;
	private Object currentValue;
	private List<Object> list;
	
	public void setProgramName(String programName) 
	{
		program = programName;
	}
	
	public void setUsageLine(String usageLine) 
	{
		usage = usageLine;
	}
	
	public void setInvalidValueArgumentInformation(String name, Argument.Type type) {
		this.name = name;
		setInvalidValueArgumentType(type);
	}
	public void setRestrictedValuesList (List<Object> list)
	{
		list = new ArrayList<Object>();
		this.list = list;
	}
	
	private void setSelectionSet()
	{
		for (int i = 0; i < list.size(); i++)
		{
			selectionSet = list.get(i) + " ";
		}
		selectionSet = selectionSet.trim();
	}
	private void setInvalidValueArgumentType(Argument.Type type) {
		switch(type) 
		{
			case INT:
				this.type = "integer";
				break;
			case FLOAT:
				this.type = "float";
				break;
			case BOOLEAN:
				this.type = "boolean";
				break;
			default:
				this.type = "string";
		}
	}
	
	public void setInvalidValue(Object value) {
		currentValue = value;
	}

	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + program + ".java: error: argument " + name;
		errorMessage = errorMessage + ": invalid restricted " + type + " value:";
		errorMessage = errorMessage + "\navailable selection set: " + selectionSet;
		errorMessage = errorMessage + "\nprovided value: " + currentValue;
		return errorMessage;
	}
}