package edu.jsu.mcis;

import java.util.*;

public class InvalidGroupMemberException extends RuntimeException
{
	
	private String programName, usageLine, currentArgument;
	
	public void setProgramName(String programName)
	{
		this.programName = programName;
	}
	public void setUsageLine(String usageLine)
	{
		this.usageLine = usageLine;
	}
	public void setCurrentArgument(String currentArgument)
	{
		this.currentArgument = currentArgument;
	}
	public String toString()
	{
		String errorMessage = usageLine + "\n";
		errorMessage = errorMessage + programName + ".java: error: Invalid group argument: " + currentArgument;
		return errorMessage;
	}
}