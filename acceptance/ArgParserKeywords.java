import edu.jsu.mcis.*;

import java.util.*;

public class ArgParserKeywords {
	private ArgParser parser;
	
	public void startVolumeCalculatorTest(String vars) {
		parser = new ArgParser();
		parser.addProgramHelpInfo("Calculate the volume of a box.");
		parser.addArg("length", "the length of the box", ArgValues.Type.INT);
		parser.addArg("width", "the width of the box", ArgValues.Type.FLOAT);
		parser.addArg("height", "the height of the box", ArgValues.Type.INT);
		parser.parse("VolumeCalculator " + vars);
	}
	
	public void startPetShowTest(String vars) {
		parser = new ArgParser();
		parser.addArg("pet", "The type of pet", ArgValues.Type.STRING);
		parser.addArg("number", "The number of pets", ArgValues.Type.INT);
		parser.addArg("rainy", "If it is raining", ArgValues.Type.BOOLEAN);
		parser.addArg("bathrooms", "The number of bathrooms", ArgValues.Type.FLOAT);
		parser.parse("GetPetNumberRainyBathrooms " + vars);
	}
	
	public void startProgramWithArgs(String vars) 
	{
		parser = new ArgParser();
		Scanner getVar = new Scanner(vars);
		String firstVar = getVar.next();
		
		
		if (vars == "7 5 2")
		{
			parser.addArg("length");
			parser.addArg("width");
			parser.addArg("height");
			parser.parse("VolumeCalculator " + vars);
		}
		else
		{
			parser.addArg("pet");
			parser.addArg("number");
			parser.addArg("rainy");
			parser.addArg("bathrooms");
			parser.parse("GetPetNumberRainyBathrooms " + vars);
		}
	}
	
	public String getLength()
	{
		return parser.getArgValue("length");
	}
	
	public String getWidth()
	{
		return parser.getArgValue("width");
	}
	
	public String getHeight()
	{
		return parser.getArgValue("height");
	}
	
	public String getPet()
	{
		return parser.getArgValue("pet");
	}
	
	public String getNumber()
	{
		return parser.getArgValue("number");
	}
	
	public String getRainy()
	{
		return parser.getArgValue("rainy");
	}
	
	public String getBathrooms()
	{
		return parser.getArgValue("bathrooms");
	}
}