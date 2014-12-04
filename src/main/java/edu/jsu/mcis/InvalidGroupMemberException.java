package edu.jsu.mcis;

import java.util.*;
	/**
     * Exception class that throws an invalid group member if it does
	 * not match.
     */
public class InvalidGroupMemberException extends RuntimeException
{
	
	private String programName, usageLine, currentArgument;
		/**
     * Sets the program name that is being used.
     * @param programName Uses the program name field.
     */
	public void setProgramName(String programName)
	{
		this.programName = programName;
	}
	/**
     * Sets the current usage line.
     * @param usageLine Uses the usage line field.
     */
	public void setUsageLine(String usageLine)
	{
		this.usageLine = usageLine;
	}
	/**
     * Sets the current argument that is being used.
     * @param currentArgument Uses the current argument field.
     */
	public void setCurrentArgument(String currentArgument)
	{
		this.currentArgument = currentArgument;
	}
	/**
     * Displays the exception error with the program name & current argument.
     */
	public String toString()
	{
		String errorMessage = usageLine + "\n";
		errorMessage = errorMessage + programName + ".java: error: Invalid group argument: " + currentArgument;
		return errorMessage;
	}
}