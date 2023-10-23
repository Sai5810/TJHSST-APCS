// Name: 
// Date:

import java.util.Arrays;

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */
public class TJArrayList < E > {
    private int size; //stores the number of objects
    private E[] myArray;
    //default constructor makes 10 cells
    public TJArrayList() {
        myArray = (E[]) new Object[1];
        size = 0;
    }
    public int size() {
        return myArray.length;
    }
    /* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length,
         and O(n) when it doubles the length of the array.
	  @return true  */
    public boolean add(E obj) {
        if(size == 0) {
            myArray[0] = obj;
            size++;
        } else {
            E[] copy = Arrays.copyOf(myArray, myArray.length + 1);
            copy[myArray.length] = obj;
            myArray = copy;
            size++;
        }
        return true;
    }
    /* inserts obj at position index.  increments size.*/
    public void add(int index, E obj) throws IndexOutOfBoundsException //this the way the real ArrayList is coded
    {
        if (index > size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if(size == 0) {
            myArray[0] = obj;
            size++;
        } else {
            E[] copy = (E[]) new Object[myArray.length + 1];
            System.arraycopy(myArray, 0, copy, 0, index);
            copy[index] = obj;
            System.arraycopy(myArray, index, copy, index + 1, myArray.length - index);
            size++;
            myArray = copy;
        }
    }

    /* return obj at position index.
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return myArray[index];
    }
    /**
     * Replaces obj at position index.
     * @return the object is being replaced.
     */
    public E set(int index, E obj) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E temp = myArray[index];
        myArray[index] = obj;
        return temp;
    }
    /*  removes the node from position index. shifts elements
     to the left.   Decrements size.
	  @return the object at position index.
	 */
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E temp = myArray[index];
        E[] copy = (E[]) new Object[myArray.length - 1];
        System.arraycopy(myArray, 0, copy, 0, index);
        System.arraycopy(myArray, index + 1, copy, index, myArray.length - index - 1);
        myArray = copy;
        size--;
        return temp;
    }
    /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
    public boolean contains(E obj) {
        for(E i: myArray) {
            if(i.equals(obj)) return true;
        }
        return false;
    }
    /*returns a String of E objects in the array with
       square brackets and commas.
     	*/
    public String toString() {
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; i < myArray.length; i++) {
            if (i > 0) b.append(", ");
            b.append(myArray[i]);
        }
        b.append("]");
        return b.toString();
    }
}