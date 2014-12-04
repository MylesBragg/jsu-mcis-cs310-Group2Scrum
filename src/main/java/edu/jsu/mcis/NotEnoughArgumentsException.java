package edu.jsu.mcis;

import java.util.*;

 /**
   * Exception class that identifies when their are not enough arguments being 
   * used when their is a given limit.
   */

public class NotEnoughArgumentsException extends RuntimeException
{
	private String usage;
	private String programName;
	private List<String> namesArray;
	private int index = 0;
	
	
	 /**
     * Exception that is thrown during runtime to specify when their are not 
     * enough arguments given.
     * @param helpUsage Sets the help usage field.
     * @param programName Sets the program field.
     */
	public NotEnoughArgumentsException(String helpUsage, String programName)
	{
		usage = helpUsage;
		this.programName = programName;
	}
	
	 /**
    * Sets the arguments inside of the HashMap in order to see which 
	* are required.
    * @param hash Used to search through a given Map.
    */
	public void setArgumentsRequired(Map<String, PositionalArgument> hash)
	{
		Iterator<String> hashKeys = hash.keySet().iterator();
		namesArray = new ArrayList<String>();
		while (hashKeys.hasNext())
		{
			namesArray.add(hashKeys.next());
		}
	}
	/**
    * Stores the current index.
    * @param currentIndex Sets the index.
    */
	public void setCurrentIndex(int currentIndex)
	{
		index = currentIndex;
	}
	
	/**
    * Displays an error message when their are not enough arguments supplied.
    * @return Error message shown when short a number of arguments.
    */
	public String toString()
	{
		String errorMessage = usage + "\n";
		errorMessage = errorMessage + programName + ".java: error: the following arguments are required:";
		for(int j = index; j < namesArray.size(); j++)
		{
			errorMessage = errorMessage + " " + namesArray.get(j);
		}
		return errorMessage;
	}
}