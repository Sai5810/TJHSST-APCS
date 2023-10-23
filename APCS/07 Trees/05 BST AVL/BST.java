// Name: 
// Date: 

interface BSTinterface {
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

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
   /*  start the addBalanced methods */
   public void addBalanced(String value) {
      size++;
      root = balance(root, value);
   }
   public TreeNode balance(TreeNode y, String value){
      if (y == null)
         return (new TreeNode(value));
      if (value.compareTo((String) y.getValue()) < 0)
         y.setLeft(balance(y.getLeft(), value));
      else if (value.compareTo((String) y.getValue()) > 0)
         y.setRight(balance(y.getRight(), value));
      else return y;
      y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
      int balance =  height(y.getLeft()) - height(y.getRight());
      if(balance > 1) {
         if(value.compareTo((String) y.getLeft().getValue()) > 0) {
            y.setLeft(leftRotate(y.getLeft()));
         }
         y = rightRotate(y);
      } else if(balance < -1) {
         if (value.compareTo((String) y.getRight().getValue()) < 0) {
            y.setRight(rightRotate(y.getRight()));
         }
         y = leftRotate(y);
      }
      return y;
   }
   public TreeNode leftRotate(TreeNode y) {
      TreeNode x = y.getRight();
      TreeNode tmp = x.getLeft();
      x.setLeft(y);
      y.setRight(tmp);
      y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
      x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
      return x;
   }
   public TreeNode rightRotate(TreeNode y) {
      TreeNode x = y.getLeft();
      TreeNode tmp = x.getRight();
      x.setRight(y);
      y.setLeft(tmp);
      y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
      x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
      return x;
   }
   public int height(TreeNode x) {
      if(x == null) {
         return -1;
      } else {
         return x.getHeight();
      }
      /*if (x == null) return -1;
      else {
         int l = height(x.getLeft());
         int r = height(x.getRight());
         if (l > r) return(l + 1);
         else return(r + 1);
      }*/
   }
}