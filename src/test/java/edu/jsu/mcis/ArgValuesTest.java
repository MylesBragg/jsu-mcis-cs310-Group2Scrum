package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgValuesTest 
{
	private ArgValues v;

	@Before
	public void testCreateInstanceofArgValues()
	{
		v = new ArgValues();
	}

	@Test
	public void testgetValueArg() {
		v.addValueArg("length", "7");
		v.addDataTypeArg("length", "integer");
		assertEquals(Integer.parseInt(v.getValueArg("length", "integer").toString()), 7);
	}
	@Test
	public void testGetOptionalVal() {
		v.addValueArg("--closet", "6");
		v.addDataTypeArg("--closet", "optional");
		assertEquals(v.getValueArg("--closet", "optional"), "6");
	}
	@Test
	public void testaddNametoValues()
	{
		v.addValueArg("length", "7");
		v.addDataTypeArg("length", "integer");
		assertEquals(Integer.parseInt(v.getValueArg("length", "integer").toString()), 7);
	}
	
	
	@Test
	public void testGetValuefromValues()
	{
		v.addValueArg("length", "7");
		assertEquals(7, v.getValueArg("length", "integer"));
	
	}
	
	@Test
	public void testMultiple()
	{
		v.addValueArg("length", "7");
		assertEquals(7, v.getValueArg("length", "integer"));
		v.addValueArg("width", "4.3");
		assertEquals(4.3f, v.getValueArg("width", "float"));
		v.addValueArg("height", "6");
		assertEquals(6, v.getValueArg("height", "integer"));
	}
	
	@Test
	public void testAddHelpArg() {
		v.addHelpArg("length", "Please add length as a whole number.");
		assertEquals("Please add length as a whole number.", v.getHelpArg("length"));
	}
	
	@Test
	public void testAddMultipleHelpArgs() {
		v.addHelpArg("length", "Please add length as a whole number.");
		assertEquals("Please add length as a whole number.", v.getHelpArg("length"));
		v.addHelpArg("width", "Please add width as a whole number.");
		assertEquals("Please add width as a whole number.", v.getHelpArg("width"));
	}
	
	@Test
	public void testAddDataTypeArg() {
		v.addDataTypeArg("length", "integer");
		assertEquals("integer", v.getDataTypeArg("length"));
	}
	
	@Test
	public void testAddMultipleDataTypeArgs() {
		v.addDataTypeArg("length", "integer");
		assertEquals("integer", v.getDataTypeArg("length"));
		v.addDataTypeArg("width", "float");
		assertEquals("float", v.getDataTypeArg("width"));
	}
}