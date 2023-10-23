// Name: Sai Vaka
// Date: 10/2/20
  
import java.util.*;
public class Permutations
{
   public static int count = 0;
    
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      int n = sc.nextInt();
      leftRight("", n);
      oddDigits("", n);
      superprime(n);
      if(count==0)
         System.out.println("no superprimes");
      else
         System.out.println("Count is "+count);
   }
   
    /**
     * Builds all the permutations of a string of length n containing Ls and Rs
     * @param s A string 
     * @param n An postive int representing the length of the string
     */
   public static void leftRight(String s, int n)
   {
      if (s.length() == n) {
         System.out.println(s);
         return;
      }
      leftRight(s + "L", n);
      leftRight(s + "R", n);
   }
   
    /**
     * Builds all the permutations of a string of length n containing odd digits
     * @param s A string 
     * @param n A postive int representing the length of the string
     */
   public static void oddDigits(String s, int n)
   {
      if (s.length() == n) {
         System.out.println(s);
         return;
      }
      oddDigits(s + "1", n);
      oddDigits(s + "3", n);
      oddDigits(s + "5", n);
      oddDigits(s + "7", n);
      oddDigits(s + "9", n);
   }
      
    /**
     * Builds all combinations of a n-digit number whose value is a superprime
     * @param n A positive int representing the desired length of superprimes  
     */
   public static void superprime(int n)
   {
      recur(2, n); //try leading 2, 3, 5, 7, i.e. all the single-digit primes
      recur(3, n); 
      recur(5, n);
      recur(7, n);
   }

    /**
     * Recursive helper method for superprime
     * @param k The possible superprime
     * @param n A positive int representing the desired length of superprimes
     */
   private static void recur(int k, int n)
   {
      if ((int) (Math.log10(k) + 1) == n) {
         count++;
         System.out.println(k);
         return;
      }
      for (String i: new String[]{"1", "2", "3", "5", "7", "9"}) {
         if (isPrime(Integer.parseInt( k + i))) {
            recur(Integer.parseInt( k + i), n);
         }
      }

   }

    /**
     * Determines if the parameter is a prime number.
     * @param n An int.
     * @return true if prime, false otherwise.
     */
   public static boolean isPrime(int n)
   {
      if (n <= 3)
         return n > 1;
      else if (n % 2 == 0 || n % 3 == 0)
         return false;
      for (int i = 5; i * i <= n; i += 6)
         if (n % i == 0 || n % (i + 2) == 0)
            return false;
      return true;
   }
}
