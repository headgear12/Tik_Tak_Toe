package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    JButton reset;
    String currentPlayer = "O";
    int turnCount = 0;

    Main() {
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("background.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 600, Image.SCALE_SMOOTH); // Use SCALE_SMOOTH for better quality
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 500, 600);
        add(background);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                buttons[index] = new JButton("");
                buttons[index].setFont(new Font("Arial", Font.BOLD, 80));
                buttons[index].setBounds(100 + col * 100, 100 + row * 100, 100, 100);
                buttons[index].addActionListener(this);
                background.add(buttons[index]);
            }
        }

        reset=new JButton("NEW GAME");
        reset.setFont(new Font("Arial", Font.PLAIN,38));
        reset.setBounds(100,500,300,50);
        reset.setForeground(Color.WHITE);
        reset.setBackground(Color.BLACK);
        background.add(reset);
        reset.addActionListener(this);

        setTitle("Tic-Tac-Toe");
        setSize(500, 600);
        setLocation(500, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == reset){
            resetBoard();
            currentPlayer =  "O";
            turnCount = 0;
        }
        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().isEmpty()) {
            return;
        }
        clickedButton.setText(currentPlayer);

        if (checkForWinner()) {
            JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
            resetBoard();
        } else if (turnCount == 8) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        } else {
            currentPlayer = currentPlayer.equals("O") ? "X" : "O";
            turnCount++;
        }
    }

    private boolean checkForWinner() {
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] condition : winConditions) {
            if (buttons[condition[0]].getText().equals(currentPlayer) &&
                    buttons[condition[1]].getText().equals(currentPlayer) &&
                    buttons[condition[2]].getText().equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
        }
        turnCount = 0;
        currentPlayer = "O";
    }

    public static void main(String[] args) {
        new Main();
    }
}
