import edu.jsu.mcis.*;

import java.io.*;
import java.util.*;

public class SimpleCalc {
	
	public static void main(String [] args) {
		int operand1, operand2;
		String operator, equation;
		
		ArgumentParser p = new ArgumentParser("SimpleCalc");
		
		p.addPositionalArgument("operand1", Argument.Type.INT);
		p.addPositionalArgument("operand2", Argument.Type.INT);
		p.addPositionalArgument("operator", Argument.Type.STRING);
		
		p.addNamedArgument("exponent", Argument.Type.INT);
		p.setNamedArgumentRequired("exponent");
		p.setNamedArgumentAlternateName("exponent", "p");
		
		p.setArgumentDescription("operand1", "Enter the first whole number");
		p.setArgumentDescription("operand2", "Enter the second whole number");
		p.setArgumentDescription("operator", "Enter the operator you wish to use");
		p.setArgumentDescription("exponent", "Enter a number to act as an exponent");
		
		p.parse(args);
		
		if (Arrays.asList(args).contains("-h") || Arrays.asList(args).contains("--help")){
		
		}
		else {
			operand1 = p.getArgumentValue("operand1");
			operand2 = p.getArgumentValue("operand2");
			operator = p.getArgumentValue("operator");
		
			switch(operator) {
				case "add":
					if (p.getArgumentValue("exponent").equals("Error key not found.")){
						equation = operand1 + " + " + operand2 + " = ";
						System.out.println(equation + (operand1 + operand2));
					}
					else {
						int power = p.getArgumentValue("exponent");
						equation = "(" + operand1 + " + " + operand2 + ")^" + power + " = ";
						double answer = Math.pow((operand1 + operand2), power);
						System.out.println(equation + answer);
					}
					break;
				case "subtract":
					if (p.getArgumentValue("exponent").equals("Error key not found.")){
						equation = operand1 + " - " + operand2 + " = ";
						System.out.println(equation + (operand1 - operand2));
					}
					else {
						int power = p.getArgumentValue("exponent");
						equation = "(" + operand1 + " - " + operand2 + ")^" + power + " = ";
						double answer = Math.pow((operand1 - operand2), power);
						System.out.println(equation + answer);
					}
					break;
				case "multiply":
					if (p.getArgumentValue("exponent").equals("Error key not found.")){
						equation = operand1 + " * " + operand2 + " = ";
						System.out.println(equation + (operand1 * operand2));
					}
					else {
						int power = p.getArgumentValue("exponent");
						equation = "(" + operand1 + " * " + operand2 + ")^" + power + " = ";
						double answer = Math.pow((operand1 * operand2), power);
						System.out.println(equation + answer);
					}
					break;
				case "divide":
					if (p.getArgumentValue("exponent").equals("Error key not found.")){
						equation = operand1 + " / " + operand2 + " = ";
						System.out.println(equation + (operand1 / operand2));
					}
					else {
						int power = p.getArgumentValue("exponent");
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