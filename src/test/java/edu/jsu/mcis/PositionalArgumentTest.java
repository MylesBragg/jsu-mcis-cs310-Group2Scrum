package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class PositionalArgumentTest 
{
	private PositionalArgument v, w, x;
	
	@Test
	public void testAddNewPositonalArgument() {
		v = new PositionalArgument("length", Argument.Type.FLOAT, 1);
		assertEquals("length", v.getName());
	}
	
	@Test
	public void testGetPositonalArgumentPosition() {
		v = new PositionalArgument("length", Argument.Type.FLOAT, 1);
		assertEquals(1, v.getPositionId());
	}
	@Test
	public void testGetPositonalArgumentType() {
		v = new PositionalArgument("length", Argument.Type.FLOAT, 1);
		assertEquals(Argument.Type.FLOAT, v.getType());
	}
	@Test
	public void testSetPositonalArgumentDescription() {
		v = new PositionalArgument("length", Argument.Type.FLOAT, 1);
		v.setDescription("The length of the box");
		assertEquals("The length of the box", v.getDescription());
	}
	@Test
	public void testSetPositonalArgumentValue() {
		v = new PositionalArgument("length", Argument.Type.FLOAT, 1);
		v.setValue("3.5");
		assertEquals(3.5f, v.getValue());
	}
	@Test
	public void testSetPositionalArgumentValueFalse() {
		v = new PositionalArgument("rainy", Argument.Type.BOOLEAN, 1);
		v.setValue("false");
		assertEquals(false, v.getValue());
	}
	@Test
	public void testAddMultipleNewPositonalArguments() {
		v = new PositionalArgument("length", Argument.Type.FLOAT, 1);
		w = new PositionalArgument("width", Argument.Type.FLOAT, 2);
		x = new PositionalArgument("height", Argument.Type.FLOAT, 3);
		assertEquals("length", v.getName());
		assertEquals("width", w.getName());
		assertEquals("height", x.getName());
	}
	@Test
	public void testGetMultiplePositonalArgumentsPositionId() {
		v = new PositionalArgument("length", Argument.Type.FLOAT, 1);
		w = new PositionalArgument("width", Argument.Type.FLOAT, 2);
		x = new PositionalArgument("height", Argument.Type.FLOAT, 3);
		assertEquals(1, v.getPositionId());
		assertEquals(2, w.getPositionId());
		assertEquals(3, x.getPositionId());
	}
	@Test
	public void testSetMultiplePositonalArgumentsValue() {
		v = new PositionalArgument("length", Argument.Type.FLOAT, 1);
		w = new PositionalArgument("width", Argument.Type.FLOAT, 2);
		x = new PositionalArgument("height", Argument.Type.FLOAT, 3);
		v.setValue("3.5");
		assertEquals(3.5f, v.getValue());
		w.setValue("7.5");
		assertEquals(7.5f, w.getValue());
		x.setValue("1.5");
		assertEquals(1.5f, x.getValue());
	}
	@Test
	public void testSetSomePositonalArgumentsDesription() {
		v = new PositionalArgument("length", Argument.Type.FLOAT, 1);
		w = new PositionalArgument("width", Argument.Type.FLOAT, 2);
		x = new PositionalArgument("height", Argument.Type.FLOAT, 3);
		v.setDescription("The length of the shape");
		x.setDescription("The height of the shape");
		assertEquals("The length of the shape", v.getDescription());
		assertEquals("", w.getDescription());
		assertEquals("The height of the shape", x.getDescription());
	}
	@Test
	public void testSetPositionalArgumentIntValue() {
		v = new PositionalArgument("count", Argument.Type.INT, 1);
		v.setValue("12");
		assertEquals(12, v.getValue());
	}
	@Test
	public void testSetPositionalArgumentBooleanValue() {
		v = new PositionalArgument("rainy", Argument.Type.BOOLEAN, 1);
		v.setValue("true");
		assertEquals(true, v.getValue());
	}
	@Test
	public void testSetPositionalArgumentStringValue() {
		v = new PositionalArgument("roomType", Argument.Type.STRING, 1);
		v.setValue("suite");
		assertEquals("suite", v.getValue());
	}
	@Test(expected=NumberFormatException.class)
	public void testSetInvalidPositionalArgumentValue() {
		v = new PositionalArgument("rainy", Argument.Type.BOOLEAN, 1);
		v.setValue("yes");
	}
}