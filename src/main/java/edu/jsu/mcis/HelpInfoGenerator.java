package edu.jsu.mcis;

import java.util.*;

public class HelpInfoGenerator
{
	public String getHelpInfo(List<String> arrayOfNames, String program, ArgValues allArgVals, String progDesc)
	{
		String helpMessage = getUsageLine(arrayOfNames, program) + "\n\n";
		helpMessage = helpMessage + progDesc + "\n\n";
		helpMessage = helpMessage + getPosArgsInfo(arrayOfNames, allArgVals);
		return helpMessage;
	}
	
	public String getUsageLine(List<String> arrayOfNames, String program)
	{
		String helpMessage = "usage: java "	+ program;
		for(int i = 0; i < arrayOfNames.size(); i++)
		{
			helpMessage = helpMessage + " " + arrayOfNames.get(i);
		}
		return helpMessage;
	}
	
	public String getPosArgsInfo(List<String> arrayOfNames, ArgValues allArgVals)
	{
		String name = "";
		String posArgsHelp = "positional arguments: ";
		for(int i = 0; i < arrayOfNames.size(); i++)
		{
			name = arrayOfNames.get(i);
			posArgsHelp = posArgsHelp + name + " " + allArgVals.getHelpArg(name) + "\n";
		}
		posArgsHelp = posArgsHelp.trim();
		return posArgsHelp;
	}
}