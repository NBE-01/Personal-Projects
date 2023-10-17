/*
 *Neil Burman 
 *2023
 *
 * Main that contains main method that calls postfix class
 * */
import java.util.Scanner;
import java.util.EmptyStackException;
public class CalculatorClient{
	 public static void main(String[] args)  {
		 
		  Scanner in = new Scanner(System.in);
		  String expression ="";
		 
		  System.out.println("Enter the expression: ");//getting the infix form
		  expression = in.nextLine();
		  expression = expression + " ";
		  String post = Postfix.convertToPostfix(expression);//converts to postfix
		  System.out.println(post);//prints postfix form
		  System.out.println(Postfix.evaluatePostfix(post));  //displays the answer
	 }	
}

