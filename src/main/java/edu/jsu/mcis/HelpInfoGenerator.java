package edu.jsu.mcis;

import java.util.*;
public class HelpInfoGenerator
{
	public String getHelpInfo(LinkedHashMap<String, ArgValues> hash, String program, String progDesc)
	{
		String helpMessage = getUsageLine(hash, program) + "\n\n";
		helpMessage = helpMessage + progDesc + "\n\n";
		helpMessage = helpMessage + getPosArgsInfo(hash);
		return helpMessage;
	}
	
	public String getUsageLine(LinkedHashMap<String, ArgValues> hash, String program)
	{
		String helpMessage = "usage: java " + program;
		Iterator<String> hashKeys = hash.keySet().iterator();
		String argList = "";
		while (hashKeys.hasNext())
		{
			argList = argList + " " + hashKeys.next();
		}
		argList = argList.trim();
		helpMessage = helpMessage + " " + argList;
		helpMessage = helpMessage.trim();
		return helpMessage;
	}
	
	public String getPosArgsInfo(LinkedHashMap<String, ArgValues> hash)
	{
		String posArgsHelp = "positional arguments: ";
		Iterator<String> hashKeys = hash.keySet().iterator();
		String currentKey = "";
		String argsHelp = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			argsHelp = argsHelp + currentKey + " " + hash.get(currentKey).getHelpArg() + "\n";
		}
		argsHelp = argsHelp.trim();
		posArgsHelp = posArgsHelp + argsHelp;
		posArgsHelp = posArgsHelp.trim();
		return posArgsHelp;
	}
}