package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgValuesTest 
{
	private ArgValues v;
	
	@Test
	public void testGetOptionalVal() {
		v = new OptArgValues("type", "This is length the type information help", dataTypeDefinitions.STRING);
		v.addValueArg("box");
		assertEquals(v.getValueArg(), "box");
	}
	
	
}