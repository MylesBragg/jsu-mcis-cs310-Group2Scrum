import edu.jsu.mcis.*;

import java.io.*;
import java.util.*;

public class VolumeCalculator
{
	public static void main(String[] args)
	{
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
		p.appendNamedArgumentGroupMember("cylinder", "radius1");
		p.appendNamedArgumentGroupMember("cylinder", "height");
		
		p.setNamedArgumentGroupHeader("sphere");
		p.appendNamedArgumentGroupMember("sphere", "radius1");
		
		p.setNamedArgumentGroupHeader("hollowSphere");
		p.appendNamedArgumentGroupMember("hollowSphere", "radius1");
		p.appendNamedArgumentGroupMember("hollowSphere", "radius2");
		
		p.parse(args);
		
		String shape = "";
		double volume = 0.3;
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
			
			double vol1 = 0.3;
			double vol2 = 0.3;
			vol1 = (4/3) * Math.PI * Math.POW(radius, 3);
			vol2 = (4/3) * Math.PI * Math.POW(radius, 3);
			
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