
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReklamText extends JFrame {

    public ReklamText() {

        // själva fönstret
        setTitle("Reklamskylt - Textdel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        
        setLayout(new BorderLayout());

        // Panel (texts)
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // 4 annonser (kund, meddelande, betalat)
        java.util.List<String[]> messages = new ArrayList<>();
        messages.add(new String[]{"Hederlige Harrys Bilar:", "En bra bilaffär (för Harry)", "5000"});
        messages.add(new String[]{"Farmor Ankas Pajer AB", "Skynda innan Mårten äter allt!", "2000"});
        messages.add(new String[]{"Svarte Petters Svartbyggen", "Bygga svart? Ring Petter", "1500"});
        messages.add(new String[]{"Långbens detektivbyrå", "Mysterier? Ring Långben", "4000"});

        // tabellen (överst)
        JPanel header = new JPanel(new GridLayout(1, 3));
        header.setBackground(Color.DARK_GRAY);
        header.add(createLabel("KUND", Color.WHITE, true));
        header.add(createLabel("Meddelande", Color.WHITE, true));
        header.add(createLabel("Betalat", Color.WHITE, true));
        panel.add(header);

        // Skriver ut ,kundens information
        for (String[] msg : messages) {
            JPanel row = new JPanel(new GridLayout(1, 3));
            row.setBackground(Color.BLACK);
            row.add(createLabel(msg[0], Color.GREEN, false));
            row.add(createLabel(msg[1], Color.GREEN, false));
            row.add(createLabel(msg[2] + " kr", Color.GREEN, false));
            panel.add(row);
        }

        // Lägger till panelen i fönstret
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Hjälpmetod som gör det enklare att skapa textetiketter
    private JLabel createLabel(String text, Color color, boolean bold) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(color);
        label.setFont(new Font("Consolas", bold ? Font.BOLD : Font.PLAIN, 16));
        return label;
    }

    // Huvudmetoden för att starta programmet (bara textdelen)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReklamText::new);
    }
}