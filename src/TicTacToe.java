import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.Border;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == "") {
                            tile.setText(currentPlayer);
                            checkWinner();
                            if(!gameOver) {
                                /*Verwirrendes Zeug, was ich einfach so aus dem Internet kopiert hab :)
                                Aber es macht, dass zwischen Spieler x und o gewechselt wird*/
                                currentPlayer = Objects.equals(currentPlayer, playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }

                        }

                    }
                });
            }
        }
    }
    void checkWinner() {
        //horizontal
        for(int r = 0; r < 3; r++) {
            if(board[r][0].getText() == "") continue;

            if(board[r][0].getText() == board[r][1].getText() &&
               board[r][1].getText() == board[r][2].getText()) {
                for(int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }
        //vertical
        for(int r = 0; r < 3; r++) {
            if(board[0][r].getText() == "") continue;

            if(board[0][r].getText() == board[1][r].getText() &&
               board[1][r].getText() == board[2][r].getText()) {
                for(int i = 0; i < 3; i++) {
                    setWinner(board[i][r]);
                }
                gameOver = true;
                return;
            }
        }
        //diagonal bin ich zu faul für, mach doch selbst!
    }
    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " wins");
    }
}
