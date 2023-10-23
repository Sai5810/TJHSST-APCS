// Name:
// Date:

import java.util. * ;

public class TreeLab {
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool";
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below

   public static void main(String[] args) {
      root = buildTree(root, s);
      System.out.print(display(root, 0));

      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));

      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));

      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));

      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }

   public static TreeNode buildTree(TreeNode root, String s) {
      root = new TreeNode("" + s.charAt(1), null, null);
      for (int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5);
      insert(root, "B", 18, 5);
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level) {
      TreeNode p = t;
      for (int k = level - 2; k > 0; k--) {
         if ((pos & (1 << k)) == 0) p = p.getLeft();
         else p = p.getRight();
      }
      if ((pos & 1) == 0) p.setLeft(new TreeNode(s, null, null));
      else p.setRight(new TreeNode(s, null, null));
   }

   private static String display(TreeNode t, int level) {
      // turn your head towards left shoulder visualize tree
      if (t == null) return "";
      return display(t.getRight(), level + 1) + //recurse right
              "\t".repeat(Math.max(0, level)) +
              t.getValue() + "\n" +
              display(t.getLeft(), level + 1);
   }

   public static String preorderTraverse(TreeNode t) {
      String toReturn = "";
      if (t == null) return "";
      toReturn += t.getValue() + " "; //preorder visit
      toReturn += preorderTraverse(t.getLeft()); //recurse left
      toReturn += preorderTraverse(t.getRight()); //recurse right
      return toReturn;
   }

   public static String inorderTraverse(TreeNode t) {
      String toReturn = "";
      if (t == null) return "";
      toReturn += inorderTraverse(t.getLeft()); //recurse left
      toReturn += t.getValue() + " "; //preorder visit
      toReturn += inorderTraverse(t.getRight()); //recurse right
      return toReturn;
   }

   public static String postorderTraverse(TreeNode t) {
      String toReturn = "";
      if (t == null) return "";
      toReturn += postorderTraverse(t.getLeft()); //recurse left
      toReturn += postorderTraverse(t.getRight()); //recurse right
      toReturn += t.getValue() + " "; //preorder visit
      return toReturn;
   }

   public static int countNodes(TreeNode t) {
      if (t == null) return 0;
      return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
   }

   public static int countLeaves(TreeNode t) {
      if (t == null) return 0;
      else if(t.getLeft() == null && t.getRight() == null) return 1;
      else return countLeaves(t.getLeft()) + countLeaves(t.getRight());
   }

   /*  there are clever ways and hard ways to count grandparents  */
   public static int countGrandParents(TreeNode t) {
      Queue<TreeNode> q = new LinkedList<>();
      int x = 0;
      q.add(t);
      while(!q.isEmpty()) {
         TreeNode tem = q.remove();
         boolean flag = false;
         if(tem.getLeft() != null) {
            if(tem.getLeft().getLeft() != null || tem.getLeft().getRight() != null) {
               flag = true;
            }
            q.add(tem.getLeft());
         }
         if(tem.getRight() != null) {
            if(tem.getRight().getLeft() != null || tem.getRight().getRight() != null) {
               flag = true;
            }
            q.add(tem.getRight());
         }
         if(flag) x++;
      }
      return x;
   }

   public static int countOnlys(TreeNode t) {
      int s = 0;
      if(t.getLeft() != null) {
         if (t.getRight() != null){
            s += countOnlys(t.getLeft()) + countOnlys(t.getRight());
         } else {
            s += countOnlys(t.getLeft()) + 1;
         }
      } else if (t.getRight() != null) {
         s += countOnlys(t.getRight()) + 1;
      }
      return s;
   }

   /* returns the max of the heights to the left and the heights to the right
      returns -1 in case the tree is null
     */
   public static int height(TreeNode t) {
      if (t == null) return -1;
      return Math.max(height(t.getLeft()), height(t.getRight())) + 1;
   }

   /* return the max of the sum of the heights to the left and the heights to the right
    */
   public static int longestPath(TreeNode t) {
      Queue<TreeNode> q = new LinkedList<>();
      q.add(t);
      int mx = height(t.getLeft()) + height(t.getRight()) + 2;
      while(!q.isEmpty()) {
         TreeNode tem = q.remove();
         mx = Math.max(height(tem.getLeft()) + height(tem.getRight()) + 2, mx);
         if(tem.getLeft() != null) {
            q.add(tem.getLeft());
         }
         if(tem.getRight() != null) {
            q.add(tem.getRight());
         }
      }
      return mx;
   }

   /*  Object must be cast to Comparable in order to call .compareTo
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t) {
      Queue<TreeNode> q = new LinkedList<>();
      Comparable mn = (Comparable)t.getValue();
      q.add(t);
      while(!q.isEmpty()) {
         TreeNode tem = q.remove();
         if(((Comparable)tem.getValue()).compareTo(mn) < 0) {
            mn = (Comparable) tem.getValue();
         }
         if(tem.getLeft() != null) {
            q.add(tem.getLeft());
         }
         if(tem.getRight() != null) {
            q.add(tem.getRight());
         }
      }
      return mn;
   }

   /*  Object must be cast to Comparable in order to call .compareTo
    */
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t) {
      Queue<TreeNode> q = new LinkedList<>();
      Comparable mn = (Comparable)t.getValue();
      q.add(t);
      while(!q.isEmpty()) {
         TreeNode tem = q.remove();
         if(((Comparable)tem.getValue()).compareTo(mn) > 0) {
            mn = (Comparable) tem.getValue();
         }
         if(tem.getLeft() != null) {
            q.add(tem.getLeft());
         }
         if(tem.getRight() != null) {
            q.add(tem.getRight());
         }
      }
      return mn;
   }

   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t) {
      Queue<TreeNode> q = new LinkedList<>();
      StringBuilder sb = new StringBuilder();
      q.add(t);
      while(!q.isEmpty()) {
         TreeNode tem = q.remove();
         sb.append(tem.getValue());
         if(tem.getLeft() != null) {
            q.add(tem.getLeft());
         }
         if(tem.getRight() != null) {
            q.add(tem.getRight());
         }
      }
      return sb.toString();
   }
}
