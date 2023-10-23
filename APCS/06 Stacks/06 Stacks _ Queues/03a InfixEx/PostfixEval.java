// Name:
// Date:

import java.util.*;
/**********************************************
 Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120


 *************************************/
public class PostfixEval {
   public static final String operators = "+ - * / % ^ !";

   public static void main(String[] args) {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList <String> postfixExp = new ArrayList <> ();
      postfixExp.add("9 5 /");
      for (String pf: postfixExp) {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }

   public static double eval(String pf) {
      List <String> postfixParts = new ArrayList <> (Arrays.asList(pf.split(" ")));
      Stack<Double> s = new Stack<>();
      double temp = 0;
      for(String i: postfixParts){
         if(operators.contains(i)) {
            temp = s.pop();
            switch (i) {
               case "+":
                  s.push(temp + s.pop());
                  break;
               case "-":
                  s.push(s.pop() - temp);
                  break;
               case "*":
                  s.push(temp * s.pop());
                  break;
               case "/":
                  s.push(s.pop() / temp);
                  break;
               case "%":
                  s.push(s.pop() % temp);
                  break;
               case "^":
                  s.push(Math.pow(s.pop(), temp));
                  break;
               case "!":
                  double f = 1;
                  for (int j = 2; j <= temp; j++) {
                     f *= j;
                  }
                  s.push(f);
                  break;
            }
         } else {
            s.push(Double.parseDouble(i));
         }
      }
      return s.peek();
   }

   public static double eval(double a, double b, String ch) {
      return 0;
   }

   public static boolean isOperator(String op) {
      return false;
   }
}

