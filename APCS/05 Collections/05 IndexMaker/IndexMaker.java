/*This program takes a text file, creates an index (by line numbers)
for all the words in the file and writes the index
into the output file.  The program prompts the user for the file names.*/

import java.util.*;
import java.io.*;

public class IndexMaker {
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter input file name: ");
        String inFileName = keyboard.nextLine().trim();
        Scanner inputFile = new Scanner(new File(inFileName));
        String outFileName = "fishIndex.txt";
        PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
        indexDocument(inputFile, outputFile);
        inputFile.close();
        outputFile.close();
        System.out.println("Done.");
    }
    public static void indexDocument(Scanner inputFile, PrintWriter outputFile) {
        DocumentIndex index = new DocumentIndex();
        String line = null;
        int lineNum = 0;
        while (inputFile.hasNextLine()) {
            lineNum++;
            index.addAllWords(inputFile.nextLine(), lineNum);
        }
        for (IndexEntry entry: index) outputFile.println(entry);
    }
}
class DocumentIndex extends ArrayList <IndexEntry> {
    //constructors
    //ArrayList<IndexEntry> s = new ArrayList<>();
    /** extracts all the words from str, skipping punctuation and whitespace
     and for each word calls addWord().  In this situation, a good way to
     extract while also skipping punctuation is to use String's split method,
     e.g., str.split("[., \"!?]")       */
    public void addAllWords(String str, int lineNum) {
        str = str.toUpperCase();
        for(String i: str.split("[., \"!?]")){
            if(!i.equals("")) addWord(i, lineNum);
        }
    }

    /** calls foundOrInserted, which returns a position.  At that position,
     updates that IndexEntry's list of line numbers with lineNum. */
    public void addWord(String word, int lineNum) {
        int pos = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getWord().equals(word)) {
                this.get(i).add(lineNum);
                return;
            }
            if(this.get(i).getWord().compareTo(word) < 0) {
                pos = i + 1;
            }
        }
        this.add(pos, new IndexEntry(word));
        (this.get(pos)).add(lineNum);
    }

    /** traverses this DocumentIndex and compares word to the words in the
     IndexEntry objects in this list, looking for the correct position of
     word. If an IndexEntry with word is not already in that position, the
     method creates and inserts a new IndexEntry at that position. The
     method returns the position of either the found or the inserted
     IndexEntry.*/
    private int foundOrInserted(String word) {
        return 0;
    }
}

class IndexEntry implements Comparable <IndexEntry> {
    //fields
    private String word;
    private ArrayList<Integer> numsList;
    //constructors
    public IndexEntry(String s) {
        word = s.toUpperCase();
        numsList = new ArrayList<>();
    }
    /**  appends num to numsList, but only if it is not already in that list.*/
    public void add(int num) {
        for(int i: numsList){
            if(i == num) return;
        }
        numsList.add(num);
    }

    /** this is a standard accessor method  */
    public String getWord() {
        return word;
    }

    /**  returns a string representation of this Index Entry.  */
    public String toString() {
        StringBuilder s = new StringBuilder(word + " ");
        for(int i = 0; i < numsList.size(); i++) {
            s.append(numsList.get(i));
            if(i != numsList.size() - 1) s.append(", ");
        }
        return s.toString();
    }

    @Override
    public int compareTo(IndexEntry o) {
        return 0;
    }
}
