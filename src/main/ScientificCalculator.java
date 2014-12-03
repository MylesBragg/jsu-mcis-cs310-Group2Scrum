import edu.jsu.mcis.*;

import java.io.*;
import java.util.*;

public class ScientificCalculator
{
	public static void main(String[] args)
	{
		ArgumentParser p = new ArgumentParser("ScientificCalculator");
		
		p.addPositionalArgument("operand1", Argument.Type.FLOAT);
		p.addPositionalArgument("operand2", Argument.Type.FLOAT);
		p.addPositionalArgument("operand3", Argument.Type.FLOAT);
		p.addPositionalArgument("controller", Argument.Type.BOOLEAN);
		p.addPositionalArgument("operator", Argument.Type.STRING);
		
		p.addNamedArgument("exponent", Argument.Type.INT);
		//p.setNamedArgumentRequired("exponent");
		p.setNamedArgumentAlternateName("exponent", "p");
		
		p.setArgumentDescription("operand1", "Enter the first whole number");
		p.setArgumentDescription("operand2", "Enter the second whole number");
		p.setArgumentDescription("operand3", "Enter the third whole number");
		p.setArgumentDescription("controller", "Enter the third whole number");
		p.setArgumentDescription("operator", "Enter the operator you wish to use");
		p.setArgumentDescription("exponent", "Enter a number to act as an exponent");
		
		p.parse(args);
		
		//if (Arrays.asList(args).contains("-h") || Arrays.asList(args).contains("--help"))
		//{
		
		//}
		//else 
		//{
		operand1 = p.getArgumentValue("operand1");
		operand2 = p.getArgumentValue("operand2");
		operand3 = p.getArgumentValue("operand3");
		operator = p.getArgumentValue("operator");
		
		switch(operator) 
		{
			case "add":
				if (p.getArgumentValue("exponent").equals("Error key not found."))
				{
					equation = operand1 + " + " + operand2 + " = ";
					System.out.println(equation + (operand1 + operand2));
				}
				else 
				{
					int power = p.getArgumentValue("exponent");
					equation = "(" + operand1 + " + " + operand2 + ")^" + power + " = ";
					double answer = Math.pow((operand1 + operand2), power);
					System.out.println(equation + answer);
				}
				break;
			case "subtract":
				if (p.getArgumentValue("exponent").equals("Error key not found."))
				{
					equation = operand1 + " - " + operand2 + " = ";
					System.out.println(equation + (operand1 - operand2));
				}
				else 
				{
					int power = p.getArgumentValue("exponent");
					equation = "(" + operand1 + " - " + operand2 + ")^" + power + " = ";
					double answer = Math.pow((operand1 - operand2), power);
					System.out.println(equation + answer);
				}
				break;
			case "multiply":
				if (p.getArgumentValue("exponent").equals("Error key not found."))
				{
					equation = operand1 + " * " + operand2 + " = ";
					System.out.println(equation + (operand1 * operand2));
				}
				else 
				{
					int power = p.getArgumentValue("exponent");
					equation = "(" + operand1 + " * " + operand2 + ")^" + power + " = ";
					double answer = Math.pow((operand1 * operand2), power);
					System.out.println(equation + answer);
				}
				break;
			case "divide":
				if (p.getArgumentValue("exponent").equals("Error key not found."))
				{
					equation = operand1 + " / " + operand2 + " = ";
					System.out.println(equation + (operand1 / operand2));
				}
				else 
				{
					int power = p.getArgumentValue("exponent");
					equation = "(" + operand1 + " / " + operand2 + ")^" + power + " = ";
					double answer = Math.pow((operand1 / operand2), power);
					System.out.println(equation + answer);
				}
				break;
			default:
				System.out.println("You must select a proper operator.");
		}
	/**************************************************************************************/
		p = new ArgumentParser("VolumeCalculator");
		
		p.addNamedArgument("box", Argument.Type.BOOLEAN);
		p.addNamedArgument("cylinder", Argument.Type.BOOLEAN);
		p.addNamedArgument("sphere", Argument.Type.BOOLEAN);
		p.addNamedArgument("hollowSphere", Argument.Type.BOOLEAN);
		
		p.addNamedArgument("length", Argument.Type.FLOAT);
		p.addNamedArgument("width", Argument.Type.FLOAT);
		p.addNamedArgument("height", Argument.Type.FLOAT);
		p.addNamedArgument("radius1", Argument.Type.FLOAT);
		p.addNamedArgument("radius2", Argument.Type.FLOAT);
		
		p.setArgumentDescription("box", "Header for calculating the volume of a box");
		p.setArgumentDescription("cylinder", "Header for calculating the volume of a cylinder");
		p.setArgumentDescription("sphere", "Header for calculating the volume of a sphere");
		p.setArgumentDescription("hollowSphere", "Header for calculating the volume of a hollow sphere");
		
		p.setArgumentDescription("length", "the length of the box");
		p.setArgumentDescription("width", "the width of the box");
		p.setArgumentDescription("height", "the height of the box or cylinder");
		p.setArgumentDescription("radius1", "the radius of the cylinder or sphere");
		p.setArgumentDescription("radius2", "the inner radius of the hollow sphere");
		
		p.setNamedArgumentGroupHeader("box");
		p.appendNamedArgumentGroupMember("box", "length");
		p.appendNamedArgumentGroupMember("box", "width");
		p.appendNamedArgumentGroupMember("box", "height");
		
		p.setNamedArgumentGroupHeader("cylinder");
		p.appendNamedArgumentGroupMember("box", "radius1");
		p.appendNamedArgumentGroupMember("box", "height");
		
		p.setNamedArgumentGroupHeader("sphere");
		p.appendNamedArgumentGroupMember("box", "radius1");
		
		p.setNamedArgumentGroupHeader("hollowSphere");
		p.appendNamedArgumentGroupMember("box", "radius1");
		p.appendNamedArgumentGroupMember("box", "radius2");
		
		p.parse(args);
		
		String shape = "";
		float volume = 0.0f;
		if(p.getArgumentValue("box"))
		{
			shape = "box";
			float length = p.getArgumentValue("length");
			float width = p.getArgumentValue("width");
			float height = p.getArgumentValue("height");
		
			volume = length * width * height;
		}
		if(p.getArgumentValue("cylinder"))
		{
			shape = "cylinder";
			float height = p.getArgumentValue("height");
			float radius = p.getArgumentValue("radius1");
			
			volume = Math.PI * Math.pow(radius, 2) * height;
		}
		if(p.getArgumentValue("sphere"))
		{
			shape = "sphere";
			float radius = p.getArgumentValue("radius1");
			
			volume = (4/3) * Math.PI * Math.POW(radius, 3);
		}
		if(p.getArgumentValue("hollowSphere"))
		{
			shape = "hollow sphere";
			float radius1 = p.getArgumentValue("radius1");
			float radius2 = p.getArgumentValue("radius2");
			
			float vol1 = (4/3) * Math.PI * Math.POW(radius, 3);
			float vol2 = (4/3) * Math.PI * Math.POW(radius, 3);
			if(vol1 > vol2)
			{
				volume = vol1 - vol2;
				System.out.println("The outer sphere volume is " + vol1);
				System.out.println("The inner sphere volume is " + vol2);
			}
			else
			{
				volume = vol2 - vol1;
				System.out.println("The outer sphere volume is " + vol2);
				System.out.println("The inner sphere volume is " + vol1);
			}
		}
		String response = ("The volume of the " + shape + " is: " + volume);
		System.out.println(response);
		
		System.out.println("\nI am making an xml file for the parser.");
		File f = new File("Volume Calculator.xml");
		XMLFactory.saveArgumentParser(p);
	}
}