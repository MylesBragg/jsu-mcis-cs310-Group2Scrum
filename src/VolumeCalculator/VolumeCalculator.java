import edu.jsu.mcis.*;

import java.io.*;
import java.util.*;

public class VolumeCalculator
{

	public static void main(String[] args)
	{
		ArgParser p;
        String parseString = "";
		String lowerCaseStr = "";
		String myString = "";
		
		p = new ArgParser();
		myString = p.fromArgsToString(args);
		//myString = "VolumeCalculator " + myString;
		System.out.print(myString);
		
		p.addArg("length", "the length of the box", "float");
		p.addArg("width", "the width of the box", "float");
		p.addArg("height", "the height of the box", "float");
		p.addOptionalArg("save", "help", "Stuff", "");
		p.addOptionalArg("type", "you get no help", "float", 42); // re-factor to fit these parameters
		String response = p.parse(myString);
		if(response == "Parsing Completed")
		{
			String shape = p.getArgValue("type");	// re-factor hard-coding
			float length = p.getArgValue("length");
			float width = p.getArgValue("width");
			float height = p.getArgValue("height");
			float volume = length * width * height;
			response = ("The volume of the " + shape + " is: " + volume);
			System.out.println("here I am " + p.getArgValue("type"));
		}
		System.out.println(response);
	}
}