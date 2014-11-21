package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser 
{
	private Map<String, PositionalArgument> positionalArgumentHolder;
	private Map<String, NamedArgument> namedArgumentHolder;
	private String programName, programDescription;
	private int currentPositionalArgumentCount, currentPosArgIndex;
	
	public ArgumentParser(String programName)
	{
		this.programName = programName;
		positionalArgumentHolder = new LinkedHashMap<String, PositionalArgument>();
		namedArgumentHolder = new LinkedHashMap<String, NamedArgument>();
		currentPositionalArgumentCount = 0;
	}
	
	public void setProgramDescription(String programDescription)
	{
		this.programDescription = programDescription;
	}
	
	public String getProgramDescription()
	{
		return programDescription;
	}
	
	public String getProgramName()
	{
		return programName;
	}
	public void addPositionalArgument(String name, Argument.Type dataType)
	{
		positionalArgumentHolder.put(name, new PositionalArgument(name, dataType, incrementCurrentPositionalArgumentCount()));
	}
	
	private int incrementCurrentPositionalArgumentCount()
	{
		currentPositionalArgumentCount++;
		return currentPositionalArgumentCount;
	}
	
	public int getPositionId(String name)
	{
		return positionalArgumentHolder.get(name).getPositionId();
	}
	
	public int getPositionalArgumentSize()
	{
		return positionalArgumentHolder.size();
	}
	
	public void setArgumentDescription(String name, String description)
	{
		String namedArgumentFullName = getNamedArgumentFullName(name);
		if (namedArgumentFullName.equals("")) 
		{
			setDescriptionForTheRightHolder(name, description);
		}
		else 
		{
			namedArgumentHolder.get(namedArgumentFullName).setDescription(description);
		}
	}
	
	private void setDescriptionForTheRightHolder(String name, String description)//needs a better name
	{
		if (namedArgumentHolder.containsKey(name)) 
		{
			namedArgumentHolder.get(name).setDescription(description);
		}
		else if (positionalArgumentHolder.containsKey(name)) 
		{
			positionalArgumentHolder.get(name).setDescription(description);
		}
		else 
		{
			System.out.println("I need an exception handler here");
		}
	}
	
	public String getArgumentDescription(String name)
	{
		String namedArgumentFullName = getNamedArgumentFullName(name);
		if (namedArgumentFullName.equals("")) 
		{
			return getDescriptionFromTheRightHolder(name);
		}
		else 
		{
			return namedArgumentHolder.get(namedArgumentFullName).getDescription();
		}
	}
	
	private String getDescriptionFromTheRightHolder(String name)//needs a better name
	{
		if (namedArgumentHolder.containsKey(name)) 
		{
			return namedArgumentHolder.get(name).getDescription();
		}
		else if (positionalArgumentHolder.containsKey(name)) 
		{
			return positionalArgumentHolder.get(name).getDescription();
		}
		return "";
	}
	
	public Argument.Type getArgumentType(String name)
	{
		String namedArgumentFullName = getNamedArgumentFullName(name);
		if (namedArgumentFullName.equals("")) 
		{
			return getTypeFromTheRightHolder(name);
		}
		else 
		{
			return namedArgumentHolder.get(namedArgumentFullName).getType();
		}
	}	
	
	private Argument.Type getTypeFromTheRightHolder(String name)//needs a better name
	{
		if (namedArgumentHolder.containsKey(name)) 
		{
			return namedArgumentHolder.get(name).getType();
		}
		else if (positionalArgumentHolder.containsKey(name)) 
		{
			return positionalArgumentHolder.get(name).getType();
		}
		return Argument.Type.STRING;
	}
	
	public void addNamedArgument(String name, Argument.Type dataType)
	{
		namedArgumentHolder.put(name, new NamedArgument(name, dataType));
	}
	
	public void setNamedArgumentAlternateName(String name, String alternateName)
	{
		namedArgumentHolder.get(name).setAlternateName(alternateName);
	}
	
	public String getNamedArgumentAlternateName(String name)
	{
		return namedArgumentHolder.get(name).getAlternateName();
	}
	
	public void setNamedArgumentDefaultValue(String name, Object defaultValue)
	{
		String fullName = getNamedArgumentFullName(name);
		if (fullName.equals("")) 
		{
			setDefaultValue(name, defaultValue);
		}
		else 
		{
			setDefaultValue(fullName, defaultValue);
		}
	}
	
	private void setDefaultValue(String name, Object defaultValue)//might need a better name
	{
		try
		{
			namedArgumentHolder.get(name).setDefaultValue(defaultValue);
		}
		catch (NumberFormatException e)
		{
			throwInvalidNamedValueException(name, defaultValue);
		}
	}
	
	private void throwInvalidNamedValueException(String name, Object defaultValue)
	{
		InvalidValueException x = new InvalidValueException();
		x.setProgramName(programName);
		x.setUsageLine(getUsageLine());
		x.setInvalidValueInformation(name, defaultValue, namedArgumentHolder.get(name).getType());
		throw x;
	}
	
	public <T> T getNamedArgumentDefaultValue(String name)
	{
		String fullName = getNamedArgumentFullName(name);
		if (fullName.equals("")) 
		{
			return namedArgumentHolder.get(name).getDefaultValue();
		}
		else 
		{
			return namedArgumentHolder.get(fullName).getDefaultValue();
		}
	}
	
	public void setNamedArgumentRequired(String name)
	{
		String fullName = getNamedArgumentFullName(name);
		if (fullName.equals("")) 
		{
			namedArgumentHolder.get(name).setRequired();
		}
		else 
		{
			namedArgumentHolder.get(fullName).setRequired();
		}
	}
	
	public boolean getNamedArgumentRequired(String name)
	{
		String fullName = getNamedArgumentFullName(name);
		if (fullName.equals("")) 
		{
			return namedArgumentHolder.get(name).getRequired();
		}
		else 
		{
			return namedArgumentHolder.get(fullName).getRequired();
		}
	}
	
	private String getNamedArgumentFullName(String alternateName)
	{
		Iterator<String> hashKeys = namedArgumentHolder.keySet().iterator();
		String name;
		while(hashKeys.hasNext())
		{
			name = hashKeys.next();
			String currentAlternateName = namedArgumentHolder.get(name).getAlternateName();
			if(currentAlternateName.equals(alternateName))//need to move this to a separate function
			{
				return name;
			}
		}
		return "";
	}
	
	public void parse(String[] argumentsToParse) 
	{
		String myString = "";
		for (int i = 0; i < argumentsToParse.length; i++)
		{
			myString += argumentsToParse[i] + " ";
		}
		parseFormattedString(myString);
	}
	
	public void parse(String argumentsToParse) 
	{
		parseFormattedString(argumentsToParse);
	}
	
	private void parseFormattedString(String argumentsToParse)
	{
		String nextValue = "";
		String previousValue = "";
		Scanner argumentScanner = new Scanner(argumentsToParse);
		currentPosArgIndex = 1;
		
		while (argumentScanner.hasNext()) 
		{
			nextValue = argumentScanner.next();
			checkValue(nextValue, argumentScanner);
		}
		throwNotEnoughArugmentsExceptions(nextValue);
	}
	
	private void throwNotEnoughArugmentsExceptions(String nextValue)
	{
		if (!nextValue.equals("-h") && !nextValue.equals("--help") && currentPosArgIndex <= positionalArgumentHolder.size())
		{	
			String usageLine = getUsageLine();
			throw new NotEnoughArgumentsException(usageLine, programName, positionalArgumentHolder, positionalArgumentHolder.size());
		}
	}
	
	private void checkValue(String nextValue, Scanner argumentScanner)
	{
		if (nextValue.equals("--help") || nextValue.equals("-h")) 
		{
			System.out.println(getHelpInfo());
		}
		else 
		{
			checkArgumentValue(nextValue, argumentScanner);
		}
	}
	
	private void checkArgumentValue(String nextValue, Scanner argumentScanner)
	{
		if ((nextValue.charAt(0) == '-' && nextValue.charAt(1) == '-') || nextValue.charAt(0) == '-') 
		{
			checkNamedArgumentValue(nextValue, argumentScanner);
		}
		else 
		{
			checkPositionalArgumentValue(nextValue, argumentScanner);
		}
	}
	
	private void checkNamedArgumentValue(String nextValue, Scanner argumentScanner)
	{
		String namedArgument = checkNamedArgument(nextValue);	
		if (namedArgument.equals("")) 
		{
			System.out.println("I need an exception handler here");
		}
		else 
		{
			checkNamedArgumentValueType(nextValue, argumentScanner);
		}
	}
	
	private void checkNamedArgumentValueType(String nextValue, Scanner argumentScanner)
	{
		String namedArgument = checkNamedArgument(nextValue);
		if (checkNamedArgumentTypeBoolean(namedArgument)) 
		{
			setArgumentValue(namedArgument, "true");
		}
		else 
		{
			String namedValue = argumentScanner.next();
			setArgumentValue(namedArgument, namedValue);
		}
	}
	
	private void checkPositionalArgumentValue(String nextValue, Scanner argumentScanner)
	{
		String positionalArgName = getPositionalArgumentName(currentPosArgIndex);
		if (positionalArgumentHolder.size() >= currentPosArgIndex) 
		{
			setArgumentValue(getPositionalArgumentName(currentPosArgIndex), nextValue);
			currentPosArgIndex++;
		}
		else 
		{
			String usageLine = getUsageLine();
			throw new TooManyArgumentsException(usageLine, programName, nextValue, argumentScanner);
		}
	}
	
	private String getPositionalArgumentName(int position)
	{
		Iterator<String> keyIterator = positionalArgumentHolder.keySet().iterator();
		String currentKey;
		
		while(keyIterator.hasNext()) 
		{
			currentKey = keyIterator.next();
			if (positionalArgumentHolder.get(currentKey).getPositionId() == position)//need to move this to a separate function
			{
				return currentKey;
			}
		}
		return "Positional Argument Not Found Error";
	}
	
	public String getArgumentNames()
	{
		Iterator<String> keyIterator = positionalArgumentHolder.keySet().iterator();
		String keyString = " ";
		while(keyIterator.hasNext()) 
		{
			keyString = keyString + keyIterator.next() + " ";
		}
		
		keyIterator = namedArgumentHolder.keySet().iterator();
		while(keyIterator.hasNext()) 
		{
			keyString = keyString + keyIterator.next() + " ";
		}
		return keyString.trim();
	}
	
	private String checkNamedArgument(String name) 
	{
		String fullName = getNamedArgumentFullName(name.substring(1));
		
		if (fullName.equals("")) 
		{
			return checkFullNamedArgument(name.substring(2));
		}
		else 
		{
			return fullName;
		}
	}
	
	private String checkFullNamedArgument(String name)
	{
		if (namedArgumentHolder.containsKey(name)) 
		{
			return name;
		}
		else 
		{
			return "";
		}
	}
	private boolean checkNamedArgumentTypeBoolean(String name) 
	{
		if (namedArgumentHolder.get(name).getType().equals(Argument.Type.BOOLEAN))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	private void setArgumentValue(String argumentName, String argumentValue)
	{
		if (namedArgumentHolder.containsKey(argumentName)) 
		{
			setNamedValue(argumentName, argumentValue);
		}
		if (positionalArgumentHolder.containsKey(argumentName)) 
		{
			setPositionalValue(argumentName, argumentValue);
		}
	}
	
	private void setNamedValue(String argumentName, String argumentValue)
	{
		try
		{
			namedArgumentHolder.get(argumentName).setValue(argumentValue);
		}
		catch(NumberFormatException e) 
		{
			throwInvalidNamedValueException(argumentName, argumentValue);
		}
	}
	
	private void setPositionalValue(String argumentName, String argumentValue)
	{
		try
		{
			positionalArgumentHolder.get(argumentName).setValue(argumentValue);
		}
		catch(NumberFormatException e) 
		{
			throwInvalidPositionalValueException(argumentName, argumentValue);
		}
	}
	
	private void throwInvalidPositionalValueException(String argumentName, String argumentValue)
	{
		InvalidValueException x = new InvalidValueException();
		x.setProgramName(programName);
		x.setUsageLine(getUsageLine());
		x.setInvalidValueInformation(argumentName, argumentValue, positionalArgumentHolder.get(argumentName).getType());
		throw x;
	}
	
	public <T> T getArgumentValue(String name)
	{
		if (positionalArgumentHolder.containsKey(name)) 
		{
			return positionalArgumentHolder.get(name).getValue();
		}
		else if (namedArgumentHolder.containsKey(name)) 
		{
			return (T)namedArgumentHolder.get(name).getValue();
		}
		else 
		{
			return getAlternateNamedArgumentValue(getNamedArgumentFullName(name));
		}
	}
	private <T> T getAlternateNamedArgumentValue(String alternateFullName)
	{
		if (alternateFullName.equals("")) 
		{
			return (T)"Error key not found";
		}
		else 
		{
			return (T)namedArgumentHolder.get(alternateFullName).getValue();
		}
	}
	public String getHelpString()
	{
		return getHelpInfo();
	}
	
	private String getHelpInfo()
	{
		String helpMessage = this.getUsageLine() + "\n\n";
		helpMessage = helpMessage + programDescription + "\n\n";
		helpMessage = helpMessage + getPositionalArgumentsInfo();
		return helpMessage;
	}
	
	private String getUsageLine()
	{
		String usageString = "usage: java " + programName;
		Iterator<String> hashKeys = positionalArgumentHolder.keySet().iterator();
		String argumentList = "";
		while (hashKeys.hasNext())
		{
			argumentList = argumentList + " " + hashKeys.next();
		}
		hashKeys = namedArgumentHolder.keySet().iterator();
		String currentKey = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			if(namedArgumentHolder.get(currentKey).getRequired())
			{
				argumentList = argumentList + " [--" + currentKey + "]";
			}
		}
		argumentList = argumentList.trim();
		usageString = usageString + " " + argumentList;
		usageString = usageString.trim();
		return usageString;
	}
	
	private String getPositionalArgumentsInfo()
	{
		String positionalArgumentsDescription = "positional arguments: ";
		Iterator<String> hashKeys = positionalArgumentHolder.keySet().iterator();
		String currentKey = "";
		String argumentsDescription = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			argumentsDescription = argumentsDescription + currentKey + " " + positionalArgumentHolder.get(currentKey).getDescription() + "\n";
		}
		hashKeys = namedArgumentHolder.keySet().iterator();
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			if(namedArgumentHolder.get(currentKey).getRequired())
			{
				argumentsDescription = argumentsDescription + "[--" + currentKey + "] " + namedArgumentHolder.get(currentKey).getDescription() + "\n";
			}
		}
		argumentsDescription = argumentsDescription.trim();
		positionalArgumentsDescription = positionalArgumentsDescription + argumentsDescription;
		positionalArgumentsDescription = positionalArgumentsDescription.trim();
		return positionalArgumentsDescription;
	}	
}