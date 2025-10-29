import javax.swing.*;
import java.awt.*;

public class  ColorMood1 {
    private JFrame frame;
    private JLabel moodLabel;
    private JPanel colorPanel;

    public  ColorMood1() {

        frame = new JFrame("Цвет настроения"); // Создаем главное окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout()); // Главная панель

        JPanel buttonPanel = new JPanel();  // Панель для кнопок
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        colorPanel = new JPanel(new BorderLayout()); // Панель, которая будет менять цвет
        colorPanel.setBackground(Color.WHITE);

        JButton redButton = new JButton("Красный"); // Создаем кнопки
        JButton blueButton = new JButton("Синий");
        JButton greenButton = new JButton("Зелёный");

        moodLabel = new JLabel("Выберите цвет настроения", SwingConstants.CENTER); // Метка для отображения цвета настроения
        moodLabel.setFont(new Font("Arial", Font.BOLD, 16));
        moodLabel.setOpaque(true);
        moodLabel.setBackground(Color.LIGHT_GRAY);
        moodLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        redButton.addActionListener(e -> changeColor(Color.RED, "Красный")); // Добавляем обработчики событий
        blueButton.addActionListener(e -> changeColor(Color.BLUE, "Синий"));
        greenButton.addActionListener(e -> changeColor(Color.GREEN, "Зелёный"));

        buttonPanel.add(redButton); // Добавляем кнопки на панель
        buttonPanel.add(blueButton);
        buttonPanel.add(greenButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH); // Добавляем все на главную панель
        mainPanel.add(colorPanel, BorderLayout.CENTER);
        mainPanel.add(moodLabel, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    private void changeColor(Color color, String colorName) {
        colorPanel.setBackground(color); // Меняем цвет центральной панели

        moodLabel.setText("Ваш цвет настроения: " + colorName); // Обновляем текст метки
        moodLabel.setBackground(color);
        moodLabel.setForeground(Color.WHITE);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new  ColorMood1().show());
    }
}