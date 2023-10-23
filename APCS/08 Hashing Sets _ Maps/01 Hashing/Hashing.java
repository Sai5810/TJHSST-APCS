// Name:
// Date:

 /*
    Assignment:  This hashing program results in collisions.
    You are to implement three different collision schemes: linear
    probing, rehashing, and chaining.  Then implement a search
    algorithm that is appropriate for each collision scheme.
  */
import java.util.*;
import javax.swing.*;
public class Hashing {
   public static void main(String[] args) {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
              "Hashing!\n" + "Enter the size of the array:  ")); //20
      int numItems = Integer.parseInt(JOptionPane.showInputDialog("Add n items:  ")); //15
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
              "The Load Factor is " + (double) numItems / arrayLength + "\nWhich collision scheme?\n" +
                      "1. Linear Probing\n" + "2. Rehashing\n" + "3. Chaining"));
      Hashtable table = null;
      switch (scheme) {
         case 1:
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2:
            table = new HashtableRehash(arrayLength);
            break;
         case 3:
            table = new HashtableChaining(arrayLength);
            break;
         default:
            System.exit(0);
      }
      for (int i = 0; i < numItems; i++) table.add("Item" + i);
      int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
              "Search for:  Item0" + " to " + "Item" + (numItems - 1)));
      while (itemNumber != -1) {
         String key = "Item" + itemNumber;
         int index = table.indexOf(key);
         if (index >= 0) {
            System.out.println(key + " found  at index " + index); //found it
         } else {
            System.out.println(key + " not found!");
         }
         itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                 "Search for:  Item0" + " to " + "Item" + (numItems - 1)));
      }
      System.exit(0);
   }
}

/*********************************************/
interface Hashtable {
   void add(Object obj);
   int indexOf(Object obj);
}
/***************************************************/

class HashtableLinearProbe implements Hashtable {
   private Object[] array;

   public HashtableLinearProbe(int size) { //constructor
      array = new Object[size];
   }

   public void add(Object obj) {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if (array[index] != null) {
         System.out.println(obj + "\t" + code + "\tCollision at " + index);
         index = linearProbe(index);
      }
      array[index] = obj;
      System.out.println(obj + "\t" + code + "\t" + index);
   }

   public int linearProbe(int index) {
      for(int i = index; i < array.length; i++) {
         if(array[i] == null) {
            return i + 1;
         }
      }
      return 0;
   }

   public int indexOf(Object obj) {
      int index = Math.abs(obj.hashCode() % array.length);
      while (array[index] != null) {
         if (array[index].equals(obj)) { //found it
            return index;
         } else {//search for it in a linear probe manner
            index++;
            System.out.println("Looking at index " + index);
         }
         if(index == array.length) {
            return -1;
         }
      }
      //not found
      return -1;
   }
}

/*****************************************************/
class HashtableRehash implements Hashtable {
   private Object[] array;
   private int constant;

   public HashtableRehash(int size) { //constructor
      array = new Object[size];
   }

   public void add(Object obj) {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if (array[index] != null) {
         System.out.println(obj + "\t" + code + "\tCollision at " + index);
         index = rehash(index);
      }
      array[index] = obj;
      System.out.println(obj + "\t" + code + "\t" + index);
   }

   public int rehash(int index) {
      int n = array.length;
      for (int i = 2; i <= n; i++) {
         if(n % i != 0) {
            return (index + i) % n;
         }
      }
      return 0;
   }

   public int indexOf(Object obj) {
      int index = Math.abs(obj.hashCode() % array.length);
      while (array[index] != null) {
         if (array[index].equals(obj)) { //found it
            return index;
         } else {//search for it in a rehashing manner
            index++;
            System.out.println("Looking at index " + index);
         }
         if(index == array.length) {
            return -1;
         }
      }
      //not found
      return -1;
   }
}

/********************************************************/
class HashtableChaining implements Hashtable {
   private LinkedList[] array;

   public HashtableChaining(int size) {
      //instantiate the array
      //instantiate the LinkedLists
      array = new LinkedList[size];
      Arrays.fill(array, new LinkedList<>());
   }
   public void add(Object obj) {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " + index + ": " + array[index]);
   }

   public int indexOf(Object obj) {
      int index = Math.abs(obj.hashCode() % array.length);
      if (!array[index].isEmpty()) {
         for (int i = 0; i < array[index].size(); i++) {
            if (array[index].get(i).equals(obj)) {
               return i;
            }
         }
      }
      return -1;
   }
}