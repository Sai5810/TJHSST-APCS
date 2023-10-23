// Name: Sai Vaka
// Date: 9/10/20
import java.util.*; 

public class Modes {
    public static void main(String[] args) {
        int[] tally = {2, 2, 2};
        display(tally);
        int[] modes = calculateModes(tally);
        display(modes);
        int sum = 0;
        for (int i : tally) sum += i;
        System.out.println("kth \tindex");
        for (int k = 1; k <= sum; k++) {
            System.out.println(k + "\t\t" + kthDataValue(tally, k));
       }
    }
   static class comp implements Comparator<int[]> {
     public comp(){}
     @Override
     public int compare(int[] a, int[] b) {
       return Integer.compare(b[0], a[0]);
     }
   }
    /**
     * precondition: tally.length > 0
     * postcondition: returns an int array that contains the modes(s);
     *                the array's length equals the number of modes.
     */
    public static int[] calculateModes(int[] tally) {
        int flag = 0;
        for (int i : tally) {
            if (i != tally[0]) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            return new int[0];
        }
        int n = findMax(tally);
        List<Integer> t = new ArrayList<Integer>();
        for (int i  = 0; i < tally.length; i++) {
            if (tally[i] == n) t.add(i);
        }
        int[] mod = new int[t.size()];
        for (int i = 0; i < t.size(); i++)
            mod[i] = t.get(i);
        return mod;
    }

    /**  
     * precondition:  tally.length > 0
     * 	             0 < k <= total number of values in data collection
     * postcondition: returns the kth value in the data collection
     *                represented by tally
     */
    public static int kthDataValue(int[] tally, int k) {
        int[] ps = new int[tally.length];
        ps[0] = tally[0]; 
        for (int i = 1; i < tally.length; i++) 
            ps[i] = ps[i - 1] + tally[i]; 
        for (int i = 0; i < ps.length; i++) {
            if (ps[i] >= k)
               return i;
        }
        return -1;
    }

    /**
     * precondition:  nums.length > 0
     * postcondition: returns the maximal value in nums
     */
    public static int findMax(int[] nums) {
        int pos = 0;
        for (int k = 1; k < nums.length; k++)
            if (nums[k] > nums[pos])
                pos = k;
        return nums[pos];
    }

    public static void display(int[] args) {
        for (int arg : args) System.out.print(arg + " ");
        System.out.println();
        System.out.println();
    }
}

