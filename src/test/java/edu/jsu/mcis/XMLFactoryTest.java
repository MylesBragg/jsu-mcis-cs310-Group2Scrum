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
}