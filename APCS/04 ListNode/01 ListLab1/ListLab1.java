// Name:     
// Date:
import java.util.*;
public class ListLab1 {
   public static void main(String[] args) {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer", new ListNode("science", new ListNode("java",
              new ListNode("coffee", head))));
      print(head);
      print(head);
      //uncomment the code below for ListLab1 Assignment
      ListNode a = copyNode(head);
      System.out.println("The head has a value \"" + head.getValue() + "\" at " + head);
      System.out.println("The copy of head has a value of \"" + a.getValue() + "\" at " + a);
      System.out.print("Copy the list: ");
      ListNode copy = copyList(head);
      print(copy);
      System.out.print("The rest of the list: ");
      ListNode theRest = rest(copy);
      print(theRest);
      System.out.println("First of the rest = " + first(theRest));
      System.out.println("Second of the rest = " + second(theRest));
      ListNode p = pointerToLast(theRest);
      System.out.println("Pointer to Last = " + p.getValue() + " at " + p);
      ListNode c = copyOfLast(theRest);
      System.out.println("Copy of Last =    " + c.getValue() + " at " + c);
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in .next();
      theRest = insertFirst(theRest, x);
      theRest = insertLast(theRest, x);
      print(theRest);
   }
   public static void print(ListNode head) {
      System.out.print("[");
      while (head != null) {
         System.out.print(head.getValue());
         head = head.getNext();
         if (head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   // returns a new node that is a copy of the argument node.
   public static ListNode copyNode(ListNode arg) {
      return new ListNode(arg.getValue(), null);
   }
   // returns a new list that is a copy of the original list. this method is recursive!
   // example call:  ListNode head2 = copyList(head);
   public static ListNode copyList(ListNode arg) {
      ListNode copy = new ListNode(null, arg);
      return copy.getNext();
   }
   //returns a new linked list containing copies of each node in
   // the original list except the first node, maintaining the
   // order of the original list.  It is not correct to just
   // return a pointer to the 2nd node of the original linked list.  This method is recursive.
   public static ListNode rest(ListNode h) {
      ListNode rev = new ListNode(h.getValue(), null);
      h = h.getNext();
      while (h != null) {
         rev = new ListNode(h.getValue(), rev);
         h = h.getNext();
      }
      ListNode copy = new ListNode(rev.getValue(), null);
      rev = rev.getNext();
      while (rev != null) {
         copy = new ListNode(rev.getValue(), copy);
         rev = rev.getNext();
      }
      return copy.getNext();
   }
   // returns the value of the first node, or null if the list is empty
   public static Object first(ListNode head) {
      return head.getValue();
   }
   // returns the value of the second node, or null if the list is empty or if there is only one node.
   // hint:  second could call the first of rest.
   public static Object second(ListNode head) {
      return head.getNext().getValue();
   }

   //returns a reference to the last node in the list, or null if the list is empty.
   public static ListNode pointerToLast(ListNode head) {
      while(head.getNext() != null) {
         head = head.getNext();
      }
      return head;
   }
   //returns a copy of the last node (not just its value!).  copyofLast can be recursive.
   public static ListNode copyOfLast(ListNode head) {
      while(head.getNext() != null) {
         head = head.getNext();
      }
      return copyNode(head);
   }
   //returns a reference to a list whose first node's value is specified by the argument, and the
   //first node's next links to the original list.
   public static ListNode insertFirst(ListNode head, Object arg) {
      return new ListNode(arg, head);
   }
   //returns a reference to a list whose last node's value is specified by the argument, such
   //that this last node has been appended to the original list and had its next is set to null
   public static ListNode insertLast(ListNode head, Object arg) {
      ListNode copy = head;
      while(head.getNext() != null) {
         head = head.getNext();
      }
      head.setNext(new ListNode(arg, null));
      return copy;
   }
}