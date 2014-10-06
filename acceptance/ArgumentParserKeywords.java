import edu.jsu.mcis.*;

public class ArgumentParserKeywords {
	private ArgumentParser parser;
	
	public void startVolumeCalculator(String vars) {
		parser = new ArgumentParser();
		parser.addArg("length");
		parser.addArg("width");
		parser.addArg("height");
		parser.parse("VolumeCalculator " + vars);
	}
	
	public void startGetPetNumberRainyBathrooms(String vars) {
		parser = new ArgumentParser();
		parser.addArg("pet");
		parser.addArg("number");
		parser.addArg("rainy");
		parser.addArg("bathrooms");
		parser.parse("GetPetNumberRainyBathrooms " + vars);
	}
		
	public String getLength()
	{
		return parser.getArgumentValue("length");
	}
	
	public String getWidth()
	{
		return parser.getArgumentValue("width");
	}
	
	public String getHeight()
	{
		return parser.getArgumentValue("height");
	}
	
	public String getPet()
	{
		return parser.getArgumentValue("pet");
	}
	
	public String getNumber()
	{
		return parser.getArgumentValue("number");
	}
	
	public String getRainy()
	{
		return parser.getArgumentValue("rainy");
	}
	
	public String getBathrooms()
	{
		return parser.getArgumentValue("bathrooms");
	}
}