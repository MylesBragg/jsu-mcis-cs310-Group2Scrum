package edu.jsu.mcis;


public class ArgumentParser
{
	private int[] integerArray;
	private int length, width, heighth;
	String[] stringArray;
	String program;
	ArgumentValues myValues;
	
	public ArgumentParser(String[] passedArgs)
	{
		stringArray = passedArgs;
		program = passedArgs[0];//Will change to ** new ArgumentValues(passedArgs[0]);
		ArgumentValues[] myValues = new ArgumentValues[passedArgs.length-1];
		for(int i = 1; i < passedArgs.length; i++)
		{
			myValues[i-1].setName(passedArgs[i]);
		}
	}
	
	public int getNumberOfArguments()
	{
		return (stringArray.length - 1);
	}
	
	public String getProgramName()
	{
		return myValues.getProgram();
	}
	
	public int getLength()
	{
		return myValues.getLength();
	}
	
	public int getWidth()
	{
		return myValues.getWidth();
	}
	
	public int getHeighth()
	{
		return myValues.getHeighth();
	}
	
	public static void main(String[] args)// example: java ArgumentParser {7 6 2}
	{
		ArgumentParser p = new ArgumentParser(args);
	}
}