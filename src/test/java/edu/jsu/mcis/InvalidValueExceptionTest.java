package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class InvalidValueExceptionTest
{	
	private String name, helpUsage;
	private InvalidValueException ive;
	
	@Before
	public void StartUp() {
		name = "width";
		helpUsage = "usage: java VolumeCalculator length width height";
		ive = new InvalidValueException();
		ive.setProgramName("VolumeCalculator");
		ive.setUsageLine(helpUsage);
	}
	@Test
	public void testInvalidFloatValueException()
	{
		String currentValue = "something";
		ive.setInvalidValueInformation(name, currentValue, Argument.Type.FLOAT);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid float value: something", 
					errorString);
		assertEquals(helpUsage, ive.getUsage());
		assertEquals(name, ive.getName());
		assertEquals("float", ive.getType());
		assertEquals(currentValue, ive.getCurrentValue());
	}
	@Test
	public void testInvalidIntegerValueException()
	{
		String currentValue = "something";
		ive.setInvalidValueInformation(name, currentValue, Argument.Type.INT);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid integer value: something", 
					errorString);
		assertEquals(helpUsage, ive.getUsage());
		assertEquals(name, ive.getName());
		assertEquals("integer", ive.getType());
		assertEquals(currentValue, ive.getCurrentValue());
	}
	@Test
	public void testInvalidBooleanValueException()
	{
		String currentValue = "something";
		ive.setInvalidValueInformation(name, currentValue, Argument.Type.BOOLEAN);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid boolean value: something", 
					errorString);
		assertEquals(helpUsage, ive.getUsage());
		assertEquals(name, ive.getName());
		assertEquals("boolean", ive.getType());
		assertEquals(currentValue, ive.getCurrentValue());
	}
	@Test
	public void testInvalidStringValueException()
	{
		int currentValue = 7;
		ive.setInvalidValueInformation(name, currentValue, Argument.Type.STRING);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid string value: 7", 
					errorString);
		assertEquals(helpUsage, ive.getUsage());
		assertEquals(name, ive.getName());
		assertEquals("string", ive.getType());
		assertEquals(currentValue, ive.getCurrentValue());
	}
	
}