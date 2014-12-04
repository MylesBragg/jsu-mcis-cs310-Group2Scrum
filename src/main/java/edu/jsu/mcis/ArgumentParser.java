package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser 
{
	private Map<String, PositionalArgument> positionalArgumentHolder;
	private Map<String, NamedArgument> namedArgumentHolder;
	private Map<String, NamedArgumentGroup> namedArgumentGroupHolder;
	private String programName, programDescription, currentGroupHeader;
	private int currentPositionalArgumentCount, currentPosArgIndex;
	private boolean helpSystemExit;
	
	public ArgumentParser(String programName)
	{
		this.programName = programName;
		positionalArgumentHolder = new LinkedHashMap<String, PositionalArgument>();
		namedArgumentHolder = new LinkedHashMap<String, NamedArgument>();
		namedArgumentGroupHolder = new LinkedHashMap<String, NamedArgumentGroup>();
		currentPositionalArgumentCount = 0;
		currentGroupHeader = "";
		helpSystemExit = true;
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
			setDescriptionWhenFullNameGiven(name, description);
		}
		else 
		{
			namedArgumentHolder.get(namedArgumentFullName).setDescription(description);
		}
	}
	
	private void setDescriptionWhenFullNameGiven(String name, String description)
	{
		if (namedArgumentHolder.containsKey(name)) 
		{
			namedArgumentHolder.get(name).setDescription(description);
		}
		else if (positionalArgumentHolder.containsKey(name)) 
		{
			positionalArgumentHolder.get(name).setDescription(description);
		}
	}
	
	public String getArgumentDescription(String name)
	{
		String namedArgumentFullName = getNamedArgumentFullName(name);
		if (namedArgumentFullName.equals("")) 
		{
			return getDescriptionWhenFullNameGiven(name);
		}
		else 
		{
			return namedArgumentHolder.get(namedArgumentFullName).getDescription();
		}
	}
	
	private String getDescriptionWhenFullNameGiven(String name)
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
			return getTypeWhenFullNameGiven(name);
		}
		else 
		{
			return namedArgumentHolder.get(namedArgumentFullName).getType();
		}
	}	
	
	private Argument.Type getTypeWhenFullNameGiven(String name)
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
	
	public void appendRestrictedValues(String name, List<Object> values)
	{
		String namedArgumentFullName = getNamedArgumentFullName(name);
		if (namedArgumentFullName.equals("")) 
		{
			appendRestrictedValuesWhenFullNameGiven(name, values);
		}
		else 
		{
			namedArgumentHolder.get(namedArgumentFullName).appendRestrictedValues(values);
		}
	}
	
	private void appendRestrictedValuesWhenFullNameGiven(String name, List<Object> values)
	{
		if (namedArgumentHolder.containsKey(name)) 
		{
			namedArgumentHolder.get(name).appendRestrictedValues(values);
		}
		else if (positionalArgumentHolder.containsKey(name)) 
		{
			positionalArgumentHolder.get(name).appendRestrictedValues(values);
		}
	}
	
	public void setMultipleValuesListSize(String name, int size)
	{
		String namedArgumentFullName = getNamedArgumentFullName(name);
		if (namedArgumentFullName.equals("")) 
		{
			setMultipleValuesListSizeWhenFullNameGiven(name, size);
		}
		else 
		{
			namedArgumentHolder.get(namedArgumentFullName).setMultipleValuesListSize(size);
		}
	}
	
	private void setMultipleValuesListSizeWhenFullNameGiven(String name, int size)
	{
		if (namedArgumentHolder.containsKey(name)) 
		{
			namedArgumentHolder.get(name).setMultipleValuesListSize(size);
		}
		else if (positionalArgumentHolder.containsKey(name)) 
		{
			positionalArgumentHolder.get(name).setMultipleValuesListSize(size);
		}
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
	
	public void setNamedArgumentGroupHeader(String name)
	{
		String namedArgumentFullName = getNamedArgumentFullName(name);
		if (namedArgumentFullName.equals("")) 
		{
			if (namedArgumentHolder.containsKey(name))
			{
				namedArgumentGroupHolder.put(name, new NamedArgumentGroup(name));
			}
		}
		else 
		{
			namedArgumentGroupHolder.put(namedArgumentFullName, new NamedArgumentGroup(namedArgumentFullName));
		}
	}
	
	public void appendNamedArgumentGroupMember(String groupHeader, String newGroupMember)
	{
		String namedArgumentFullName = getNamedArgumentFullName(newGroupMember);
		if (namedArgumentFullName.equals("")) 
		{
			if (namedArgumentHolder.containsKey(newGroupMember))
			{
				namedArgumentGroupHolder.get(groupHeader).appendGroupMember(newGroupMember);
			}
		}
		else 
		{
			namedArgumentGroupHolder.get(groupHeader).appendGroupMember(namedArgumentFullName);
		}
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
	
	private void setDefaultValue(String name, Object defaultValue)
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
	
	private void throwInvalidNamedValueException(String name, Object value)
	{
		InvalidValueException x = new InvalidValueException();
		x.setProgramName(programName);
		x.setUsageLine(getUsageLine());
		x.setInvalidValueArgumentInformation(name, namedArgumentHolder.get(name).getType());
		x.setInvalidValue(value);
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
			if(currentAlternateName.equals(alternateName))
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
		if (!currentGroupHeader.equals(""))
		{
			
			if (namedArgumentGroupHolder.get(currentGroupHeader).getCurrentGroupSize() != (namedArgumentGroupHolder.get(currentGroupHeader).getOverallGroupSize() + 1))
			{
				throwNotEnoughGroupArgumentsExceptions();
			}
		}
		throwNotEnoughArgumentsExceptions(nextValue);
	}
	
	private void checkValue(String nextValue, Scanner argumentScanner)
	{
		if (nextValue.equals("--help") || nextValue.equals("-h")) 
		{
			System.out.println(getHelpInfo());
			if(helpSystemExit)
			{
				System.exit(0);
			}
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
		if (!namedArgument.equals("")) 
		{
			checkNamedArgumentValueType(nextValue, argumentScanner);
		}
	}
	
	private void checkNamedArgumentValueType(String nextValue, Scanner argumentScanner)
	{
		String namedArgument = checkNamedArgument(nextValue);
		if(checkGroupHeader(namedArgument))
		{
			currentGroupHeader = namedArgument;
		}
		else if (!currentGroupHeader.equals(""))
		{
			if (namedArgumentGroupHolder.get(currentGroupHeader).getCurrentGroupSize() <= namedArgumentGroupHolder.get(currentGroupHeader).getOverallGroupSize())
			{
				if (!namedArgumentGroupHolder.get(currentGroupHeader).checkGroup(namedArgument))
				{
					InvalidGroupMemberException currentException = new InvalidGroupMemberException();
					currentException.setProgramName(programName);
					currentException.setUsageLine(getUsageLine());
					currentException.setCurrentArgument(namedArgument);
					throw currentException;
				}
			}
		}
		if (checkNamedArgumentTypeBoolean(namedArgument)) 
		{
			setArgumentValue(namedArgument, "true");
		}
		else 
		{
			int multipleValuesListSize = namedArgumentHolder.get(namedArgument).getMultipleValuesListSize();
			
			if (multipleValuesListSize == 0)
			{
				String namedValue = argumentScanner.next();
				setArgumentValue(namedArgument, namedValue);
			}
			else
			{
				for (int i = 0; i < multipleValuesListSize; i++)
				{
					String namedValue = argumentScanner.next();
					setArgumentValue(namedArgument, namedValue);
				}
			}
		}
	}
	
	private boolean checkGroupHeader(String name)
	{
		Iterator<String> keyList = namedArgumentGroupHolder.keySet().iterator();
		while (keyList.hasNext())
		{
			if (name.equals(keyList.next()))
			{
				return true;
			}
		}
		return false;
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
	
	private void checkPositionalArgumentValue(String nextValue, Scanner argumentScanner)
	{
		String positionalArgName = getPositionalArgumentName(currentPosArgIndex);
		if (positionalArgumentHolder.size() >= currentPosArgIndex) 
		{
			int multipleValuesListSize = positionalArgumentHolder.get(getPositionalArgumentName(currentPosArgIndex)).getMultipleValuesListSize();
			
			if (multipleValuesListSize == 0)
			{
				setArgumentValue(getPositionalArgumentName(currentPosArgIndex), nextValue);
			}
			else
			{
				setArgumentValue(getPositionalArgumentName(currentPosArgIndex), nextValue);
				for (int i = 0; i < multipleValuesListSize - 1; i++)
				{
					String newValue = argumentScanner.next();
					setArgumentValue(getPositionalArgumentName(currentPosArgIndex), newValue);
				}
			}
			
			currentPosArgIndex++;
		}
		else 
		{
			throwTooManyArgumentException(nextValue, argumentScanner);
		}
	}
	
	private void throwTooManyArgumentException(String nextValue, Scanner argumentScanner) 
	{
		TooManyArgumentsException currentException = new TooManyArgumentsException(getUsageLine(), programName);
		currentException.setNextValue(nextValue);
		currentException.setArgumentScanner(argumentScanner);
		throw currentException;
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
	
	private void throwNotEnoughArgumentsExceptions(String nextValue)
	{
		if (!nextValue.equals("-h") && !nextValue.equals("--help") && currentPosArgIndex <= positionalArgumentHolder.size())
		{
			NotEnoughArgumentsException currentException = new NotEnoughArgumentsException(getUsageLine(), programName);
			currentException.setArgumentsRequired(positionalArgumentHolder);
			currentException.setCurrentIndex(currentPosArgIndex - 1);
			throw currentException;
		}
	}
	
	private void throwNotEnoughGroupArgumentsExceptions()
	{
		NotEnoughGroupArgumentsException currentException = new NotEnoughGroupArgumentsException(getUsageLine(), programName);
		currentException.setCurrentGroupHeader(currentGroupHeader);
		currentException.setArgumentsRequired(namedArgumentGroupHolder);
		currentException.setCurrentIndex((namedArgumentGroupHolder.get(currentGroupHeader).getCurrentGroupSize()) - 1);
		throw currentException;
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
			if (namedArgumentHolder.get(argumentName).checkIfRestrictedValue(argumentValue))
			{
				namedArgumentHolder.get(argumentName).setValue(argumentValue);
			}
			else
			{
				InvalidRestrictedValueException currentException = new InvalidRestrictedValueException();
				currentException.setProgramName(programName);
				currentException.setUsageLine(getUsageLine());
				currentException.setInvalidValueArgumentInformation(argumentName, namedArgumentHolder.get(argumentName).getType());
				currentException.setRestrictedValuesList(namedArgumentHolder.get(argumentName).getRestrictedValues());
				currentException.setInvalidValue(argumentValue);
				throw currentException;
			}
			
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
			if (positionalArgumentHolder.get(argumentName).checkIfRestrictedValue(argumentValue))
			{
				positionalArgumentHolder.get(argumentName).setValue(argumentValue);
			}
			else
			{
				InvalidRestrictedValueException currentException = new InvalidRestrictedValueException();
				currentException.setProgramName(programName);
				currentException.setUsageLine(getUsageLine());
				currentException.setInvalidValueArgumentInformation(argumentName, positionalArgumentHolder.get(argumentName).getType());
				currentException.setRestrictedValuesList(positionalArgumentHolder.get(argumentName).getRestrictedValues());
				currentException.setInvalidValue(argumentValue);
				throw currentException;
			}
			
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
		x.setInvalidValueArgumentInformation(argumentName, positionalArgumentHolder.get(argumentName).getType());
		x.setInvalidValue(argumentValue);
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
	
	public <T> List<T> getArgumentValues(String name)
	{
		if (positionalArgumentHolder.containsKey(name)) 
		{
			return positionalArgumentHolder.get(name).getMultipleValuesList();
		}
		else if (namedArgumentHolder.containsKey(name)) 
		{
			return namedArgumentHolder.get(name).getMultipleValuesList();
		}
		else 
		{
			return getAlternateNamedArgumentValues(getNamedArgumentFullName(name));
		}
	}
	
	private <T> List<T> getAlternateNamedArgumentValues(String alternateFullName)
	{
		if (alternateFullName.equals("")) 
		{
			List<T> errorList = new ArrayList<T>();
			return errorList;
		}
		else 
		{
			return namedArgumentHolder.get(alternateFullName).getMultipleValuesList();
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
			argumentList = argumentList + getRequiredNamedArgument(currentKey);
		}
		argumentList = argumentList.trim();
		usageString = usageString + " " + argumentList;
		usageString = usageString.trim();
		return usageString;
	}
	
	private String getRequiredNamedArgument(String currentKey)
	{
		if(namedArgumentHolder.get(currentKey).getRequired())
		{
			return " [--" + currentKey + "]";
		}
		else
		{
			return "";
		}
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
			argumentsDescription = argumentsDescription + getRequiredNamedArgumentDescription(currentKey);
			
		}
		argumentsDescription = argumentsDescription.trim();
		positionalArgumentsDescription = positionalArgumentsDescription + argumentsDescription;
		positionalArgumentsDescription = positionalArgumentsDescription.trim();
		return positionalArgumentsDescription;
	}
	
	private String getRequiredNamedArgumentDescription(String currentKey)
	{
		if(namedArgumentHolder.get(currentKey).getRequired())
		{
			return "[--" + currentKey + "] " + namedArgumentHolder.get(currentKey).getDescription() + "\n";
		}
		else
		{
			return "";
		}
	}
	
	public void setHelpSystemExit(boolean b)
	{
		helpSystemExit = b;
	}
}