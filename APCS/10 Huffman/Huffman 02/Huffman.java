// name:        date:
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
public class Huffman {
    private static FileWriter sch;
    private static Map<Character, String> hCode = new HashMap<>();
    public static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        //Prompt for two strings
        System.out.print("Encoding using Huffman codes");
        System.out.print("\nWhat message? ");
        String message = keyboard.nextLine();
        System.out.print("\nEnter middle part of filename:  ");
        String middlePart = keyboard.next();
        huffmanize(message, middlePart);

    }
    public static void huffmanize(String message, String middlePart) throws IOException {
        sch = new FileWriter("scheme." + middlePart + ".txt");
        FileWriter msg = new FileWriter("message." + middlePart + ".txt");
        HashMap <Character, Integer> freq = new HashMap<>();
        for (char c: message.toCharArray()) {
            if(freq.containsKey(c)) {
                freq.put(c, freq.get(c) + 1);
            } else {
                freq.put(c, 1);
            }
        }
        PriorityQueue<HuffmanTreeNode> q = new PriorityQueue<>();
        for(Map.Entry<Character, Integer> i : freq.entrySet()) {
            q.add(new HuffmanTreeNode(i.getKey(), i.getValue()));
        }
        while (q.size() > 1) {
            HuffmanTreeNode x = q.poll();
            HuffmanTreeNode y = q.poll();
            q.add(new HuffmanTreeNode(x.freq + y.freq, x, y));
        }
        getCode(q.poll(), "");
        StringBuilder sb = new StringBuilder();
        for (char c: message.toCharArray()) {
            sb.append(hCode.get(c));
        }
        msg.write(sb.toString());
        msg.close();
        sch.close();
        //Make a frequency table of the letters
        //Put each letter-frequency pair into a HuffmanTreeNode. Put each
        //        node into a priority queue (or a min-heap).
        //Use the priority queue of nodes to build the Huffman tree
        //Process the string letter-by-letter and search the tree for the
        //       letter. It's recursive. As you recur, build the path through the tree,
        //       where going left is 0 and going right is 1.
        //System.out.println the binary message
        //Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
        //System.out.println the scheme from the tree--needs a recursive helper method
        //Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
    }
    public static void getCode(HuffmanTreeNode root, String s) throws IOException {
        if (root.left == null && root.right == null && root.value != null) {
            sch.write(root.value + s + "\n");
            hCode.put(root.value, s);
            return;
        }
        getCode(root.left, s + "0");
        getCode(root.right, s + "1");
    }
}
/*
 * This tree node stores two values.  Look at TreeNode's API for some help.
 * The compareTo method must ensure that the lowest frequency has the highest priority.
 */
class HuffmanTreeNode implements Comparable < HuffmanTreeNode > {
    public Character value = null;
    public int freq;
    public HuffmanTreeNode left, right;
    public HuffmanTreeNode(Character value1, int freq1) {
        value = value1;
        freq = freq1;
    }
    public HuffmanTreeNode(int freq1, HuffmanTreeNode l1, HuffmanTreeNode r1) {
        freq = freq1;
        left = l1;
        right = r1;
    }
    @Override
    public int compareTo(HuffmanTreeNode o) {
        return this.freq - o.freq;
    }
}