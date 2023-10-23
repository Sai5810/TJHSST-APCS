// Name:
// Date:

import java.util.*;
/*****************************************

 Parentheses Match
 5 + 7		 good!
 ( 15 + -7 )		 good!
 ) 5 + 7 (		 BAD
 ( ( 5.0 - 7.3 ) * 3.5 )		 good!
 < { 5 + 7 } * 3 >		 good!
 [ ( 5 + 7 ) * ] 3		 good!
 ( 5 + 7 ) * 3		 good!
 5 + ( 7 * 3 )		 good!
 ( ( 5 + 7 ) * 3		 BAD
 [ ( 5 + 7 ] * 3 )		 BAD
 [ ( 5 + 7 ) * 3 ] )		 BAD
 ( [ ( 5 + 7 ) * 3 ]		 BAD
 ( ( ( ) $ ) )		 good!
 ( ) [ ]		 good!

 *******************************************/
public class ParenMatch {
   public static final String LEFT = "([{<";
   public static final String RIGHT = ")]}>";
   public static void main(String[] args) {
      System.out.println("Parentheses Match");
      ArrayList <String> parenExp = new ArrayList <> ();
      parenExp.add("[ ( 5 + 7 ) * 3 ] )");
      for (String s: parenExp) {
         boolean good = checkParen(s);
         if (good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }

   //returns the index of the left parentheses or -1 if is not
   public static int isLeftParen(String p) {
      return LEFT.indexOf(p);
   }

   //returns the index of the right parentheses or -1 if is not
   public static int isRightParen(String p) {
      return RIGHT.indexOf(p);
   }

   public static boolean checkParen(String exp) {
      Stack<Character> s = new Stack<>();
      for(char c : exp.toCharArray()) {
         if("{[<(".indexOf(c) != -1) {
            s.push(c);
         } else {
            switch(c) {
               case '}':
                  if(s.isEmpty() || s.peek() != '{') return false;
                  s.pop();
                  break;
               case '>':
                  if(s.isEmpty() || s.peek() != '<') return false;
                  s.pop();
                  break;
               case ')':
                  if(s.isEmpty() || s.peek() != '(') return false;
                  s.pop();
                  break;
               case ']':
                  if(s.isEmpty() || s.peek() != '[') return false;
                  s.pop();
                  break;
            }
         }
      }
      return s.isEmpty();
   }
}