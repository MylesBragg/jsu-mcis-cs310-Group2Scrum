package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class NotEnoughArgsExceptionTest
{
	private LinkedHashMap<String, ArgValues> arrayOfNames;
	
	@Test
	public void testNotEnoughArgs()
	{
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.FLOAT));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.INT));
		String program = "VolumeCalculator";
		int loc = 1;
		HelpInfoGenerator h = new HelpInfoGenerator();
		String helpUsage = h.getUsageLine(arrayOfNames, program);
		NotEnoughArgsException neae = new NotEnoughArgsException(helpUsage, program, arrayOfNames, loc);
		String errorString = neae.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: the following arguments are required: width height", 
					errorString);
	}
	@Test
	public void testGetters()
	{
		
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.FLOAT));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.INT));
		String program = "VolumeCalculator";
		int loc = 1;
		HelpInfoGenerator h = new HelpInfoGenerator();
		String helpUsage = h.getUsageLine(arrayOfNames, program);
		NotEnoughArgsException neae = new NotEnoughArgsException(helpUsage, program, arrayOfNames, loc);
		assertEquals("usage: java VolumeCalculator length width height", neae.getUsage());
		List<String> sentList;
		sentList = new ArrayList<String>();
		sentList.add("length");
		sentList.add("width");
		sentList.add("height");
		assertEquals(sentList, neae.getNamesArray());
		assertEquals(1, neae.getIndex());
	}
}