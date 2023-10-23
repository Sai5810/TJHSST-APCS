import java.util.*;
import java.io.*;
/*  
Test your AVL tree with this driver.
*/

public class BST_AVL_driver
{
   public static void main( String[] args ) throws Exception
   {
      BST balancedTree = new BST();  
      //Scanner in = new Scanner(System.in);
      //System.out.print("Type in a line: ");  
      //String line = in.nextLine();
      //String line = "K Z N";
      //String line = "5 3 2";  //left-left case (right rotation)
      //String line = "3 5 7";  //right-right case (left rotation)
      //String line = "5 3 4";  //left-right case (left rotation then right rotation)
      //String line = "3 5 4";  //right-left case (right rotation then left rotation)
      String line = "a b c d e f g";
      //String line = "g f e d c b a";
      //String line = "J E H B F G D"; //doubleright, doubleright, doubleright
      //String line = "L P N U R O"; //doubleleft, doubleleft, doubleleft
      //String line = "M H D F E J U L X I P K"; //left, doubleleft, doubleright  
      
      String[] str = line.split(" ");
      for(String item : str)
      {
         balancedTree.addBalanced(item);  //implement addBalanced() in your BST class
         System.out.println(balancedTree.display());
         System.out.println("------------------------------");
      }
   }
}

