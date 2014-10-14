package edu.jsu.mcis;

import java.util.*;


public class ArgumentParser
{
	private ArgumentValues argVals;
	private ArgumentValues[] allArgVals;
	private List<String> positionalArgNames;
	private List<String> optionalArgNames;
	String program = "";
	
	public ArgumentParser()
	{
		allArgVals = new ArgumentValues[2];
		
		allArgVals[0] = new ArgumentValues();
		allArgVals[1] = new ArgumentValues();
		
		argVals  = new ArgumentValues();
		
		positionalArgNames = new ArrayList<String>();
		optionalArgNames = new ArrayList<String>();
	}
	
	public void addArg(String name, String help, String dataType)
	{
		if (name.contains("--") || name.contains("-")) {
			optionalArgNames.add(name);
			allArgVals[1].addHelpArgument(name, help);
			allArgVals[1].addDataTypeArgument(name, "optional");
		}
		else {
			positionalArgNames.add(name);
			allArgVals[0].addHelpArgument(name, help);
			allArgVals[0].addDataTypeArgument(name, dataType);
		}
		
	}
	
	public void addArgumentHelp(String name, String help) 
	{
		
	}
	
	public void addArgumentDataType(String name, String dataType) 
	{
		
	}
	
	public String parse(String myString)
	{
		String nextValue = "";
		Scanner argScanner = new Scanner(myString);
		
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

			if(allArgVals[0].size() < positionalArgNames.size())
			{
				int i = allArgVals[0].size();
				ArgumentErrorHandler error = new ArgumentErrorHandler();
				String errorMessage = error.buildStringTooFewArguments(positionalArgNames, program, i);
				return errorMessage;
			}
		}
		catch (IndexOutOfBoundsException e)
		{
			ArgumentErrorHandler error = new ArgumentErrorHandler();
			String errorMessage = error.buildStringTooManyArguments(positionalArgNames, program, nextValue, argScanner);
			return errorMessage;
		}
		return "Parsing Completed";
	}
	
	public void adder(String[] argValues)
	{
		for (int i = 0; i < argValues.length; i++) 
		{
			if ((argValues[i].contains("--") || argValues[i].contains("-"))) 
			{
				allArgVals[1].addValueArgument(argValues[i], argValues[i + 1]);
				i++;
			}
			else
			{
				allArgVals[0].addValueArgument(positionalArgNames.get(i), argValues[i]);
			}
		}
	}
	
	public <T extends Comparable<T>> T getArgumentValue(String name) {
		T value;
		switch(getArgumentDataType(name)) {
			case "optional":
				Class<T> optCast = (Class<T>) String.class;
				value = optCast.cast(allArgVals[1].getValueArgument(name, getArgumentDataType(name)));
				return value;
			case "integer":
				Class<T> intCast = (Class<T>) Integer.class;
				value = intCast.cast(allArgVals[0].getValueArgument(name, getArgumentDataType(name)));
				return value;
			case "string":
				Class<T> strCast = (Class<T>) String.class;
				value = strCast.cast(allArgVals[0].getValueArgument(name, getArgumentDataType(name)));
				return value;
			case "boolean":
				Class<T> boolCast = (Class<T>) Boolean.class;
				value = boolCast.cast(allArgVals[0].getValueArgument(name, getArgumentDataType(name)));
				return value;
			case "float":
				Class<T> floatCast = (Class<T>) Float.class;
				value = floatCast.cast(allArgVals[0].getValueArgument(name, getArgumentDataType(name)));
				return value;
			default:
				
				return null;
		}
	}
	private String getArgumentDataType(String name) {
		if (name.contains("--") || name.contains("-")) {
			return allArgVals[1].getDataTypeArgument(name);
		}
		else {
			return allArgVals[0].getDataTypeArgument(name);
		}
	}
	//public String getArgumentValue(String name)
	//{
		//return argVals.getValueArgument(name);
	//}
	
	public String getHelpArgumentValue(String name) 
	{
		return "   " + argVals.getHelpArgument(name) + "   ";
	}
}