// Name:
// Date:

import java.util.*;
import java.io.*;


public class MazeMaster {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the maze's filename (no .txt): ");
        Maze m = new Maze(sc.next() + ".txt");
        // Maze m = new Maze();    //extension
        m.display();
        System.out.println("Options: ");
        System.out.println("1: Mark all dots.");
        System.out.println("2: Mark all dots and display the number of recursive calls.");
        System.out.println("3: Mark only the correct path.");
        System.out.println("4: Mark only the correct path. If no path exists, say so.");
        System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, say so.");
        System.out.print("Please make a selection: ");
        m.solve(sc.nextInt());
        m.display(); //display solved maze
    }
}

class Maze {
    //constants
    private final char WALL = 'W';
    private final char DOT = '.';
    private final char START = 'S';
    private final char EXIT = 'E';
    private final char TEMP = 'o';
    private final char PATH = '*';
    //instance fields
    private char[][] maze;
    private int startRow, startCol;

    //constructors

    /*
     * EXTENSION
     * This is a no-arg constructor that generates a random maze
     */
    public Maze() {

    }

    /*
     * Copy Constructor
     */
    public Maze(char[][] m) {
        maze = m;
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                if (maze[r][c] == START) //identify start location
                {
                    startRow = r;
                    startCol = c;
                }
            }
        }
    }

    /*
     * Use a try-catch block
     * Use next(), not nextLine()
     */
    public Maze(String filename) {
        try {
            Scanner infile = new Scanner(new File(filename));
            int h = infile.nextInt();
            int w = infile.nextInt();
            infile.nextLine();
            char[][] v = new char [h][w];
            for(int i = 0; i < h; i++){
                String line = infile.nextLine();
                for(int k = 0; k < w; k++) v[i][k] = line.charAt(k);
            }
            maze = v;
            for (int r = 0; r < maze.length; r++) {
                for (int c = 0; c < maze[0].length; c++) {
                    if (maze[r][c] == START) //identify start location
                    {
                        startRow = r;
                        startCol = c;
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public char[][] getMaze() {
        return maze;
    }

    public void display() {
        if (maze == null)
            return;
        for (char[] chars : maze) {
            for (int b = 0; b < maze[0].length; b++) {
                System.out.print(chars[b]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void solve(int n) {
        switch (n) {
            case 1: {
                markAll(startRow, startCol);
                break;
            }
            case 2: {
                int count = markAllAndCountRecursions(startRow, startCol);
                System.out.println("Number of recursions = " + count);
                break;
            }
            case 3: {
                markTheCorrectPath(startRow, startCol);
                break;
            }
            case 4: { //use mazeNoPath.txt
                if (!markTheCorrectPath(startRow, startCol))
                    System.out.println("No path exists.");
                break;
            }
            case 5: {
                if (!markCorrectPathAndCountSteps(startRow, startCol, 0))
                    System.out.println("No path exists.");
                break;
            }
            default:
                System.out.println("File not found");
        }
    }

    /*
     * From handout, #1.
     * Fill the maze, mark every step.
     * This is a lot like AreaFill.
     */
    public void markAll(int r, int c) {
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length) return;
        if (maze[r][c] == '.') {
            maze[r][c] = '*';
        } else if (maze[r][c] != 'S') {
            return;
        }
        markAll(r + 1, c);
        markAll(r - 1, c);
        markAll(r, c - 1);
        markAll(r, c + 1);
    }

    /*
     * From handout, #2.
     * Fill the maze, mark and count every recursive call as you go.
     * Like AreaFill's counting without a static variable.
     */
    public int markAllAndCountRecursions(int r, int c) {
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length) return 0;
        if (maze[r][c] == '.') {
            maze[r][c] = '*';
        } else if (maze[r][c] != 'S') {
            return 0;
        }
        return 1 + markAllAndCountRecursions(r + 1, c) + markAllAndCountRecursions(r - 1, c) +
                markAllAndCountRecursions(r, c - 1) + markAllAndCountRecursions(r, c + 1);
    }

    /*
     * From handout, #3.
     * Solve the maze, OR the booleans, and mark the path through it with a "*"
     * Recur until you find E, then mark the True path.
     */
    public boolean markTheCorrectPath(int r, int c) {
        if(maze[r][c] == 'E') return true;
        if(maze[r][c] != 'S') maze[r][c] = '*';
        if(r < maze.length-1 && "*W".indexOf(maze[r + 1][c]) == -1 && markTheCorrectPath(r + 1, c)) return true;
        if(r > 0 && "*W".indexOf(maze[r - 1][c]) == -1  && markTheCorrectPath(r - 1, c)) return true;
        if(c < maze[0].length-1 && "*W".indexOf(maze[r][c + 1]) == -1  && markTheCorrectPath(r, c + 1)) return true;
        if(c > 0 && "*W".indexOf(maze[r][c - 1]) == -1 && markTheCorrectPath(r, c - 1)) return true;
        maze[r][c] = '.';
        return false;
    }


    /*  4   Mark only the correct path. If no path exists, say so.
            Hint:  the method above returns the boolean that you need. */


    /*
     * From handout, #5.
     * Solve the maze, mark the path, count the steps.
     * Mark only the correct path and display the number of steps.
     * If no path exists, say so.
     */
    public boolean markCorrectPathAndCountSteps(int r, int c, int count) {
        if(maze[r][c] == 'E') {
            System.out.println("Number of steps = " + count);
            return true;
        }
        if(maze[r][c] != 'S') maze[r][c] = '*';
        if(r < maze.length-1 && "*W".indexOf(maze[r + 1][c]) == -1 && markCorrectPathAndCountSteps(r + 1, c, count+1)) return true;
        if(r > 0 && "*W".indexOf(maze[r - 1][c]) == -1  && markCorrectPathAndCountSteps(r - 1, c, count+1)) return true;
        if(c < maze[0].length-1 && "*W".indexOf(maze[r][c + 1]) == -1  && markCorrectPathAndCountSteps(r, c + 1,count+1)) return true;
        if(c > 0 && "*W".indexOf(maze[r][c - 1]) == -1 && markCorrectPathAndCountSteps(r, c - 1,count+1)) return true;
        maze[r][c] = '.';
        return false;
    }
}