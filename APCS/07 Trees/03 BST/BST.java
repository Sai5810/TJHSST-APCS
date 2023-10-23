//Name: 
//Date:

import java.util.LinkedList;
import java.util.Queue;

interface BSTinterface {
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   //public boolean remove(String obj);
   public String min();
   public String max();
   public String toString();
}

/*******************
 Represents a binary search tree holding Strings.
 Implements (most of) BSTinterface, above.
 The recursive methods all have a public method calling a private helper method.
 Copy the display() method from TreeLab.
 **********************/
class BST implements BSTinterface {
   private TreeNode root;
   private int size;
   public BST() {
      root = null;
      size = 0;
   }
   public int size() {
      return size;
   }
   public TreeNode getRoot() {
      return root;
   }
   /***************************************
    @param s -- one string to be inserted
    ****************************************/
   public void add(String s) {
      size++;
      root = add(root, s);
   }
   //recursive helper method
   private TreeNode add(TreeNode t, String s)
   {
      if(t == null) return new TreeNode(s, null, null);
      if(s.compareTo((String) t.getValue()) <= 0) t.setLeft(add(t.getLeft(), s));
      else t.setRight(add(t.getRight(), s));
      return t;
   }

   public String display() {
      return display(root, 0);
   }
   //recursive helper method
   private String display(TreeNode t, int level) {
      if (t == null) return "";
      return display(t.getRight(), level + 1) + "\t".repeat(Math.max(0, level)) + t.getValue() + "\n" +
              display(t.getLeft(), level + 1);
   }

   public boolean contains(String obj) {
      return contains(root, obj);
   }
   //recursive helper method
   private boolean contains(TreeNode t, String x) {
      while(t != null) {
         if(x.equals(t.getValue())) return true;
         if(x.compareTo((String) t.getValue()) < 0) t = t.getLeft();
         else t = t.getRight();
      }
      return false;
   }

   public String min() {
      return min(root);
   }
   //use iteration
   private String min(TreeNode t) {
      if(t == null) return null;
      while(t.getLeft() != null) t = t.getLeft();
      return (String) t.getValue();
   }

   public String max() {
      return max(root);
   }
   //recursive helper method
   private String max(TreeNode t) {
      if(t == null) return null;
      while(t.getRight() != null) t = t.getRight();
      return (String) t.getValue();
   }

   public String toString() {
      return toString(root);
   }
   //an in-order traversal.  Use recursion.
   private String toString(TreeNode t) {
      if (t == null) return "";
      return toString(t.getLeft()) + t.getValue() + " " + toString(t.getRight());
   }
}