package edu.jsu.mcis;

import java.io.*;
import java.util.*;

public class Controller
{

	public static void main(String[] args)
	{
		ArgumentParser p;
		Scanner scan = new Scanner(System.in);
        String parseString = "";
		String lowerCaseStr = "";
		String myString = "";
		

		
		for (int i = 0; i < args.length; i++) {
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
				p.addArg("length", "Enter a whole number as length", "integer");
				p.addArg("width", "Enter a float number as width", "float");
				p.addArg("height", "Enter a whole number as height", "integer");
				String response = p.parse(myString);
				System.out.println(response);
				myString = scan.nextLine();
			}
			
			
		}
	}
}