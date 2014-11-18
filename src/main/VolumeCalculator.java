import edu.jsu.mcis.*;

import java.io.*;
import java.util.*;

public class VolumeCalculator
{

	public static void main(String[] args)
	{
		ArgumentParser p;
		
		p = new ArgumentParser("VolumeCalculator");
		
		p.addPositionalArgument("length", Argument.Type.FLOAT);
		p.addPositionalArgument("width", Argument.Type.FLOAT);
		p.addPositionalArgument("height", Argument.Type.FLOAT);
		
		p.setArgumentDescription("length", "the length of the box");
		p.setArgumentDescription("width", "the width of the box");
		p.setArgumentDescription("height", "the height of the box");
		
		p.addNamedArgument("save", Argument.Type.BOOLEAN);
		p.addNamedArgument("type", Argument.Type.STRING);
		
		p.setNamedArgumentAlternateName("type", "t");
		p.setNamedArgumentDefaultValue("type", "box");
		
		p.parse(args);
		
		String shape = p.getArgumentValue("type");
		float length = p.getArgumentValue("length");
		float width = p.getArgumentValue("width");
		float height = p.getArgumentValue("height");
		
		float volume = length * width * height;
		
		String response = ("The volume of the " + shape + " is: " + volume);
		System.out.println(response);
	}
}