package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class OptArgValuesTest 
{
	private OptArgValues v;

	//Basic Functionality for get methods
	@Test
	public void testGetName() {
		v = new OptArgValues("type", "This is the type information help", ArgValues.Type.STRING);
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
	@Test 
	public void testConstructorNameHelpDataTypeIntDefValue(){
		v = new OptArgValues("type", "This is the type information help", ArgValues.Type.INT, 7);
		assertEquals(ArgValues.Type.INT,v.getDataType());
		assertEquals(7, v.getArgDefault());
	}
	@Test 
	public void testConstructorNameHelpDataTypeBoolDefValue(){
		v = new OptArgValues("type", "This is the type information help", ArgValues.Type.BOOLEAN, true);
		assertEquals(ArgValues.Type.BOOLEAN,v.getDataType());
		assertEquals(true, v.getArgDefault());
	}
	@Test 
	public void testConstructorNameHelpDataTypeFloatDefValue(){
		v = new OptArgValues("type", "This is the type information help", ArgValues.Type.FLOAT, 3.5f);
		assertEquals(ArgValues.Type.FLOAT,v.getDataType());
		assertEquals(3.5f, v.getArgDefault());
	}
	@Test 
	public void testConstructorNameShortHelpDataTypeIntDefValue(){
		v = new OptArgValues("type", "t", "This is the type information help", ArgValues.Type.INT, 7);
		assertEquals(ArgValues.Type.INT,v.getDataType());
	}
	@Test 
	public void testConstructorNameShortHelpDataTypeBoolDefValue(){
		v = new OptArgValues("type", "t", "This is the type information help", ArgValues.Type.BOOLEAN, false);
		assertEquals(ArgValues.Type.BOOLEAN,v.getDataType());
	}
	@Test 
	public void testConstructorNameShortHelpDataTypeFloatDefValue(){
		v = new OptArgValues("type", "t", "This is the type information help", ArgValues.Type.FLOAT, 3.5f);
		assertEquals(ArgValues.Type.FLOAT,v.getDataType());
	}
	//********************************************************//
	@Test 
	public void testSetArgDefaultInt(){
		v = new OptArgValues("type", "This is the type information help", ArgValues.Type.INT);
		v.setArgDefault("7");
		assertEquals(ArgValues.Type.INT,v.getDataType());
		assertEquals(7, v.getArgDefault());
	}
	@Test 
	public void testSetArgDefaultBooleanTrue(){
		v = new OptArgValues("type", "This is the type information help", ArgValues.Type.BOOLEAN);
		v.setArgDefault("true");
		assertEquals(ArgValues.Type.BOOLEAN,v.getDataType());
		assertEquals(true, v.getArgDefault());
	}
	@Test 
	public void testSetArgDefaultBooleanFalse(){
		v = new OptArgValues("type", "This is the type information help", ArgValues.Type.BOOLEAN);
		v.setArgDefault("false");
		assertEquals(ArgValues.Type.BOOLEAN,v.getDataType());
		assertEquals(false, v.getArgDefault());
	}
	@Test 
	public void testSetArgDefaultThrowNumberFormatException(){
		try
		{
			v = new OptArgValues("type", "This is the type information help", ArgValues.Type.BOOLEAN);
			v.setArgDefault("junk");
			assert false;
		}
		catch(NumberFormatException nfe)
		{
			assert true;
		}
	}
	@Test 
	public void testSetArgDefaultFloat(){
		v = new OptArgValues("type", "t", "This is the type information help", ArgValues.Type.FLOAT);
		v.setArgDefault("3.5");
		assertEquals(ArgValues.Type.FLOAT,v.getDataType());
		assertEquals(3.5f, v.getArgDefault());
	}
}