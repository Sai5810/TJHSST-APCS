// Name: Sai Vaka
// Date: 9/27/20
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
//here any additional imports that you may need

public class Cemetery
{
   /******************************************

    John William ALLARDYCE, 17 Mar 1844, 2.9
    Frederic Alex. ALLARDYCE, 21 Apr 1844, 0.17
    Philip AMIS, 03 Aug 1848, 1.0
    Thomas ANDERSON, 06 Jul 1845, 27.0
    Edward ANGEL, 20 Nov 1842, 22.0
    Lucy Ann COLEBACK, 23 Jul 1843, 0.2685
    Thomas William COLLEY, 08 Aug 1833, 0.011
    Joseph COLLIER, 03 Apr 1831, 58.0

    In the St. Mary Magdelene Old Fish Cemetery --> 
    Name of youngest person: Thomas William COLLEY
    Age of youngest person: 0.011
    Name of oldest person: Joseph COLLIER
    Age of oldest person: 58.0

    **************************************/
   public static void main (String [] args) {
      File file = new File("cemetery_short.txt");
      //File file = new File("cemetery.txt");
      try {
         int numEntries = countEntries(file);
         Person[] cemetery = readIntoArray(file, numEntries);
         //see what you have
         for (Person person : cemetery) System.out.println(person);
         int min = locateMinAgePerson(cemetery);
         int max = locateMaxAgePerson(cemetery);
         System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery --> ");
         System.out.println("Name of youngest person: " + cemetery[min].getName());
         System.out.println("Age of youngest person: " + cemetery[min].getAge());
         System.out.println("Name of oldest person: " + cemetery[max].getName());
         System.out.println("Age of oldest person: " + cemetery[max].getAge());
         //you may create other testing cases here
         //comment them out when you submit your file to Grade-It
      }
      catch (FileNotFoundException e) {
         System.out.println("File not found");
      }
   }
   
   /* Counts and returns the number of entries in File f. 
      Returns 0 if the File f is not valid.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f) throws FileNotFoundException {
      Scanner s = new Scanner(f);
      int l = 0;
      while (s.hasNextLine()) {
         s.nextLine();
         l++;
      }
      s.close();
      return l;
   }

   /* Reads the data from file f (you may assume each line has same allignment).
      Fills the array with Person objects. If File f is not valid return null.
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num) throws FileNotFoundException {
      Scanner s = new Scanner(f);
      Person[] pers = new Person[num];
      for (int i = 0; i < num; i++) {
         String d = s.nextLine();
         pers[i] = makeObjects(d);
      }
      s.close();
      return pers;
   }
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
      This method is made public for gradeit testing purposes.
      This method should not be used in any other class!!!
   */
   public static Person makeObjects(String d)
   {
      /*System.out.println(d.substring(0, 24).trim());
      System.out.println(d.substring(24, 37).trim());
      System.out.println(d.substring(37, 42).trim());*/
      return new Person(d.substring(0, 24).trim(), d.substring(24, 37).trim(), d.substring(37, 42).trim());
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      Person m = new Person("","","999999999");
      int q = 0;
      for (int i = 0; i < arr.length; i++) {
         if (arr[i].getAge() < m.getAge()) {
            m = arr[i];
            q = i;
         }
      }
      return q;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      Person m = new Person("","","0");
      int q = 0;
      for (int i = 0; i < arr.length; i++) {
         if (arr[i].getAge() > m.getAge()) {
            m = arr[i];
            q = i;
         }
      }
      return q;
   }        
} 

class Person
{
   //constant that can be used for formatting purposes
   private static final DecimalFormat df = new DecimalFormat("0.0000");
   /* private fields */
   private final String n, bD;
   private final double a;
   /* a three-arg constructor  
    @param name, burialDate may have leading or trailing spaces
    It creates a valid Person object in which each field has the leading and trailing
    spaces eliminated*/
   public Person(String name, String burialDate, String age)
   {
      n = name;
      bD = burialDate;
      a = calculateAge(age);
   }
   /* any necessary accessor methods (at least "double getAge()" and "String getName()" )
   make sure your get and/or set methods use the same data type as the field  */
   
   
   /*handles the inconsistencies regarding age
     @param a = a string containing an age from file. Ex: "12", "12w", "12d"
     returns the age transformed into year with 4 decimals rounding
   */
   public double calculateAge(String a)
   {
      if (a.length() < 2) {
         return Double.parseDouble(a);
      }
      double v = Double.parseDouble(a.substring(0, a.length() - 1));
      if (a.charAt(a.length() - 1) == 'w') {
         return v / 52;
      }
      else if (a.charAt(a.length() - 1) == 'd') {
         return v / 365;
      }
      else {
         return Double.parseDouble(a);
      }
   }
   public double getAge() {
      return a;
   }

   public String getName() {
      return n;
   }
}

