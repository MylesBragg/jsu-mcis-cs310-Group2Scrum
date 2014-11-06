package edu.jsu.mcis;

import java.util.*;
public class ArgParser {
	
	private LinkedHashMap<String, Argument> argValueHolder;
	private LinkedHashMap<String, OptionalArgument> optArgValueHolder;
	private List<String> posArgNames;
	private String program;
	private String progDesc;
	
	public ArgParser(String progName) {
		
		program = progName;
		
		argValueHolder = new LinkedHashMap<String, Argument>();
		optArgValueHolder = new LinkedHashMap<String, OptionalArgument>();
		
		posArgNames = new ArrayList<String>();
		
	}
	
	public void addProgramHelpInfo(String progHelp)
	{
		progDesc = progHelp;
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
	
	public void addArg(String name, String help, Argument.Type dataType) {
		argValueHolder.put(name, new Argument(name, help, dataType));
		posArgNames.add(name);
	}
	public void addOptArg(String name, String help, Argument.Type dataType) {
		optArgValueHolder.put(name, new OptionalArgument(name, help, dataType));
	}
	public void addOptArg(String name, String help, Argument.Type dataType, String defaultVal) {
		optArgValueHolder.put(name, new OptionalArgument(name, help, dataType, defaultVal));
	}
	public void addOptArg(String name, String help, Argument.Type dataType, int defaultVal) {
		optArgValueHolder.put(name, new OptionalArgument(name, help, dataType, defaultVal));
	}
	public void addOptArg(String name, String help, Argument.Type dataType, float defaultVal) {
		optArgValueHolder.put(name, new OptionalArgument(name, help, dataType, defaultVal));
	}
	public void addOptArg(String name, String help, Argument.Type dataType, boolean defaultVal) {
		optArgValueHolder.put(name, new OptionalArgument(name, help, dataType, defaultVal));
	}
	public void addOptArg(String name, String shortName, String help, Argument.Type dataType) {
		optArgValueHolder.put(name, new OptionalArgument(name, shortName, help, dataType));
	}
	public void addOptArg(String name, String shortName, String help, Argument.Type dataType, String defaultVal) {
		optArgValueHolder.put(name, new OptionalArgument(name, shortName, help, dataType, defaultVal));
	}
	public void addOptArg(String name, String shortName, String help, Argument.Type dataType, int defaultVal) {
		optArgValueHolder.put(name, new OptionalArgument(name, shortName, help, dataType, defaultVal));
	}
	public void addOptArg(String name, String shortName, String help, Argument.Type dataType, float defaultVal) {
		optArgValueHolder.put(name, new OptionalArgument(name, shortName, help, dataType, defaultVal));
	}
	public void addOptArg(String name, String shortName, String help, Argument.Type dataType, boolean defaultVal) {
		optArgValueHolder.put(name, new OptionalArgument(name, shortName, help, dataType, defaultVal));
	}
	
	public void parse(String argsToParse) {
	
		String nextValue = "";
		String previousValue = "";
		Scanner argScanner = new Scanner(argsToParse);
		String[] args = new String[1];
		int currentPosArgIndex = 0;
		
		while (argScanner.hasNext()) {
		
			nextValue = argScanner.next();
			if (nextValue.equals("-h") || nextValue.equals("--help")) {
				System.out.println(getHelpInfo());
			}
			else {
				try {
					if (nextValue.contains("--")) {
						if (optArgValueHolder.containsKey(nextValue.substring(2))) {
							if (optArgValueHolder.get(nextValue.substring(2)).getFlagBit()) {
								addOptArgValue(nextValue.substring(2), "true");
							}
							else {
								addOptArgValue(nextValue.substring(2), argScanner.next());
							}
							
						}
					}
					else if (nextValue.contains("-")) {
						String optShort = nextValue.substring(1);
						String optName = getOptArgFullName(optShort);
						
						if (optArgValueHolder.get(optName).getFlagBit()) {
								addOptArgValue(optName, "true");
							}
							else {
								String optValue = argScanner.next();
								addOptArgValue(optName, optValue);
							}
					}
					else {
						addArgValue(posArgNames.get(currentPosArgIndex), nextValue);
						currentPosArgIndex++;
					}
				}
				catch (IndexOutOfBoundsException e) {
					String usageLine = getUsageLine();
					throw new TooManyArgsException(usageLine, program, nextValue, argScanner);
				}
			}
		}
		if (!nextValue.equals("-h") && !nextValue.equals("--help") && currentPosArgIndex < argValueHolder.size())
		{	
			String usageLine = getUsageLine();
			throw new NotEnoughArgsException(usageLine, program, argValueHolder, argValueHolder.size());
		}
	}
	
	public void addArgValue(String argName, String argValue) {
		try {
			argValueHolder.get(argName).setValue(argValue);
		}
		catch(NumberFormatException nfe)
		{
			String usageLine = getUsageLine();
			throw new InvalidValueException(usageLine, program, argName, argValueHolder.get(argName).getType(), argValue);
		}
	}
	
	public void addOptArgValue(String optArgName, String optArgValue) {
		try {
			optArgValueHolder.get(optArgName).setValue(optArgValue);
		}
		catch(NumberFormatException nfe) {
			String usageLine = getUsageLine();
			throw new InvalidValueException(usageLine, program, optArgName, optArgValueHolder.get(optArgName).getType(), optArgValue);
		}
	}
	
	public String getOptArgFullName(String shortName)
	{
		Iterator<String> hashKeys = optArgValueHolder.keySet().iterator();
		String name;
		while(hashKeys.hasNext())
		{
			name = hashKeys.next();
			String currentShortName = optArgValueHolder.get(name).getShortName();
			if(currentShortName.equals(shortName))
			{
				return name;
			}
		}
		return "";
	}
	
	private String getHelpInfo()
	{
		String helpMessage = this.getUsageLine() + "\n\n";
		helpMessage = helpMessage + progDesc + "\n\n";
		helpMessage = helpMessage + getPosArgsInfo();
		return helpMessage;
	}
	
	private String getUsageLine()
	{
		String usageString = "usage: java " + program;
		Iterator<String> hashKeys = argValueHolder.keySet().iterator();
		String argList = "";
		while (hashKeys.hasNext())
		{
			argList = argList + " " + hashKeys.next();
		}
		hashKeys = optArgValueHolder.keySet().iterator();
		String currentKey = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			if(optArgValueHolder.get(currentKey).getRequiredBit())
			{
				argList = argList + " [" + currentKey + "]";
			}
		}
		argList = argList.trim();
		usageString = usageString + " " + argList;
		usageString = usageString.trim();
		return usageString;
	}
	
	private String getPosArgsInfo()
	{
		String posArgsHelp = "positional arguments: ";
		Iterator<String> hashKeys = argValueHolder.keySet().iterator();
		String currentKey = "";
		String argsHelp = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			argsHelp = argsHelp + currentKey + " " + argValueHolder.get(currentKey).getDescription() + "\n";
		}
		hashKeys = optArgValueHolder.keySet().iterator();
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			if(optArgValueHolder.get(currentKey).getRequiredBit())
			{
				argsHelp = argsHelp + "[" + currentKey + "] " + optArgValueHolder.get(currentKey).getDescription() + "\n";
			}
		}
		argsHelp = argsHelp.trim();
		posArgsHelp = posArgsHelp + argsHelp;
		posArgsHelp = posArgsHelp.trim();
		return posArgsHelp;
	}
	
	public String getHelpString() {
		return getHelpInfo();
	}
	
	public <T> T getArgValue(String name) {
		if (argValueHolder.containsKey(name)) {
			return argValueHolder.get(name).getValue();
		}
		else if (optArgValueHolder.containsKey(name)) {
			return (T)optArgValueHolder.get(name).getValue();
		}
		else {
			String optFullName = getOptArgFullName(name);
			if (optFullName.equals("")) {
				return (T)"Error key not found";
			}
			else {
				return (T)optArgValueHolder.get(optFullName).getValue();
			}
		}
	}
	public void setOptArgRequired(String name) {
		String optFullName = getOptArgFullName(name);
		if (optFullName.equals("")) {
			optArgValueHolder.get(name).setRequiredBit();
		}
		else {
			optArgValueHolder.get(optFullName).setRequiredBit();
		}
	}
}