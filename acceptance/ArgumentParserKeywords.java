import edu.jsu.mcis.*;

import java.util.*;

public class ArgumentParserKeywords {
	private ArgumentParser parser;
	
	public void startVolumeCalculatorTest(String vars) {
		parser = new ArgumentParser("VolumeCalculator");
		parser.setProgramDescription("Calculate the volume of a box.");
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.INT);
		parser.addPositionalArgument("height", Argument.Type.INT);
		parser.setArgumentDescription("length", "the length of the box");
		parser.setArgumentDescription("width", "the width of the box");
		parser.setArgumentDescription("height", "the height of the box");
		parser.parse(vars);
	}
	
	public void startPetShowTest(String vars) {
		parser = new ArgumentParser("PetShow");
		parser.addPositionalArgument("pet", Argument.Type.STRING);
		parser.addPositionalArgument("number", Argument.Type.INT);
		parser.addPositionalArgument("rainy", Argument.Type.BOOLEAN);
		parser.addPositionalArgument("bathrooms", Argument.Type.FLOAT);
		parser.setArgumentDescription("pet", "The type of pet");
		parser.setArgumentDescription("number", "The number of pets");
		parser.setArgumentDescription("rainy", "If it is raining");
		parser.setArgumentDescription("bathrooms", "The number of bathrooms");
		parser.parse(vars);
	}
	
	public void startProgramWithNamedString(String vars)
	{
		parser = new ArgumentParser("OptionalArguments");
		parser.addPositionalArgument("length", Argument.Type.INT);
		parser.addPositionalArgument("width", Argument.Type.INT);
		parser.addPositionalArgument("height", Argument.Type.INT);
		parser.addNamedArgument("type", Argument.Type.STRING);
		parser.setArgumentDescription("length", "the length of the box");
		parser.setArgumentDescription("width", "the width of the box");
		parser.setArgumentDescription("height", "the height of the box");
		parser.setArgumentDescription("type", "Optional argument 'type' help");
		parser.parse(vars);
	}
	
	public void startProgramWithNamedBoolean(String vars)
	{
		parser = new ArgumentParser("NamedArguments");
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.addPositionalArgument("height", Argument.Type.FLOAT);
		parser.addNamedArgument("type", Argument.Type.BOOLEAN);
		parser.setArgumentDescription("length", "the length of the box");
		parser.setArgumentDescription("width", "the width of the box");
		parser.setArgumentDescription("height", "the height of the box");
		parser.setArgumentDescription("type", "Optional argument 'type' help");
		parser.parse(vars);
	}

	public void startProgramWithNamedFloat(String vars)
	{
		parser = new ArgumentParser("NamedArguments");
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.addPositionalArgument("height", Argument.Type.FLOAT);
		parser.addNamedArgument("type", Argument.Type.FLOAT);
		parser.setArgumentDescription("length", "the length of the box");
		parser.setArgumentDescription("width", "the width of the box");
		parser.setArgumentDescription("height", "the height of the box");
		parser.setArgumentDescription("type", "Optional argument 'type' help");
		parser.parse(vars);
	}

	public void startProgramWithNamedInt(String vars)
	{
		parser = new ArgumentParser("NamedArguments");
		parser.addPositionalArgument("length", Argument.Type.FLOAT);
		parser.addPositionalArgument("width", Argument.Type.FLOAT);
		parser.addPositionalArgument("height", Argument.Type.FLOAT);
		parser.addNamedArgument("type", Argument.Type.INT);
		parser.setArgumentDescription("length", "the length of the box");
		parser.setArgumentDescription("width", "the width of the box");
		parser.setArgumentDescription("height", "the height of the box");
		parser.setArgumentDescription("type", "Optional argument 'type' help");
		parser.parse(vars);
	}
	
	public void startProgramFromXML(String XMLFile, String vars)
	{
		parser = XMLFactory.createArgumentParser("XML File Test", XMLFile);
		parser.parse(vars);
	}
	
	public String get(String name)
	{
		return parser.getArgumentValue(name).toString();
	}
}