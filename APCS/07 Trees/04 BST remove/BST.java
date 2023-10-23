// Name: 
// Date: 

interface BSTinterface {
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
 Copy your BST code.  Implement the remove() method.
 Test it with BST_Delete.java
 **********************/
public class BST implements BSTinterface {
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
   private TreeNode add(TreeNode t, String s) {
      if (t == null) return new TreeNode(s, null, null);
      if (s.compareTo((String) t.getValue()) <= 0) t.setLeft(add(t.getLeft(), s));
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
      while (t != null) {
         if (x.equals(t.getValue())) return true;
         if (x.compareTo((String) t.getValue()) < 0) t = t.getLeft();
         else t = t.getRight();
      }
      return false;
   }

   public String min() {
      return min(root);
   }
   //use iteration
   private String min(TreeNode t) {
      if (t == null) return null;
      while (t.getLeft() != null) t = t.getLeft();
      return (String) t.getValue();
   }

   public String max() {
      return max(root);
   }
   //recursive helper method
   private String max(TreeNode t) {
      if (t == null) return null;
      while (t.getRight() != null) t = t.getRight();
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
   public void remove(String target) {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target) {
      if(current == null) return null;
      if(target.compareTo((String) current.getValue()) < 0) {
         current.setLeft(remove(current.getLeft(), target));
      } else if(target.compareTo((String) current.getValue()) > 0) {
         current.setRight(remove(current.getRight(), target));
      } else {
         if(current.getLeft() == null && current.getRight() == null) {
            return null;
         } else if(current.getLeft() != null && current.getRight() != null) {
            TreeNode t = current.getRight();
            while (t.getLeft() != null) t = t.getLeft();
            current.setValue(t.getValue());
            current.setRight(remove(current.getRight(), (String) t.getValue()));
            return current;
         } else {
            return ((current.getRight() != null) ? current.getRight() : current.getLeft());
         }
      }
      return current;
   }
}