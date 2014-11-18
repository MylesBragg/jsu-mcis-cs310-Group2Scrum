package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class XMLFactoryTest {
	@Test
	public void testParseXML() {
		ArgumentParser p = XMLFactory.createArgumentParser("Volume Calculator", "\\XMLTest\\xmlTest.xml");
		p.parse("7.5");
		assertEquals(7.5f, p.getArgumentValue("length"));
	}
	@Test
	public void testParseXMLNamedArgument() {
		ArgumentParser p = XMLFactory.createArgumentParser("Volume Calculator", "\\XMLTest\\xmlNamedTest.xml");
		p.parse("7.5 --type box");
		assertEquals("box", p.getArgumentValue("type"));
	}
	@Test
	public void testParseXMLShortNamedArgument() {
		ArgumentParser p = XMLFactory.createArgumentParser("Volume Calculator", "\\XMLTest\\xmlShortNamedTest.xml");
		p.parse("7.5 -t box");
		assertEquals("box", p.getArgumentValue("t"));
	}
	@Test
	public void testParseXMLDefaultValueArgument() {
		ArgumentParser p = XMLFactory.createArgumentParser("Volume Calculator", "\\XMLTest\\xmlNamedDefaultValueTest.xml");
		p.parse("7.5");
		assertEquals("box", p.getArgumentValue("type"));
	}
	@Test
	public void testParseXMLRequiredArgument() {
		ArgumentParser p = XMLFactory.createArgumentParser("Volume Calculator", "\\XMLTest\\xmlNamedRequiredTest.xml");
		p.parse("7.5 --type box");
		assertEquals("box", p.getArgumentValue("type"));
	}
	@Test
	public void testGetArgumentNames() 
	{
		ArgumentParser p = new ArgumentParser("Volume Calculator");
		p.addPositionalArgument("length", Argument.Type.FLOAT);
		p.addPositionalArgument("width", Argument.Type.FLOAT);
		p.addPositionalArgument("height", Argument.Type.FLOAT);
		p.addNamedArgument("type", Argument.Type.STRING);
		XMLFactory.writeArgumentParser(p);
		assertEquals("length width height type", XMLFactory.getArgumentNames());
	}
	@Test
	public void testGetArgumentType() 
	{
		ArgumentParser p = new ArgumentParser("Volume Calculator");
		p.addPositionalArgument("length", Argument.Type.FLOAT);
		p.addPositionalArgument("width", Argument.Type.FLOAT);
		p.addPositionalArgument("height", Argument.Type.FLOAT);
		p.addNamedArgument("type", Argument.Type.STRING);
		XMLFactory.writeArgumentParser(p);
		assertEquals(Argument.Type.FLOAT, XMLFactory.getArgumentType("length"));
	}
	@Test
	public void testGetArgumentDescription() 
	{
		ArgumentParser p = new ArgumentParser("Volume Calculator");
		p.addPositionalArgument("length", Argument.Type.FLOAT);
		p.setArgumentDescription("length", "this is the text description");
		XMLFactory.writeArgumentParser(p);
		assertEquals("this is the text description", XMLFactory.getArgumentDescription("length"));
	}
	@Test
	public void testGetNamedArgumentAlternateName() 
	{
		ArgumentParser p = new ArgumentParser("Volume Calculator");
		p.addNamedArgument("type", Argument.Type.STRING);
		p.setNamedArgumentAlternateName("type", "t");
		XMLFactory.writeArgumentParser(p);
		assertEquals("t", XMLFactory.getNamedArgumentAlternateName("type"));
	}
}