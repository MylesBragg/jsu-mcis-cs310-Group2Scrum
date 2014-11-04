import edu.jsu.mcis.*;

import java.io.*;
import java.util.*;

public class simpleCalc {
	
	public static void main(String [] args) {
		int operand1, operand2;
		String operator, equation;
		
		ArgParser newParser = new ArgParser("simpleCalc");
		
		newParser.addArg("operand1", "Enter the first whole number", ArgValues.Type.INT);
		newParser.addArg("operand2", "Enter the second whole number", ArgValues.Type.INT);
		newParser.addArg("operator", "Enter the operator you wish to use", ArgValues.Type.STRING);
		newParser.addOptArg("power", "p", "Enter a number to act as a power", ArgValues.Type.INT);
		
		newParser.setOptArgRequired("power");
		
		newParser.parser(newParser.fromArgstoString(args));
		
		if (Array.asLists(args).contains("-h") || Array.asLists(args).contains("--help")){
		
		}
		else {
			operand1 = newParser.getArgumentValue("operand1");
			operand2 = newParser.getArgumentValue("operand2");
			operator = newParser.getArgumentValue("operator");
		
			switch(operator) {
				case "+":
					if (newParser.getArgumentValue("power").equals("Error key not found.")){
						equation = operand1 + operator + operand2 + "=";
						System.out.println(equation + (operand1 + operand2));
					}
					else {
						int power = newParser.getArgumentValue("power");
						equation = "(" + operand1 + operator + operand2 + ")^" + power + "=";
						double answer = Math.pow((operand1 + operand2), power);
						System.out.println(equation + answer);
					}
					
				case "-":
					if (newParser.getArgumentValue("power").equals("Error key not found.")){
						equation = operand1 + operator + operand2 + "=";
						System.out.println(equation + (operand1 - operand2));
					}
					else {
						int power = newParser.getArgumentValue("power");
						equation = "(" + operand1 + operator + operand2 + ")^" + power + "=";
						double answer = Math.pow((operand1 - operand2), power);
						System.out.println(equation + answer);
					}
				case "*":
					if (newParser.getArgumentValue("power").equals("Error key not found.")){
						equation = operand1 + operator + operand2 + "=";
						System.out.println(equation + (operand1 * operand2));
					}
					else {
						int power = newParser.getArgumentValue("power");
						equation = "(" + operand1 + operator + operand2 + ")^" + power + "=";
						double answer = Math.pow((operand1 * operand2), power);
						System.out.println(equation + answer);
					}
				case "/":
					if (newParser.getArgumentValue("power").equals("Error key not found.")){
						equation = operand1 + operator + operand2 + "=";
						System.out.println(equation + (operand1 / operand2));
					}
					else {
						int power = newParser.getArgumentValue("power");
						equation = "(" + operand1 + operator + operand2 + ")^" + power + "=";
						double answer = Math.pow((operand1 / operand2), power);
						System.out.println(equation + answer);
					}
				default:
					System.out.println("You must select a proper operator.");
			}
		}
		
		
	}
}