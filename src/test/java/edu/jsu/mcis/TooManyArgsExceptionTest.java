package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TooManyArgsExceptionTest
{
	@Test
	public void testTooManyArgs()
	{
		String program = "VolumeCalculator";
		String nextValue = "43";
		Scanner argScanner = new Scanner("99");
		String helpUsage = "usage: java VolumeCalculator length width height";
		TooManyArgsException tmae = new TooManyArgsException(helpUsage, program, nextValue, argScanner);
		String errorString = tmae.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: unrecognized arguments: 43 99", 
					errorString);
	}
	@Test
	public void testGetters()
	{
		String program = "VolumeCalculator";
		String nextValue = "43";
		Scanner argScanner = new Scanner("99");
		String helpUsage = "usage: java VolumeCalculator length width height";
		TooManyArgsException tmae = new TooManyArgsException(helpUsage, program, nextValue, argScanner);
		assertEquals("usage: java VolumeCalculator length width height", tmae.getUsage());
		assertEquals(nextValue, tmae.getNextValue());
		assertEquals(argScanner, tmae.getArgs());
	}
}