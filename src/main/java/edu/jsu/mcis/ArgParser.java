package edu.jsu.mcis;

import java.util.*;
public class ArgParser {
	
	private LinkedHashMap<String, ArgValues> argValueHolder;
	private LinkedHashMap<String, OptArgValues> optArgValueHolder;
	private List<String> posArgNames;
	private String program;
	private String progDesc;
	private String helpString;
	
	public ArgParser(String progName) {
		
		program = progName;
		
		argValueHolder = new LinkedHashMap<String, ArgValues>();
		optArgValueHolder = new LinkedHashMap<String, OptArgValues>();
		
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
	
	public void addArg(String name, String help, ArgValues.Type dataType) {
		argValueHolder.put(name, new ArgValues(name, help, dataType));
		posArgNames.add(name);
	}
	public void addOptionalArg(String name, String help, ArgValues.Type dataType) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType));
	}
	public void addOptionalArg(String name, String help, ArgValues.Type dataType, String defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType, defaultVal));
	}
	public void addOptionalArg(String name, String help, ArgValues.Type dataType, int defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType, defaultVal));
	}
	public void addOptionalArg(String name, String help, ArgValues.Type dataType, float defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType, defaultVal));
	}
	public void addOptionalArg(String name, String help, ArgValues.Type dataType, boolean defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType, defaultVal));
	}
	public void addOptionalArg(String name, String shortName, String help, ArgValues.Type dataType) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType));
	}
	public void addOptionalArg(String name, String shortName, String help, ArgValues.Type dataType, String defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType, defaultVal));
	}
	public void addOptionalArg(String name, String shortName, String help, ArgValues.Type dataType, int defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType, defaultVal));
	}
	public void addOptionalArg(String name, String shortName, String help, ArgValues.Type dataType, float defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType, defaultVal));
	}
	public void addOptionalArg(String name, String shortName, String help, ArgValues.Type dataType, boolean defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType, defaultVal));
	}
	
	public void parse(String argsToParse) {
	
		String nextValue = "";
		String previousValue = "";
		Scanner argScanner = new Scanner(argsToParse);
		String[] args = new String[1];
		int currentPosArgIndex = 0;
		
		while (argScanner.hasNext()) {
		
			nextValue = argScanner.next();
			if (nextValue.equals("-h")) {			//getHelpInfo
				HelpInfoGenerator h = new HelpInfoGenerator();
				helpString = h.getHelpInfo(argValueHolder, program, progDesc);
				System.out.println(helpString);
			}
			else {
				try {
					if (nextValue.contains("--")) {
						if (optArgValueHolder.containsKey(nextValue.substring(2))) {
							addOptArgValue(nextValue.substring(2), argScanner.next());
						}
					}
					else if (nextValue.contains("-")) {
						if (optArgValueHolder.containsKey(nextValue.substring(1))) {
							addOptArgValue(nextValue.substring(1), argScanner.next());
						}
					}
					else {
						addArgValue(posArgNames.get(currentPosArgIndex), nextValue);
						currentPosArgIndex++;
					}
				}
				catch (IndexOutOfBoundsException e) {
					String usageLine = getHelpUsageText();
					throw new TooManyArgsException(usageLine, program, nextValue, argScanner);
				}
			}
		}
		if (!nextValue.equals("-h") && currentPosArgIndex < argValueHolder.size()) {
			String helpUsage = getHelpUsageText();
			throw new NotEnoughArgsException(helpUsage, program, argValueHolder, argValueHolder.size());
		}
	}
	
	public void addArgValue(String argName, String argValue) {
		try {
			argValueHolder.get(argName).addValueArg(argValue);
		}
		catch(NumberFormatException nfe)
		{
			String helpUsage = getHelpUsageText();
			throw new InvalidValueException(helpUsage, program, argName, argValueHolder.get(argName).getDataTypeArg(), argValue);
		}
	}
	
	public void addOptArgValue(String optArgName, String optArgValue) {
		
	}
	
	public String getHelpUsageText() {
		HelpInfoGenerator h = new HelpInfoGenerator();
		return h.getUsageLine(argValueHolder, program);
	}
	
	public String getHelpString() {
		return helpString;
	}
	
	public <T> T getArgValue(String name) {
		return argValueHolder.get(name).getValueArg();
	}
}