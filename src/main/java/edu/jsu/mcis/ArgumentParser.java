package edu.jsu.mcis;

import java.util.*;

public class ArgumentParser
{
	private List<String> arrayOfNames;
	private List<String> arrayOfValues;
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
			String program = p.next();
			int count = 0;
			while(p.hasNext())
			{
				nextValue = p.next();
				arrayOfValues.set(count, nextValue);
				count++;
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
}