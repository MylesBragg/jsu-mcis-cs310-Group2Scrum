import edu.jsu.mcis.*;
import java.io.*;
public class WriteAndSaveParser {
	public static void main(String[] args) {
		File f = new File("Volume Calcluator.xml");
		ArgumentParser parser;
		
		if(f.exists()) {
			System.out.println("I am reading from an xml file.");
			parser = XMLFactory.createArgumentParser("Volume Calculator", f.getName());
		}
		else {
			System.out.println("I am making an xml file.");
			parser = new ArgumentParser("Volume Calculator");
			parser.addPositionalArgument("length", Argument.Type.FLOAT);
			parser.addPositionalArgument("width", Argument.Type.FLOAT);
			parser.addPositionalArgument("height", Argument.Type.FLOAT);
			XMLFactory.writeArgumentParser(parser);
		}
		
		parser.parse(args);
		
		float length, width, height;
		length = parser.getArgumentValue("length");
		width = parser.getArgumentValue("width");
		height = parser.getArgumentValue("height");
		System.out.println("length: " + length);
		System.out.println("width: " + width);
		System.out.println("height: " + height);
		System.out.println("Volume: " + (length * width * height));
	}
}