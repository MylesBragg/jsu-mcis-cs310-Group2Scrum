package edu.jsu.mcis;

import java.util.*;

public class InvalidValueException extends RuntimeException
{
	String usage;
	String program;
	String argName;
	String argDataType;
	String currentValue;
	
	public InvalidValueException(String helpUsage, String prog, String argN, dataTypeDefinitions argDT, String currValue)
	{
		usage = helpUsage;
		program = prog;
		argName = argN;
		if(argDT.equals(dataTypeDefinitions.INT))
		{
			argDataType = "integer";
		}
		if(argDT.equals(dataTypeDefinitions.FLOAT))
		{
			argDataType = "float";
		}
		if(argDT.equals(dataTypeDefinitions.BOOLEAN))
		{
			argDataType = "boolean";
		}
		if(argDT.equals(dataTypeDefinitions.STRING))
		{
			argDataType = "string";
		}
		currentValue = currValue;
	}
	
	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + program + ".java: error: argument " + argName;
		errorMessage = errorMessage + ": invalid " + argDataType + " value: " + currentValue;
		return errorMessage;
	}
}