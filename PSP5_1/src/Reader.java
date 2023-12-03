import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Reader extends JFrame {
    JButton btn;
    JTextField number1, number2, result;
    JRadioButton sum, sub, mul, div, clear;
    JLabel lbl1, lbl2, lbl3, lbl4;
    ButtonGroup btnGroup;

    public Reader(String str) {
        setTitle(str);
        setSize(295, 310);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn = new JButton("Выполнить");
        number1 = new JTextField();
        number2 = new JTextField();
        result = new JTextField();
        sum = new JRadioButton("Суммировать");
        sub = new JRadioButton("Вычесть");
        mul = new JRadioButton("Умножить");
        div = new JRadioButton("Поделить");
        clear = new JRadioButton("Очистить");
        btnGroup = new ButtonGroup();
        btnGroup.add(sum);
        btnGroup.add(sub);
        btnGroup.add(mul);
        btnGroup.add(div);
        btnGroup.add(clear);
        lbl1 = new JLabel("Число 1");
        lbl2 = new JLabel("Число 2");
        lbl3 = new JLabel("Результат");
        lbl4 = new JLabel("Действие");

        setLayout(null);
        number1.setSize(60, 30);
        number2.setSize(60, 30);
        result.setSize(100, 30);
        lbl1.setSize(60, 20);
        lbl2.setSize(60, 20);
        lbl3.setSize(100, 20);
        lbl4.setSize(40, 20);
        btn.setSize(100, 30);
        sum.setSize(150, 20);
        sub.setSize(150, 20);
        mul.setSize(150, 20);
        div.setSize(150, 20);
        clear.setSize(150, 20);

        number1.setLocation(10, 40);
        number2.setLocation(90, 40);
        result.setLocation(170, 40);
        btn.setLocation(170, 90);
        lbl1.setLocation(10, 20);
        lbl2.setLocation(90, 20);
        lbl3.setLocation(170, 20);
        sum.setLocation(10, 80);
        sub.setLocation(10, 120);
        mul.setLocation(10, 160);
        div.setLocation(10, 200);
        clear.setLocation(10, 240);

        result.setEnabled(false);
        clear.setSelected(true);

        add(number1);
        add(number2);
        add(result);
        add(lbl1);
        add(lbl2);
        add(lbl3);
        add(sum);
        add(sub);
        add(mul);
        add(div);
        add(clear);
        add(btn);

        btn.addActionListener(new BtnActionListener());
        number1.addKeyListener(new KeyAdapter());
        number2.addKeyListener(new KeyAdapter());
    }

    public class KeyAdapter implements KeyListener {

        public void keyTyped(KeyEvent e) {
            if (e.getSource() == number1) {
                if (number1.getText().length() >= 4) {
                    e.consume();
                }
            }
            if (e.getSource() == number2) {
                if (number2.getText().length() >= 4) {
                    e.consume();
                }
            }
            char c = e.getKeyChar();
            if (!Character.isDigit(c)) {
                e.consume();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public class BtnActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String result_string = "";
            if ((number1.getText().isEmpty() || number2.getText().isEmpty()) && !clear.isSelected()) {
                result_string = "Введите числа";
            } else {
                if (sum.isSelected()) {
                    result_string = String.valueOf(Integer.parseInt(number1.getText()) + Integer.parseInt(number2.getText()));
                } else if (sub.isSelected()) {
                    result_string = String.valueOf(Integer.parseInt(number1.getText()) - Integer.parseInt(number2.getText()));
                } else if (div.isSelected()) {
                    result_string = String.valueOf(Integer.parseInt(number1.getText()) / Integer.parseInt(number2.getText()));
                } else if (mul.isSelected()) {
                    result_string = String.valueOf(Integer.parseInt(number1.getText()) * Integer.parseInt(number2.getText()));
                } else {
                    number2.setText("");
                    number1.setText("");
                }
            }
            result.setText(result_string);
        }
    }

}
