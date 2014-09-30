package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest 
{
	private ArgumentParser parser;
	
	//-h
	
	@Before
	public void testStartNewParse()
	{
		String[] myArgs = new String[4];
		myArgs[0] = "MyProg";
		myArgs[1] = "7";
		myArgs[2] = "6";
		myArgs[3] = "3";
		parser = new ArgumentParser(myArgs);
	}
	
	@Test
	public void testArgumentInputs()
	{
		int success = parser.getNumberOfArguments();
		assertEquals(3, success);
		assertEquals("MyProg", parser.getProgramName());
		assertEquals(7, parser.getLength());
		assertEquals(6, parser.getWidth());
		assertEquals(9, parser.getHeighth());
	}
	
	
}