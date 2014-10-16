package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ArgumentErrorHandlerTest
{
	private List<String> arrayOfNames;
	
	@Test
	public void testTooFewArguments()
	{
		ArgumentErrorHandler error = new ArgumentErrorHandler();
		arrayOfNames = new ArrayList<String>();
		arrayOfNames.add("length");
		arrayOfNames.add("width");
		arrayOfNames.add("height");
		String program = "VolumeCalculator";
		int location = 1;
		String errorString = error.buildStringTooFewArguments(arrayOfNames, program, location);
	
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: the following arguments are required: width height", 
					errorString);
	}
	
	//@Test
	public void testTooManyArguments()
	{
		ArgumentErrorHandler error = new ArgumentErrorHandler();
		arrayOfNames = new ArrayList<String>();
		arrayOfNames.add("length");
		arrayOfNames.add("width");
		arrayOfNames.add("height");
		String program = "VolumeCalculator";
		String nextValue = "43";
		Scanner argScanner = new Scanner("99");
		String errorString = error.buildStringTooManyArguments(arrayOfNames, program, nextValue, argScanner);
		
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: unrecognized arguments: 43 99", 
					errorString);
	}
}