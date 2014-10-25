package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgParserTest 
{
	private ArgParser parser;
	
	@Before
	public void StartUp()
	{
		parser = new ArgParser("VolumeCalculator");
	}
	
	@Test
	public void testNewAddArgAndValue()
	{
		String myString = "7";
		parser.addArg("length", "Enter a whole number", "integer");
		parser.parse(myString);
		assertTrue(parser.getArgValue("length").equals(7));
	}
	
	@Test
	public void testNewAddMultipleArgsAndValues()
	{
		String myString = "7 5.0 2";
		parser.addArg("length", "Enter a whole number as length", "integer");
		parser.addArg("width", "Enter a float number as width", "float");
		parser.addArg("height", "Enter a whole number as height", "integer");
		parser.parse(myString);
		assertTrue(parser.getArgValue("length").equals(7));
		assertTrue(parser.getArgValue("width").equals(5.0f));
		assertTrue(parser.getArgValue("height").equals(2));
	}
	
	@Test
	public void addOptVal()
	{
		String myString = "7 --type closet";
		parser.addArg("length", "Enter whole number", "integer");
		parser.addOptionalArg("--type", "-t", "Set Type", "optional", "");
		parser.parse(myString);
		assertEquals("closet", parser.getArgValue("--type"));
	}
	
	@Test
	public void addOptValAfterPositionalVal()
	{
		String myString = "7 --type closet";
		parser.addArg("length", "Enter whole number", "integer");
		parser.addOptionalArg("--type", "-t", "Set Type", "optional", "");
		parser.parse(myString);
		assertTrue(parser.getArgValue("length").equals(7));
		assertEquals("closet", parser.getArgValue("--type"));
	}
	
	@Test 
	public void addOptValBeforePositionalVal()
	{
		String myString = "--type closet 7";
		parser.addOptionalArg("--type", "-t", "Set Type", "optional", "");
		parser.addArg("length", "Enter whole number", "integer");
		parser.parse(myString);
		assertEquals("closet", parser.getArgValue("--type"));
		assertTrue(parser.getArgValue("length").equals(7));
	}
	
	@Test
	public void addOptValBetweenPositionalVals()
	{
		String myString = "7 --type closet 5";
		parser.addOptionalArg("--type", "-t", "Set Type", "optional", "");
		parser.addArg("length", "Enter whole number", "integer");
		parser.addArg("width", "Enter whole number", "integer");
		parser.parse(myString);
		assertEquals("closet", parser.getArgValue("--type"));
		assertTrue(parser.getArgValue("length").equals(7));
		assertTrue(parser.getArgValue("width").equals(5));
	}
	
	@Test
	public void testAddOptValDefaultVal()
	{
		parser.addOptionalArg("--type", "-t", "Set Calculation Type", "optional", "box");
		assertEquals("box", parser.getArgValue("--type"));
	}
	
	@Test
	public void testAddFlagOptVal()
	{
	String myString = "--save";
	parser.addOptionalArg("--save", "-v", "Save Calculations", "flag", "");
	parser.parse(myString);
	assertEquals(true, parser.getArgValue("--save"));
	}
	
	//@Test
	public void testShortNameOptArg()
	{
		String myString = "7 -t closet 5";
		parser.addOptionalArg("--type", "-t", "Set Type", "optional", "box");
		parser.addArg("length", "Enter whole number", "integer");
		parser.addArg("width", "Enter whole number", "integer");
		parser.parse(myString);
		assertEquals("closet", parser.getArgValue("-t"));
	}

	@Test
	public void testInvalidArgInt()
	{
		try
		{
			String myString = "7 something 2";
			parser.addArg("length", "Enter a whole number as length", "float");
			parser.addArg("width", "Enter a float number as width", "integer");
			parser.addArg("height", "Enter a whole number as height", "float");
			parser.parse(myString);
			assert false;
		}
		catch(InvalidValueException e)
		{
			assert true;
		}
	}
	
	@Test
	public void testInvalidArgFloat()
	{
		try
		{
			String myString = "7 something 2";
			parser.addArg("length", "Enter a whole number as length", "float");
			parser.addArg("width", "Enter a float number as width", "float");
			parser.addArg("height", "Enter a whole number as height", "float");
			parser.parse(myString);
			assert false;
		}
		catch(InvalidValueException e)
		{
			assert true;
		}
	}
	
	@Test
	public void testInvalidArgBoolean()
	{
		try
		{
			String myString = "7 something 2";
			parser.addArg("length", "Enter a whole number as length", "float");
			parser.addArg("width", "Enter a float number as width", "boolean");
			parser.addArg("height", "Enter a whole number as height", "float");
			parser.parse(myString);
			assert false;
		}
		catch(InvalidValueException e)
		{
			assert true;
		}
	}
	
	@Test
	public void testTooFewArgs()
	{
		try
		{
			parser.addArg("length", "Enter a whole number as length", "float");
			parser.addArg("width", "Enter a float number as width", "float");
			parser.addArg("height", "Enter a whole number as height", "float");
			parser.parse("7 5.2");
			assert false;
		}
		catch(NotEnoughArgsException e)
		{
			assert true;
		}
	}
	
	@Test
	public void testTooManyArgs()
	{
		try
		{
			parser.addArg("length", "Enter a whole number as length", "float");
			parser.addArg("width", "Enter a float number as width", "float");
			parser.addArg("height", "Enter a whole number as height", "float");
			parser.parse("7 5 2 43");
			assert false;
		}
		catch(TooManyArgsException e)
		{
			assert true;
		}
	}
	
	@Test
    public void testAddHelpWithArg()
    {
         parser.addArgHelp("length", "Please enter the length as a whole number");
         assertEquals("Please enter the length as a whole number", parser.getHelpArgValue("length"));
    }

    @Test
    public void testProgramHelp()
    {
        parser.addArgHelp("-h", "usage: java VolumeCalculator length width height\n");
        assertEquals("usage: java VolumeCalculator length width height\n" + "\n" +"Calculate the volume of a box.\n "+"\n"+
                      "positional arguments: length the length of the box\n"+"width the width of the box\n"+"height the height of the box\n",
        parser.getHelpArgValue("-h"));
    }
}