// Name:
// Date:

import java.text.DecimalFormat;

public class HeapSort {
   public static int SIZE; //9 or 100

   public static void main(String[] args) {
      //Part 1: Given a heap, sort it. Do this part first.
      /*SIZE = 9;
      double[] heap = {-1,99,80,85,17,30,84,2,16,1};
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));*/
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      SIZE = 100;
      double[] heap = new double[SIZE + 1];
      createRandom(heap);
      display(heap);
      makeHeap(heap, SIZE);
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
   }

   //******* Part 1 ******************************************
   public static void display(double[] array) {
      for (int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");
   }

   public static void sort(double[] array) {
      for (int i = array.length - 1; i > 1; --i) {
         swap(array, 1, i);
         int curr = 1;
         while (2 * curr < i) {
            int child = 2 * curr;
            if (child + 1 < i && array[child] < array[child + 1]) {
               child++;
            }
            if (array[curr] < array[child]) {
               swap(array, curr, child);
            }
            curr = child;
         }
      }
   }

   public static void swap(double[] array, int a, int b) {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }

   public static void heapDown(double[] array, int k, int size) {
   }

   public static boolean isSorted(double[] arr) {
      for (int i = 1; i < arr.length - 1; ++i) {
         if (arr[i] > arr[i + 1]) {
            return false;
         }
      }
      return true;
   }

   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places)
   public static double[] createRandom(double[] array) {
      array[0] = -1; //because it will become a heap
      DecimalFormat df = new DecimalFormat("###.##");
      for(int i = 1; i < array.length - 1; ++i) {
         array[i] = Double.parseDouble(df.format(Math.random() * 99 + 1));
      }
      return array;
   }

   //turn the random array into a heap
   public static void makeHeap(double[] array, int size) {
      for(int i = 1; i < size; ++i) {
         int j = i;
         while(array[j] > array[j / 2]) {
            swap(array, j, j/2);
            j = j/2;
         }
      }
   }
}