// Name:
// Date:
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval {
   public static final String LEFT = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";

   public static void main(String[] args) {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      ArrayList <String> infixExp = new ArrayList <> ();
      infixExp.add("8 + 1 * 2 - 9 / 3");
      for (String infix: infixExp) {
         String pf = infixToPostfix(infix); //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf);
         //System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }

   public static String infixToPostfix(String infix) {
      List <String> nums = new ArrayList <> (Arrays.asList(infix.split(" ")));
      StringBuilder s = new StringBuilder ();
      Stack<String> ops = new Stack<>();
      HashMap<String, Integer> prec = new HashMap<>();
      prec.put("+", 0);
      prec.put("-", 0);
      prec.put("*", 1);
      prec.put("/", 1);
      prec.put("%", 1);
      prec.put("^", 2);
      prec.put("!", 3);
      for(String i : nums) {
         if(Character.isLetterOrDigit(i.charAt(0))) {
            s.append(i);
         } else if (i.equals(")")) {
            while (!ops.peek().equals("(")) {
               s.append(ops.pop());
            }
            ops.pop();
         } else {
            if (!ops.isEmpty() && !i.equals("(") && !ops.peek().equals("(") && prec.get(i) <= prec.get(ops.peek())) {
               while (!ops.isEmpty() && !"()".contains(ops.peek()) && prec.get(i) <= prec.get(ops.peek())) {
                  s.append(ops.pop());
               }
            }
            ops.push(i);
         }
      }
      while(!ops.empty()) {
         s.append(ops.pop());
      }
      return s.toString().replace("", " ").trim();
   }

   //returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2) {
      return true;
   }
}


