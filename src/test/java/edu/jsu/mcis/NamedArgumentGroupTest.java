package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class NamedArgumentGroupTest 
{
	private NamedArgumentGroup currentGroup;
	
	@Test
	public void testSetNewGroup()
	{
		currentGroup = new NamedArgumentGroup("transport");
		assertTrue(currentGroup.checkGroupHeader("transport"));
	}
	
	@Test
	public void testAddNewGroupMember()
	{
		currentGroup = new NamedArgumentGroup("transport");
		currentGroup.appendGroupMember("transportType");
		assertTrue(currentGroup.checkGroup("transportType"));
	}
	
	@Test
	public void testCheckGroupHeaderFail()
	{
		currentGroup = new NamedArgumentGroup("transport");
		assertFalse(currentGroup.checkGroupHeader("transports"));
	}
	
	@Test
	public void testCheckGroupFail()
	{
		currentGroup = new NamedArgumentGroup("transport");
		currentGroup.appendGroupMember("transportType");
		assertFalse(currentGroup.checkGroup("transportTypes"));
	}
}