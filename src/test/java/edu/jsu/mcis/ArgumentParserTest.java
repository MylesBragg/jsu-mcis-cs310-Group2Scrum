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
		parser.addOptionalArguments("--type", "-t", "Set Type", "optional", "");
		parser.parse(myString);
		assertEquals("closet", parser.getArgumentValue("--type"));
	}
	
	@Test
	public void addOptValAfterPositionalVal() {
		String myString = "volCal 7 --type closet";
		parser.addArg("length", "Enter whole number", "integer");
		parser.addOptionalArguments("--type", "-t", "Set Type", "optional", "");
		parser.parse(myString);
		assertTrue(parser.getArgumentValue("length").equals(7));
		assertEquals("closet", parser.getArgumentValue("--type"));
	}
	@Test 
	public void addOptValBeforePositionalVal() {
		String myString = "volCal --type closet 7";
		parser.addOptionalArguments("--type", "-t", "Set Type", "optional", "");
		parser.addArg("length", "Enter whole number", "integer");
		parser.parse(myString);
		assertEquals("closet", parser.getArgumentValue("--type"));
		assertTrue(parser.getArgumentValue("length").equals(7));
	}
	@Test
	public void addOptValBetweenPositionalVals() {
		String myString = "volCal 7 --type closet 5";
		parser.addOptionalArguments("--type", "-t", "Set Type", "optional", "");
		parser.addArg("length", "Enter whole number", "integer");
		parser.addArg("width", "Enter whole number", "integer");
		parser.parse(myString);
		assertEquals("closet", parser.getArgumentValue("--type"));
		assertTrue(parser.getArgumentValue("length").equals(7));
		assertTrue(parser.getArgumentValue("width").equals(5));
	}
	@Test
	public void testAddOptValDefaultVal() {
		parser.addOptionalArguments("--type", "-t", "Set Calculation Type", "optional", "box");
		assertEquals("box", parser.getArgumentValue("--type"));
	}
	@Test
	public void testAddFlagOptVal() {
	String myString = "volCal --save";
	parser.addOptionalArguments("--save", "-v", "Save Calculations", "flag", "");
	parser.parse(myString);
	assertEquals(true, parser.getArgumentValue("--save"));
	}
	
	@Test
	public void testShortNameOptArg() {
		String myString = "volCal 7 -t closet 5";
		parser.addOptionalArguments("--type", "-t", "Set Type", "optional", "box");
		parser.addArg("length", "Enter whole number", "integer");
		parser.addArg("width", "Enter whole number", "integer");
		parser.parse(myString);
		assertEquals("closet", parser.getArgumentValue("-t"));
	}
	@Test
	public void testShortNameOptArgWithTwoDashes() {
		String myString = "volCal 7 --t closet 5";
		parser.addOptionalArguments("--type", "--t", "Set Type", "optional", "box");
		parser.addArg("length", "Enter whole number", "integer");
		parser.addArg("width", "Enter whole number", "integer");
		parser.parse(myString);
		assertEquals("closet", parser.getArgumentValue("--t"));
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
	
	@Test
    public void testAddHelpWithArgument()
    {
         parser.addArgumentHelp("length", "Please enter the length as a whole number");
         assertEquals("Please enter the length as a whole number", parser.getHelpArgumentValue("length"));
    }

    @Test
    public void testProgramHelp()
    {
        parser.addArgumentHelp("-h", "usage: java VolumeCalculator length width height\n");
        assertEquals("usage: java VolumeCalculator length width height\n" + "\n" +"Calculate the volume of a box.\n "+"\n"+
                      "positional arguments: length the length of the box\n"+"width the width of the box\n"+"height the height of the box\n",
        parser.getHelpArgumentValue("-h"));
    }
}