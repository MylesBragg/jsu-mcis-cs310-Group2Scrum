package edu.jsu.mcis;

import java.io.*;
import java.util.*;

public class Controller
{

	public static void main(String[] args)
	{
		ArgumentParser p;
		Scanner scan = new Scanner(System.in);
		String lowerCaseStr = "";
		String myString = "";
		
		for (int i = 0; i < args.length; i++) 
		{
			myString += args[i] + " ";
		}
		System.out.print(myString);
		
		//System.out.println("Enter string to parse (Type 'Done' to exit):");
		while (!lowerCaseStr.equals("done"))
		{
			p = new ArgumentParser();
			
			lowerCaseStr = myString.toLowerCase();
			if(!lowerCaseStr.equals("done"))
			{
				p.addArg("length", "Enter a whole number as length", "float");
				p.addArg("width", "Enter a float number as width", "float");
				p.addArg("height", "Enter a whole number as height", "float");
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
				myString = scan.nextLine();
			}
		}
	}
}