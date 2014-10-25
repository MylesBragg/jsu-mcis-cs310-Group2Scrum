package edu.jsu.mcis;

import java.util.*;

public class InvalidValueException extends RuntimeException
{
	String usage;
	String program;
	String argName;
	String argDataType;
	String currentValue;
	
	public InvalidValueException(String helpUsage, String prog, String argN, String argDT, String currValue)
	{
		usage = helpUsage;
		program = prog;
		argName = argN;
		argDataType = argDT;
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