package main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import java.util.ArrayList;

public class PatternCheck extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel patternLabel, text1Label, text2Label, resultLabel;
    private JTextField patternInput, text1Input;
    private JTextArea text2Input, resultOutput;
    private JButton verifyButton;

    public PatternCheck() {
        setTitle("PatternCheck Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));
        patternLabel = new JLabel("Pattern:");
        patternInput = new JTextField();
        text1Label = new JLabel("Text1:");
        text1Input = new JTextField();
        text2Label = new JLabel("Text2:");
        text2Input = new JTextArea(5, 20);
        patternLabel.setHorizontalAlignment(JTextField.CENTER);
        text1Label.setHorizontalAlignment(JTextField.CENTER);
        text2Label.setHorizontalAlignment(JTextField.CENTER);
        Font boldFont = new Font("Times New Roman", Font.BOLD, 14);
        patternLabel.setFont(boldFont);
        text1Label.setFont(boldFont);
        text2Label.setFont(boldFont);

        Font inputFont = new Font("Times New Roman", Font.PLAIN, 14);
        patternInput.setFont(inputFont);
        text1Input.setFont(inputFont);
        text2Input.setFont(inputFont);
        text2Input.setLineWrap(true); 
        
        inputPanel.add(patternLabel);
        inputPanel.add(patternInput);
        inputPanel.add(text1Label);
        inputPanel.add(text1Input);
        inputPanel.add(text2Label);
        inputPanel.add(new JScrollPane(text2Input));

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        verifyButton = new JButton("CHECK");
        verifyButton.setBackground(Color.GREEN); 
        verifyButton.setFocusable(false);
        buttonPanel.add(verifyButton);

        add(buttonPanel, BorderLayout.CENTER);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout(10, 10));
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultOutput = new JTextArea(5, 20);
        resultOutput.setEditable(false);
        resultOutput.setFont(inputFont); 
        resultOutput.setLineWrap(true); 
        
        int borderWidth = 3; 
        LineBorder lineBorder = new LineBorder(Color.GREEN, borderWidth); 
        inputPanel.setBorder(lineBorder);
     
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(resultOutput), BorderLayout.CENTER);

        add(resultPanel, BorderLayout.SOUTH);

        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pattern = patternInput.getText();
                String text1 = text1Input.getText();
                String text2 = text2Input.getText();

                if (pattern.isEmpty() ) {
                    resultLabel.setText("There is nothing to check.");
                    resultOutput.setText("");
                } else {
                    if (isPatternValid(pattern)) {
                        resultLabel.setText("The entered pattern is valid.");

                        if (Pattern.matches(pattern, text1)) {
                            resultLabel.setText("Text1 has a match with the pattern.");
                        } else {
                            resultLabel.setText("Text1 does not have a match with the pattern.");
                        }

                        Pattern p = Pattern.compile(pattern);
                        Matcher m = p.matcher(text2);
                        ArrayList<String> matches = new ArrayList<>();
                        while (m.find()) {
                            matches.add(m.group());
                        }
                        resultOutput.setText("Strings that match the pattern in Text2 are: \n");
                        for (String match : matches) {
                            resultOutput.append(match + "\n");
                        }
                        if (matches.isEmpty()) {
                            resultOutput.setText("Text2 does not have a match with the pattern.");
                        } else {
                            resultOutput.setText("Strings that match the pattern in Text2 are: \n");
                            for (String match : matches) {
                                resultOutput.append(match + "\n");
                            }
                        }
                    } else {
                        resultLabel.setText("The entered pattern is invalid!");
                        resultOutput.setText("");
                    }
                }
            }
        });
        verifyButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                verifyButton.setBackground(Color.MAGENTA); 
            }

            public void mouseExited(MouseEvent e) {
                verifyButton.setBackground(Color.GREEN); 
        }
        });
        

        pack();
        setLocationRelativeTo(null);
        setSize(800, 500);
    }

    public boolean isPatternValid(String pattern) {
        try {
            Pattern.compile(pattern);
            return true;
        } catch (PatternSyntaxException ex) {
            return false;
        }
    }
}