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
	public void testNewAddArgumentAndValue() {
		String myString = "volCal 7";
		parser.addArg("length", "Enter a whole number", "integer");
		parser.parse(myString);
		assertTrue(parser.getArgumentValue("length").equals(7));
	}
	
	@Test
	public void testNewAddMultipleArgumentsAndValues() {
		String myString = "volCal 7 5.0 2";
		parser.addArg("length", "Enter a whole number as length", "integer");
		parser.addArg("width", "Enter a float number as width", "float");
		parser.addArg("height", "Enter a whole number as height", "integer");
		parser.parse(myString);
		assertTrue(parser.getArgumentValue("length").equals(7));
		assertTrue(parser.getArgumentValue("width").equals(5.0f));
		assertTrue(parser.getArgumentValue("height").equals(2));
	}
	@Test
	public void addOptVal() {
		String myString = "volCal --type closet";
		parser.addArg("length", "Enter whole number", "integer");
		parser.addArg("--type", "Set Type", "optional");
		parser.parse(myString);
		assertEquals("closet", parser.getArgumentValue("--type"));
	}
	@Test
<<<<<<< HEAD
=======
	public void addOptValAfterPositionalVal() {
		String myString = "volCal 7 --type closet";
		parser.addArg("length", "Enter whole number", "integer");
		parser.addArg("--type", "Set Type", "optional");
		parser.parse(myString);
		assertTrue(parser.getArgumentValue("length").equals(7));
		assertEquals("closet", parser.getArgumentValue("--type"));
	}
	@Test 
	public void addOptValBeforePositionalVal() {
		String myString = "volCal --type closet 7";
		parser.addArg("--type", "Set Type", "optional");
		parser.addArg("length", "Enter whole number", "integer");
		parser.parse(myString);
		assertEquals("closet", parser.getArgumentValue("--type"));
		assertTrue(parser.getArgumentValue("length").equals(7));
	}
	@Test
	public void addOptValBetweenPositionalVals() {
		String myString = "volCal 7 --type closet 5";
		parser.addArg("--type", "Set Type", "optional");
		parser.addArg("length", "Enter whole number", "integer");
		parser.addArg("width", "Enter whole number", "integer");
		parser.parse(myString);
		assertEquals("closet", parser.getArgumentValue("--type"));
		assertTrue(parser.getArgumentValue("length").equals(7));
		assertTrue(parser.getArgumentValue("width").equals(5));
	}
	/*@Test
>>>>>>> 3f3a6a0fff9db5817ac0403355fee9c83f8088db
	public void testSingleArgumentAdder()
	{
		String[] myString = new String[1];
		myString[0] = "4";
		parser.addArg("length", "Enter a whole number as length", "integer");
		parser.adder(myString);
		assertEquals(4, parser.getArgumentValue("length"));
	}
	
	@Test
	public void testDoubleArgumentAdder()
	{
		String[] myString = new String[2];
		myString[0] = "4";
		myString[1] = "7.5";
		parser.addArg("length", "Enter a whole number as length", "integer");
		parser.addArg("width", "Enter a float number as width", "float");
		parser.adder(myString);
		assertEquals(4, parser.getArgumentValue("length"));
		assertEquals(7.5f, parser.getArgumentValue("width"));
	}
	
	@Test
	public void testMultipleArgumentAdder()
	{
		String[] myString = new String[3];
		myString[0] = "4";
		myString[1] = "7.3";
		myString[2] = "9";
		parser.addArg("length", "Enter a whole number as length", "integer");
		parser.addArg("width", "Enter a float number as width", "float");
		parser.addArg("height", "Enter a whole number as height", "integer");
		parser.adder(myString);
		assertEquals(4, parser.getArgumentValue("length"));
		assertEquals(7.3f, parser.getArgumentValue("width"));
		assertEquals(9, parser.getArgumentValue("height"));
	}
	
	@Test
	public void testCompleteParsing()
	{
		parser.addArg("length", "Enter a whole number as length", "integer");
		parser.addArg("width", "Enter a float number as width", "float");
		parser.addArg("height", "Enter a whole number as height", "integer");
		assertEquals("Parsing Completed", parser.parse("VolumeCalculator 7 5.2 2"));
	}
	
	@Test
	public void testTooFewArguments()
	{
		parser.addArg("length", "Enter a whole number as length", "integer");
		parser.addArg("width", "Enter a float number as width", "float");
		parser.addArg("height", "Enter a whole number as height", "integer");
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: the following arguments are required: height", 
					parser.parse("VolumeCalculator 7 5.2"));
	}
	
	@Test
	public void testTooManyArguments()
	{
		parser.addArg("length", "Enter a whole number as length", "integer");
		parser.addArg("width", "Enter a float number as width", "float");
		parser.addArg("height", "Enter a whole number as height", "integer");
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: unrecognized arguments: 43", 
					parser.parse("VolumeCalculator 7 5 2 43"));
	}
	
/*	//@Test
	public void testProgramHelp()
	{
		parser.addArgumentHelp("-h", "Calculate math problems");
		assertEquals("   Calculate math problems   ", parser.getHelpArgumentValue("-h"));
	}

	//@Test
	public void testAddHelpWithArgument() 
	{
		parser.addArgumentHelp("length", "Please enter the length as a whole number");
		assertEquals("   Please enter the length as a whole number   ", parser.getHelpArgumentValue("length"));
	}
	
	//@Test
	public void testParseAString()
	{	
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		parser.parse("VolumeCalculator 7 6 2");
		assertEquals("7", parser.getArgumentValue("length"));
		assertEquals("6", parser.getArgumentValue("width"));
		assertEquals("2", parser.getArgumentValue("height"));
	}*/
}