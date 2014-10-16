package edu.jsu.mcis;

import java.io.*;
import java.util.*;

public class Controller
{

	
	public static void main(String[] args)
	{
		ArgumentParser p = new ArgumentParser();
		String nameHelpDataTypeString = "";
		String parseString = "";
		
		System.out.println("Pressing enter with no parameter exits parameter addition loop.");
		int count = 1;
		System.out.println("Enter parameter " + count + " name, dataType, and help information.");
		System.out.print("(parameter, dataType, help): ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		nameHelpDataTypeString = tryCatchFunction(br);
		
		
		System.out.print("\n Enter string with program name, required parameters,\n" +
							"and any optional parameters you wish to parse: ");
		br = new BufferedReader(new InputStreamReader(System.in));
		parseString = tryCatchFunction(br);
		System.out.println(p.parse(parseString));
	}
	
	public static String tryCatchFunction(BufferedReader br)
	{
		String stuff = null;
		try
		{
			stuff = br.readLine();
		}
		catch (IOException e)
		{
			System.out.println("I/O error reading parameter list");
			System.exit(1);
		}
		return stuff;
	}
}