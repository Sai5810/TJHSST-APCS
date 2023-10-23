// Name:    Date:

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


public class NQueens_text extends JPanel
{
    // Instance Variables: Encapsulated data for EACH NQueens problem
    private JButton[][] board;
    private int N;
    JSlider speedSlider;
    private int timerDelay;

    public NQueens_text(int n)
    {
        Scanner q = new Scanner(System.in);
        N = q.nextInt();
        this.setLayout(new BorderLayout());
        JPanel north = new JPanel();
        north.setLayout(new FlowLayout());
        add(north, BorderLayout.NORTH);
        JLabel label = new JLabel( N + "Queens solution");
        north.add(label);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(N,N));
        add(center, BorderLayout.CENTER);
        board = new JButton[N][N];
        for(int r = 0; r < N; r++)
            for(int c = 0; c < N; c++)
            {
                board[r][c] = new JButton();
                board[r][c].setBackground(Color.blue);
                center.add(board[r][c]);
            }

        speedSlider = new JSlider();
        speedSlider.setInverted(true);
        add(speedSlider, BorderLayout.SOUTH);
    }

    /** Returns the number of queens to be placed on the board. **/
    public int numQueens()
    {
        return N;
    }

    /** Solves (or attempts to solve) the N Queens Problem. **/
    public boolean solve()
    {
        return isPlaced(0, 0);
    }

    /**
     * Iteratively try to place the queen in each column.
     * Recursively try the next row.
     **/
    private boolean isPlaced(int row, int col)
    {
        if(col == N) return true; //matrix is filled
        if (col < 0 || row < 0 || row > board.length || col > board[0].length) return false;
        for(int i = 0; i < board.length; i++) {
            if(locationIsOK(i, col)){
                addQueen(i, col);
                if (!isPlaced(0, col + 1)) {
                    removeQueen(i, col);
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /** Verify that another queen can't attack this location.
     * You only need to check the locations above this row.
     * Iteration is fine here.
     **/
    private boolean locationIsOK(int r, int c)
    {
        for (int i = 0; i < board.length; i++) {
            if(board[i][c].getBackground() == Color.RED || board[r][i].getBackground() == Color.RED) return false;
        }
        int x = r;
        int y = c;
        while(x > -1 && y > -1) {
            if (board[x][y].getBackground() == Color.RED)  return false;
            x--;
            y--;
        }
        x = r;
        y = c;
        while(x > -1 && y < board.length) {
            if (board[x][y].getBackground() == Color.RED)  return false;
            x--;
            y++;
        }
        x = r;
        y = c;
        while(x < board.length && y < board.length) {
            if (board[x][y].getBackground() == Color.RED)  return false;
            x++;
            y++;
        }
        x = r;
        y = c;
        while(x < board.length && y > -1) {
            if (board[x][y].getBackground() == Color.RED)  return false;
            x++;
            y--;
        }
        return true;
    }

    /** Adds a queen to the specified location.
     **/
    private void addQueen(int r, int c)
    {
        board[r][c].setBackground(Color.RED);
        pause();
    }

    /** Removes a queen from the specified location.
     **/
    private void removeQueen(int r, int c)
    {
        board[r][c].setBackground(Color.BLUE);
        pause();
    }
    private void pause()
    {
        int timerDelay = speedSlider.getValue();
        for(int i=1; i<=timerDelay*1E7; i++)  {}
    }
}