package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser
{
	public ArgumentValues argVals = new ArgumentValues();
	private List<String> arrayOfNames;
	private List<String> arrayOfValues;
	private List<String> arrayOfPrograms;
	String program = "";
	
	public ArgumentParser()
	{
		arrayOfNames = new ArrayList<String>();
		arrayOfValues = new ArrayList<String>();
	}
	
	public void addArg(String name)
	{
		arrayOfNames.add(name);
		arrayOfValues.add(" ");
	}
	
	public void addArg(String name, String help) 
	{
		arrayOfNames.add(name);
		arrayOfValues.add(" ");
		argVals.addHelpArgument(name, help);
	}
	
	public void addProgram(String name, String help) 
	{
		arrayOfNames.add(name);
		arrayOfValues.add(" ");
		argVals.addHelpArgument(name, help);
	}
	
	public int getNumArguments()
	{
		return arrayOfValues.size();
	}

	public String parse(String something)
	{
		String nextValue = "";
		try
		{
			Scanner p = new Scanner(something);
			program = p.next();
			
			int count = 0;
			while(p.hasNext())
			{
				nextValue = p.next();
				
				if(nextValue == "-h") 
				{
					getHelpMessage(program);
				}
				else
				{
					arrayOfValues.set(count, nextValue);
					count++;
				}
			}
			
			for(int i = 0; i < arrayOfValues.size(); i++)
			{
				if(arrayOfValues.get(i) == " ")
				{
					return arrayOfNames.get(i) + " missing";
				}
			}
		}
		catch (IndexOutOfBoundsException e)
		{
			return "Unrecognized argument " + nextValue;
		}
		return "Good";
	}
	
	public String getArgumentValue(String name)
	{
		for(int i = 0; i < arrayOfNames.size(); i++)
		{
			if(arrayOfNames.get(i) == name)
			{
				return arrayOfValues.get(i);
			}
		}
		return "Invalid Argument Name";
	}
	
	public String getHelpMessage() 
	{
		return argVals.getHelpArgument(program);
	}
	
	public String getHelpMessage(String help) 
	{
		return argVals.getHelpArgument(help);
	}
	
	public String getProgramName() {
		return program;
	}
}