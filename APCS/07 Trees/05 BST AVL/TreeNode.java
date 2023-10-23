 /* TreeNode class for the AP Exams */
 public class TreeNode {
      private Object value; 
      private TreeNode left, right;
      private int height;
   
       public TreeNode(Object initValue) {
         value = initValue; 
         left = null; 
         right = null;
         height = 1;
      }
   
       public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight) {
         value = initValue; 
         left = initLeft; 
         right = initRight;
         height = 1;
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
      public int getHeight() {
           return height;
      }
     public void setHeight(int val) {
         height = val;
     }
}
