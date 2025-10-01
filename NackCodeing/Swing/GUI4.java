package Swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI4 implements ActionListener {

    private JFrame frame;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel panel;
    public GUI4() {
        frame = new JFrame("Jämför Tal");
        button1 = new JButton("Jämför");
        panel = new JPanel();
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(20);

        textField3.setEditable(false); 

        createButtonGUI();
    }

    private void createButtonGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        button1.addActionListener(this);
        panel.add(new Label("Tal 1"));
        panel.add(textField1);

        panel.add(new Label("Tal 2"));
        panel.add(textField2);

        panel.add(button1);

        panel.add(new Label("Största Talet"));
        panel.add(textField3);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int num1 =Integer.parseInt(textField1.getText());

            int num2 =Integer.parseInt(textField2.getText());

            int max = Math.max(num1, num2);
            textField3.setText("Största Talet är: " + max);
            
        } catch (NumberFormatException ex) {
            textField3.setText("Fel: Ange ett heltal");
            

        }
    }


    public static void main(String[] args) {
        new GUI4();
    }
}
