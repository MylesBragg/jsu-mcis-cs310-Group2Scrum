package edu.jsu.mcis;

import java.util.*;

public class HelpInfoGenerator
{
	public String getHelpInfo(HashMap<String, ArgValues> hash, String program, String progDesc)
	{
		String helpMessage = getUsageLine(hash, program) + "\n\n";
		helpMessage = helpMessage + progDesc + "\n\n";
		helpMessage = helpMessage + getPosArgsInfo(hash);
		return helpMessage;
	}
	
	public String getUsageLine(HashMap<String, ArgValues> hash, String program)
	{
		String helpMessage = "usage: java " + program;
		Iterator<String> hashKeys = hash.keySet().iterator();
		String argList = "";
		while (hashKeys.hasNext())
		{
			argList = hashKeys.next() + " " + argList;
		}
		argList = argList.trim();
		helpMessage = helpMessage + " " + argList;
		helpMessage = helpMessage.trim();
		return helpMessage;
	}
	
	public String getPosArgsInfo(HashMap<String, ArgValues> hash)
	{
		String posArgsHelp = "positional arguments: ";
		Iterator<String> hashKeys = hash.keySet().iterator();
		String currentKey = "";
		String argsHelp = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			argsHelp = currentKey + " " + hash.get(currentKey).getHelpArg() + "\n" + argsHelp;
		}
		argsHelp = argsHelp.trim();
		posArgsHelp = posArgsHelp + argsHelp;
		posArgsHelp = posArgsHelp.trim();
		return posArgsHelp;
	}
}