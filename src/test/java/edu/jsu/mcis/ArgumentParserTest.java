package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ArgumentParserTest 
{
	private ArgumentParser parser;
	
	private LinkedHashMap<String, PositionalArgument> arrayOfNames;
	private LinkedHashMap<String, NamedArgument> arrayOfOptNames;
	
	@Before
	public void StartUp()
	{
		parser = new ArgumentParser("VolumeCalculator");
	}
	@Test
	public void testSetProgramDescription() {
		parser.setProgramDescription("Calculate the volume of a shape");
		assertEquals("Calculate the volume of a shape", parser.getProgramDescription());
	}
	@Test
	public void testAddNewPositionalArgument() {
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		assertEquals(1, parser.getPositionId("length"));
	}
	@Test
	public void testAddMultiplePositionalArguments() {
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		assertEquals(1, parser.getPositionId("length"));
		assertEquals(2, parser.getPositionId("width"));
	}
	@Test
	public void testSetPositionalArgumentDescription() {
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.setArgumentDescription("length", "The length of the shape");
		assertEquals("The length of the shape", parser.getArgumentDescription("length"));
	}
	@Test
	public void testSetMultiplePositionalArugmentDescriptions() {
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.setArgumentDescription("length", "The length of the shape");
		parser.setArgumentDescription("width", "The width of the shape");
		assertEquals("The length of the shape", parser.getArgumentDescription("length"));
		assertEquals("The width of the shape", parser.getArgumentDescription("width"));
	}
	@Test
	public void testSetNamedArgumentWithDescription() {
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setArgumentDescription("type", "The type of shape");
		assertEquals("The type of shape", parser.getArgumentDescription("type"));
	}
	@Test
	public void testSetNamedArgumentAlternateName() {
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setNamedArgumentAlternateName("type", "t");
		assertEquals("t", parser.getNamedArgumentAlternateName("type"));
	}
	@Test
	public void testSetSomeNamedArgumentAlternateName() {
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.addNamedArgument("room", Argument.Type.STRING);
		parser.addNamedArgument("const", Argument.Type.INT);
		parser.setNamedArgumentAlternateName("type", "t");
		parser.setNamedArgumentAlternateName("const", "constant");
		assertEquals("t", parser.getNamedArgumentAlternateName("type"));
		assertEquals("", parser.getNamedArgumentAlternateName("room"));
		assertEquals("constant", parser.getNamedArgumentAlternateName("const"));
	}
	@Test
	public void testSetNamedArgumentDefaultValue() {
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setNamedArgumentDefaultValue("type", "box");
		assertEquals("box", parser.getNamedArgumentDefaultValue("type"));
	}
	@Test
	public void testSetNamedArgumentsDefaultValuesAllType() {
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.addNamedArgument("rainy", Argument.Type.BOOLEAN);
		parser.addNamedArgument("const", Argument.Type.INT);
		parser.addNamedArgument("bathCount", Argument.Type.FLOAT);
		parser.setNamedArgumentDefaultValue("type", "box");
		parser.setNamedArgumentDefaultValue("rainy", true);
		parser.setNamedArgumentDefaultValue("const", 33);
		parser.setNamedArgumentDefaultValue("bathCount", 3.5f);
		assertEquals("box", parser.getNamedArgumentDefaultValue("type"));
		assertEquals(true, parser.getNamedArgumentDefaultValue("rainy"));
		assertEquals(33, parser.getNamedArgumentDefaultValue("const"));
		assertEquals(3.5f, parser.getNamedArgumentDefaultValue("bathCount"));
	}
	@Test(expected=NumberFormatException.class)
	public void testSetNamedArgumenDefaultValueInvalid() {
		parser.addNamedArgument("rainy", Argument.Type.BOOLEAN);
		parser.setNamedArgumentDefaultValue("rainy", "yes");
	}
	@Test(expected=NumberFormatException.class)
	public void testSetNamedArgumentDefaultValueInvalidMiddleValue() {
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.addNamedArgument("rainy", Argument.Type.BOOLEAN);
		parser.addNamedArgument("const", Argument.Type.INT);
		parser.setNamedArgumentDefaultValue("type", "box");
		parser.setNamedArgumentDefaultValue("rainy", "yes");
		parser.setNamedArgumentDefaultValue("const", 33);
	}
	@Test
	public void testSetNamedArgumentRequired() {
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setNamedArgumentRequired("type");
		assertEquals(true, parser.getNamedArgumentRequired("type"));
	}
	@Test
	public void testSetSomeNamedArgumentsRequired() {
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.addNamedArgument("const", Argument.Type.INT);
		parser.addNamedArgument("rainy", Argument.Type.BOOLEAN);
		parser.setNamedArgumentRequired("type");
		parser.setNamedArgumentRequired("rainy");
		assertEquals(true, parser.getNamedArgumentRequired("type"));
		assertEquals(false, parser.getNamedArgumentRequired("const"));
		assertEquals(true, parser.getNamedArgumentRequired("rainy"));
	}
	@Test
	public void testAddPositionalArgumentValues() {
		String argumentsToParse = "7.2 5.9 2.1";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.addPositionalArgument("height", Argument.Type.FLOAT);
		parser.parse(argumentsToParse);
		assertEquals(7.2f, parser.getArgumentValue("length"));
		assertEquals(5.9f, parser.getArgumentValue("width"));
		assertEquals(2.1f, parser.getArgumentValue("height"));
	}
	@Test
	public void testAddPositionalArgumentValuesAllTypes() {
		String argumentsToParse = "7.2 5 true suite";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("width", Argument.Type.INT);
		parser.addPositionalArgument("rainy", Argument.Type.BOOLEAN);
		parser.addPositionalArgument("roomType", Argument.Type.STRING);
		parser.parse(argumentsToParse);
		assertEquals(7.2f, parser.getArgumentValue("length"));
		assertEquals(5, parser.getArgumentValue("width"));
		assertEquals(true, parser.getArgumentValue("rainy"));
		assertEquals("suite", parser.getArgumentValue("roomType"));
	}
	@Test
	public void testParseStringArray() {
		String[] arguments = new String[3];
		arguments[0] = "7";
		arguments[1] = "5";
		arguments[2] = "2";
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.INT);
		parser.addPositionalArgument("height", Argument.Type.INT);
		parser.parse(arguments);
		assertEquals(7, parser.getArgumentValue("length"));
		assertEquals(5, parser.getArgumentValue("width"));
		assertEquals(2, parser.getArgumentValue("height"));
	}
	@Test
	public void testAddPositionalAndNamedArgumentValues() {
		String argumentsToParse = "7.2 --type box";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.parse(argumentsToParse);
		assertEquals(7.2f, parser.getArgumentValue("length"));
		assertEquals("box", parser.getArgumentValue("type"));
	}
	@Test(expected=InvalidValueException.class)
	public void testSetPositionalArgumentValue() {
		String argumentsToParse = "7.2 something 7";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("rainy", Argument.Type.BOOLEAN);
		parser.addPositionalArgument("const", Argument.Type.INT);
		parser.parse(argumentsToParse);
	}
	@Test(expected=InvalidValueException.class)
	public void testSetNamedArgumentValue() {
		String argumentsToParse = "7.2 --bathCount something 7";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addNamedArgument("bathCount", Argument.Type.INT);
		parser.addPositionalArgument("const", Argument.Type.INT);
		parser.parse(argumentsToParse);
	}
	@Test
    public void testProgramHelpShortName()
    {
		parser.setProgramDescription("Calculate the volume of a box.");
        parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.addPositionalArgument("height", Argument.Type.INT);
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setArgumentDescription("length", "the length of the box");
		parser.setArgumentDescription("width", "the width of the box");
		parser.setArgumentDescription("height", "the height of the box");
		parser.setArgumentDescription("type", "the type of shape");
		parser.parse("-h");
        assertEquals("usage: java VolumeCalculator length width height\n" + "\n" + "Calculate the volume of a box.\n" +
					"\n" + "positional arguments: length the length of the box\n" + "width the width of the box\n" + 
					"height the height of the box", parser.getHelpString());
    }
	@Test
    public void testProgramHelpFullName()
    {
		parser.setProgramDescription("Calculate the volume of a box.");
        parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.addPositionalArgument("height", Argument.Type.INT);
		parser.setArgumentDescription("length", "the length of the box");
		parser.setArgumentDescription("width", "the width of the box");
		parser.setArgumentDescription("height", "the height of the box");
		parser.parse("--help");
        assertEquals("usage: java VolumeCalculator length width height\n" + "\n" + "Calculate the volume of a box.\n" +
					"\n" + "positional arguments: length the length of the box\n" + "width the width of the box\n" + 
					"height the height of the box", parser.getHelpString());
    }
	
	@Test
	public void testGetHelpInfoWithOptionals()
	{
		String program = "VolumeCalculator";
		parser.setProgramDescription("Calculate the volume of a box.");
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.addPositionalArgument("height", Argument.Type.INT);
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setArgumentDescription("length", "the length of the box");
		parser.setArgumentDescription("width", "the width of the box");
		parser.setArgumentDescription("height", "the height of the box");
		parser.setArgumentDescription("type", "the type of shape");
		parser.setNamedArgumentRequired("type");
		assertEquals("usage: java VolumeCalculator length width height [--type]\n" + 
					"\n" + 
					"Calculate the volume of a box.\n" +
					"\n" + 
					"positional arguments: length the length of the box\n" + 
					"width the width of the box\n" + 
					"height the height of the box\n" +
					"[--type] the type of shape", parser.getHelpString());
	}

	public void testSetAndGetRequiredBit()
	{
		String program = "VolumeCalculator";
		parser.setProgramDescription("Calculate the volume of a box.");
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.BOOLEAN);
		parser.addPositionalArgument("height", Argument.Type.FLOAT);
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.addNamedArgument("pet", Argument.Type.STRING);
		parser.setArgumentDescription("length", "the length of the box");
		parser.setArgumentDescription("width", "the width of the box");
		parser.setArgumentDescription("height", "the height of the box");
		parser.setArgumentDescription("type", "the type of shape");
		parser.setArgumentDescription("pet", "my companion, he is irrelevant");
		parser.setNamedArgumentAlternateName("type", "t");
		parser.setNamedArgumentAlternateName("pet", "p");
		parser.setNamedArgumentRequired("type");
		parser.setNamedArgumentRequired("p");
		assertEquals("usage: java VolumeCalculator length width height [--type]\n" + 
					"\n" + 
					"Calculate the volume of a box.\n" +
					"\n" + 
					"positional arguments: length the length of the box\n" + 
					"width the width of the box\n" + 
					"height the height of the box\n" +
					"[--type] the type of shape\n" +
					"[--pet] my companion, he is irrelevant", parser.getHelpString());
	}
/*
	@Test
	public void testGetUsageLineWithOptArgs()
	{
		String program = "VolumeCalculator";
		arrayOfNames = new LinkedHashMap<String, Argument>();
		arrayOfOptNames = new LinkedHashMap<String, OptionalArgument>();
		arrayOfNames.put("length", new Argument("length", "the length of the box", Argument.Type.INT));
		arrayOfNames.put("width", new Argument("width", "the width of the box", Argument.Type.BOOLEAN));
		arrayOfNames.put("height", new Argument("height", "the height of the box", Argument.Type.FLOAT));
		arrayOfOptNames.put("type", new OptionalArgument("type", "This is the type information help", Argument.Type.STRING));
		arrayOfOptNames.get("type").setRequiredBit();
		HelpInfoGenerator h = new HelpInfoGenerator();
		String usage = h.getUsageLine(arrayOfNames, arrayOfOptNames, program);
		assertEquals(usage, "usage: java VolumeCalculator length width height [type]");
	}
	
	@Test
	public void testCreatePosArgsHelp()
	{
		arrayOfNames = new LinkedHashMap<String, Argument>();
		arrayOfNames.put("length", new Argument("length", "the length of the box", Argument.Type.INT));
		arrayOfNames.put("width", new Argument("width", "the width of the box", Argument.Type.BOOLEAN));
		arrayOfNames.put("height", new Argument("height", "the height of the box", Argument.Type.FLOAT));
		HelpInfoGenerator h = new HelpInfoGenerator();
		String posArgs = h.getPosArgsInfo(arrayOfNames);
		assertEquals(posArgs, "positional arguments: length the length of the box\n" + 
								"width the width of the box\n" +							
								"height the height of the box");
	}
	
	@Test
	public void testCreatePosArgsHelpWithOptArgs()
	{
		arrayOfNames = new LinkedHashMap<String, Argument>();
		arrayOfOptNames = new LinkedHashMap<String, OptionalArgument>();
		arrayOfNames.put("length", new Argument("length", "the length of the box", Argument.Type.INT));
		arrayOfNames.put("width", new Argument("width", "the width of the box", Argument.Type.BOOLEAN));
		arrayOfNames.put("height", new Argument("height", "the height of the box", Argument.Type.FLOAT));
		arrayOfOptNames.put("type", new OptionalArgument("type", "This is the type information help", Argument.Type.STRING));
		arrayOfOptNames.get("type").setRequiredBit();
		arrayOfOptNames.put("option", new OptionalArgument("option", "This is the option information help", Argument.Type.STRING));
		HelpInfoGenerator h = new HelpInfoGenerator();
		String posArgs = h.getPosArgsInfo(arrayOfNames, arrayOfOptNames);
		assertEquals(posArgs, "positional arguments: length the length of the box\n" + 
								"width the width of the box\n" +							
								"height the height of the box\n" +
								"[type] This is the type information help");
	}
	
	@Test
	public void testCompleteHelpInfo()
	{
		String program = "VolumeCalculator";
		String progDesc = "Calculate the volume of a box.";
		arrayOfNames = new LinkedHashMap<String, Argument>();
		arrayOfNames.put("length", new Argument("length", "the length of the box", Argument.Type.INT));
		arrayOfNames.put("width", new Argument("width", "the width of the box", Argument.Type.BOOLEAN));
		arrayOfNames.put("height", new Argument("height", "the height of the box", Argument.Type.FLOAT));
		HelpInfoGenerator h = new HelpInfoGenerator();
		String completeHelp = h.getHelpInfo(arrayOfNames, program, progDesc);
		assertEquals(completeHelp, "usage: java VolumeCalculator length width height\n\n" +
									"Calculate the volume of a box.\n\n" +
									"positional arguments: length the length of the box\n" + 
									"width the width of the box\n" +							
									"height the height of the box");
	}
	
	@Test
	public void testCompleteHelpInfoWithOptArgs()
	{
		String program = "VolumeCalculator";
		String progDesc = "Calculate the volume of a box.";
		arrayOfNames = new LinkedHashMap<String, Argument>();
		arrayOfOptNames = new LinkedHashMap<String, OptionalArgument>();
		arrayOfNames.put("length", new Argument("length", "the length of the box", Argument.Type.INT));
		arrayOfNames.put("width", new Argument("width", "the width of the box", Argument.Type.BOOLEAN));
		arrayOfNames.put("height", new Argument("height", "the height of the box", Argument.Type.FLOAT));
		arrayOfOptNames.put("type", new OptionalArgument("type", "This is the type information help", Argument.Type.STRING));
		arrayOfOptNames.get("type").setRequiredBit();
		arrayOfOptNames.put("option", new OptionalArgument("option", "This is the option information help", Argument.Type.STRING));
		HelpInfoGenerator h = new HelpInfoGenerator();
		String completeHelp = h.getHelpInfo(arrayOfNames, program, progDesc);
		assertEquals(completeHelp, "usage: java VolumeCalculator length width height\n\n" +
									"Calculate the volume of a box.\n\n" +
									"positional arguments: length the length of the box\n" + 
									"width the width of the box\n" +							
									"height the height of the box");
	}*/
	/*
	@Test
	public void testNewAddArgAndValue()
	{
		String myString = "7";
		parser.addArg("length", "Enter a whole number", Argument.Type.INT);
		parser.parse(myString);
		assertEquals(7, parser.getArgValue("length"));
	}
	
	@Test
	public void testNewAddMultipleArgsAndValues()
	{
		String myString = "7 false 2";
		parser.addArg("length", "Enter a whole number as length", Argument.Type.INT);
		parser.addArg("width", "Enter a boolean value for width", Argument.Type.BOOLEAN);
		parser.addArg("height", "Enter a float number as height", Argument.Type.FLOAT);
		parser.parse(myString);
		assertEquals(7, parser.getArgValue("length"));
		assertEquals(false, parser.getArgValue("width"));
		assertEquals(2f, parser.getArgValue("height"));
	}
	
	@Test
	public void addOptVal()
	{
		String myString = "7 --type closet";
		parser.addArg("length", "Enter whole number", Argument.Type.INT);
		parser.addOptArg("type", "t", "Set Type", Argument.Type.STRING);
		parser.parse(myString);
		assertEquals("closet", parser.getArgValue("type"));
	}
	
	@Test
	public void addOptValAfterPositionalVal()
	{
		String myString = "7 --type closet";
		parser.addArg("length", "Enter whole number", Argument.Type.INT);
		parser.addOptArg("type", "t", "Set Type", Argument.Type.STRING);
		parser.parse(myString);
		assertEquals("closet", parser.getArgValue("type"));
	}
	
	@Test 
	public void addOptValBeforePositionalVal()
	{
		String myString = "--type closet 7";
		parser.addOptArg("type", "t", "Set Type", Argument.Type.STRING);
		parser.addArg("length", "Enter whole number", Argument.Type.INT);
		parser.parse(myString);
		assertEquals("closet", parser.getArgValue("type"));
	}
	
	@Test
	public void addOptValBetweenPositionalVals()
	{
		String myString = "7 --type closet 5";
		parser.addOptArg("type", "t", "Set Type", Argument.Type.STRING);
		parser.addArg("length", "Enter whole number", Argument.Type.INT);
		parser.addArg("width", "Enter whole number", Argument.Type.INT);
		parser.parse(myString);
		assertEquals("closet", parser.getArgValue("type"));
	}
	
	@Test
	public void testAddOptValDefaultVal()
	{
		parser.addOptArg("type", "t", "Set Calculation Type", Argument.Type.STRING, "box");
		assertEquals("box", parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameHelpDataType()
	{
		parser.addOptArg("type", "Set Calculation Type", Argument.Type.STRING);
		parser.addOptArgValue("type", "test");
		assertEquals("test", parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameHelpDataTypeStringDefValue()
	{
		parser.addOptArg("type", "Set Calculation Type", Argument.Type.STRING, "box");
		assertEquals("box", parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameHelpDataTypeIntDefValue()
	{
		parser.addOptArg("type", "Set Calculation Type", Argument.Type.STRING, 7);
		assertEquals(7, parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameHelpDataTypeFloatDefValue()
	{
		parser.addOptArg("type", "Set Calculation Type", Argument.Type.STRING, 3.5f);
		assertEquals(3.5f, parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameHelpDataTypeBoolDefValue()
	{
		parser.addOptArg("type", "Set Calculation Type", Argument.Type.STRING, false);
		assertEquals(false, parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameShortHelpDataType()
	{
		parser.addOptArg("type", "t", "Set Calculation Type", Argument.Type.STRING);
		parser.addOptArgValue("type", "test");
		assertEquals("test", parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameShortHelpDataTypeStringDefValue()
	{
		parser.addOptArg("type", "t", "Set Calculation Type", Argument.Type.STRING, "box");
		assertEquals("box", parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameShortHelpDataTypeIntDefValue()
	{
		parser.addOptArg("type", "t", "Set Calculation Type", Argument.Type.STRING, 7);
		assertEquals(7, parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameShortHelpDataTypeFloatDefValue()
	{
		parser.addOptArg("type", "t", "Set Calculation Type", Argument.Type.STRING, 3.5f);
		assertEquals(3.5f, parser.getArgValue("type"));
	}
	@Test
	public void testAddOptArgNameShortHelpDataTypeBoolDefValue()
	{
		parser.addOptArg("type", "t", "Set Calculation Type", Argument.Type.STRING, true);
		assertEquals(true, parser.getArgValue("type"));
	}
	@Test
	public void testAddFlagOptVal()
	{
	String myString = "--save";
	parser.addOptArg("save", "v", "Save Calculations", Argument.Type.FLAG);
	parser.parse(myString);
	assertEquals(true, parser.getArgValue("save"));
	}
	
	@Test
	public void testShortNameOptArg()
	{
		String myString = "7 -t closet 5";
		parser.addOptArg("type", "t", "Set Type", Argument.Type.STRING, "box");
		parser.addArg("length", "Enter whole number", Argument.Type.INT);
		parser.addArg("width", "Enter whole number", Argument.Type.INT);
		parser.parse(myString);
		assertEquals("closet", parser.getArgValue("t"));
	}
	@Test
	public void testFromArgsToString()
	{
		String[] myArray = new String[3];
		myArray[0] = "7";
		myArray[1] = "true";
		myArray[2] = "3.5";
		assertEquals("7 true 3.5 ", parser.fromArgsToString(myArray));
	}

	@Test
	public void testInvalidArgInt()
	{
		try
		{
			String myString = "7 something 2";
			parser.addArg("length", "Enter a float number as length", Argument.Type.FLOAT);
			parser.addArg("width", "Enter a whole number as width", Argument.Type.INT);
			parser.addArg("height", "Enter a float number as height", Argument.Type.FLOAT);
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
			parser.addArg("length", "Enter a whole number as length", Argument.Type.INT);
			parser.addArg("width", "Enter a float number as width", Argument.Type.FLOAT);
			parser.addArg("height", "Enter a whole number as height", Argument.Type.INT);
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
			parser.addArg("length", "Enter a whole number as length", Argument.Type.INT);
			parser.addArg("width", "Enter a float number as width", Argument.Type.BOOLEAN);
			parser.addArg("height", "Enter a whole number as height", Argument.Type.INT);
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
			parser.addArg("length", "Enter a whole number as length", Argument.Type.INT);
			parser.addArg("width", "Enter a float number as width", Argument.Type.FLOAT);
			parser.addArg("height", "Enter a whole number as height", Argument.Type.INT);
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
			parser.addArg("length", "Enter a whole number as length", Argument.Type.INT);
			parser.addArg("width", "Enter a float number as width", Argument.Type.FLOAT);
			parser.addArg("height", "Enter a whole number as height", Argument.Type.INT);
			parser.parse("7 5 2 43");
			assert false;
		}
		catch(TooManyArgsException e)
		{
			assert true;
		}
	}

    */
}