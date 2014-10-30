package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgValuesTest 
{
	private ArgValues v;

	@Test
	public void testgetValueArg() {
		v = new ArgValues("length", "This is length help", dataTypeDefinitions.FLOAT);
		v.addValueArg("7");
		assertEquals(v.getValueArg(), 7f);
	}
	
	/*@Test
	public void testGetOptionalVal() {
		v = new ArgValues("length", "This is length help", dataTypeDefinitions.FLOAT);
		v.addValueArg("--closet", "6");
		v.addDataTypeArg("--closet", "optional");
		assertEquals(v.getValueArg(), "6");
	}*/
	
	@Test
	public void testMultiple()
	{
		ArgValues v, w, x;
		v = new ArgValues("length", "This is length help", dataTypeDefinitions.INT);
		w = new ArgValues("width", "This is width help", dataTypeDefinitions.FLOAT);
		x = new ArgValues("height", "This is height help", dataTypeDefinitions.BOOLEAN);
		v.addValueArg("7");
		assertEquals(7, v.getValueArg());
		w.addValueArg("4.3");
		assertEquals(4.3f, w.getValueArg());
		x.addValueArg("true");
		assertEquals(true, x.getValueArg());
	}
	
	@Test
	public void testAddHelpArg() {
		v = new ArgValues("length", "This is length help", dataTypeDefinitions.FLOAT);
		assertEquals(v.getHelpArg(), "This is length help");
	}
	
	@Test
	public void testAddMultipleHelpArgs() {
		ArgValues v, w, x;
		v = new ArgValues("length", "This is length help", dataTypeDefinitions.INT);
		w = new ArgValues("width", "This is width help", dataTypeDefinitions.FLOAT);
		x = new ArgValues("height", "This is height help", dataTypeDefinitions.BOOLEAN);
		assertEquals("This is length help", v.getHelpArg());
		assertEquals("This is width help", w.getHelpArg());
		assertEquals("This is height help", x.getHelpArg());
	}
	
	@Test
	public void testAddDataTypeArg() {
		v = new ArgValues("length", "This is length help", dataTypeDefinitions.FLOAT);
		assertEquals(v.getDataTypeArg(), dataTypeDefinitions.FLOAT);
	}
	
	@Test
	public void testAddMultipleDataTypeArgs() {
		ArgValues v, w, x;
		v = new ArgValues("length", "This is length help", dataTypeDefinitions.INT);
		w = new ArgValues("width", "This is width help", dataTypeDefinitions.FLOAT);
		x = new ArgValues("height", "This is height help", dataTypeDefinitions.BOOLEAN);
		assertEquals(dataTypeDefinitions.INT, v.getDataTypeArg());
		assertEquals(dataTypeDefinitions.FLOAT, w.getDataTypeArg());
		assertEquals(dataTypeDefinitions.BOOLEAN, x.getDataTypeArg());
	}
}