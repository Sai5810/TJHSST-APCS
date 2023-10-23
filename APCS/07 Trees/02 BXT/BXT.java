// Name: 
// Date:  
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings.
 */

import java.util.*;

public class BXT {
    private TreeNode root;

    public BXT() {
        root = null;
    }
    public TreeNode getRoot() {//for Grade-It
        return null;
    }

    public void buildTree(String str) {
        Stack<TreeNode> s = new Stack<>();
        for(String i: str.split(" ")) {
            if(i.matches("[+/*%-]")){
                TreeNode t1 = s.pop();
                TreeNode t2 = s.pop();
                s.push(new TreeNode(i, t2, t1));
            } else {
                s.push(new TreeNode(i));
            }
        }
        root = s.peek();
    }

    public double evaluateTree() {
        return evaluateNode(root);
    }

    private double evaluateNode(TreeNode t) { //recursive
        if(t == null) return 0;
        if(t.getLeft() == null && t.getRight() == null) return Double.parseDouble((String) t.getValue());
        switch((String)t.getValue()){
            case("+"):
                return evaluateNode(t.getLeft()) + evaluateNode(t.getRight());
            case("%"):
                return evaluateNode(t.getLeft()) % evaluateNode(t.getRight());
            case("-"):
                return evaluateNode(t.getLeft()) - evaluateNode(t.getRight());
            case("*"):
                return evaluateNode(t.getLeft()) * evaluateNode(t.getRight());
            case("/"):
                return evaluateNode(t.getLeft()) / evaluateNode(t.getRight());
        }
        return 0;
    }

    private double computeTerm(String s, double a, double b) {
        return 0;
    }

    private boolean isOperator(String s) {
        return false;
    }

    public String display() {
        return display(root, 0);
    }

    private String display(TreeNode t, int level) {
        if (t == null)
            return "";
        return display(t.getRight(), level + 1) + "\t".repeat(Math.max(0, level)) + t.getValue() + "\n" +
                display(t.getLeft(), level + 1);
    }

    public String inorderTraverse() {
        return inorderTraverse(root);
    }

    private String inorderTraverse(TreeNode t) {
        if (t == null) return "";
        return inorderTraverse(t.getLeft()) + t.getValue() + " " + inorderTraverse(t.getRight());
    }

    public String preorderTraverse() {
        return preorderTraverse(root);
    }

    private String preorderTraverse(TreeNode root) {
        if (root == null) return "";
        return root.getValue() + " " + preorderTraverse(root.getLeft()) + preorderTraverse(root.getRight());
    }

    /* extension */
    // public String inorderTraverseWithParentheses()
    // {
    // return inorderTraverseWithParentheses(root);
    // }
    //
    // private String inorderTraverseWithParentheses(TreeNode t)
    // {
    // return "";
    // }
}