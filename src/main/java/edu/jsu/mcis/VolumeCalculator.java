import edu.jsu.mcis.*;

import java.io.*;
import java.util.*;

public class VolumeCalculator
{

	public static void main(String[] args)
	{
		ArgumentParser p;
		//Scanner scan = new Scanner(System.in);
        String parseString = "";
		String lowerCaseStr = "";
		String myString = "";
		
		p = new ArgumentParser();
		myString = p.fromArgsToString(args);
		System.out.print(myString);
		parseString = "VolumeCalculator" + myString;
		
		p.addArg("length", "the length of the box", "float");
		p.addArg("width", "the width of the box", "float");
		p.addArg("height", "the height of the box", "float");
		String response = p.parse(myString);
		if(response == "Parsing Completed")
		{
			String shape = p.getArgumentValue("type");
			float length = p.getArgumentValue("length");
			float width = p.getArgumentValue("width");
			float height = p.getArgumentValue("height");
			float volume = length * width * height;
			response = ("The volume of the " + shape + " is: " + volume);
		}
		System.out.println(response);
	}
}