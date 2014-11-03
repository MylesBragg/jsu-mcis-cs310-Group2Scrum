package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class InvalidValueExceptionTest
{
	private LinkedHashMap<String, ArgValues> arrayOfNames;
	
	@Test
	public void testInvalidValueException()
	{
		arrayOfNames = new LinkedHashMap<String, ArgValues>();
		arrayOfNames.put("length", new ArgValues("length", "the length of the box", ArgValues.Type.INT));
		arrayOfNames.put("width", new ArgValues("width", "the width of the box", ArgValues.Type.FLOAT));
		arrayOfNames.put("height", new ArgValues("height", "the height of the box", ArgValues.Type.INT));
		String program = "VolumeCalculator";
		String argName = "width";
		String argDataType = "float";
		String currentValue = "something";
		HelpInfoGenerator h = new HelpInfoGenerator();
		String helpUsage = h.getUsageLine(arrayOfNames, program);
		InvalidValueException ive = new InvalidValueException(helpUsage, program, argName, ArgValues.Type.FLOAT, currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid float value: something", 
					errorString);
	}
}