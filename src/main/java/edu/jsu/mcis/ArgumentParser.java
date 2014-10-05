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
		//if (argValues.length == arrayOfNames.size()) {
			for (int i = 0; i < argValues.length; i++) {
				argVals.addValueArgument(arrayOfNames.get(i), argValues[i]);
			}
		//}
		//else if (argValues.length > arrayOfNames.size()) {
			//System.out.print("You have entered too many arguments.");
			//System.exit(0);
		//}
		//else {
			//System.out.print("You have entered too few arguments.");
			//System.exit(0);
		//}
	}
	
	public String getArgumentValue(String name)
	{
		return argVals.getValueArgument(name);
	}
	
	public String getHelpArgumentValue(String name) {
		return argVals.getHelpArgument(name);
	}
}