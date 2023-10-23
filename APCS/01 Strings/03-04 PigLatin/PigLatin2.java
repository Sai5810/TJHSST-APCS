// Name: Tyler Richard
// Date: 9/17/2020
import java.util.*;
import java.io.*;
public class PigLatin2
{
   public static void main(String[] args) 
   {
      // part_1_using_pig();
      part_2_using_piglatenizeFile();
      //System.out.println(pigReverse("!Hello!"));
      
      /*  extension only    */
      // String pigLatin = pig("What!?");
      // System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      // pigLatin = pig("{(Hello!)}");
      // System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      // pigLatin = pig("\"McDonald???\"");
      // System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            sc.close();
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String nums = "0123456789";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUYaeiouy";
   public static String pig(String s)
   {
      if(s.length() == 0)
         return "";
      
      // Check if all chars are numbers, if so return the number
      boolean allNums = true;
      for(char c : s.toCharArray())
         if(nums.indexOf(c) == -1 && punct.indexOf(c) == -1)
            allNums = false;
      if(allNums)
         return s;
   
      //remove and store the beginning punctuation and ending punc
      String begPunc = "";
      String endPunc = "";
      for(int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if(letters.indexOf(c) == -1)
            begPunc += c;
         else
            break;
      }
      for(int i = s.length() - 1; i > begPunc.length(); i--) {
         char c = s.charAt(i);
         if(letters.indexOf(c) == -1)
            endPunc = c + endPunc;
         else
            break;
      }
      String noPunc = s.substring(begPunc.length(), s.length() - endPunc.length());
      // System.out.println(begPunc);
      // System.out.println(noPunc);
      // System.out.println(endPunc);
      
      //START HERE with the basic case:
      //     find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      if(noPunc.length() == 0)
         return "**** NO VOWEL ****";

      int firstVowel = -1;
      char prev = noPunc.charAt(0);
      for(int i = 0; i < noPunc.length(); i++) {
         char c = noPunc.toLowerCase().charAt(i);
         if(i == 0 && c == 'y')
            continue;
         if(prev == 'q' && c == 'u')
            continue;
         if(vowels.indexOf(c) > -1) {
            firstVowel = i;
            break;
         }
         prev = c;
      }
      
      //if no vowel has been found
      if(firstVowel == -1)
         return "**** NO VOWEL ****";
      
      //is the first letter capitalized?
      char firstLetter;
      if(Character.isUpperCase(noPunc.charAt(0)))
         firstLetter = Character.toUpperCase(noPunc.charAt(firstVowel));
      else
         firstLetter = noPunc.charAt(firstVowel);
      
      
      //return the piglatinized word
      // Get Stuff Before Vowel of noPunc
      String beforeVowel ="";
      if(firstVowel != 0 && noPunc.length() > 1)
         beforeVowel = noPunc.substring(0, 1).toLowerCase() + noPunc.substring(1, firstVowel);
      else
         beforeVowel = noPunc.substring(0, firstVowel).toLowerCase();

      // Get Stuff After Vowel of noPunc
      String vowelAndOn = noPunc.substring(firstVowel + 1);

      // Use the Proper Ending; either 'ay' or 'way'
      String ending = "";
      if(firstVowel == 0)
         ending = "way";
      else
         ending = "ay";
      
      // Combine all parts to make final string
      return begPunc + firstLetter + vowelAndOn + beforeVowel + ending + endPunc;
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
      sc.close();
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.out.println(e);
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      while(infile.hasNextLine()) {
         String[] words = infile.nextLine().split(" ");
         for(int i = 0; i < words.length; i++)
            words[i] = pig(words[i]);
         for(String word : words)
            outfile.print(word + " ");
         outfile.println();
      }
      
      
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
      // For extra challenge I can only access each value of in once
      char[] in = pig(s).toCharArray();
      char[] out = new char[in.length];
      int symbEnd = 0;
      int symbStart = 0;
      // Find where the letters start and symbols end
      for(int i = 0; i < in.length; i++) {
         //System.out.println(i);
         out[i] = in[i];
         if(letters.indexOf(out[i]) != -1) {
            symbEnd = i;
            break;
         }
      }
      // Find where the letters end and symbols start
      // Also, when you find it, write in the proper values of symbEnd and symbStart,
      // which are the first occurences of letters on both ends of the string
      for(int i = in.length - 1; i > symbEnd; i--) {
         System.out.println(i);
         out[i] = in[i];
         if(letters.indexOf(out[i]) != -1) {
            symbStart = i;

            char swap = out[symbEnd];
            out[symbEnd] = out[symbStart];
            out[symbStart] = swap;

            break;
         }
      }
      // Fill in the rest of out with the reversed chars
      for(int i = symbEnd + 1; i < symbStart; i++) {
         System.out.println(i);
         out[i] = in[symbStart - i + symbEnd];
      }
      // Correct Caps
      if(Character.isUpperCase(out[symbStart])) {
         out[symbStart] = Character.toLowerCase(out[symbStart]);
         out[symbEnd] = Character.toUpperCase(out[symbEnd]);
      }
      // Return String form of char array
      return new String(out);
   }
}
