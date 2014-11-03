package edu.jsu.mcis;

import java.util.*;

public class InvalidValueException extends RuntimeException
{
	String usage;
	String program;
	String argName;
	String argDataType;
	String currentValue;
	
	public InvalidValueException(String helpUsage, String prog, String argN, ArgValues.Type argDT, String currValue)
	{
		usage = helpUsage;
		program = prog;
		argName = argN;
		if(argDT.equals(ArgValues.Type.INT))
		{
			argDataType = "integer";
		}
		if(argDT.equals(ArgValues.Type.FLOAT))
		{
			argDataType = "float";
		}
		if(argDT.equals(ArgValues.Type.BOOLEAN))
		{
			argDataType = "boolean";
		}
		if(argDT.equals(ArgValues.Type.STRING))
		{
			argDataType = "string";
		}
		currentValue = currValue;
	}
	
	public String getUsage()
	{
		return usage;
	}
	
	public String getArgName()
	{
		return argName;
	}
	
	public String getArgDataType()
	{
		return argDataType;
	}
	
	public String getCurrentValue()
	{
		return currentValue;
	}

	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + program + ".java: error: argument " + argName;
		errorMessage = errorMessage + ": invalid " + argDataType + " value: " + currentValue;
		return errorMessage;
	}
}