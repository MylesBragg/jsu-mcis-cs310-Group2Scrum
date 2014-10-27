package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class InvalidValueExceptionTest
{
	private List<String> arrayOfNames;
	
	@Test
	public void testInvalidValueException()
	{
		arrayOfNames = new ArrayList<String>();
		arrayOfNames.add("length");
		arrayOfNames.add("width");
		arrayOfNames.add("height");
		String program = "VolumeCalculator";
		String argName = "width";
		String argDataType = "float";
		String currentValue = "something";
		HelpInfoGenerator h = new HelpInfoGenerator();
		String helpUsage = h.getUsageLine(arrayOfNames, program);
		InvalidValueException ive = new InvalidValueException(helpUsage, program, argName, argDataType, currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid float value: something", 
					errorString);
	}
}