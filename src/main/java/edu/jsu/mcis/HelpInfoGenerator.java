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
		String currentKey = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			helpMessage = helpMessage + " " + currentKey;
		}
		return helpMessage;
	}
	
	public String getPosArgsInfo(HashMap<String, ArgValues> hash)
	{
		String posArgsHelp = "positional arguments: ";
		Iterator<String> hashKeys = hash.keySet().iterator();
		String currentKey = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			posArgsHelp = posArgsHelp + currentKey + " " + hash.get(currentKey).getHelpArg(currentKey) + "\n";
		}
		posArgsHelp = posArgsHelp.trim();
		return posArgsHelp;
	}
}