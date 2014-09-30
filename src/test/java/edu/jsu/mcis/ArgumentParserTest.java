package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest 
{
	private ArgumentParser parser;
	
	
	@Before
	public void testStartNewParse()
	{
		parser = new ArgumentParser();
	}
	
	@Test
	public void testArgumentInputs()
	{
		boolean success = parser.addArguments("MyProg 7 6 9");
		assertTrue(success);
		assertEquals("MyProg", parser.getProgramName());
		assertEquals(7, parser.getLength());
		assertEquals(6, parser.getWidth());
		assertEquals(9, parser.getHeighth());
	}
	
/*	@Test
	public void testReadInAString()
	{
		String testString = parser.readInString("This works!");
		assertEquals("This works!", testString);
	}
	
	@Test
	public void testParseProgName()
	{
		String dontCare = parser.readInString("MyProg 7 6 9");
		String progName = parser.getProgramName();
		assertEquals("MyProg", progName);
	}*/
}