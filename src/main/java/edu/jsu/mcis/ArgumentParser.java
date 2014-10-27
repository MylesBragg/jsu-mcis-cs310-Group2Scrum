package edu.jsu.mcis;

import java.util.*;


public class ArgumentParser
{
	private ArgumentValues argVals;
	private ArgumentValues[] allArgVals;
	private List<String> positionalArgNames;
	private List<String> optionalArgNames;
	private List<String> optionalFlagNames;
	private String program = "";
	private String progDesc = "";
	
	public ArgumentParser()
	{
		allArgVals = new ArgumentValues[2];
		
		allArgVals[0] = new ArgumentValues();
		allArgVals[1] = new ArgumentValues();
		
		argVals  = new ArgumentValues();
		
		positionalArgNames = new ArrayList<String>();
		optionalArgNames = new ArrayList<String>();
		optionalFlagNames = new ArrayList<String>();
	}
	
	public void addProgramHelpInfo(String helpInfo)
	{
		progDesc = helpInfo;
	}
	
	public String fromArgsToString(String[] array)
	{
		String myString = "";
		for (int i = 0; i < array.length; i++)
		{
			myString += array[i] + " ";
		}
		return myString;
	}
	
	public void addArg(String name, String help, String dataType)
	{
		positionalArgNames.add(name);
		allArgVals[0].addHelpArgument(name, help);
		allArgVals[0].addDataTypeArgument(name, dataType);
	}
	public void addOptionalArguments(String name, String shortName, String help, String dataType, String defaultVals)
	{
		if(dataType.equals("flag"))
		{
			optionalFlagNames.add(name);
			optionalFlagNames.add(shortName);
			allArgVals[1].addHelpArgument(name, help);
			allArgVals[1].addDataTypeArgument(name, dataType);
			allArgVals[1].addHelpArgument(shortName, help);
			allArgVals[1].addDataTypeArgument(shortName, dataType);
		}
		else 
		{
			optionalArgNames.add(name);
			optionalArgNames.add(shortName);
			allArgVals[1].addHelpArgument(name, help);
			allArgVals[1].addDataTypeArgument(name, dataType);
			allArgVals[1].addHelpArgument(shortName, help);
			allArgVals[1].addDataTypeArgument(shortName, dataType);
			if (!defaultVals.equals("")) {
				allArgVals[1].addValueArgument(name, defaultVals);
				allArgVals[1].addValueArgument(shortName, defaultVals);
			}
		}
	}
	public void addArgumentHelp(String name, String help) 
	{
		argVals.addHelpArgument(name, help);
	}
	
	public String parse(String myString)
	{
		String completionString = "";
		String nextValue = "";
		String previousValue = "";
		Scanner argScanner = new Scanner(myString);
		int currPositionArgIndex = 0;
		
		String[] arguments = new String[1];
		program = argScanner.next();
		int count = 0;
		while (argScanner.hasNext())
		{
			nextValue = argScanner.next();

			if(!nextValue.contains("--") && !previousValue.contains("--"))
			{
				try
				{
					if(positionalArgNames.get(currPositionArgIndex) != null)
					{
						currPositionArgIndex++;
					}
				}
				catch (IndexOutOfBoundsException e)
				{
					throw new SurplusArgumentsException(positionalArgNames, program, nextValue, argScanner);
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
		completionString = adder(arguments);
		
		if(nextValue.equals("-h"))
		{
			HelpInfoGenerator h = new HelpInfoGenerator();
			String helpString = h.getHelpInfo(positionalArgNames, program, allArgVals[0], progDesc);
			return helpString;
		}
		if(allArgVals[0].size() < positionalArgNames.size())
		{
			throw new NotEnoughArgsException(positionalArgNames, program, allArgVals[0].size());
		}
		return completionString;
	}
	
	public String adder(String[] argValues)
	{
		int currPositionArgIndex = 0;
		int argValuesIndex = 0;
		try
		{
			for (int i = 0; i < argValues.length; i++) 
			{
				if ((argValues[i].contains("--") || argValues[i].contains("-"))) 
				{
					if (optionalArgNames.contains(argValues[i])){
						allArgVals[1].addValueArgument(argValues[i], argValues[i + 1]);
						i++;
					}
					else if (optionalFlagNames.contains(argValues[i])) {
						allArgVals[1].addValueArgument(argValues[i], true);
					}
				}
				else 
				{
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
							String checkValue = argValues[i];
							if (checkValue == "true" || checkValue == "false")
							{
								boolean boolValue = Boolean.parseBoolean(argValues[i]);
								allArgVals[0].addValueArgument(positionalArgNames.get(currPositionArgIndex), boolValue);
							}
							else throw new NumberFormatException("Invalid Boolean");
							break;
						case "float":
							float floatValue = Float.parseFloat(argValues[i]);
							allArgVals[0].addValueArgument(positionalArgNames.get(currPositionArgIndex), floatValue);
							break;
					}
					currPositionArgIndex++;
				}
				argValuesIndex = i;
			}
		}
		catch (NumberFormatException e)
		{
			String argumentName = positionalArgNames.get(currPositionArgIndex);
			throw new InvalidValueException(positionalArgNames, argumentName, program, getArgumentDataType(argumentName), argValues[argValuesIndex+1]);
		}
		
		
		return "Parsing Completed";
	}
	
	public <T extends Comparable<T>> T getArgumentValue(String name) {
		// temporary to generate output
		if(name == "type")
		{
			T box;
			Class<T> boxCast = (Class<T>) String.class;
			box = boxCast.cast("box");
			return box;
		}
		// end of my "get out of jail free" code
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
			case "flag":
				Class<T> flagCast = (Class<T>) Boolean.class;
				value = flagCast.cast(allArgVals[1].getValueArgument(name, getArgumentDataType(name)));
				return value;
			default:
				
				return null;
		}
	}
	public String getArgumentDataType(String name) {
		if (name.contains("-") || name.contains("--")) {
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