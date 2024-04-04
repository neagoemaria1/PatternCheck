package main;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PatternCheck patternCheck = new PatternCheck();
                patternCheck.setVisible(true);
            }
        });
    }
}