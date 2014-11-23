package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class InvalidValueExceptionTest
{	
	private String name, helpUsage;
	private InvalidValueException ive;
	
	@Before
	public void StartUp() 
	{
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
		ive.setInvalidValueArgumentInformation(name, Argument.Type.FLOAT);
		ive.setInvalidValue(currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid float value: something", 
					errorString);
	}
	
	@Test
	public void testInvalidIntegerValueException()
	{
		String currentValue = "something";
		ive.setInvalidValueArgumentInformation(name, Argument.Type.INT);
		ive.setInvalidValue(currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid integer value: something", 
					errorString);
	}
	
	@Test
	public void testInvalidBooleanValueException()
	{
		String currentValue = "something";
		ive.setInvalidValueArgumentInformation(name, Argument.Type.BOOLEAN);
		ive.setInvalidValue(currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid boolean value: something", 
					errorString);
	}
	
	@Test
	public void testInvalidStringValueException()
	{
		int currentValue = 7;
		ive.setInvalidValueArgumentInformation(name, Argument.Type.STRING);
		ive.setInvalidValue(currentValue);
		String errorString = ive.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid string value: 7", 
					errorString);
	}
}