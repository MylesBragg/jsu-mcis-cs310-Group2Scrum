package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class OptionalArgumentTest 
{
	private OptionalArgument v;

	//Basic Functionality for get methods
	@Test
	public void testGetName() {
		v = new OptionalArgument("type", "This is the type information help", Argument.Type.STRING);
		assertEquals("type",v.getName());
	}
	@Test
	public void testGetShortName(){
		v = new OptionalArgument("pet", "dog", "This is my personal companion", Argument.Type.STRING);
		assertEquals("dog",v.getShortName());
	}
	@Test
	public void testGetArgumentHelp(){
		v = new OptionalArgument("pet", "This is my personal companion", Argument.Type.STRING);
		assertEquals("This is my personal companion",v.getDescription());
	}
	@Test
	public void testGetDefaultValue(){
		v = new OptionalArgument("type","Has some interesting traits", Argument.Type.STRING, "circle");
		assertEquals("circle",v.getValue());
	}
	@Test 
	public void testGetDataType(){
		v = new OptionalArgument("type", "This is length the type information help", Argument.Type.STRING);
		assertEquals(Argument.Type.STRING,v.getType());
	}
	@Test 
	public void testConstructorNameHelpDataTypeIntDefValue(){
		v = new OptionalArgument("type", "This is the type information help", Argument.Type.INT, 7);
		assertEquals(Argument.Type.INT,v.getType());
		assertEquals(7, v.getValue());
	}
	@Test 
	public void testConstructorNameHelpDataTypeBoolDefValue(){
		v = new OptionalArgument("type", "This is the type information help", Argument.Type.BOOLEAN, true);
		assertEquals(Argument.Type.BOOLEAN,v.getType());
		assertEquals(true, v.getValue());
	}
	@Test 
	public void testConstructorNameHelpDataTypeFloatDefValue(){
		v = new OptionalArgument("type", "This is the type information help", Argument.Type.FLOAT, 3.5f);
		assertEquals(Argument.Type.FLOAT,v.getType());
		assertEquals(3.5f, v.getValue());
	}
	@Test 
	public void testConstructorNameShortHelpDataTypeIntDefValue(){
		v = new OptionalArgument("type", "t", "This is the type information help", Argument.Type.INT, 7);
		assertEquals(Argument.Type.INT,v.getType());
	}
	@Test 
	public void testConstructorNameShortHelpDataTypeBoolDefValue(){
		v = new OptionalArgument("type", "t", "This is the type information help", Argument.Type.BOOLEAN, false);
		assertEquals(Argument.Type.BOOLEAN,v.getType());
	}
	@Test 
	public void testConstructorNameShortHelpDataTypeFloatDefValue(){
		v = new OptionalArgument("type", "t", "This is the type information help", Argument.Type.FLOAT, 3.5f);
		assertEquals(Argument.Type.FLOAT,v.getType());
	}
	//********************************************************//
	@Test 
	public void testSetArgDefaultInt(){
		v = new OptionalArgument("type", "This is the type information help", Argument.Type.INT);
		v.setValue("7");
		assertEquals(Argument.Type.INT,v.getType());
		assertEquals(7, v.getValue());
	}
	@Test 
	public void testSetArgDefaultBooleanTrue(){
		v = new OptionalArgument("type", "This is the type information help", Argument.Type.BOOLEAN);
		v.setValue("true");
		assertEquals(Argument.Type.BOOLEAN,v.getType());
		assertEquals(true, v.getValue());
	}
	@Test 
	public void testSetArgDefaultBooleanFalse(){
		v = new OptionalArgument("type", "This is the type information help", Argument.Type.BOOLEAN);
		v.setValue("false");
		assertEquals(Argument.Type.BOOLEAN,v.getType());
		assertEquals(false, v.getValue());
	}
	@Test 
	public void testSetArgDefaultThrowNumberFormatException(){
		try
		{
			v = new OptionalArgument("type", "This is the type information help", Argument.Type.BOOLEAN);
			v.setValue("junk");
			assert false;
		}
		catch(NumberFormatException nfe)
		{
			assert true;
		}
	}
	@Test 
	public void testSetArgDefaultFloat(){
		v = new OptionalArgument("type", "t", "This is the type information help", Argument.Type.FLOAT);
		v.setValue("3.5");
		assertEquals(Argument.Type.FLOAT,v.getType());
		assertEquals(3.5f, v.getValue());
	}
}