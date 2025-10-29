import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class  ColorMood3 {
    private JFrame frame;
    private JLabel moodLabel;

    public ColorMood3() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {

        frame = new JFrame("Цвет настроения - Задание 3"); // Создаем главное окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 300);

        JPanel buttonPanel = new JPanel(); // Создаем панель для кнопок
        buttonPanel.setLayout(new FlowLayout());

        moodLabel = new JLabel("Выберите цвет настроения", JLabel.CENTER); // Создаем метку для отображения настроения
        moodLabel.setFont(new Font("Arial", Font.BOLD, 16));
        moodLabel.setOpaque(true);
        moodLabel.setBackground(Color.WHITE);

        JButton redButton = createButtonWithMultipleListeners("Красный", Color.RED); // Создаем кнопки с ActionListener и MouseListener
        JButton blueButton = createButtonWithMultipleListeners("Синий", Color.BLUE);
        JButton greenButton = createButtonWithMultipleListeners("Зеленый", Color.GREEN);

        buttonPanel.add(redButton); // Добавляем кнопки на панель
        buttonPanel.add(blueButton);
        buttonPanel.add(greenButton);

        frame.add(buttonPanel, BorderLayout.CENTER); // Добавляем компоненты в окно
        frame.add(moodLabel, BorderLayout.SOUTH);

        frame.setVisible(true); // Отображаем окно
    }

    private JButton createButtonWithMultipleListeners(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 40));

        button.addActionListener(new ActionListener() { // ActionListener для нажатия кнопки
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(color);
                moodLabel.setText("Ваш цвет настроения: " + text);
                System.out.println("Кнопка \"" + text + "\" была нажата");
            }
        });

        button.addMouseListener(new MouseAdapter() { // MouseListener для реакции на наведение мыши
            @Override
            public void mouseEntered(MouseEvent e) {

                JOptionPane.showMessageDialog( // Показываем всплывающее сообщение при наведении
                        frame,
                        "Ты навёл курсор на кнопку \"" + text + "\"!",
                        "Внимание",
                        JOptionPane.INFORMATION_MESSAGE
                );
                System.out.println("Курсор наведен на кнопку: " + text);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new  ColorMood3();
            }
        });
    }
}