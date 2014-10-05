package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser
{
	private ArgumentValues argVals;
	private List<String> arrayOfNames;
	String program = "";
	
	public ArgumentParser()
	{
		argVals  = new ArgumentValues();
		arrayOfNames = new ArrayList<String>();
	}
	
	public void addArg(String name)
	{
		arrayOfNames.add(name);
	}
	
	public void addArgumentHelp(String name, String help) 
	{
		argVals.addHelpArgument(name, help);
	}
	public void addArgumentDataType(String name, String dataType) {
		argVals.addDataTypeArgument(name, dataType);
	}
	

	public void parse(String[] argValues)
	{
		for (int i = 0; i < argValues.length; i++) {
			argValues.addValueAgument(arrayOfName.get(i), argValues[i]);
		}
	}
	
	public String getArgumentValue(String name)
	{
		return argValues.getArgumentValue(name);
	}
	
	public String getHelpArgumentValue(String name) {
		return getHelpArgument(name);
	}
}