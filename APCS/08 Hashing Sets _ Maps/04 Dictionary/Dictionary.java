// Name: 
// Date: 

import java.io.*;
import java.util.*;

public class Dictionary {
    static void translate(Scanner c) throws IOException {
        Scanner inf = new Scanner(new File("spanglish.txt"));
        System.out.println("Do you want to convert Spanish to English or English to Spanish? ");
        System.out.println("0: English to Spanish | 1: Spanish to English");
        Map<String, Set<String>> dict = makeDictionary(inf);
        boolean flag = false;
        if (c.nextLine().equals("1")) {
            dict = reverse(dict);
            flag = true;
        }
        System.out.println("What word would you like converted?: ");
        String word = c.nextLine();
        if (dict.get(word) == null) {
            System.out.println("Sorry, we don't have this translation.");
        } else {
            StringBuilder x = new StringBuilder();
            for (String j : dict.get(word)) {
                x.append(j).append(", ");
            }
            x.setLength(x.length() - 2);
            System.out.println(x.toString());
        }
        System.out.println("Did your word have missing translations?");
        System.out.println("0: No | 1: Yes, I will add them");
        BufferedWriter fout;
        fout = new BufferedWriter(new FileWriter("modified.txt"));
        if (c.nextLine().equals("1")) {
            inf = new Scanner(new File("spanglish.txt"));
            while (inf.hasNextLine()) {
                fout.write(inf.nextLine() + "\n");
            }
            System.out.println("Type in the translation");
            if (flag) {
                fout.write(c.nextLine() + "\n");
                fout.write(word + "\n");
            } else {
                fout.write(word + "\n");
                fout.write(c.nextLine() + "\n");
            }
        }
        fout.close();
    }
    static void edit(Scanner c) throws IOException {
        Scanner inf = new Scanner(new File("spanglish.txt"));
        Map<String, String> d = new TreeMap<>();
        while (inf.hasNextLine()) {
            d.put(inf.nextLine(), inf.nextLine());
        }
        String s;
        do {
            System.out.println("Type in the key-value pair that will be modified in the format k, v");
            System.out.println("-1 to stop editing");
            s = c.nextLine();
            if (!s.equals("-1")) {
                System.out.println("Would you like to add, remove, or modify this pair?");
                System.out.println("0: Add | 1: Remove | 2: Modify");
                String[] arrS = s.split(", ");
                switch (c.nextLine()) {
                    case "0":
                        d.put(arrS[0], arrS[1]);
                        break;
                    case "1":
                        d.remove(arrS[0]);
                        break;
                    case "2":
                        System.out.println("Do you want to edit the key or value?");
                        System.out.println("0: Key | 1: Value");
                        if (c.nextLine().equals("0")) {
                            System.out.println("Enter the value of the key");
                            d.put(c.nextLine(), d.remove(arrS[0]));
                        } else {
                            System.out.println("Enter the value");
                            d.replace(arrS[0], c.nextLine());
                        }
                        break;
                }
            }
        } while(!s.equals("-1"));
        BufferedWriter fut = new BufferedWriter(new FileWriter("adminupdate.txt"));
        for(Map.Entry<String,String> i: d.entrySet()) {
            fut.write(i.getKey() + "\n");
            fut.write(i.getValue() + "\n");
        }
        fut.close();
    }
   public static void main(String[] args) throws IOException {
       Scanner c = new Scanner(System.in);
       System.out.println("Do you want to translate or edit the list?");
       System.out.println("0: Translate | 1: Edit");
       if (c.nextLine().equals("0")) {
           translate(c);
       } else {
           System.out.println("Enter the password");
           if (c.nextLine().equals("password123")) {
               edit(c);
           }
       }
   }
   public static Map < String, Set < String >> makeDictionary(Scanner infile) {
      TreeMap <String,Set <String>> dict = new TreeMap<>();
      while(infile.hasNextLine()) {
         add(dict, infile.nextLine(), infile.nextLine());
      }
      return dict;
   }

   public static void add(Map < String, Set < String >> dict, String l, String l2) {
      if(dict.containsKey(l)) {
         dict.get(l).add(l2);
      } else {
         TreeSet<String> t = new TreeSet<>();
         t.add(l2);
         dict.put(l, t);
      }
   }

   public static void display(Map < String, Set < String >> m) {
      StringBuilder x = new StringBuilder();
      for(Map.Entry< String, Set < String >> i : m.entrySet()) {
         x.append(i.getKey()).append(" [");
         for(String j: i.getValue()) {
            x.append(j).append(", ");
         }
         x.setLength(x.length() - 2);
         x.append("]\n");
      }
      System.out.println(x.toString());
   }

   public static Map < String, Set < String >> reverse(Map < String, Set < String >> dictionary) {
      TreeMap <String,Set <String>> dict = new TreeMap<>();
      for(Map.Entry< String, Set < String >> i : dictionary.entrySet()) {
         for(String j: i.getValue()) {
            add(dict, j, i.getKey());
         }
      }
      return dict;
   }
}