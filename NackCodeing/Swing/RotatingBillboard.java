import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotatingBillboard extends JPanel implements ActionListener {
    private double angle = 0;
    private Timer timer;

    public RotatingBillboard() {
        timer = new Timer(50, this); // updates every 50 milliseconds
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Move origin to center
        g2d.translate(centerX, centerY);

        // Rotate by current angle
        g2d.rotate(angle);

        // Draw a blue rectangle (the billboard)
        g2d.setColor(Color.BLUE);
        g2d.fillRect(-100, -50, 200, 100);

        // Draw the text on the billboard
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fm = g2d.getFontMetrics();
        String text = "Rotating Billboard";

        // Center the text inside the rectangle
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        g2d.drawString(text, -textWidth / 2, textHeight / 4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 0.05;  // Increment rotation angle
        repaint();      // Trigger repaint to animate
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotating Billboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new RotatingBillboard());
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
    }
}
