package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class NotEnoughArgsExceptionTest
{
	private List<String> arrayOfNames;
/*	
	@Test
	public void testNotEnoughArgs()
	{
		arrayOfNames = new ArrayList<String>();
		arrayOfNames.add("length");
		arrayOfNames.add("width");
		arrayOfNames.add("height");
		String program = "VolumeCalculator";
		int loc = 1;
		HelpInfoGenerator h = new HelpInfoGenerator();
		String helpUsage = h.getUsageLine(arrayOfNames, program);
		NotEnoughArgsException neae = new NotEnoughArgsException(helpUsage, program, arrayOfNames, loc);
		String errorString = neae.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: the following arguments are required: width height", 
					errorString);
	}*/
}