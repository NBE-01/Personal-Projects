/**
   A class that provides a static method to create a postfix expression
   equivalent to a given infix expression and a static method to evaluate
   a given postfix expression.
*/


public class Postfix 
{
   /** Creates a postfix expression that represents a given infix expression.
    
       @param infix  A string that is a valid infix expression.
       @return  A string that is the postfix expression equivalent to infix. */
   public static String convertToPostfix(String infix)
   {
      StackInterface<Character> operatorStack = new LinkedStack<>();//holds operators in a stack to keep track of order
      
      StringBuilder postfix = new StringBuilder();// holds the actual postfix string
      int characterCount = infix.length();
      char topOperator;
      for (int index = 0; index < characterCount; index++)
      {
         boolean done = false;
         Character nextCharacter = infix.charAt(index);
        //System.out.println(":"+nextCharacter+":");
      
         if (nextCharacter>='0' && nextCharacter<='9' || nextCharacter==' ') {
             postfix = postfix.append(nextCharacter);
           //System.out.println(":"+nextCharacter+":");
         }
         else
         {
            switch (nextCharacter)
            {

               
               case '+': case '-': case '*': case '/'://if it is a operator
                  while (!done && !operatorStack.isEmpty())
                  {
                     topOperator = operatorStack.peek();

                     if (getPrecedence(nextCharacter) <= getPrecedence(topOperator))//checks if multiplication/division so they are done first
                     {
                        postfix = postfix.append(topOperator);
                       // System.out.println(":"+topOperator+":");

                        operatorStack.pop();
                     } 
                     else
                        done = true;
                  } // end while
                  index++; //skip the space after the operator
                  operatorStack.push(nextCharacter);
                  break;
               
               

               default: break; // Ignore unexpected characters
            } // end switch
         } // end if
      } // end for
    
      while (!operatorStack.isEmpty())
      {
         topOperator = operatorStack.pop();
         postfix = postfix.append(topOperator);
      } // end while

      return postfix.toString();
   } // end convertToPostfix

   // Indicates the precedence of a given operator.
   // Precondition: operator is a character that is (, ), +, -, *, /, or ^.
   // Returns an integer that indicates the precedence of operator:
   //         0 if ( or ), 1 if + or -, 2 if * or /, 3 if ^, 
   //         -1 if anything else. */
  	/*
  	 * Computes the operation given to it
  	 * @param operator symbol that decides precedence 
  	 * @return value higher if multiplication or division so that they are done first
  	 * */
   private static int getPrecedence(char operator)//gets precedence
   {
      switch (operator)
      {
         
         case '+': case '-': return 1;
         case '*': case '/': return 2;
         
      } // end switch
    
      return -1;
   } // end getPrecedence

   /** Evaluates a given postfix expression.
       @param postfix  a string that is a valid postfix expression.
       @return  the value of the postfix expression. */
   
   public static double evaluatePostfix(String postfix)
   {
      StackInterface<Double> valueStack = new LinkedStack<>();//holds all the values
      int characterCount = postfix.length();
      String strnumber = "";
      for (int index = 0; index < characterCount; index++)//length of the postfix expression
      {
         Character nextCharacter = postfix.charAt(index);
         if(nextCharacter== ' ') {
        	 
             Double number = Double.parseDouble(strnumber);
             valueStack.push(number);
             strnumber = "";
             //System.out.println("Pushed on stack "+ number);
         }
         else if(nextCharacter >= '0'&& nextCharacter <= '9' ) {//checks to see if it is a number
        	 strnumber += nextCharacter.toString();
         }
         else if(nextCharacter == '+'||nextCharacter == '-'||nextCharacter == '*'||nextCharacter == '/') {//checks if operator
        	 Double operandTwo = valueStack.pop();
             Double operandOne = valueStack.pop();
             Double result = compute(operandOne, operandTwo, nextCharacter);
             valueStack.push(result);
         }
         

      } // end for
      
      return (valueStack.peek()).doubleValue();
   } // end evaluatePostfix

   	/*
   	 * Computes the operation given to it
   	 * @param operandOne number at the bottom of the stack that operation is occuring to
   	 * @param operandTwo number at the top of the stack 
   	 * @param operator operator which dictates what operation is to be done
   	 * @return result is the arithmetic answer between both operandOne and operandTwo
   	 * */
   private static Double compute(Double operandOne, Double operandTwo, char operator)
   {
      double result;
      
      switch (operator)
      {
         case '+': 
            result = operandOne.doubleValue() + operandTwo.doubleValue();
            break;
            
         case '-': 
            result = operandOne.doubleValue() - operandTwo.doubleValue();
            break;

         case '*':
            result = operandOne.doubleValue() * operandTwo.doubleValue();
             break;

         case '/': 
            result = operandOne.doubleValue() / operandTwo.doubleValue();
            break;

            
         default:    // Unexpected character
            result = 0;
            break;
      } // end switch

      return result;
   } // end compute
} // end Postfix