package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class HelpInfoGeneratorTest
{
	private List<String> arrayOfNames;
	
	@Test
	public void testgetUsageLine()
	{
		String program = "VolumeCalculator";
		arrayOfNames = new ArrayList<String>();
		arrayOfNames.add("length");
		arrayOfNames.add("width");
		arrayOfNames.add("height");
		HelpInfoGenerator h = new HelpInfoGenerator();
		String usage = h.getUsageLine(arrayOfNames, program);
		assertEquals(usage, "usage: java VolumeCalculator length width height");
	}
	
	@Test
	public void testCreatePosArgsHelp()
	{
		arrayOfNames = new ArrayList<String>();
		arrayOfNames.add("length");
		arrayOfNames.add("width");
		arrayOfNames.add("height");
		ArgValues allArgVals = new ArgValues();
		allArgVals.addHelpArg("length", "the length of the box");
		allArgVals.addHelpArg("width", "the width of the box");
		allArgVals.addHelpArg("height", "the height of the box");
		HelpInfoGenerator h = new HelpInfoGenerator();
		String posArgs = h.getPosArgsInfo(arrayOfNames, allArgVals);
		assertEquals(posArgs, "positional arguments: length the length of the box\n" + 
								"width the width of the box\n" +							
								"height the height of the box");
	}
	
	@Test
	public void testCompleteHelpInfo()
	{
		String program = "VolumeCalculator";
		String progDesc = "Calculate the volume of a box.";
		arrayOfNames = new ArrayList<String>();
		arrayOfNames.add("length");
		arrayOfNames.add("width");
		arrayOfNames.add("height");
		ArgValues allArgVals = new ArgValues();
		allArgVals.addHelpArg("length", "the length of the box");
		allArgVals.addHelpArg("width", "the width of the box");
		allArgVals.addHelpArg("height", "the height of the box");
		HelpInfoGenerator h = new HelpInfoGenerator();
		String completeHelp = h.getHelpInfo(arrayOfNames, program, allArgVals, progDesc);
		assertEquals(completeHelp, "usage: java VolumeCalculator length width height\n\n" +
									"Calculate the volume of a box.\n\n" +
									"positional arguments: length the length of the box\n" + 
									"width the width of the box\n" +							
									"height the height of the box");
	}
}