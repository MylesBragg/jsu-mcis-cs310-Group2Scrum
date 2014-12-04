package edu.jsu.mcis;

import java.util.*;


 /**
   * Exception class that is used to identify whether an invalid value is being 
   * used in the place of an argument.
   */
public class InvalidValueException extends RuntimeException
{
	private String usage;
	private String program;
	private String name;
	private String type;
	private Object currentValue;
	
	
	/**
     * Sets the programs name specified by a String. 
     * @param programName Sets the program field.
     */
	public void setProgramName(String programName) 
	{
		program = programName;
	}
	/**
     * Sets the usage to a String value.
     * @param usageLine Sets the usage field. 
     */
	public void setUsageLine(String usageLine) 
	{
		usage = usageLine;
	}
	 /**
     * Sets the name and argument type to be validated.
	 * @param name Used to set the name field.
	 * @param type Used to set the type of invalid argument.
     */
	public void setInvalidValueArgumentInformation(String name, Argument.Type type) {
		this.name = name;
		setInvalidValueArgumentType(type);
	}
	
	/**
     * Sets the name and argument type to be validated.
	 * @param type Used to identify what value if being used.
     */
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
     * Sets the name and argument type to be validated.
	 * @param value Sets the current invalid value.
     */
	public void setInvalidValue(Object value) {
		currentValue = value;
	}
    /**
     * Displays an error when a current argument being supplied is not supported. 
     * @return Returns a helpful message describing the error.
     */
	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + program + ".java: error: argument " + name;
		errorMessage = errorMessage + ": invalid " + type + " value: " + currentValue;
		return errorMessage;
	}
}