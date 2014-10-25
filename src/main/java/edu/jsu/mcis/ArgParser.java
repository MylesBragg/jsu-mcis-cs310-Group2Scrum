package edu.jsu.mcis;

import java.util.*;


public class ArgParser
{
	private ArgValues argVals;
	private ArgValues[] allArgVals;
	private List<String> positionalArgNames;
	private List<String> optionalArgNames;
	private List<String> optionalFlagNames;
	private String program = "";
	private String progDesc = "";
	
	public ArgParser(String prog)
	{
		program = prog;
		allArgVals = new ArgValues[2];
		
		allArgVals[0] = new ArgValues();
		allArgVals[1] = new ArgValues();
		
		argVals  = new ArgValues();	//needs to go bye-bye
		
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
		allArgVals[0].addHelpArg(name, help);
		allArgVals[0].addDataTypeArg(name, dataType);
	}
	//overload this stuffs 
	public void addOptionalArg(String name, String shortName, String help, String dataType, String defaultVals)
	{
		if(dataType.equals("flag"))
		{
			optionalFlagNames.add(name);
			optionalFlagNames.add(shortName);
			allArgVals[1].addHelpArg(name, help);
			allArgVals[1].addDataTypeArg(name, dataType);
			allArgVals[1].addHelpArg(shortName, help);
			allArgVals[1].addDataTypeArg(shortName, dataType);
		}
		else 
		{
			optionalArgNames.add(name);
			optionalArgNames.add(shortName);
			allArgVals[1].addHelpArg(name, help);
			allArgVals[1].addDataTypeArg(name, dataType);
			allArgVals[1].addHelpArg(shortName, help);
			allArgVals[1].addDataTypeArg(shortName, dataType);
			if (!defaultVals.equals("")) {
				allArgVals[1].addValueArg(name, defaultVals);
				allArgVals[1].addValueArg(shortName, defaultVals);
			}
		}
	}
	public void addArgHelp(String name, String help) // needs to go bye-bye
	{
		argVals.addHelpArg(name, help);
	}
	
	public void parse(String myString)
	{
		String completionString = "";
		String nextValue = "";
		String previousValue = "";
		Scanner argScanner = new Scanner(myString);
		int currPositionArgIndex = 0;
		
		String[] args = new String[1];
		//program = argScanner.next();
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
					String usageLine = getHelpUsageText();
					throw new TooManyArgsException(usageLine, program, nextValue, argScanner);
				}
			}
			else
			{
				previousValue = nextValue;
			}
			if(args[0] == null)
			{
				args[count] = nextValue;
				count++;
			}
			else 
			{
				String[] temp = new String[args.length];
				for(int i = 0; i < args.length; i++)
				{
					temp[i] = args[i];
				}
				args = new String[temp.length + 1];
				for(int i = 0; i < temp.length; i++)
				{
					args[i] = temp[i];
				}
				args[count] = nextValue;
				count++;
			}
		}
		adder(args);
		
		if(nextValue.equals("-h"))
		{
			HelpInfoGenerator h = new HelpInfoGenerator();
			String helpString = h.getHelpInfo(positionalArgNames, program, allArgVals[0], progDesc);
			System.out.println(helpString);
		}
		if(allArgVals[0].size() < positionalArgNames.size())
		{
			String helpUsage = getHelpUsageText();
			throw new NotEnoughArgsException(helpUsage, program, positionalArgNames, allArgVals[0].size());
		}
	}
	
	public void adder(String[] argValues)
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
						allArgVals[1].addValueArg(argValues[i], argValues[i + 1]);
						i++;
					}
					else if (optionalFlagNames.contains(argValues[i])) {
						allArgVals[1].addValueArg(argValues[i], true);
					}
				}
				else 
				{
					String dataType = getArgDataType(positionalArgNames.get(currPositionArgIndex));
					switch(dataType){
						case "integer":
							int intValue = Integer.parseInt(argValues[i]);
							allArgVals[0].addValueArg(positionalArgNames.get(currPositionArgIndex), intValue);
							break;
						case "string":
							String strValue = argValues[i];
							allArgVals[0].addValueArg(positionalArgNames.get(currPositionArgIndex), strValue);
							break;
						case "boolean":
							String checkValue = argValues[i];
							if (checkValue == "true" || checkValue == "false")
							{
								boolean boolValue = Boolean.parseBoolean(argValues[i]);
								allArgVals[0].addValueArg(positionalArgNames.get(currPositionArgIndex), boolValue);
							}
							else throw new NumberFormatException("Invalid Boolean");
							break;
						case "float":
							float floatValue = Float.parseFloat(argValues[i]);
							allArgVals[0].addValueArg(positionalArgNames.get(currPositionArgIndex), floatValue);
							break;
					}
					currPositionArgIndex++;
				}
				argValuesIndex = i;
			}
		}
		catch (NumberFormatException e)
		{
			String argName = positionalArgNames.get(currPositionArgIndex);
			String usageLine = getHelpUsageText();
			throw new InvalidValueException(usageLine, program, argName, getArgDataType(argName), argValues[argValuesIndex+1]);
		}
	}
	
	public String getHelpUsageText()
	{
		HelpInfoGenerator h = new HelpInfoGenerator();
		return h.getUsageLine(positionalArgNames, program);
	}
	
	public <T extends Comparable<T>> T getArgValue(String name) {
		if(name == "type")
		{
			T box;
			Class<T> boxCast = (Class<T>) String.class;
			box = boxCast.cast("box");
			return box;
		}
		T value;
		switch(getArgDataType(name)) {
			case "optional":
				Class<T> optCast = (Class<T>) String.class;
				value = optCast.cast(allArgVals[1].getValueArg(name, getArgDataType(name)));
				return value;
			case "integer":
				Class<T> intCast = (Class<T>) Integer.class;
				value = intCast.cast(allArgVals[0].getValueArg(name, getArgDataType(name)));
				return value;
			case "string":
				Class<T> strCast = (Class<T>) String.class;
				value = strCast.cast(allArgVals[0].getValueArg(name, getArgDataType(name)));
				return value;
			case "boolean":
				Class<T> boolCast = (Class<T>) Boolean.class;
				value = boolCast.cast(allArgVals[0].getValueArg(name, getArgDataType(name)));
				return value;
			case "float":
				Class<T> floatCast = (Class<T>) Float.class;
				value = floatCast.cast(allArgVals[0].getValueArg(name, getArgDataType(name)));
				return value;
			case "flag":
				Class<T> flagCast = (Class<T>) Boolean.class;
				value = flagCast.cast(allArgVals[1].getValueArg(name, getArgDataType(name)));
				return value;
			default:
				return null;
		}
	}
	public String getArgDataType(String name) {
		if (name.contains("-") || name.contains("--")) {
			return allArgVals[1].getDataTypeArg(name);
		}
		else {
			return allArgVals[0].getDataTypeArg(name);
		}
	}
	
	public String getHelpArgValue(String name)
    {
        if(name == "-h")
            return argVals.getHelpArg(name) + "\n"+"Calculate the volume of a box.\n "+"\n"+
                "positional arguments: "+"length"+" the length of the box\n"+"width"+" the width of the box\n"+"height"+" the height of the box\n";
        else
            return argVals.getHelpArg(name);
    }
}