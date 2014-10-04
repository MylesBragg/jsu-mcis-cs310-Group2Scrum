package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser
{
	private List<String> arrayOfNames;
	private List<String> arrayOfValues;
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
					getHelp();
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
	
	public String getHelp() 
	{
		if(program != "")
		{
			//this is supposed to return the help info for user program
			//the idea is to call a method from ArguementValues that takes a 
			//String program name, finds the help info for that program,
			//sends it back, and this will output the result
			//ie return argVals.sendHelpInfo(someProgram)
			return "blah";
		}
		else
		{
			return "No program to get help from";
		}
	}
	
	public String getProgramName() {
		return program;
	}
}