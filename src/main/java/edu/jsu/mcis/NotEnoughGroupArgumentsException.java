package edu.jsu.mcis;

import java.util.*;

	/**
     * Exception class that throws and error when their are not enough arguments 
	 * being supplied to the group. 
     */
public class NotEnoughGroupArgumentsException extends RuntimeException
{
	private String usage;
	private String programName;
	private List<String> namesArray;
	private int index = 0;
	private String currentGroupHeader;
	/**
     * Constructor that sets the help usage and program name.
     * @param helpUsage Uses the help usage field. 
	 * @param programName Uses the program name field.
     */
	public NotEnoughGroupArgumentsException(String helpUsage, String programName)
	{
		usage = helpUsage;
		this.programName = programName;
	}
	/**
     * Sets the required arguments by iterating through the HashMap. 
     * @param hash Uses the hash field.
     */
	public void setArgumentsRequired(Map<String, NamedArgumentGroup> hash)
	{
		
		namesArray = new ArrayList<String>();
		for(int i = 0; i < hash.get(currentGroupHeader).getOverallGroupSize(); i++)
		{
			namesArray.add(hash.get(currentGroupHeader).getGroupMember(i));
		}
	}
	/**
     * Sets the current group header with the given parameter.
     * @param currentGroupHeader Uses the current group header field.
     */
	public void setCurrentGroupHeader(String currentGroupHeader)
	{
		this.currentGroupHeader = currentGroupHeader;
	}
	/**
     * Sets the current list index.
     * @param currentIndex Uses the index field.
     */
	public void setCurrentIndex(int currentIndex)
	{
		index = currentIndex;
	}
	/**
     * Displays the group header and the arguments that are required.
     */
	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + programName + ".java: error: the following group arguments from, " + currentGroupHeader + " group, are required:";
		for(int j = index; j < namesArray.size(); j++)
		{
			errorMessage = errorMessage + " " + namesArray.get(j);
		}
		return errorMessage;
	}
}