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

	@Test
	public void testaddNametoValues()
	{
		v.setName("length");
		assertEquals(v.getName(1),"length" );
	}	
	@Test
	public void testGetValuefromValues()
	{
		v.setName("length");
		v.setValue("length", "7");
		assertEquals("7", v.getValue("length"));
	
	}
	
	@Test
	public void testMultiple()
	{
		v.setName("length");
		v.setValue("length", "7");
		assertEquals("7", v.getValue("length"));
		v.setName("width");
		v.setValue("width", "4");
		assertEquals("4", v.getValue("width"));
		v.setName("height");
		v.setValue("height", "6");
		assertEquals("6", v.getValue("height"));
	}
	
	//@Test
	public void setFirstArgumentValue() {
		ArgumentValues valueHolder = new ArgumentValues();
		valueHolder.setName("length");
		valueHolder.setValue("7");
		//assertEquals("length", valueHolder.getName());
		//assertEquals("7", valueHolder.getValue());
	}
	//@Test
	public void getFirstArgumentValueAsInt() {
		ArgumentValues valueHolder = new ArgumentValues();
		valueHolder.setName("length");
		valueHolder.setValue("7");
		//assertEquals("length", valueHolder.getName());
		//assertEquals(7, Integer.parseInt(valueHolder.getValue()));
	}
	@Test
	public void setTwoArguments() {
		ArgumentValues valueHolder = new ArgumentValues();
		valueHolder.setName("length");
		valueHolder.setValue("length", "7");
		valueHolder.setName("width");
		valueHolder.setValue("width", "5");
		assertEquals("length", valueHolder.getName(0));
		assertEquals("width", valueHolder.getName(1));
		assertEquals("7", valueHolder.getValue("length"));
		assertEquals("5", valueHolder.getValue("width"));
		
		
	}
}