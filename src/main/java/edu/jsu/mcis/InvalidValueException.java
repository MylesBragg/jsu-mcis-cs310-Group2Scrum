package edu.jsu.mcis;

import java.util.*;

public class InvalidValueException extends RuntimeException
{
	String usage;
	String argName;
	String prog;
	String argDataType;
	String currValue;
	
	public InvalidValueException(List<String> arrayOfNames, String argumentName, String program, String argDT, String currentValue)
	{
		HelpInfoGenerator h = new HelpInfoGenerator();
		usage = h.getUsageLine(arrayOfNames, program);
		argName = argumentName;
		prog = program;
		argDataType = argDT;
		currValue = currentValue;
	}
	
	public String toString()
	{
		String errorMessage = usage;
		errorMessage = errorMessage + "\n" + prog + ".java: error: argument " + argName + ": invalid ";
		errorMessage = errorMessage + argDataType + " value: " + currValue;
		return errorMessage;
	}
}