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
		
		newParser.parse(newParser.fromArgsToString(args));
		
		operand1 = newParser.getArgValue("operand1");
		operand2 = newParser.getArgValue("operand2");
		operator = newParser.getArgValue("operator");
		equation = operand1 + operator + operand2 + "=";
		
		switch(operator) {
			case "+":
				System.out.println(equation + (operand1 + operand2));
				break;
			case "-":
				System.out.println(equation + (operand1 - operand2));
				break;
			case "*":
				System.out.println(equation + (operand1 * operand2));
				break;
			case "/":
				System.out.println(equation + (operand1 / operand2));
				break;
			default:
				System.out.println("You must select a proper operator.");
		}
		
	}
}