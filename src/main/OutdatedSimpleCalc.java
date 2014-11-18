import edu.jsu.mcis.*;

import java.io.*;
import java.util.*;

public class SimpleCalc {
	
	public static void main(String [] args) {
		int operand1, operand2;
		String operator, equation;
		
		ArgParser newParser = new ArgParser("SimpleCalc");
		
		newParser.addArg("operand1", "Enter the first whole number", ArgValues.Type.INT);
		newParser.addArg("operand2", "Enter the second whole number", ArgValues.Type.INT);
		newParser.addArg("operator", "Enter the operator you wish to use", ArgValues.Type.STRING);
		newParser.addOptArg("power", "p", "Enter a number to act as a power", ArgValues.Type.INT);
		
		newParser.setOptArgRequired("power");
		String myString = newParser.fromArgsToString(args);
		newParser.parse(myString);
		
		if (Arrays.asList(args).contains("-h") || Arrays.asList(args).contains("--help")){
		
		}
		else {
			operand1 = newParser.getArgValue("operand1");
			operand2 = newParser.getArgValue("operand2");
			operator = newParser.getArgValue("operator");
		
			switch(operator) {
				case "add":
					if (newParser.getArgValue("power").equals("Error key not found.")){
						equation = operand1 + " + " + operand2 + " = ";
						System.out.println(equation + (operand1 + operand2));
					}
					else {
						int power = newParser.getArgValue("power");
						equation = "(" + operand1 + " + " + operand2 + ")^" + power + " = ";
						double answer = Math.pow((operand1 + operand2), power);
						System.out.println(equation + answer);
					}
					break;
				case "subtract":
					if (newParser.getArgValue("power").equals("Error key not found.")){
						equation = operand1 + " - " + operand2 + " = ";
						System.out.println(equation + (operand1 - operand2));
					}
					else {
						int power = newParser.getArgValue("power");
						equation = "(" + operand1 + " - " + operand2 + ")^" + power + " = ";
						double answer = Math.pow((operand1 - operand2), power);
						System.out.println(equation + answer);
					}
					break;
				case "multiply":
					if (newParser.getArgValue("power").equals("Error key not found.")){
						equation = operand1 + " * " + operand2 + " = ";
						System.out.println(equation + (operand1 * operand2));
					}
					else {
						int power = newParser.getArgValue("power");
						equation = "(" + operand1 + " * " + operand2 + ")^" + power + " = ";
						double answer = Math.pow((operand1 * operand2), power);
						System.out.println(equation + answer);
					}
					break;
				case "divide":
					if (newParser.getArgValue("power").equals("Error key not found.")){
						equation = operand1 + " / " + operand2 + " = ";
						System.out.println(equation + (operand1 / operand2));
					}
					else {
						int power = newParser.getArgValue("power");
						equation = "(" + operand1 + " / " + operand2 + ")^" + power + " = ";
						double answer = Math.pow((operand1 / operand2), power);
						System.out.println(equation + answer);
					}
					break;
				default:
					System.out.println("You must select a proper operator.");
			}
		}
		
		
	}
}