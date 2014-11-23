package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TooManyArgumentsExceptionTest
{
	@Test
	public void testTooManyArguments()
	{
		String program = "VolumeCalculator";
		String nextValue = "43";
		Scanner argScanner = new Scanner("99");
		String helpUsage = "usage: java VolumeCalculator length width height";
		TooManyArgumentsException tmae = new TooManyArgumentsException(helpUsage, program);
		tmae.setNextValue(nextValue);
		tmae.setArgumentScanner(argScanner);
		String errorString = tmae.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: unrecognized arguments: 43 99", 
					errorString);
	}
}