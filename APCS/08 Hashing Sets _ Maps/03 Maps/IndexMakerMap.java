// Name: 
// Date: 

import java.io.*;
import java.util.*;

/* This program takes a text file, creates an index (by line numbers)
 * for all the words in the file and writes the index
 * into the output file.  The program prompts the user for the file names.
 */
public class IndexMakerMap {
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("\nEnter input file name: ");
        String infileName = keyboard.nextLine().trim();
        Scanner inputFile = new Scanner(new File(infileName));

        DocumentIndex index = makeIndex(inputFile);

        //System.out.println( index.toString() );
        PrintWriter outputFile = new PrintWriter(new FileWriter("fishIndex.txt"));
        outputFile.println(index.toString());
        inputFile.close();
        outputFile.close();
        System.out.println("Done.");
    }

    public static DocumentIndex makeIndex(Scanner inputFile) {
        DocumentIndex index = new DocumentIndex();
        int lineNum = 0;
        while (inputFile.hasNextLine()) {
            lineNum++;
            index.addAllWords(inputFile.nextLine(), lineNum);
        }
        return index;
    }
}

class DocumentIndex extends TreeMap < String, TreeSet < Integer >> {
    /** Extracts all the words from str, skipping punctuation and whitespace
     *  and for each word calls addWord(word, num).  A good way to skip punctuation
     *  and whitespace is to use String's split method, e.g., split("[., \"!?]")
     */
    public void addAllWords(String str, int lineNum) {
        str = str.toUpperCase();
        for(String i: str.split("[., \"!?]")){
            if(!i.equals("")){
                addWord(i, lineNum);
            }
        }
    }

    /** Makes the word uppercase.  If the word is already in the map, updates the lineNum.
     *  Otherwise, adds word and ArrayList to the map, and updates the lineNum
     */
    public void addWord(String word, int lineNum) {
        word = word.toUpperCase();
        if(this.get(word) != null) {
            this.get(word).add(lineNum);
        } else {
            TreeSet<Integer> t = new TreeSet<>();
            t.add(lineNum);
            this.put(word, t);
        }
    }

    public String toString() {
        StringBuilder x = new StringBuilder();
        for(Map.Entry<String,TreeSet<Integer>> i : this.entrySet()) {
            x.append(i.getKey()).append(" ");
            for(Integer j: i.getValue()) {
                x.append(j).append(", ");
            }
            x.setLength(x.length() - 2);
            x.append("\n");
        }
        return x.toString();
    }
}