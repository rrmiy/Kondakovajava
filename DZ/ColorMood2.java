import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class  ColorMood2 {
    private JFrame frame;
    private JLabel moodLabel;
    private JPanel colorPanel;

    public  ColorMood2() {

        frame = new JFrame("Цвет настроения"); // Создаем главное окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout()); // Главная панель с BorderLayout

        JPanel buttonPanel = new JPanel(); // Панель для кнопок
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

        ActionListener colorChangeListener = new ActionListener() { // ПЕРВЫЙ СЛУШАТЕЛЬ - меняет цвет фона
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonText = ((JButton) e.getSource()).getText();

                switch (buttonText) {
                    case "Красный":
                        changeColor(Color.RED, "Красный");
                        break;
                    case "Синий":
                        changeColor(Color.BLUE, "Синий");
                        break;
                    case "Зелёный":
                        changeColor(Color.GREEN, "Зелёный");
                        break;
                }
            }
        };

        ActionListener consoleListener = new ActionListener() { // ВТОРОЙ СЛУШАТЕЛЬ - выводит сообщение в консоль
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonText = ((JButton) e.getSource()).getText();
                System.out.println("Кнопка \"" + buttonText + "\" была нажата");
            }
        };

        redButton.addActionListener(colorChangeListener); // Добавляем ОБА слушателя к каждой кнопке
        redButton.addActionListener(consoleListener);

        blueButton.addActionListener(colorChangeListener);
        blueButton.addActionListener(consoleListener);

        greenButton.addActionListener(colorChangeListener);
        greenButton.addActionListener(consoleListener);

        buttonPanel.add(redButton);
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
        SwingUtilities.invokeLater(() -> new  ColorMood2().show());
    }
}