package edu.jsu.mcis;

import java.util.*;

public class Controller
{
	public static void main(String[] args)
	{
		ArgumentParser p = new ArgumentParser();
		System.out.println("Pressing enter with no parameter exits parameter addition.");
		do
		{
			System.out.print("Enter the NAME of the next parameter: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String name = null;
			try
			{
				name = br.readLine();
			}
			catch (IOException e)
			{
				System.out.println("I/O error reading parameter name");
				System.exit(1);
			}
			p.addArg(name);
		} while(name != null);
		
		System.out.print("\n Enter string with program name, required parameters,\n" +
							"and optional parameters you wish to parse: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		String parseString = null;
		try
		{
			parseString = br.readLine();
		}
		catch (IOException e)
		{
			System.out.println("I/O error reading parameter name");
			System.exit(1);
		}
		System.out.println(p.parse(parseString));
		
	}
}