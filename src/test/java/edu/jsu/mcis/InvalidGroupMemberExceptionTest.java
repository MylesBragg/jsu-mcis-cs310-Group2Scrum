package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class InvalidGroupMemberExceptionTest
{	
	private String name, helpUsage, currentArgument;
	private InvalidGroupMemberException igme;
	
	@Before
	public void StartUp() 
	{
		helpUsage = "usage: java VolumeCalculator length width height";
		igme = new InvalidGroupMemberException();
	}
	
	@Test
	public void testInvalidGroupMemberException()
	{
		String currentValue = "something";
		igme.setProgramName("VolumeCalculator");
		igme.setUsageLine(helpUsage);
		igme.setCurrentArgument(currentValue);
		String errorString = igme.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: Invalid group argument: something", 
					errorString);
	}
}