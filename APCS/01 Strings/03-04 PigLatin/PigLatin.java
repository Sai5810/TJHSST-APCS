// Name: Sai Vaka
// Date: 9/18/20
import java.util.*;
import java.io.*;
public class PigLatin {
   public static void main(String[] args) {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      /*  extension only    */
      /*String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"*/
   }

   public static void part_1_using_pig() {
      Scanner sc = new Scanner(System.in);
      while(true) {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println(p);
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s) {
      if(s.length() == 0)
         return "";
      StringBuilder sb = new StringBuilder(s);
      //remove and store the beginning punctuation
      int pi = 0;
      while (punct.indexOf(sb.charAt(pi)) != -1) {
         pi++;
      }
      //remove and store the ending punctuation
      int ei = sb.length() - 1;
      while (punct.indexOf(sb.charAt(ei)) != -1) {
         ei--;
      }
      ei += 1;
      if (vowels.indexOf(sb.charAt(pi)) != -1)
         if (ei == sb.length()) {
            return sb.substring(0, pi) + sb.substring(pi, ei) + "way";
         } else {
            return sb.substring(0, pi) + sb.substring(pi, ei) + "way" + sb.substring(ei);
         }

      //START HERE with the basic case:
      //     find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      int ind = -1;
      for(int i = pi; i < ei; i++) {
         if (sb.charAt(i) == 'q' && sb.charAt(i + 1) == 'u') {
            if (ei == sb.length()) {
               return sb.substring(0, pi) + sb.substring(i + 2, ei) + sb.substring(pi, i) + "quay";
            } else {
               return sb.substring(0, pi) + sb.substring(i + 2, ei) + sb.substring(pi, i) + "quay" + sb.substring(ei);
            }
         }
         if (sb.charAt(i) == 'Q' && sb.charAt(i + 1) == 'u') {
            if (ei == sb.length()) {
               String uncap = sb.substring(0, pi) + sb.substring(i + 2, ei) + sb.substring(pi, i) + "quay";
               return uncap.substring(0,1).toUpperCase() + uncap.substring(1);
            } else {
               String uncap = sb.substring(0, pi) + sb.substring(i + 2, ei) + sb.substring(pi, i) + "quay" + sb.substring(ei);
               return uncap.substring(0,1).toUpperCase() + uncap.substring(1);
            }
         }
         if (vowels.indexOf(sb.charAt(i)) != -1) {
            ind = i;
            break;
         }
         else if (i != pi && sb.charAt(i) == 'y') {
            ind = i;
            break;
         }
      }
      //if no vowel has been found
      if (ind == -1) {
         return "**** NO VOWEL ****";
      }
      //is the first letter capitalized
      if (Character.isUpperCase(s.charAt(pi))) {
         sb.replace(pi, pi + 1, String.valueOf(Character.toLowerCase(s.charAt(pi))));
         if (ei == sb.length()) {
            return sb.substring(0, pi) + sb.substring(ind, ind + 1).toUpperCase() + sb.substring(ind + 1, ei) +
                    sb.substring(pi, ind) + "ay";
         } else {
            return sb.substring(0, pi) + sb.substring(ind, ind + 1).toUpperCase() + sb.substring(ind + 1, ei) +
                    sb.substring(pi, ind) + "ay" + sb.substring(ei);
         }
      }
      else {
         if (ei == sb.length()) {
            return sb.substring(0, pi) + sb.substring(ind, ei) + sb.substring(pi, ind) + "ay";
         } else {
            return sb.substring(0, pi) + sb.substring(ind, ei) + sb.substring(pi, ind) + "ay" + sb.substring(ei);
         }
      }
      //return the piglatinized word
   }


   public static void part_2_using_piglatenizeFile() {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) {
      Scanner infile = null;
      try {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e) {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e) {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      while (infile.hasNextLine()) {
        String[] data = infile.nextLine().split(" ");
        for(String i: data)
           outfile.print(pig(i) + " ");
         outfile.print("\n");
        /*for(int i = 0; i < data.length; i++) {
           outfile.write(pig(data[i]));
           if (i != data.length - 1) {
              outfile.write(" ");
           }
         }
         outfile.write("\n");*/
      }
      outfile.close();
      infile.close();
   }
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after
    punctuation.
    */
   public static String pigReverse(String s) {
      if(s.length() == 0)
         return "";
      StringBuilder sb = new StringBuilder(s);
      int pi = 0;
      while (punct.indexOf(sb.charAt(pi)) != -1) {
         pi++;
      }
      //remove and store the ending punctuation
      int ei = sb.length() - 1;
      while (punct.indexOf(sb.charAt(ei)) != -1) {
         ei--;
      }
      ei += 1;
      StringBuilder fp = new StringBuilder(sb.substring(0, pi));
      StringBuilder ep = new StringBuilder(sb.substring(ei));
      sb = new StringBuilder(sb.substring(pi, ei));
      if (Character.isUpperCase(sb.charAt(0))) {
         sb.replace(0, 1, String.valueOf(Character.toLowerCase(sb.charAt(0))));
         sb.replace(sb.length()-1, sb.length(), String.valueOf(Character.toUpperCase(sb.charAt(sb.length()-1))));
      }
      return fp.toString() + sb.reverse().toString() + ep.toString();
   }
}
