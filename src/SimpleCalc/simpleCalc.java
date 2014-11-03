import edu.jsu.mcis.*;

import java.io.*;
import java.util.*;

public class simpleCalc {
	
	public static void main(String [] args) {
		int operand1, operand2;
		String operator, equation;
		
		ArgParser newParser = new ArgParser("simpleCalc");
		
		newParser.addArg("operand1", "Enter the first whole number", INT);
		newParser.addArg("operand2", "Enter the second whole number", INT);
		newParser.addArg("operator", "Enter the operator you wish to use", STRING);
		
		newParser.parser(newParser.fromArgstoString(args));
		
		operand1 = newParser.getArgumentValue("operand1");
		operand2 = newParser.getArgumentValue("operand2");
		operator = newParser.getArgumentValue("operator");
		equation = operand1 + operator + operand2 + "=";
		
		switch(operator) {
			case "+":
				System.out.println(equation + (operand1 + operand2));
			case "-":
				System.out.println(equation + (operand1 - operand2));
			case "*":
				System.out.println(equation + (operand1 * operand2));
			case "/":
				System.out.println(equation + (operand1 / operand2));
			default:
				System.out.println("You must select a proper operator.");
		}
		
	}
}