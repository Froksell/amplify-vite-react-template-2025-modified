package Swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI1_3 implements ActionListener {

    private JFrame frame;
    private JButton button;
    private JButton button2;
    private JButton button3;
    private JPanel panel;
    private JTextField textField;
    public GUI1_3() {
        frame = new JFrame("Button");
        button = new JButton("Button");
        button2 = new JButton("Button2");
        button3 = new JButton("Button3");
        panel = new JPanel();
        textField = new JTextField(20);

        createButtonGUI();
    }

    private void createButtonGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        button.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        panel.add(textField);
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        frame.add(panel, BorderLayout.CENTER);
        textField.setText("");

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();  
        if (source == button) {
            button.setText("Clicked");
            button3.setText("Knapp 3 ändrad");

        } else if (source==button2) {
            button2.setText("Hello");
            textField.setText("Hello World");
        } else if (source==button3) {
            button.setText("Ding");
            textField.setText("Button 3 pressed");
            panel.setBackground(java.awt.Color.GRAY);
        }
    }


    public static void main(String[] args) {
        new GUI1_3();
    }
}
