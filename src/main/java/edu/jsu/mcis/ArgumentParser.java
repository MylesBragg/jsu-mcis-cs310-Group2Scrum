package edu.jsu.mcis;

import java.util.*;
/**
  * Class that parses through the given information from the command line
  * and translates it into the proper format by assigning it to a specific HashMap
  * based on its type of argument. 
  */
public class ArgumentParser 
{
	private Map<String, PositionalArgument> positionalArgumentHolder;
	private Map<String, NamedArgument> namedArgumentHolder;
	private Map<String, NamedArgumentGroup> namedArgumentGroupHolder;
	private String programName, programDescription, currentGroupHeader;
	private int currentPositionalArgumentCount, currentPosArgIndex;
	private boolean helpSystemExit;
	
	/**
     * Creates a LinkedHashMap for PositionalArguments,NamedArguments, & NamedArgumentGroups with
     * the addition to setting the current argument positional count to 0.
     * @param programName Sets the program name field.
     */
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
	    /**
     * Sets the programs description. 
     * @param programDescription Sets the program description field.
     */
	public void setProgramDescription(String programDescription)
	{
		this.programDescription = programDescription;
	}
	    /**
     * Returns the current program description.
     * @return Gives the program description.
     */
	public String getProgramDescription()
	{
		return programDescription;
	}
	/** 
     * Returns the current program name.
     */
	public String getProgramName()
	{
		return programName;
	}
	    /**
     * Adds a positional argument to the HashMap based on the String name 
     * and Argument type.
     * @param name Sets the name field.
     * @param dataType Sets the data type field.
     */
	public void addPositionalArgument(String name, Argument.Type dataType)
	{
		positionalArgumentHolder.put(name, new PositionalArgument(name, dataType, incrementCurrentPositionalArgumentCount()));
	}
	
	private int incrementCurrentPositionalArgumentCount()
	{
		currentPositionalArgumentCount++;
		return currentPositionalArgumentCount;
	}
	    /**
     * Returns the position ID based on the arguments name in the HashMap.
     * @param name Uses the name field.
     * @return Gives the current position ID.
     */
	public int getPositionId(String name)
	{
		return positionalArgumentHolder.get(name).getPositionId();
	}
	    /**
     * Return the current size of the PositionalArguments HashMap.
     * @return Gives a numeric value of the current size. 
     */
	public int getPositionalArgumentSize()
	{
		return positionalArgumentHolder.size();
	}
	    /**
     * Sets a description to the current holder if the argument name is equal 
     * to " ".Otherwise it will set the description in the NamedArgument HashMap
     * if the name matches the key.
     * @param name Uses the name field.
     * @param description Uses the description field.
     */
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
	    /**
     * Returns the argument description by searching the NamedArgument HashMap. 
     * If the name parameter is " ", then it will return the current description
     * holder.
     * @param name Uses the name field. 
     * @return Gives the description of the argument.
     */
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
	    /**
     * Returns the enumerated value based on whether the name is contained in 
     * the NamedArgument HashMap. If the name parameter is " ", then it will return
     * the current type holder.
     * @param name Uses the name field.
     * @return Gives an enumeration of the argument type.
     */
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
	/**
     * Sets restricted values based on the given list based on a given name. 
     * @param name Used to set the restricted name values.
	 * @param values Uses a given list to append restricted values.
     */
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
	/**
     * Creates a list of multiple values based on the name given while also allowing the ability to set its size. 
     * @param name Sets the list if given a name.
	 * @param size Sets the size of the values list. 
     */
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
	    /**
     * Adds a named argument in its corresponding HashMap.
     * @param name Uses the name field.
     * @param dataType Uses the data type field.
     */
	public void addNamedArgument(String name, Argument.Type dataType)
	{
		namedArgumentHolder.put(name, new NamedArgument(name, dataType));
	}
	    /**
     * Sets an alternate name that can be used for named arguments that can be 
     * accessed from the HashMap.
     * @param name Uses the name field.
     * @param alternateName Uses the alternate name field.
     */
	public void setNamedArgumentAlternateName(String name, String alternateName)
	{
		namedArgumentHolder.get(name).setAlternateName(alternateName);
	}
	    /**
     * Returns the current alternate name argument from the HashMap.
     * @param name Uses the name field.
     * @return Gives the alternate name of the argument.
     */
	public String getNamedArgumentAlternateName(String name)
	{
		return namedArgumentHolder.get(name).getAlternateName();
	}
	/**
     * Creates a new argument group header with the given String name value.
     * @param name Sets the named group argument header.
     */
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
	/**
     * Appends the group member with an assigned group header to the NamedArgument HashMap.
	 * However if the name is left " " it will instead append just the group member and not include the full argument name.  
     * @param groupHeader Used to set the group header.
	 * @param newGroupMember Used to add a new group member.
     */
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
	    /**
     * Sets a default value for named arguments based on whether the String 
     * is " " or contains information.
     * @param name Uses the name field 
     * @param defaultValue Uses the default value field 
     */
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
	    /**
     * Returns the current named arguments default value based on whether the 
     * String is " " or contains information.
     * @param <T> Gives any data type.
     * @param name Uses the name field.
     * @return Shows a default value from the named argument HashMap.
     */
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
	    /**
     * Sets the named arguments required field based on whether the String is 
     * " " or contains information. 
     * @param name Uses the name field. 
     */
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
	    /**
     * Returns the named arguments required attribute based on whether the 
     * String is " " or contains information. 
     * @param name Uses the name field.
     * @return Gives a required response based on the name.
     */
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
	    /**
     * Creates a blank String in order to parse the data appropriately. 
     * @param argumentsToParse Sets the String data if given an Array.
     */
	public void parse(String[] argumentsToParse) 
	{
		String myString = "";
		for (int i = 0; i < argumentsToParse.length; i++)
		{
			myString += argumentsToParse[i] + " ";
		}
		parseFormattedString(myString);
	}
	    /**
     * Formats the parsed information by setting the String appropriately. 
     * @param argumentsToParse Sets the String data if given a normal String 
     * value.
     */
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
	    /**
     * Searches through both the NamedArguments & PositionalArguments by iterating
     * over the HashMaps. 
     * @return Gives a argument of each name of the above HashMaps.
     */
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
	    /**
     * Returns the argument value based on the name in either the NamedArgument 
     * or PositionalArgument HashMap.
     * @param <T> Gives any data type. 
     * @param name Uses current name.
     * @return Shows the argument based on the name given.
     */
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
	/**
     * Returns the list argument value based on the name in either the NamedArgument 
     * or PositionalArgument HashMap.
     * @param <T> Gives any data type. 
     * @param name Uses current name.
     * @return Shows the argument based on the name given.
     */
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
	    /**
     * Returns the current help information.
     * @return Displays the proper help message.
     */
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
	
	/**
     * Sets the help system exit to either true or false.
     */
	public void setHelpSystemExit(boolean b)
	{
		helpSystemExit = b;
	}
}