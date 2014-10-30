package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgValuesTest 
{
	private ArgValues v;

	@Test
	public void testgetValueArg() {
		v = new ArgValues("length", "This is length help", ArgValues.Type.FLOAT);
		v.addValueArg("7");
		assertEquals(v.getValueArg(), 7f);
	}
	
	/*@Test
	public void testGetOptionalVal() {
		v = new ArgValues("length", "This is length help", ArgValues.Type.FLOAT);
		v.addValueArg("--closet", "6");
		v.addDataTypeArg("--closet", "optional");
		assertEquals(v.getValueArg(), "6");
	}*/
	
	@Test
	public void testMultiple()
	{
		ArgValues v, w, x;
		v = new ArgValues("length", "This is length help", ArgValues.Type.INT);
		w = new ArgValues("width", "This is width help", ArgValues.Type.FLOAT);
		x = new ArgValues("height", "This is height help", ArgValues.Type.BOOLEAN);
		v.addValueArg("7");
		assertEquals(7, v.getValueArg());
		w.addValueArg("4.3");
		assertEquals(4.3f, w.getValueArg());
		x.addValueArg("true");
		assertEquals(true, x.getValueArg());
	}
	
	@Test
	public void testAddHelpArg() {
		v = new ArgValues("length", "This is length help", ArgValues.Type.FLOAT);
		assertEquals(v.getHelpArg(), "This is length help");
	}
	
	@Test
	public void testAddMultipleHelpArgs() {
		ArgValues v, w, x;
		v = new ArgValues("length", "This is length help", ArgValues.Type.INT);
		w = new ArgValues("width", "This is width help", ArgValues.Type.FLOAT);
		x = new ArgValues("height", "This is height help", ArgValues.Type.BOOLEAN);
		assertEquals("This is length help", v.getHelpArg());
		assertEquals("This is width help", w.getHelpArg());
		assertEquals("This is height help", x.getHelpArg());
	}
	
	@Test
	public void testAddDataTypeArg() {
		v = new ArgValues("length", "This is length help", ArgValues.Type.FLOAT);
		assertEquals(v.getDataTypeArg(), ArgValues.Type.FLOAT);
	}
	
	@Test
	public void testAddMultipleDataTypeArgs() {
		ArgValues v, w, x;
		v = new ArgValues("length", "This is length help", ArgValues.Type.INT);
		w = new ArgValues("width", "This is width help", ArgValues.Type.FLOAT);
		x = new ArgValues("height", "This is height help", ArgValues.Type.BOOLEAN);
		assertEquals(ArgValues.Type.INT, v.getDataTypeArg());
		assertEquals(ArgValues.Type.FLOAT, w.getDataTypeArg());
		assertEquals(ArgValues.Type.BOOLEAN, x.getDataTypeArg());
	}
}