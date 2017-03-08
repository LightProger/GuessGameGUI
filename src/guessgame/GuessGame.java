package guessgame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessGame extends JFrame implements ActionListener {

//        Переменные
    JPanel textdispPanel, textPanel, buttonPanel;
    JLabel numberLabel, textLabel;
    JTextField textField;
    JButton newGame;
    int number = 0;
    int displayValue = 0;
    String[] messsage = {"Поздравляем! Вы угадали!", "Может еще разок?"};

//        Конструктор
    public GuessGame() {
        super("Угадай число");

//        Cоздаем окно
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);


//       Создаем панель textdispPanel
        textdispPanel = new JPanel(new GridLayout(1, 2));
        textdispPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

//        Создаем текстовое поле с надписью и добавляем ее на панель textdispPanel
        numberLabel = new JLabel();
        numberLabel.setText("Выберите число от 0 до 9: ");
        textdispPanel.add(numberLabel);

//        Cоздаем поле ввода и добавляем его на панель textdispPanel
        textField = new JTextField(1);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setToolTipText("0123456789");
        textdispPanel.add(textField);

//        Создаем панель textPanel
        textPanel = new JPanel(new GridLayout(1, 1));
        textPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

//        Cоздаем тектовое поле, куда будут выводится сообщения и добавляем его на панель textPanel
        textLabel = new JLabel();
        textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setText("");
        textPanel.add(textLabel);

//        Создаем панель для кнопки и добавляем на нее кнопку Играть
        buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        newGame = new JButton("Играть");
        newGame.addActionListener(this);
        buttonPanel.add(newGame);

//        Добавляем все панели в окно
        this.add(textdispPanel, "North");
        this.add(textPanel, "Center");
        this.add(buttonPanel, "South");
        setVisible(true);

//        Генерируем случайное число от 0 до 9
        number = (int) (Math.random() * 10);

//         Локализация кнопок
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
    }


    public static void main(String[] args) {
        new GuessGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        Создаем источник действия
        Object theButton = e.getSource();

//        Обрабатываем данные введеные в поле ввода
        String displayTextField = textField.getText();

        if (!"".equals(displayTextField)) {
            try {
                displayValue = Integer.parseInt(displayTextField);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Введите цифры!!!");
                textField.setText(null);
            }
        }

//        Дейстия при нажатии кнопки Играть
        if (theButton == newGame) {
            while (true) {
                if (displayValue > number) {
                    textLabel.setText("Ваше число больше загаданого. Попробуйте еще раз!");
                } else if (displayValue < number) {
                    textLabel.setText("Ваше число меньше загаданого. Попробуйте еще раз!");
                } else if (displayValue == number) {

                    // Окно сообщения
                    int result = JOptionPane.showConfirmDialog(GuessGame.this,
                            messsage, "Окно сообщения", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        textField.setText(null);
                        textLabel.setText(null);
                        number = (int) (Math.random() * 10);
                    } else if (result == JOptionPane.NO_OPTION) {
                        textField.setText(null);
                        textLabel.setText(null);
                        number = 0;
                    }
                }
                break;
            }
        }
    }
}
