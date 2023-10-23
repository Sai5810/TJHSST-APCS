// Name:   Date:
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
public class MatrixRecreate {
   public static void main(String[] args) {
      int[][] matrix = TheMatrix.create();
      int[] rowcount = new int[matrix.length];
      int[] colcount = new int[matrix[0].length];
      TheMatrix.count(matrix, rowcount, colcount);
      TheMatrix.display(matrix, rowcount, colcount);
      TheMatrix.re_create(rowcount, colcount);
      int[][] new_matrix = TheMatrix.getRecreatedMatrix();
      if( new_matrix == null )
         System.out.println("Did not find a match.");
      else
         TheMatrix.display( new_matrix, rowcount, colcount );
   }
}
class TheMatrix {
   //do not instantiate recreatedMatrix yet. Only instantiate and set that in recur.
   private static int[][] recreatedMatrix = null;

   public static int[][] getRecreatedMatrix() {
      return recreatedMatrix;
   }
   public static int[][] create() {
      int x = ThreadLocalRandom.current().nextInt(2, 7);
      int y = ThreadLocalRandom.current().nextInt(2, 7);
      int[][] matrix = new int[x][y];
      for (int i = 0; i < x; i++) {
         for (int j = 0; j < y; j++) {
            matrix[i][j] = ThreadLocalRandom.current().nextInt(0, 2);
         }
      }
      return matrix;
   }
   public static void count(int[][] matrix, int[] rowcount, int[] colcount) {
      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j < matrix[0].length; j++) {
            if(matrix[i][j] == 1) {
               rowcount[i]++;
               colcount[j]++;
            }
         }
      }
   }
   public static void display(int[][] matrix, int[] rowcount, int[] colcount) {
      for (int[] x : matrix) {
         System.out.println(Arrays.toString(x));
      }
   }
   public static void re_create(int[] orig_rowcount, int[] orig_colcount) {
      int[][] new_matrix = new int[orig_rowcount.length][orig_colcount.length];
      for (int[] x: new_matrix) {
         Arrays.fill(x, 7);
      }
      recur(new_matrix, orig_rowcount, orig_colcount, 0, 0);
   }
   private static void recur(int[][] new_matrix, int[] orig_rowcount, int[] orig_colcount, int row, int col) {
      if (compare(new_matrix, orig_rowcount, orig_colcount)) {//base case: if new_matrix works, then copy over to recreatedMatrix
         recreatedMatrix = new int[new_matrix.length][]; //copy over from new_matrix to recreatedMatrix (not just references)
         for (int i = 0; i < new_matrix.length; i++) {
            recreatedMatrix[i] = new int[new_matrix[i].length];
            System.arraycopy(new_matrix[i], 0, recreatedMatrix[i], 0, new_matrix[i].length);
         }
      } //we've found it!
      /*for (int[] x : new_matrix) {
         System.out.println(Arrays.toString(x));
      }
      System.out.println(row + " " + col);*/
      new_matrix[row][col] = 0;
      if(col < new_matrix[0].length - 1) {
         recur(new_matrix, orig_rowcount, orig_colcount, row, col + 1);
      }
      else if (row < new_matrix.length - 1) {
         recur(new_matrix, orig_rowcount, orig_colcount, row + 1, 0);
      }
      new_matrix[row][col] = 1;
      if(col < new_matrix[0].length - 1) {
         recur(new_matrix, orig_rowcount, orig_colcount, row, col + 1);
      }
      else if (row < new_matrix.length - 1) {
         recur(new_matrix, orig_rowcount, orig_colcount, row + 1, 0);
      }
   }
   private static boolean compare(int[][] new_matrix, int[] orig_rowcount, int[] orig_colcount) {
      int[] rowcount = new int[new_matrix.length];
      int[] colcount = new int[new_matrix[0].length];
      for (int i = 0; i < new_matrix.length; i++) {
         for (int j = 0; j < new_matrix[0].length; j++) {
            if(new_matrix[i][j] == 1) {
               rowcount[i]++;
               colcount[j]++;
            }
         }
      }
      return Arrays.equals(rowcount, orig_rowcount) && Arrays.equals(colcount, orig_colcount);
   }
}