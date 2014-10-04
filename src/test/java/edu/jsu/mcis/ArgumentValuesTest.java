package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentValuesTest 
{
private ArgumentValues v;

	@Before
	public void testCreateInstanceofArgValues()
	{
		v = new ArgumentValues();
	}

	//@Test
	public void testaddNametoValues()
	{
		v.addValueArgument("length", "7");
		assertEquals(v.getName(1),"length" );
	}	
	@Test
	public void testGetValuefromValues()
	{
		v.addValueArgument("length", "7");
		assertEquals("7", v.getValueArgument("length"));
	
	}
	
	@Test
	public void testMultiple()
	{
		v.addValueArgument("length", "7");
		assertEquals("7", v.getValueArgument("length"));
		v.addValueArgument("width", "4");
		assertEquals("4", v.getValueArgument("width"));
		v.addValueArgument("height", "6");
		assertEquals("6", v.getValueArgument("height"));
	}
	
	
	@Test
	public void testAddHelpArgument() {
		v.addHelpArgument("length", "Please add length as a whole number.");
		assertEquals("Please add length as a whole number.", v.getHelpArgument("length"));
	}
	
	@Test
	public void testAddMultipleHelpArguments() {
		v.addHelpArgument("length", "Please add length as a whole number.");
		assertEquals("Please add length as a whole number.", v.getHelpArgument("length"));
		v.addHelpArgument("width", "Please add width as a whole number.");
		assertEquals("Please add width as a whole number.", v.getHelpArgument("width"));
	}
	@Test
	public void testAddDataTypeArgument() {
		v.addDataTypeArgument("length", "integer");
		assertEquals("integer", v.getDataTypeArgument("length"));
	}
	@Test
	public void testAddMultipleDataTypeArguments() {
		v.addDataTypeArgument("length", "integer");
		assertEquals("integer", v.getDataTypeArgument("length"));
		v.addDataTypeArgument("width", "float");
		assertEquals("float", v.getDataTypeArgument("width"));
	}
}