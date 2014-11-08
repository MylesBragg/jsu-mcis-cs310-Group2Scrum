package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class InvalidValueExceptionTest
{
	@Test
	public void testInvalidFloatValueException()
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
	@Test
	public void testInvalidIntegerValueException()
	{
		String program = "VolumeCalculator";
		String argName = "width";
		String currentValue = "something";
		String helpUsage = "usage: java VolumeCalculator length width height";
		InvalidValueException ive = new InvalidValueException(helpUsage, program, argName, Argument.Type.INT, currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid integer value: something", 
					errorString);
		assertEquals(helpUsage, ive.getUsage());
		assertEquals(argName, ive.getName());
		assertEquals("integer", ive.getType());
		assertEquals(currentValue, ive.getCurrentValue());
	}
	@Test
	public void testInvalidBooleanValueException()
	{
		String program = "VolumeCalculator";
		String argName = "width";
		String currentValue = "something";
		String helpUsage = "usage: java VolumeCalculator length width height";
		InvalidValueException ive = new InvalidValueException(helpUsage, program, argName, Argument.Type.BOOLEAN, currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid boolean value: something", 
					errorString);
		assertEquals(helpUsage, ive.getUsage());
		assertEquals(argName, ive.getName());
		assertEquals("boolean", ive.getType());
		assertEquals(currentValue, ive.getCurrentValue());
	}
	@Test
	public void testInvalidStringValueException()
	{
		String program = "VolumeCalculator";
		String argName = "width";
		int currentValue = 7;
		String helpUsage = "usage: java VolumeCalculator length width height";
		InvalidValueException ive = new InvalidValueException(helpUsage, program, argName, Argument.Type.STRING, currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid string value: 7", 
					errorString);
		assertEquals(helpUsage, ive.getUsage());
		assertEquals(argName, ive.getName());
		assertEquals("string", ive.getType());
		assertEquals(currentValue, ive.getCurrentValue());
	}
	
}