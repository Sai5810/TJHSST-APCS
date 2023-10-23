import java.util.*;

/*  Driver for the BXT class.
 *  Input: a postfix string with space delimited tokens.
 */
public class BXT_Driver {
   public static void main(String[] args) {
      ArrayList <String> postExp = new ArrayList <> ();
      /*postExp.add("a b c + - d *");
      postExp.add("20.0 3.0 -4.0 + *");
      postExp.add("2 3 + 5 / 4 5 - *");
      postExp.add("5.6");*/
      postExp.add("8 4 + 3 * 5 2 ^ -");

      for (String postfix: postExp) {
         System.out.println("Postfix Exp: " + postfix);
         BXT tree = new BXT();
         tree.buildTree(postfix);
         System.out.println("BXT: ");
         System.out.println(tree.display());
         System.out.print("Infix order:  ");
         System.out.println(tree.inorderTraverse());
         System.out.print("Prefix order:  ");
         System.out.println(tree.preorderTraverse());
         System.out.print("Evaluates to " + tree.evaluateTree());
         System.out.println("\n------------------------");
      }

      /*  extension:  prints parentheses */
      //tree2.buildTree("20.0 3.0 -4 + *");   // "20.0 * ( 3.0 + -4 )"
      //System.out.println(tree2.inorderTraverseWithParentheses());
   }
}