package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentTest 
{
	private Argument v;

	@Test
	public void testGetValue() {
		v = new Argument("length", "This is length help", Argument.Type.FLOAT);
		v.setValue("7");
		assertEquals(v.getValue(), 7f);
	}
	
	@Test
	public void testGetMultipleValues()
	{
		Argument v, w, x;
		v = new Argument("length", "This is length help", Argument.Type.INT);
		w = new Argument("width", "This is width help", Argument.Type.FLOAT);
		x = new Argument("height", "This is height help", Argument.Type.BOOLEAN);
		v.setValue("7");
		assertEquals(7, v.getValue());
		w.setValue("4.3");
		assertEquals(4.3f, w.getValue());
		x.setValue("true");
		assertEquals(true, x.getValue());
	}
	
	@Test
	public void testGetDescription() {
		v = new Argument("length", "This is length help", Argument.Type.FLOAT);
		assertEquals(v.getDescription(), "This is length help");
	}
	
	@Test
	public void testGetMultipleDescriptions() {
		Argument v, w, x;
		v = new Argument("length", "This is length help", Argument.Type.INT);
		w = new Argument("width", "This is width help", Argument.Type.FLOAT);
		x = new Argument("height", "This is height help", Argument.Type.BOOLEAN);
		assertEquals("This is length help", v.getDescription());
		assertEquals("This is width help", w.getDescription());
		assertEquals("This is height help", x.getDescription());
	}
	
	@Test
	public void testGetType() {
		v = new Argument("length", "This is length help", Argument.Type.FLOAT);
		assertEquals(v.getType(), Argument.Type.FLOAT);
	}
	
	@Test
	public void testGetMultipleTypes() {
		Argument v, w, x;
		v = new Argument("length", "This is length help", Argument.Type.INT);
		w = new Argument("width", "This is width help", Argument.Type.FLOAT);
		x = new Argument("height", "This is height help", Argument.Type.BOOLEAN);
		assertEquals(Argument.Type.INT, v.getType());
		assertEquals(Argument.Type.FLOAT, w.getType());
		assertEquals(Argument.Type.BOOLEAN, x.getType());
	}
}