package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TooManyArgsExceptionTest
{
	private LinkedHashMap<String, ArgValues> arrayOfNames;
	
	@Test
	public void testTooManyArgs()
	{
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.FLOAT));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.INT));
		String program = "VolumeCalculator";
		String nextValue = "43";
		Scanner argScanner = new Scanner("99");
		HelpInfoGenerator h = new HelpInfoGenerator();
		String helpUsage = h.getUsageLine(arrayOfNames, program);
		TooManyArgsException tmae = new TooManyArgsException(helpUsage, program, nextValue, argScanner);
		String errorString = tmae.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: unrecognized arguments: 43 99", 
					errorString);
	}
}