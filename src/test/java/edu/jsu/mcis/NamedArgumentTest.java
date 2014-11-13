package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class NamedArgumentTest 
{
	private NamedArgument v, w, x, y;
	
	@Test
	public void testAddNewNamedArgument() {
		v = new NamedArgument("type", Argument.Type.STRING);
		
		assertEquals("type", v.getName());
	}
	@Test
	public void testSetNamedArgumentDescription() {
		v = new NamedArgument("type", Argument.Type.STRING);
		
		v.setDescription("The shape to calculate");
		
		assertEquals("The shape to calculate", v.getDescription());
	}
	@Test
	public void testAddNamedArgumentAlternateName() {
		v = new NamedArgument("type", Argument.Type.STRING);
		
		v.setAlternateName("t");
		
		assertEquals("t", v.getAlternateName());
	}
	@Test
	public void testSetNamedArgumentDefaultValue() {
		v = new NamedArgument("type", Argument.Type.STRING);
		
		v.setDefaultValue("box");
		
		assertEquals("box", v.getDefaultValue());
	}
	@Test
	public void testSetNamedArgumentDefaultValueFalse() {
		v = new NamedArgument("type", Argument.Type.BOOLEAN);
		v.setDefaultValue("false");
		assertEquals(false, v.getDefaultValue());
	}
	@Test
	public void testSetNamedArgumentRequired() {
		v = new NamedArgument("type", Argument.Type.STRING);
		
		v.setRequired();
		
		assertTrue(v.getRequired());
	}
	@Test
	public void testAddMultipleNewNamedArguments() {
		v = new NamedArgument("type", Argument.Type.STRING);
		w = new NamedArgument("round", Argument.Type.STRING);
		x = new NamedArgument("precision", Argument.Type.STRING);
		
		assertEquals("type", v.getName());
		assertEquals("round", w.getName());
		assertEquals("precision", x.getName());
	}
	@Test
	public void testSetSomeNamedArgumentsRequired() {
		v = new NamedArgument("type", Argument.Type.STRING);
		w = new NamedArgument("round", Argument.Type.STRING);
		x = new NamedArgument("precision", Argument.Type.STRING);
		
		v.setRequired();
		x.setRequired();
		
		assertTrue(v.getRequired());
		assertFalse(w.getRequired());
		assertTrue(x.getRequired());
	}
	@Test
	public void testSetSomeNamedArgumentsAlternateName() {
		v = new NamedArgument("type", Argument.Type.STRING);
		w = new NamedArgument("round", Argument.Type.STRING);
		x = new NamedArgument("precision", Argument.Type.STRING);
		
		v.setAlternateName("t");
		w.setAlternateName("r");
		
		assertEquals("t", v.getAlternateName());
		assertEquals("r", w.getAlternateName());
		assertEquals("", x.getAlternateName());
	}
	@Test
	public void testSetSomeNamedArgumentsDescription() {
		v = new NamedArgument("type", Argument.Type.STRING);
		w = new NamedArgument("round", Argument.Type.STRING);
		x = new NamedArgument("precision", Argument.Type.STRING);
		
		v.setDescription("The shape to calculate");
		w.setDescription("Round up or down");
		
		assertEquals("The shape to calculate", v.getDescription());
		assertEquals("Round up or down", w.getDescription());
		assertEquals("", x.getDescription());
	}
	@Test
	public void testSetNamedArgumentsDefaultValuesAllTypes() {
		v = new NamedArgument("type", Argument.Type.STRING);
		w = new NamedArgument("save", Argument.Type.BOOLEAN);
		x = new NamedArgument("constant", Argument.Type.INT);
		y = new NamedArgument("maxBathCount", Argument.Type.FLOAT);
		
		v.setDefaultValue("box");
		w.setDefaultValue(true);
		x.setDefaultValue(33);
		y.setDefaultValue(2.5f);
		
		assertEquals("box", v.getDefaultValue());
		assertEquals(true, w.getDefaultValue());
		assertEquals(33, x.getDefaultValue());
		assertEquals(2.5f, y.getDefaultValue());
	}
	@Test
	public void testSetNamedArgumentsValuesAllTypes() {
		v = new NamedArgument("type", Argument.Type.STRING);
		w = new NamedArgument("save", Argument.Type.BOOLEAN);
		x = new NamedArgument("constant", Argument.Type.INT);
		y = new NamedArgument("maxBathCount", Argument.Type.FLOAT);
		
		v.setValue("box");
		w.setValue("true");
		x.setValue("33");
		y.setValue("2.5f");
		
		assertEquals("box", v.getValue());
		assertEquals(true, w.getValue());
		assertEquals(33, x.getValue());
		assertEquals(2.5f, y.getValue());	
	}
	@Test(expected=NumberFormatException.class)
	public void testSetNamedArgumentsInvalidValue() {
		v = new NamedArgument("save", Argument.Type.BOOLEAN);
		
		v.setValue("yes");
	}
	@Test(expected=NumberFormatException.class)
	public void testSetNamedArgumentsDefaultValueInvalid() {
		v = new NamedArgument("save", Argument.Type.BOOLEAN);
		
		v.setDefaultValue("yes");
	}
}