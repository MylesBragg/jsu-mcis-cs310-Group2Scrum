import edu.jsu.mcis.*;

import java.util.*;

public class ArgParserKeywords {
	private ArgParser parser;
	
	public void startVolumeCalculatorTest(String vars) {
		parser = new ArgParser("VolumeCalculator");
		parser.addProgramHelpInfo("Calculate the volume of a box.");
		parser.addArg("length", "the length of the box", ArgValues.Type.INT);
		parser.addArg("width", "the width of the box", ArgValues.Type.INT);
		parser.addArg("height", "the height of the box", ArgValues.Type.INT);
		parser.parse(vars);
	}
	
	public void startPetShowTest(String vars) {
		parser = new ArgParser("PetShow");
		parser.addArg("pet", "The type of pet", ArgValues.Type.STRING);
		parser.addArg("number", "The number of pets", ArgValues.Type.INT);
		parser.addArg("rainy", "If it is raining", ArgValues.Type.BOOLEAN);
		parser.addArg("bathrooms", "The number of bathrooms", ArgValues.Type.FLOAT);
		parser.parse(vars);
	}
	
	public void startProgramWithOptionalArguments(String vars)
	{
		parser = new ArgParser("OptionalArguments");
		parser.addArg("length", "the length of the box", ArgValues.Type.INT);
		parser.addArg("width", "the width of the box", ArgValues.Type.INT);
		parser.addArg("height", "the height of the box", ArgValues.Type.INT);
		parser.addOptionalArg("type", "Optional argument 'type' help", ArgValues.Type.STRING);
		parser.parse(vars);
	}
	
	public String get(String name)
	{
		
		return parser.getArgValue(name).toString();
	}
	
/*	public String getWidth()
	{
		return parser.getArgValue("width").toString();
	}
	
	public String getHeight()
	{
		return parser.getArgValue("height").toString();
	}
	
	public String getPet()
	{
		return parser.getArgValue("pet").toString();
	}
	
	public String getNumber()
	{
		return parser.getArgValue("number").toString();
	}
	
	public String getRainy()
	{
		return parser.getArgValue("rainy").toString();
	}
	
	public String getBathrooms()
	{
		return parser.getArgValue("bathrooms").toString();
	}*/
}