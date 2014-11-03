package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class OptArgValuesTest 
{
	private OptArgValues v;

	//Basic Functionality for get methods
	@Test
	public void testGetName() {
		 v = new OptArgValues("type", "This is length the type information help", ArgValues.Type.STRING);
		 assertEquals("type",v.getOptName());
	}
	@Test
	public void testGetShortName(){
		v = new OptArgValues("pet", "dog", "This is my personal companion", ArgValues.Type.STRING);
		assertEquals("dog",v.getShortName());
	}
	@Test
	public void testGetArgumentHelp(){
		v = new OptArgValues("pet", "This is my personal companion", ArgValues.Type.STRING);
		assertEquals("This is my personal companion",v.getArgHelp());
	}
	@Test
	public void testGetDefaultValue(){
		 v = new OptArgValues("type","Has some interesting traits", ArgValues.Type.STRING, "circle");
		 assertEquals("circle",v.getArgDefault());
	}
	@Test 
	public void testGetDataType(){
		 v = new OptArgValues("type", "This is length the type information help", ArgValues.Type.STRING);
		 assertEquals(ArgValues.Type.STRING,v.getDataType());
	}
	
}