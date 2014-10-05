package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest 
{
	private ArgumentParser parser;
	
	@Before
	public void StartUp()
	{
		parser = new ArgumentParser();
	}
	
	
	@Test
	public void testSingleArgumentParse()
	{
		String[] myString = new String[1];
		myString[0] = "4";
		parser.addArg("length");
		parser.parse(myString);
		assertEquals("4", parser.getArgumentValue("length"));
	}
	
	@Test
	public void testDoubleArgumentParse()
	{
		String[] myString = new String[2];
		myString[0] = "4";
		myString[1] = "7";
		parser.addArg("length");
		parser.addArg("width");
		parser.parse(myString);
		assertEquals("4", parser.getArgumentValue("length"));
		assertEquals("7", parser.getArgumentValue("width"));
	}
	
	@Test
	public void testMultipleArgumentParse()
	{
		String[] myString = new String[3];
		myString[0] = "4";
		myString[1] = "7";
		myString[2] = "9";
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		parser.parse(myString);
		assertEquals("4", parser.getArgumentValue("length"));
		assertEquals("7", parser.getArgumentValue("width"));
		assertEquals("9", parser.getArgumentValue("height"));
	}
	
	
	@Test
	public void testProgramHelp()
	{
		parser.addArgumentHelp("-h", "Calculate math problems");
		assertEquals("Calculate math problems", parser.getHelpArgumentValue("-h"));
	}
	
	

	@Test
	public void testAddHelpWithArgument() 
	{
		parser.addArgumentHelp("length", "Please enter the length as a whole number");
		assertEquals("Please enter the length as a whole number", parser.getHelpArgumentValue("length"));
	}
	
	
	
}