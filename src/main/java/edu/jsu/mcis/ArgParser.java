package edu.jsu.mcis;

import java.util.*;

public class ArgParser {
	
	private HashMap<String, ArgValues> argValueHolder;
	private HashMap<String, OptArgValues> optArgValueHolder;
	private List<String> posArgNames;
	private List<String> optArgNames;
	private String program;
	private String progDesc;
	
	public ArgParser(String progName) {
		
		program = progName;
		
		argValueHolder = new HashMap<String, ArgValues>();
		optArgValueHolder = new HashMap<String, OptArgValues>();
		
		posArgNames = new ArrayList<String>();
		optArgNames = new ArrayList<String>();
		
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
	
	public void addArg(String name, String help, dataTypeDefinitions dataType) {
		argValueHolder.put(name, new ArgValues(name, help, dataType));
		posArgNames.add(name);
	}
	public void addOptionalArg(String name, String help, dataTypeDefinitions dataType) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType));
		optArgNames.add(name);
	}
	public void addOptionalArg(String name, String help, dataTypeDefinitions dataType, String defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType, defaultVal));
		optArgNames.add(name);
	}
	public void addOptionalArg(String name, String help, dataTypeDefinitions dataType, int defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType, defaultVal));
		optArgNames.add(name);
	}
	public void addOptionalArg(String name, String help, dataTypeDefinitions dataType, float defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType, defaultVal));
		optArgNames.add(name);
	}
	public void addOptionalArg(String name, String help, dataTypeDefinitions dataType, boolean defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, help, dataType, defaultVal));
		optArgNames.add(name);
	}
	public void addOptionalArg(String name, String shortName, String help, dataTypeDefinitions dataType) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType));
		optArgNames.add(name);
	}
	public void addOptionalArg(String name, String shortName, String help, dataTypeDefinitions dataType, String defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType, defaultVal));
		optArgNames.add(name);
	}
	public void addOptionalArg(String name, String shortName, String help, dataTypeDefinitions dataType, int defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType, defaultVal));
		optArgNames.add(name);
	}
	public void addOptionalArg(String name, String shortName, String help, dataTypeDefinitions dataType, float defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType, defaultVal));
		optArgNames.add(name);
	}
	public void addOptionalArg(String name, String shortName, String help, dataTypeDefinitions dataType, boolean defaultVal) {
		optArgValueHolder.put(name, new OptArgValues(name, shortName, help, dataType, defaultVal));
		optArgNames.add(name);
	}
	
	public void parse(String argsToParse) {
	
		String nextValue = "";
		String previousValue = "";
		Scanner argScanner = new Scanner(argsToParse);
		String[] args = new String[1];
		int currentPosArgIndex = 0;
		
		while (argScanner.hasNext()) {
		
			nextValue = argScanner.next();
			if (nextValue.equals("-h")) {
				HelpInfoGenerator h = new HelpInfoGenerator();
				String helpString = h.getHelpInfo(argValueHolder, program, progDesc);
				System.out.println(helpString);
			}
			else {
				try {
					if (currentPosArgIndex < argValueHolder.size()) {
						if (nextValue.contains("--")) {
							if (optArgNames.contains(nextValue.substring(2))) {
								addOptArgValue(nextValue.substring(2), argScanner.next());
							}
						}
						else if (nextValue.contains("-")) {
							if (optArgNames.contains(nextValue.substring(1))) {
								addOptArgValue(nextValue.substring(1), argScanner.next());
							}
						}
						else {
							addArgValue(posArgNames.get(currentPosArgIndex), nextValue);
							currentPosArgIndex++;
						}
					}
				}
				catch (IndexOutOfBoundsException e) {
					String usageLine = getHelpUsageText();
					throw new TooManyArgsException(usageLine, program, nextValue, argScanner);
				}
				
				if (currentPosArgIndex < argValueHolder.size()) {
					String helpUsage = getHelpUsageText();
					throw new NotEnoughArgsException(helpUsage, program, argValueHolder, argValueHolder.size());
				}
			}
		}
	}
	
	public void addArgValue(String argName, String argValue) {
		
		//argValueHolder.get(argName).addValueArg(argValue);
	}
	
	public void addOptArgValue(String optArgName, String optArgValue) {
		//optArgValueHolder.get(optArgName).addValueArg(optArgValue);
	}
	
	public String getHelpUsageText() {
		HelpInfoGenerator h = new HelpInfoGenerator();
		return h.getUsageLine(argValueHolder, program);
	}
	
	public <T> T getArgValue(String name) {
		String dataType = argValueHolder.get(name).getDataTypeArg(name);
		return argValueHolder.get(name).getValueArg(name, dataType);
	}
}