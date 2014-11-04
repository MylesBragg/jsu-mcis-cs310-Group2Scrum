package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class HelpInfoGeneratorTest
{
	private LinkedHashMap<String, ArgValues> arrayOfNames;
	private LinkedHashMap<String, OptArgValues> arrayOfOptNames;
	
	@Test
	public void testgetUsageLine()
	{
		String program = "VolumeCalculator";
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfOptNames = new LinkedHashMap<String, OptArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.BOOLEAN));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.FLOAT));
		HelpInfoGenerator h = new HelpInfoGenerator();
		String usage = h.getUsageLine(arrayOfNames, program);
		assertEquals(usage, "usage: java VolumeCalculator length width height");
	}

	@Test
	public void testgetUsageLineWithOptArgs()
	{
		String program = "VolumeCalculator";
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfOptNames = new LinkedHashMap<String, OptArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.BOOLEAN));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.FLOAT));
		arrayOfOptNames.put("type", new OptArgValues("type", "This is the type information help", ArgValues.Type.STRING));
		arrayOfOptNames.get("type").setRequiredBit();
		HelpInfoGenerator h = new HelpInfoGenerator();
		String usage = h.getUsageLine(arrayOfNames, arrayOfOptNames, program);
		assertEquals(usage, "usage: java VolumeCalculator length width height [type]");
	}
	
	@Test
	public void testCreatePosArgsHelp()
	{
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.BOOLEAN));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.FLOAT));
		HelpInfoGenerator h = new HelpInfoGenerator();
		String posArgs = h.getPosArgsInfo(arrayOfNames);
		assertEquals(posArgs, "positional arguments: length the length of the box\n" + 
								"width the width of the box\n" +							
								"height the height of the box");
	}
	
	@Test
	public void testCreatePosArgsHelpWithOptArgs()
	{
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfOptNames = new LinkedHashMap<String, OptArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.BOOLEAN));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.FLOAT));
		arrayOfOptNames.put("type", new OptArgValues("type", "This is the type information help", ArgValues.Type.STRING));
		arrayOfOptNames.get("type").setRequiredBit();
		arrayOfOptNames.put("option", new OptArgValues("option", "This is the option information help", ArgValues.Type.STRING));
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
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.BOOLEAN));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.FLOAT));
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
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfOptNames = new LinkedHashMap<String, OptArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.BOOLEAN));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.FLOAT));
		arrayOfOptNames.put("type", new OptArgValues("type", "This is the type information help", ArgValues.Type.STRING));
		arrayOfOptNames.get("type").setRequiredBit();
		arrayOfOptNames.put("option", new OptArgValues("option", "This is the option information help", ArgValues.Type.STRING));
		HelpInfoGenerator h = new HelpInfoGenerator();
		String completeHelp = h.getHelpInfo(arrayOfNames, program, progDesc);
		assertEquals(completeHelp, "usage: java VolumeCalculator length width height\n\n" +
									"Calculate the volume of a box.\n\n" +
									"positional arguments: length the length of the box\n" + 
									"width the width of the box\n" +							
									"height the height of the box");
	}
}