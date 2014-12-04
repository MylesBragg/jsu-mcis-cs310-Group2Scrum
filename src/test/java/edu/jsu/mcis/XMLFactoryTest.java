package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class XMLFactoryTest 
{
	@Test
	public void testParseXML() 
	{
		ArgumentParser p = XMLFactory.loadArgumentParser("Volume Calculator", "\\XMLTest\\xmlTest.xml");
		p.parse("7.5");
		assertEquals(7.5f, p.getArgumentValue("length"));
	}
	
	@Test
	public void testParseXMLNamedArgument() 
	{
		ArgumentParser p = XMLFactory.loadArgumentParser("Volume Calculator", "\\XMLTest\\xmlNamedTest.xml");
		p.parse("7.5 --type box");
		assertEquals("box", p.getArgumentValue("type"));
	}
	
	@Test
	public void testParseXMLShortNamedArgument() 
	{
		ArgumentParser p = XMLFactory.loadArgumentParser("Volume Calculator", "\\XMLTest\\xmlShortNamedTest.xml");
		p.parse("7.5 -t box");
		assertEquals("box", p.getArgumentValue("t"));
	}
	
	@Test
	public void testParseXMLDefaultValueArgument()
	{
		ArgumentParser p = XMLFactory.loadArgumentParser("Volume Calculator", "\\XMLTest\\xmlNamedDefaultValueTest.xml");
		p.parse("7.5");
		assertEquals("box", p.getArgumentValue("type"));
	}
	
	@Test
	public void testParseXMLRequiredArgument() 
	{
		ArgumentParser p = XMLFactory.loadArgumentParser("Volume Calculator", "\\XMLTest\\xmlNamedRequiredTest.xml");
		p.parse("7.5 --type box");
		assertEquals("box", p.getArgumentValue("type"));
	}
	@Test
	public void testSaveArgumentParser()
	{
		ArgumentParser p = new ArgumentParser("Volume Calculator");
		p.addPositionalArgument("length", Argument.Type.INT);
		p.addPositionalArgument("width", Argument.Type.FLOAT);
		p.addPositionalArgument("height", Argument.Type.BOOLEAN);
		p.addPositionalArgument("stuff", Argument.Type.STRING);
		p.addNamedArgument("type", Argument.Type.STRING);
		p.addNamedArgument("word", Argument.Type.BOOLEAN);
		p.addNamedArgument("integer", Argument.Type.INT);
		p.addNamedArgument("float", Argument.Type.FLOAT);
		p.setArgumentDescription("length", "The length of the shape");
		p.setArgumentDescription("width", "The length of the shape");
		p.setArgumentDescription("height", "The length of the shape");
		p.setArgumentDescription("type", "The type of the shape");
		XMLFactory.saveArgumentParser(p);
	}
}