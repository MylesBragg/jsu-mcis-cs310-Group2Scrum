package edu.jsu.mcis;

import java.util.*;

public class InvalidValueException extends RuntimeException
{
	private String usage;
	private String program;
	private String name;
	private String type;
	private Object currentValue;
	
	public InvalidValueException(String helpUsage, String prog, String argN, Argument.Type argDT, Object currValue)
	{
		usage = helpUsage;
		program = prog;
		name = argN;
		if(argDT.equals(Argument.Type.INT))
		{
			type = "integer";
		}
		if(argDT.equals(Argument.Type.FLOAT))
		{
			type = "float";
		}
		if(argDT.equals(Argument.Type.BOOLEAN))
		{
			type = "boolean";
		}
		if(argDT.equals(Argument.Type.STRING))
		{
			type = "string";
		}
		currentValue = currValue;
	}
	
	public String getUsage()
	{
		return usage;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}
	
	public <T> T getCurrentValue()
	{
		return (T)currentValue;
	}

	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + program + ".java: error: argument " + name;
		errorMessage = errorMessage + ": invalid " + type + " value: " + currentValue;
		return errorMessage;
	}
}