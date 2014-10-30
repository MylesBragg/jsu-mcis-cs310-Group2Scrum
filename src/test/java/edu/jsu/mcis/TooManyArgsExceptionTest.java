package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TooManyArgsExceptionTest
{
	private List<String> arrayOfNames;
/*	
	@Test
	public void testTooManyArgs()
	{
		arrayOfNames = new ArrayList<String>();
		arrayOfNames.add("length");
		arrayOfNames.add("width");
		arrayOfNames.add("height");
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
	}*/
}