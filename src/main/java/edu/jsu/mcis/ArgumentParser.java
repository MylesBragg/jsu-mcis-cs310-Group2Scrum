package edu.jsu.mcis;

import java.util.*;


public class ArgumentParser
{
	private ArgumentValues argVals;
	private List<String> arrayOfNames;
	String program = "";
	
	public ArgumentParser()
	{
		argVals  = new ArgumentValues();
		arrayOfNames = new ArrayList<String>();
	}
	
	public void addArg(String name)
	{
		arrayOfNames.add(name);
	}
	
	public void addArgumentHelp(String name, String help) 
	{
		argVals.addHelpArgument(name, help);
	}
	
	public void addArgumentDataType(String name, String dataType) 
	{
		argVals.addDataTypeArgument(name, dataType);
	}
	
	public String parse(String myString)
	{
		Scanner argScanner = new Scanner(myString);
		String nextValue = "";
		try
		{
			String[] arguments = new String[1];
			program = argScanner.next();
			int count = 0;
			while (argScanner.hasNext())
			{
				nextValue = argScanner.next();
				if(arguments[0] == null)
				{
					arguments[count] = nextValue;
					count++;
				}
				else
				{
					String[] temp = new String[arguments.length];
					for(int i = 0; i < arguments.length; i++)
					{
						temp[i] = arguments[i];
					}
					arguments = new String[temp.length + 1];
					for(int i = 0; i < temp.length; i++)
					{
						arguments[i] = temp[i];
					}
					arguments[count] = nextValue;
					count++;
				}
			}
			adder(arguments);
			for(int i = 0; i < arrayOfNames.size(); i++)
			{
				if(argVals.getValueArgument(arrayOfNames.get(i)) == null)
				{
					ArgumentErrorHandler error = new ArgumentErrorHandler();
					String errorMessage = error.buildStringTooFewArguments(arrayOfNames, program, i);
					return errorMessage;
				}
			}
		}
		catch (IndexOutOfBoundsException e)
		{
			ArgumentErrorHandler error = new ArgumentErrorHandler();
			String errorMessage = error.buildStringTooManyArguments(arrayOfNames, program, nextValue, argScanner);
			return errorMessage;
		}
		return "Parsing Completed";
	}
	
	public void adder(String[] argValues)
	{
		for (int i = 0; i < argValues.length; i++) 
		{
			argVals.addValueArgument(arrayOfNames.get(i), argValues[i]);
		}
	}
	
	public String getArgumentValue(String name)
	{
		return argVals.getValueArgument(name);
	}
	
	public String getHelpArgumentValue(String name) 
	{
		return "   " + argVals.getHelpArgument(name) + "   ";
	}
}