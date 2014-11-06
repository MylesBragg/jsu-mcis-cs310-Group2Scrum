import edu.jsu.mcis.*;

import java.util.*;

public class ArgParserKeywords {
	private ArgParser parser;
	
	public void startVolumeCalculatorTest(String vars) {
		parser = new ArgParser("VolumeCalculator");
		parser.addProgramHelpInfo("Calculate the volume of a box.");
		parser.addArg("length", "the length of the box", Argument.Type.INT);
		parser.addArg("width", "the width of the box", Argument.Type.INT);
		parser.addArg("height", "the height of the box", Argument.Type.INT);
		parser.parse(vars);
	}
	
	public void startPetShowTest(String vars) {
		parser = new ArgParser("PetShow");
		parser.addArg("pet", "The type of pet", Argument.Type.STRING);
		parser.addArg("number", "The number of pets", Argument.Type.INT);
		parser.addArg("rainy", "If it is raining", Argument.Type.BOOLEAN);
		parser.addArg("bathrooms", "The number of bathrooms", Argument.Type.FLOAT);
		parser.parse(vars);
	}
	
	public void startProgramWithOptionalArguments(String vars)
	{
		parser = new ArgParser("OptionalArguments");
		parser.addArg("length", "the length of the box", Argument.Type.INT);
		parser.addArg("width", "the width of the box", Argument.Type.INT);
		parser.addArg("height", "the height of the box", Argument.Type.INT);
		parser.addOptArg("type", "Optional argument 'type' help", Argument.Type.STRING);
		parser.parse(vars);
	}
	
	public String get(String name)
	{
		return parser.getArgValue(name).toString();
	}
}