// Name:      
// Date:

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class HuffmanPix {
    public static int WIDTH = 500;   // 500 x 500 is too big
    public static int HEIGHT = 500;
    private static Map<Integer, String> hCode = new HashMap<>();
    private static StringBuilder sch = new StringBuilder();
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Encoding using Huffman codes");
        System.out.print("\nWhat image (including extension)? ");
        String pixName = keyboard.nextLine();
        ImageIcon i = new ImageIcon(pixName);
        BufferedImage bufImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        JFrame f = new JFrame("HuffmanPix");
        f.setSize(500, 500);    // width, height
        f.setLocation(100, 50);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new DisplayPix(bufImg, i));
        f.setVisible(true);

        System.out.print("\nEnter middle part of filename:  ");
        String middlePart = keyboard.nextLine();

        huffmanize(bufImg, middlePart);

        System.exit(0);
    }


    public static void huffmanize(BufferedImage bufImg, String middlePart) throws IOException {
        /*   your Huffman code goes  here  */
        HashMap <Integer, Integer> freq = new HashMap<>();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int c = bufImg.getRGB(x, y);
                if(freq.containsKey(c)) {
                    freq.put(c, freq.get(c) + 1);
                } else {
                    freq.put(c, 1);
                }
            }
        }
        /*sch = new FileWriter("scheme." + middlePart + ".txt");
        FileWriter msg = new FileWriter("message." + middlePart + ".txt");*/
        PriorityQueue<HuffmanTreeNode> q = new PriorityQueue<>();
        for(Map.Entry<Integer, Integer> i : freq.entrySet()) {
            q.add(new HuffmanTreeNode(i.getKey(), i.getValue()));
        }
        while (q.size() > 1) {
            HuffmanTreeNode x = q.poll();
            HuffmanTreeNode y = q.poll();
            q.add(new HuffmanTreeNode(x.freq + y.freq, x, y));
        }
        getCode(q.poll(), "");
        StringBuilder code = new StringBuilder();
        for (int y = 0; y < bufImg.getHeight(); y++) {
            for (int x = 0; x < bufImg.getWidth(); x++) {
                int c = bufImg.getRGB(x, y);
                code.append(hCode.get(c));
            }
        }
        String binaryFileName = "pix." + middlePart + ".txt";
        PrintWriter outfile = new PrintWriter(new FileWriter(binaryFileName));
        outfile.print(code);
        System.out.println("Pix done");

        //Map<String, String> huffmanScheme = new HashMap<>();
        String schemeFile = "schemePix." + middlePart + ".txt";
        PrintWriter outfile2 = new PrintWriter(new FileWriter(schemeFile));
        outfile2.println("" + WIDTH + " " + HEIGHT);    //outputs the width x height
        outfile2.println(sch);
        System.out.println("Scheme done");

        outfile.close();
        outfile2.close();
    }
    public static void getCode(HuffmanTreeNode root, String s) throws IOException {
        if (root.left == null && root.right == null && root.value != null) {
            sch.append(root.value).append(":").append(s).append("\n");
            hCode.put(root.value, s);
            return;
        }
        getCode(root.left, s + "0");
        getCode(root.right, s + "1");
    }
}


/*
 * This node stores two values.
 * The compareTo method must ensure that the lowest frequency has the highest priority.
 */
class HuffmanTreeNode implements Comparable < HuffmanTreeNode > {
    public Integer value = 1;
    public int freq;
    public HuffmanTreeNode left, right;
    public HuffmanTreeNode(Integer value1, int freq1) {
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

/*
 * Minimum code necessary to display a BufferedImage.
 */
class DisplayPix extends JPanel {
    private BufferedImage img;
    private Graphics g;

    public DisplayPix(BufferedImage bufImg, ImageIcon i)   //for Huffman
    {
        int w = bufImg.getWidth();
        int h = bufImg.getHeight();
        img = bufImg;
        g = bufImg.getGraphics();
        g.drawImage(i.getImage(), 0, 0, w, h, null);
    }

    public DisplayPix(BufferedImage bufImg)              //for deHuffman
    {
        img = bufImg;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}