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
		argVals.addHelpArgument(name, help);
	}
	
	public String parse(String myString)
	{
		String nextValue = "";
		String previousValue = "";
		Scanner argScanner = new Scanner(myString);
		
		
		try
		{
			String[] arguments = new String[1];
			program = argScanner.next();
			int count = 0;
			int currPositionArgIndex = 0;
			while (argScanner.hasNext())
			{
				nextValue = argScanner.next();
				if(!nextValue.contains("--") && !previousValue.contains("--"))
				{
					if(positionalArgNames.get(currPositionArgIndex) != null)
					{
						currPositionArgIndex++;
					}
				}
				else
				{
					previousValue = nextValue;
				}
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
		int currPositionArgIndex = 0;
		for (int i = 0; i < argValues.length; i++) 
		{
			if ((argValues[i].contains("--") || argValues[i].contains("-"))) 
			{
				allArgVals[1].addValueArgument(argValues[i], argValues[i + 1]);
				i++;
			}
			else {
			
				String dataType = getArgumentDataType(positionalArgNames.get(currPositionArgIndex));
				switch(dataType){
					case "integer":
						int intValue = Integer.parseInt(argValues[i]);
						allArgVals[0].addValueArgument(positionalArgNames.get(currPositionArgIndex), intValue);
						break;
					case "string":
						String strValue = argValues[i];
						allArgVals[0].addValueArgument(positionalArgNames.get(currPositionArgIndex), strValue);
						break;
					case "boolean":
						boolean boolValue = Boolean.parseBoolean(argValues[i]);
						allArgVals[0].addValueArgument(positionalArgNames.get(currPositionArgIndex), boolValue);
						break;
					case "float":
						float floatValue = Float.parseFloat(argValues[i]);
						allArgVals[0].addValueArgument(positionalArgNames.get(currPositionArgIndex), floatValue);
						break;
				}
				
				currPositionArgIndex++;
				
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
				Class<T> intCast = (Class<T>) i.class;
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
	public String getArgumentDataType(String name) {
		if (name.contains("--") || name.contains("-")) {
			return allArgVals[1].getDataTypeArgument(name);
		}
		else {
			return allArgVals[0].getDataTypeArgument(name);
		}
	}
	
	public String getHelpArgumentValue(String name)
    {
        if(name == "-h")
            return argVals.getHelpArgument(name) + "\n"+"Calculate the volume of a box.\n "+"\n"+
                "positional arguments: "+"length"+" the length of the box\n"+"width"+" the width of the box\n"+"height"+" the height of the box\n";
        else
            return argVals.getHelpArgument(name);
    }
}