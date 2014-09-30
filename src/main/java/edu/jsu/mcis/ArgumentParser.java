package edu.jsu.mcis;

import java.util.Scanner;

public class ArgumentParser
{
	private int[] integerArray;
	private int length, width, heighth;
	Scanner enteredStringScanner;
	String parsingString;
	String program;
	
	public boolean addArguments(String s)
	{
		Scanner parsingScanner = new Scanner(s);
		program = parsingScanner.next();
		length = parsingScanner.nextInt();
		width = parsingScanner.nextInt();
		heighth = parsingScanner.nextInt();
		return true;
	}
	
/*	public String readInString(String s)
	{
		parsingString = s;
		return parsingString;
	}*/
	
	public String getProgramName()
	{
		//enteredStringScanner = new Scanner(parsingString);
		//String program = enteredStringScanner.next();
		return program;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeighth()
	{
		return heighth;
	}
}