package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ArgParserTest 
{
	private ArgParser parser;
	
	private LinkedHashMap<String, Argument> arrayOfNames;
	private LinkedHashMap<String, OptionalArgument> arrayOfOptNames;
	
	@Before
	public void StartUp()
	{
		parser = new ArgParser("VolumeCalculator");
	}
	
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

    @Test
    public void testProgramHelpShortName()
    {
		parser.addProgramHelpInfo("Calculate the volume of a box.");
        parser.addArg("length", "the length of the box", Argument.Type.INT);
		parser.addArg("width", "the width of the box", Argument.Type.FLOAT);
		parser.addArg("height", "the height of the box", Argument.Type.INT);
		parser.addOptArg("type", "the type of shape", Argument.Type.STRING);
		parser.parse("-h");
        assertEquals("usage: java VolumeCalculator length width height\n" + "\n" + "Calculate the volume of a box.\n" +
					"\n" + "positional arguments: length the length of the box\n" + "width the width of the box\n" + 
					"height the height of the box", parser.getHelpString());
    }
	@Test
    public void testProgramHelpFullName()
    {
		parser.addProgramHelpInfo("Calculate the volume of a box.");
        parser.addArg("length", "the length of the box", Argument.Type.INT);
		parser.addArg("width", "the width of the box", Argument.Type.FLOAT);
		parser.addArg("height", "the height of the box", Argument.Type.INT);
		parser.parse("--help");
        assertEquals("usage: java VolumeCalculator length width height\n" + "\n" + "Calculate the volume of a box.\n" +
					"\n" + "positional arguments: length the length of the box\n" + "width the width of the box\n" + 
					"height the height of the box", parser.getHelpString());
    }
	// Formerly HelpInfoGenerator tests
	@Test
	public void testGetHelpInfoWithOptionals()
	{
		String program = "VolumeCalculator";
		parser.addProgramHelpInfo("Calculate the volume of a box.");
		parser.addArg("length", "the length of the box", Argument.Type.INT);
		parser.addArg("width", "the width of the box", Argument.Type.BOOLEAN);
		parser.addArg("height", "the height of the box", Argument.Type.FLOAT);
		parser.addOptArg("type", "the type of shape", Argument.Type.STRING);
		parser.setOptArgRequired("type");
		assertEquals("usage: java VolumeCalculator length width height [type]\n" + 
					"\n" + 
					"Calculate the volume of a box.\n" +
					"\n" + 
					"positional arguments: length the length of the box\n" + 
					"width the width of the box\n" + 
					"height the height of the box\n" +
					"[type] the type of shape", parser.getHelpString());
	}

	public void testSetAndGetRequiredBit()
	{
		String program = "VolumeCalculator";
		parser.addProgramHelpInfo("Calculate the volume of a box.");
		parser.addArg("length", "the length of the box", Argument.Type.INT);
		parser.addArg("width", "the width of the box", Argument.Type.BOOLEAN);
		parser.addArg("height", "the height of the box", Argument.Type.FLOAT);
		parser.addOptArg("type", "t", "the type of shape", Argument.Type.STRING);
		parser.addOptArg("pet", "p", "my companion, he is irrelevant", Argument.Type.STRING);
		parser.setOptArgRequired("type");
		parser.setOptArgRequired("p");
		assertEquals("usage: java VolumeCalculator length width height [type]\n" + 
					"\n" + 
					"Calculate the volume of a box.\n" +
					"\n" + 
					"positional arguments: length the length of the box\n" + 
					"width the width of the box\n" + 
					"height the height of the box\n" +
					"[type] the type of shape\n" +
					"[pet] my companion, he is irrelevant", parser.getHelpString());
	}

/*	@Test
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
}