import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MovingLabelApp extends JFrame {
    private JLabel movingLabel;
    private JButton resetButton;
    private JPanel mainPanel;
    private Random random;

    public MovingLabelApp() {
        setTitle("Moving Label");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        random = new Random();

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel);

        movingLabel = new JLabel("Наведи на меня!");
        movingLabel.setBounds(175, 150, 150, 30);
        movingLabel.setOpaque(true);
        movingLabel.setBackground(Color.GREEN);
        movingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        resetButton = new JButton("Вернуть на место");
        resetButton.setBounds(175, 300, 150, 30);

        mainPanel.add(movingLabel);
        mainPanel.add(resetButton);

        movingLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int x = random.nextInt(350);
                int y = random.nextInt(250);
                movingLabel.setLocation(x, y);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingLabel.setLocation(175, 150);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MovingLabelApp().setVisible(true);
            }
        });
    }
}