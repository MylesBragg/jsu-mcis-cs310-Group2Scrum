package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class NotEnoughArgumentsExceptionTest
{
	private LinkedHashMap<String, PositionalArgument> arrayOfNames;

	@Test
	public void testNotEnoughArgs()
	{
		
		arrayOfNames = new LinkedHashMap<String, PositionalArgument>();
		arrayOfNames.put("length", new PositionalArgument("length", Argument.Type.INT, 1));
		arrayOfNames.put("width", new PositionalArgument("width", Argument.Type.FLOAT, 2));
		arrayOfNames.put("height", new PositionalArgument("height", Argument.Type.INT, 3));
		arrayOfNames.get("length").setDescription("the length of the box");
		arrayOfNames.get("width").setDescription("the width of the box");
		arrayOfNames.get("height").setDescription("the height of the box");
		String program = "VolumeCalculator";
		int loc = 1;
		String helpUsage = "usage: java VolumeCalculator length width height";
		NotEnoughArgumentsException neae = new NotEnoughArgumentsException(helpUsage, program);
		neae.setArgumentsRequired(arrayOfNames);
		neae.setCurrentIndex(loc);
		String errorString = neae.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: the following arguments are required: width height", 
					errorString);
	}
}