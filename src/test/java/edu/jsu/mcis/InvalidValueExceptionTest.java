package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class InvalidValueExceptionTest
{
	@Test
	public void testInvalidValueException()
	{
		String program = "VolumeCalculator";
		String argName = "width";
		String currentValue = "something";
		String helpUsage = "usage: java VolumeCalculator length width height";
		InvalidValueException ive = new InvalidValueException(helpUsage, program, argName, Argument.Type.FLOAT, currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid float value: something", 
					errorString);
		assertEquals(helpUsage, ive.getUsage());
		assertEquals(argName, ive.getName());
		assertEquals("float", ive.getType());
		assertEquals(currentValue, ive.getCurrentValue());
	}
}