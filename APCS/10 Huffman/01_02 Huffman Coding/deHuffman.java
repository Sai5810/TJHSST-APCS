// Name:              Date:
import java.util.*;
import java.io.*;
public class deHuffman {
   public static void main(String[] args) throws IOException {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message." + middlePart + ".txt"));
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme." + middlePart + ".txt"));

      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);

      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines) {
      TreeNode root = new TreeNode(null);
      while(huffLines.hasNextLine()) {
         String s = huffLines.nextLine();
         TreeNode t = root;
         for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == '0') {
               t.setLeft(null);
               t = t.getLeft();
            } else {
               t.setRight(null);
               t = t.getRight();
            }
            if (t.getLeft() == null && t.getRight() == null) {
               t.setValue(s.charAt(i));
               t = root;
            }
         }
      }
      return root;
   }
   public static String dehuff(String text, TreeNode root) {
      StringBuilder s = new StringBuilder();
      TreeNode t = root;
      for (int i = 0; i < text.length(); i++){
         if(text.charAt(i) == '0') {
            t = t.getLeft();
         } else {
            t = t.getRight();
         }
         if (t.getLeft() == null && t.getRight() == null) {
            s.append(t.getValue());
            t = root;
         }
      }
      return s.toString();
   }
}

/* TreeNode class for the AP Exams */
class TreeNode {
   private Object value;
   private TreeNode left, right;

   public TreeNode(Object initValue) {
      value = initValue;
      left = null;
      right = null;
   }

   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight) {
      value = initValue;
      left = initLeft;
      right = initRight;
   }

   public Object getValue() {
      return value;
   }

   public TreeNode getLeft() {
      return left;
   }

   public TreeNode getRight() {
      return right;
   }

   public void setValue(Object theNewValue) {
      value = theNewValue;
   }

   public void setLeft(TreeNode theNewLeft) {
      left = theNewLeft;
   }

   public void setRight(TreeNode theNewRight) {
      right = theNewRight;
   }
}