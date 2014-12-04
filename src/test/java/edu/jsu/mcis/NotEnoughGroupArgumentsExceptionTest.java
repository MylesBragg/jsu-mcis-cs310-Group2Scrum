package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class NotEnoughGroupArgumentsExceptionTest
{	
	private LinkedHashMap<String, NamedArgumentGroup> arrayOfNames;
	private String name, helpUsage, currentArgument;
	private NotEnoughGroupArgumentsException negae;
	
	@Before
	public void StartUp() 
	{
		name = "VolumeCalculator";
		helpUsage = "usage: java VolumeCalculator length width height";
		negae = new NotEnoughGroupArgumentsException(helpUsage, name);
	}
	
	@Test
	public void testInvalidGroupMemberException()
	{
		arrayOfNames = new LinkedHashMap<String, NamedArgumentGroup>();
		arrayOfNames.put("length", new NamedArgumentGroup("length"));
		arrayOfNames.get("length").appendGroupMember("arg1");

		negae.setCurrentGroupHeader("length");
		negae.setArgumentsRequired(arrayOfNames);
		negae.setCurrentIndex(0);
		
		String errorString = negae.toString();
		assertEquals("usage: java VolumeCalculator length width height\n" +
					"VolumeCalculator.java: error: the following group arguments from, length group, are required: arg1", 
					errorString);
	}
}