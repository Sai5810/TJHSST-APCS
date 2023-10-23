//Name:
//Date:

import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue < E extends Comparable < E >> {
   private ArrayList < E > myHeap;

   public HeapPriorityQueue() {
      myHeap = new ArrayList<>();
      myHeap.add(null);
   }

   public boolean add(E obj) {
      if(myHeap.size() != 1) {
         myHeap.add(obj);
         int j = myHeap.size() - 1;
         while(j/2 >= 1 && myHeap.get(j).compareTo(myHeap.get(j/2)) < 0) {
            swap(j, j/2);
            j = j/2;
         }
      } else {
         myHeap.add(obj);
      }
      return true;
   }

   public E remove() {
      E ret = myHeap.get(1);
      myHeap.set(1, myHeap.get(myHeap.size() - 1));
      myHeap.remove( myHeap.size() - 1);
      int curr = 1;
      while (2 * curr < myHeap.size()) {
         int child = 2 * curr;
         if (child != myHeap.size() - 1 && myHeap.get(child).compareTo(myHeap.get(child + 1)) > 0) {
            child++;
         }
         if (myHeap.get(curr).compareTo(myHeap.get(child)) > 0) {
            swap(curr, child);
         }
         curr = child;
      }
      return ret;
   }

   public E peek() {
      if(myHeap.size() == 1) {
         return null;
      } else {
         return myHeap.get(1);
      }
   }

   public boolean isEmpty() {
      return myHeap.size() == 1;
   }

   private void heapUp(int k) {

   }

   private void swap(int a, int b) {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }

   private void heapDown(int k, int size) {

   }

   public String toString() {
      return myHeap.toString();
   }
}