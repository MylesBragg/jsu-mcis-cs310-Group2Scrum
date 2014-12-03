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
	/*
	@Test
	public void testSetProgramDescription() 
	{
		parser.setProgramDescription("Calculate the volume of a shape");
		assertEquals("Calculate the volume of a shape", parser.getProgramDescription());
	}
	
	@Test
	public void testAddNewPositionalArgument() 
	{
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		assertEquals(1, parser.getPositionId("length"));
	}
	
	@Test
	public void testAddMultiplePositionalArguments() 
	{
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		assertEquals(1, parser.getPositionId("length"));
		assertEquals(2, parser.getPositionId("width"));
	}
	
	@Test
	public void testSetPositionalArgumentDescription() 
	{
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.setArgumentDescription("length", "The length of the shape");
		assertEquals("The length of the shape", parser.getArgumentDescription("length"));
	}
	
	@Test
	public void testSetMultiplePositionalArugmentDescriptions() 
	{
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.setArgumentDescription("length", "The length of the shape");
		parser.setArgumentDescription("width", "The width of the shape");
		assertEquals("The length of the shape", parser.getArgumentDescription("length"));
		assertEquals("The width of the shape", parser.getArgumentDescription("width"));
	}
	
	@Test
	public void testSetNamedArgumentWithDescription() 
	{
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setArgumentDescription("type", "The type of shape");
		assertEquals("The type of shape", parser.getArgumentDescription("type"));
	}
	
	@Test
	public void testSetNamedArgumentAlternateName() 
	{
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setNamedArgumentAlternateName("type", "t");
		assertEquals("t", parser.getNamedArgumentAlternateName("type"));
	}
	
	@Test
	public void testSetSomeNamedArgumentAlternateName() 
	{
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
	public void testSetNamedArgumentDefaultValue() 
	{
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setNamedArgumentDefaultValue("type", "box");
		assertEquals("box", parser.getNamedArgumentDefaultValue("type"));
	}
	
	@Test
	public void testSetNamedArgumentsDefaultValuesAllType() 
	{
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
	
	@Test(expected=InvalidValueException.class)
	public void testSetNamedArgumenDefaultValueInvalid() 
	{
		parser.addNamedArgument("rainy", Argument.Type.BOOLEAN);
		parser.setNamedArgumentDefaultValue("rainy", "yes");
	}
	
	@Test(expected=InvalidValueException.class)
	public void testSetNamedArgumentDefaultValueInvalidMiddleValue() 
	{
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.addNamedArgument("rainy", Argument.Type.BOOLEAN);
		parser.addNamedArgument("const", Argument.Type.INT);
		parser.setNamedArgumentDefaultValue("type", "box");
		parser.setNamedArgumentDefaultValue("rainy", "yes");
		parser.setNamedArgumentDefaultValue("const", 33);
	}
	
	@Test
	public void testSetNamedArgumentRequired() 
	{
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setNamedArgumentRequired("type");
		assertEquals(true, parser.getNamedArgumentRequired("type"));
	}
	
	@Test
	public void testSetSomeNamedArgumentsRequired() 
	{
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
	public void testAddPositionalArgumentValues() 
	{
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
	public void testAddPositionalArgumentValuesAllTypes()
	{
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
	public void testParseStringArray() 
	{
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
	public void testAddPositionalAndNamedArgumentValues() 
	{
		String argumentsToParse = "7.2 --type box";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.parse(argumentsToParse);
		assertEquals(7.2f, parser.getArgumentValue("length"));
		assertEquals("box", parser.getArgumentValue("type"));
	}
	
	@Test(expected=InvalidValueException.class)
	public void testSetPositionalArgumentValueFail() 
	{
		String argumentsToParse = "7.2 something 7";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("rainy", Argument.Type.BOOLEAN);
		parser.addPositionalArgument("const", Argument.Type.INT);
		parser.parse(argumentsToParse);
	}
	
	@Test(expected=InvalidValueException.class)
	public void testSetNamedArgumentValueFail() 
	{
		String argumentsToParse = "7.2 --bathCount something 7";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addNamedArgument("bathCount", Argument.Type.INT);
		parser.addPositionalArgument("const", Argument.Type.INT);
		parser.parse(argumentsToParse);
	}
	
	@Test
	public void testSetDescriptionInvalidArgument() 
	{
		parser.setArgumentDescription("length", "The length of the shape");
	}
	
	@Test
	public void testGetNamedArgumentValueByShortName() 
	{
		String argumentsToParse = "7.2 --bathCount 2 7";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addNamedArgument("bathCount", Argument.Type.INT);
		parser.addPositionalArgument("const", Argument.Type.INT);
		parser.setNamedArgumentAlternateName("bathCount", "b");
		parser.parse(argumentsToParse);
		assertEquals(2, parser.getArgumentValue("b"));
	}
	
	@Test
	public void testGetArgumentValueFail()
	{
		String argumentsToParse = "7.2 --type box 7";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.addPositionalArgument("const", Argument.Type.INT);
		parser.parse(argumentsToParse);
		assertEquals("Error key not found", parser.getArgumentValue("bathCount"));
	}
	
	@Test(expected=InvalidValueException.class)
	public void testWrongNamedArgumentParse() 
	{
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.INT);
		parser.parse("7 --type box 5");
	}
	
	@Test
	public void testSetNamedArgumentValueByShortName() 
	{
		String argumentsToParse = "7.2 -b 2 7";
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addNamedArgument("bathCount", Argument.Type.INT);
		parser.addPositionalArgument("const", Argument.Type.INT);
		parser.setNamedArgumentAlternateName("bathCount", "b");
		parser.parse(argumentsToParse);
		assertEquals(2, parser.getArgumentValue("b"));
	}
	
	@Test
	public void testSetNamedArgumentDescriptionByShortName()
	{
		parser.addNamedArgument("bathCount", Argument.Type.INT);
		parser.setNamedArgumentAlternateName("bathCount", "b");
		parser.setArgumentDescription("b", "How many bathrooms are in the house");
		assertEquals("How many bathrooms are in the house", parser.getArgumentDescription("b"));
	}
	
	@Test
	public void testSetArgumentDescriptionFail()
	{
		assertEquals("", parser.getArgumentDescription("b"));
	}
	
	@Test
	public void testSetNamedArgumentDefaultValueByShortName() 
	{
		parser.addNamedArgument("bathCount", Argument.Type.INT);
		parser.setNamedArgumentAlternateName("bathCount", "b");
		parser.setNamedArgumentDefaultValue("b", 2);
		assertEquals(2, parser.getNamedArgumentDefaultValue("b"));
	}
	
	@Test(expected=InvalidValueException.class)
	public void testSetNamedArgumentValueByShortNameFail() 
	{
		String argumentsToParse = "-b something";
		parser.addNamedArgument("bathCount", Argument.Type.INT);
		parser.setNamedArgumentAlternateName("bathCount", "b");
		parser.parse(argumentsToParse);
	}
	
	@Test(expected=InvalidValueException.class)
	public void testInvalidValueExceptionWithRequiredNamedArguments() 
	{
		String argumentsToParse = "something --bathCount 5";
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addNamedArgument("bathCount", Argument.Type.INT);
		parser.setNamedArgumentRequired("bathCount");
		parser.parse(argumentsToParse);
	}
	
	@Test
	public void testSetNamedArgumentRequiredByShortName() {
		parser.addNamedArgument("bathCount", Argument.Type.INT);
		parser.setNamedArgumentAlternateName("bathCount", "b");
		parser.setNamedArgumentRequired("b");
		assertEquals(true, parser.getNamedArgumentRequired("b"));
	}
	
	@Test
	public void testSetNamedFlaggedArguments() 
	{
		String argumentsToParse = "--attic -b";
		parser.addNamedArgument("basement", Argument.Type.BOOLEAN);
		parser.addNamedArgument("attic", Argument.Type.BOOLEAN);
		parser.setNamedArgumentAlternateName("basement", "b");
		parser.parse(argumentsToParse);
		assertEquals(true, parser.getArgumentValue("basement"));
		assertEquals(true, parser.getArgumentValue("attic"));
		
	}
	
	@Test(expected=TooManyArgumentsException.class)
	public void testTooManyArguments()
	{
		String argumentsToParse = "7 5 2 3";
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.addPositionalArgument("height", Argument.Type.INT);
		parser.parse(argumentsToParse);
	}
	
	@Test(expected=NotEnoughArgumentsException.class)
	public void testNotEnoughArguments() 
	{
		String argumentsToParse = "7 5";
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.addPositionalArgument("height", Argument.Type.INT);
		parser.parse(argumentsToParse);
	}
	*/
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
	
	@Test
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
		assertEquals("usage: java VolumeCalculator length width height [--type] [--pet]\n" + 
					"\n" + 
					"Calculate the volume of a box.\n" +
					"\n" + 
					"positional arguments: length the length of the box\n" + 
					"width the width of the box\n" + 
					"height the height of the box\n" +
					"[--type] the type of shape\n" +
					"[--pet] my companion, he is irrelevant", parser.getHelpString());
	}
	
	@Test 
	public void testAppendRestrictedValues()
	{
		List<Object> temp = new ArrayList<>();
		temp.add(7);
		temp.add(3);
		temp.add(6);
	
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.BOOLEAN);
		parser.addPositionalArgument("height", Argument.Type.FLOAT);
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.addNamedArgument("pet", Argument.Type.STRING);
		parser.appendRestrictedValues("length",temp);
		assertEquals(parser.getArgumentValues("length"), temp);
	
	}
}