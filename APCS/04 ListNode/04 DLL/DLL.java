// Name:
// Date:

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code

   public int size() {
      int c = 0;
      while(head != null) {
         head = head.getNext();
         c++;
      }
      return c;
   }

   /* appends obj to end of list; increases size; @return true  */
   public boolean add(Object obj) {
      addLast(obj);
      return true;
   }

   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException //this the way the real LinkedList is coded
   {
      if (index > size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode a = new DLNode(obj, null, null);
      DLNode temp = head;
      if (temp == null) {
         head = a;
      } else {
         for (int i = 1; i < index - 1; i++) {
            temp = temp.getNext();
         }
         a.setNext(temp.getNext());
         (temp.getNext()).setPrev(a);
         temp.setNext(a);
         a.setPrev(temp);
         head = temp;
      }
   }

   /* return obj at position index. 	*/
   public Object get(int index) {
      if (index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      for(int i = 0; i < index; i++) {
         head = head.getNext();
      }
      return head;
   }

   /* replaces obj at position index.
        returns the obj that was replaced*/
   public Object set(int index, Object obj) {
      if (index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      for(int i = 0; i < index; i++) {
         head = head.getNext();
      }
      Object t = head.getValue();
      head.setValue(obj);
      return t;
   }

   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index) {
      if (index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      if (head == null) return null;
      DLNode pre = new DLNode(null, null, null);
      DLNode cur = head;
      for(int i = 0; i < index; i++) {
         if(i == 0) {
            pre = head;
         } else {
            pre = pre.getNext();
         }
         cur = cur.getNext();
      }
      pre.setNext(cur.getNext());
      size--;
      return pre;
   }

   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj) {
      add(0, obj);
   }

   /* appends obj to end of list, increases size    */
   public void addLast(Object obj) {
      add(size, obj);
   }

   /* returns the first element in this list  */
   public Object getFirst() {
      return head.getValue();
   }

   /* returns the last element in this list  */
   public Object getLast() {
      while(head.getNext() != null) {
         head = head.getNext();
      }
      return head;
   }

   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst() {
      return remove(0);
   }

   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast() {
      return remove(size - 1);
   }

   /*  returns a String with the values in the list in a
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString() {
      StringBuilder s = new StringBuilder();
      while(head.getValue() != null) {
         System.out.println(head.getValue());
         s.append(head.getValue());
         head = head.getNext();
      }
      return s.toString();
   }
}