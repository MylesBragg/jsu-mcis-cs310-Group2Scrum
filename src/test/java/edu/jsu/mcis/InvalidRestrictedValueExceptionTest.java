package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class InvalidRestrictedValueExceptionTest
{	
	private String name, helpUsage;
	private InvalidRestrictedValueException irve;
	private List<Object> list;
	
	@Before
	public void StartUp() 
	{
		name = "width";
		helpUsage = "usage: java VolumeCalculator length width height";
		irve = new InvalidRestrictedValueException();
		irve.setProgramName("VolumeCalculator");
		irve.setUsageLine(helpUsage);
	}
	
	@Test
	public void testInvalidRestrictedValueException()
	{
		list = new ArrayList<Object>();
		list.add(7);
		list.add(5);
		list.add(2);
		String currentValue = "something";
		irve.setInvalidValueArgumentInformation(name, Argument.Type.FLOAT);
		irve.setRestrictedValuesList(list);
		irve.setInvalidValue(currentValue);

		String errorString = irve.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: argument width: invalid restricted float value:\n" +
					"available selection set: 7 5 2\n" +
					"provided value: something", 
					errorString);
	}
}