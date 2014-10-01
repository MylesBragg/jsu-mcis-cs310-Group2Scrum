package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest 
{
	private ArgumentParser parser;
	
	@Before
	public void StartUp()
	{
		parser = new ArgumentParser();
	}
	
	@Test
	public void testAddArgument()
	{
		parser.addArg("length");
		assertEquals(1, parser.getNumArguments());	
	}
	
	@Test
	public void testSingleArgumentParse()
	{
		parser.addArg("length");
		parser.parse("Calculator 4");
		assertEquals("4", parser.getArgumentValue("length"));
	}
	
	@Test
	public void testDoubleArgumentParse()
	{
		parser.addArg("length");
		parser.addArg("width");
		parser.parse("Calculator 4 7");
		assertEquals("4", parser.getArgumentValue("length"));
		assertEquals("7", parser.getArgumentValue("width"));
	}
	
	@Test
	public void testMultipleArgumentParse()
	{
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		parser.parse("Calculator 4 7 9");
		assertEquals("4", parser.getArgumentValue("length"));
		assertEquals("7", parser.getArgumentValue("width"));
		assertEquals("9", parser.getArgumentValue("height"));
	}
	
	@Test
	public void testInvalidArgumentNameGiven()
	{
		parser.addArg("length");
		parser.parse("Calculator 4");
		assertEquals("Invalid Argument Name", parser.getArgumentValue("width"));
	}
	
	@Test
	public void testTooManyArguments()
	{
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		assertEquals("Unrecognized argument 5", parser.parse("Calculator 4 7 9 5"));
	}
	
	@Test
	public void testTooFewArguments()
	{
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		assertEquals("height missing", parser.parse("Calculator 4 7"));
	}
	
	@Test
	public void testPetNumberRainyBathrooms()
	{
		parser.addArg("pet");
		parser.addArg("number");
		parser.addArg("rainy");
		parser.addArg("bathrooms");
		parser.parse("Program dog 2 true 3.5");
		assertEquals("dog", parser.getArgumentValue("pet"));
		assertEquals("2", parser.getArgumentValue("number"));
		assertEquals("true", parser.getArgumentValue("rainy"));
		assertEquals("3.5", parser.getArgumentValue("bathrooms"));
	}
}