package edu.jsu.mcis;

import java.util.*;
	/**
     * Exception class that throws a restricted value based on whether 
	 * it does not belong to that group. 
     */
public class InvalidRestrictedValueException extends RuntimeException
{
	private String usage, program, name, type, selectionSet;
	private Object currentValue;
	private List<Object> list;
	
	/**
     * Sets the program name that is being used.
     * @param programName Uses the program name field.
     */
	public void setProgramName(String programName) 
	{
		program = programName;
	}
	/**
     * Sets the current usage line.
     * @param usageLine Uses the usage line field.
     */
	public void setUsageLine(String usageLine) 
	{
		usage = usageLine;
	}
	/**
     * Sets the invalid argument information based on the name and type.
     * @param name Uses the name field.
	 * @param type Uses the argument type enum field.
     */
	public void setInvalidValueArgumentInformation(String name, Argument.Type type) {
		this.name = name;
		setInvalidValueArgumentType(type);
	}
	/**
     * Sets the current restricted list.
     * @param list Uses the list field.
     */
	public void setRestrictedValuesList (List<Object> list)
	{
		this.list = list;
		setSelectionSet();
	}

	private void setSelectionSet()
	{
		selectionSet = "";
		for (int i = 0; i < list.size(); i++)
		{
			selectionSet = selectionSet + list.get(i).toString() + " ";
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
	/**
     * Sets the current invalid value.
     * @param value Uses the value field.
	 */
	public void setInvalidValue(Object value) {
		currentValue = value;
	}
	/**
     * Displays an error message with the argument name,type value,selection set, & current value. 
     */
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