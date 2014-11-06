package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class NotEnoughArgsExceptionTest
{
	private LinkedHashMap<String, Argument> arrayOfNames;
	
	@Test
	public void testNotEnoughArgs()
	{
		arrayOfNames = new LinkedHashMap<String, Argument>();
		arrayOfNames.put("length", new Argument("length", "the length of the box", Argument.Type.INT));
		arrayOfNames.put("width", new Argument("width", "the width of the box", Argument.Type.FLOAT));
		arrayOfNames.put("height", new Argument("height", "the height of the box", Argument.Type.INT));
		String program = "VolumeCalculator";
		int loc = 1;
		String helpUsage = "usage: java VolumeCalculator length width height";
		NotEnoughArgsException neae = new NotEnoughArgsException(helpUsage, program, arrayOfNames, loc);
		String errorString = neae.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: the following arguments are required: width height", 
					errorString);
	}
	@Test
	public void testGetters()
	{
		
		arrayOfNames = new LinkedHashMap<String, Argument>();
		arrayOfNames.put("length", new Argument("length", "the length of the box", Argument.Type.INT));
		arrayOfNames.put("width", new Argument("width", "the width of the box", Argument.Type.FLOAT));
		arrayOfNames.put("height", new Argument("height", "the height of the box", Argument.Type.INT));
		String program = "VolumeCalculator";
		int loc = 1;
		String helpUsage = "usage: java VolumeCalculator length width height";
		NotEnoughArgsException neae = new NotEnoughArgsException(helpUsage, program, arrayOfNames, loc);
		assertEquals("usage: java VolumeCalculator length width height", neae.getUsage());
		List<String> sentList;
		sentList = new ArrayList<String>();
		sentList.add("length");
		sentList.add("width");
		sentList.add("height");
		assertEquals(sentList, neae.getNamesArray());
		assertEquals(1, neae.getIndex());
	}
}