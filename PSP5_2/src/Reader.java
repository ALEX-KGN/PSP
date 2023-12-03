import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class Reader extends JFrame {
    int k;
    Object boxA, boxB, boxC;
    File file = new File("GUI.txt");
    static JLabel l1, l2, l3, l4;
    JComboBox box_1, box_2, box_3;
    static JButton b, del, show;

    static JTextArea textArea;
    static JTextField text1, text2;
    static String[] box1 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    static String[] box2 = {"январь","февраль","март","апрель","май","июнь","июль","август","сентябрь","октябрь","ноябрь","декабрь"};
    static String[] box3 = {"2023","2024"};

    public Reader(String str){
        super(str);
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b = new JButton("Внесение в базу");
        del = new JButton("Очистить");
        show = new JButton("Вывести продукцию");
        text1 = new JTextField(9);
        text2 = new JTextField(9);
        l1 = new JLabel("Название продукта");
        l2 = new JLabel("Количество (шт.)");
        l3 = new JLabel("Дата поступления");
        l4 = new JLabel("Принятая продукция");
        box_1 = new JComboBox(box1);
        box_2 = new JComboBox(box2);
        box_3 = new JComboBox<Object>(box3);
        textArea = new JTextArea();

        setLayout(null);
        del.setSize(100, 30);
        del.setLocation(10, 260);
        b.setSize(150,30);
        b.setLocation(120, 260);
        show.setSize(190,30);
        show.setLocation(280,260);
        text1.setSize(220,25);
        text1.setLocation(250, 20);
        text2.setSize(220,25);
        text2.setLocation(250,65);
        l1.setSize(200,25);
        l1.setLocation(30, 20);
        l2.setSize(220, 25);
        l2.setLocation(30, 60);
        l3.setSize(200, 25);
        l3.setLocation(30, 100);
        l4.setSize(200, 25);
        l4.setLocation(30, 140);
        box_1.setSize(40,25);
        box_1.setLocation(250,105);
        box_2.setSize(100, 25);
        box_2.setLocation(300, 105);
        box_3.setSize(60, 25);
        box_3.setLocation(410, 105);
        textArea.setSize(220,100);
        textArea.setLocation(250,140);
        textArea.setEnabled(false);

        add(b);
        add(del);
        add(text1);
        add(text2);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(box_1);
        add(box_2);
        add(box_3);
        add(textArea);
        add(show);

        b.addActionListener(new ButtonActionListener());
        del.addActionListener(new DelActionListener());
        box_1.addActionListener(new BoxActionListener());
        box_2.addActionListener(new BoxActionListener());
        box_3.addActionListener(new BoxActionListener());
        text2.addKeyListener(new KeyAdapter());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try{
                if(!file.exists()){
                    file.createNewFile();
                }
                FileWriter out = new FileWriter(file, true);
                try{
                    String text1 = Reader.text1.getText();
                    out.write(text1 + " - ");
                    if(k == 1)
                        out.write("Отпуск медикамента осуществляется без рецепта. " + "\n");
                    else if(k == -1)
                        out.write("Отпуск медикамента осуществляется при наличии рецепта. " + "\n");
                    out.write(" (Дата поступления:" + boxA + " " + boxB + " " + boxC + ")");
                } finally{
                    out.close();
                }
            }catch(IOException e1){
                throw new RuntimeException(e1);
            }
        }
    }


    public class DelActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == del){
                text1.setText(null);
                text2.setText(null);
                textArea.setText(null);
            }
        }
    }
    public class BoxActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == box_1){
                boxA = box_1.getSelectedItem();
            }
            if(e.getSource() == box_2){
                boxB = box_2.getSelectedItem();
            }
            if(e.getSource() == box_3){
                boxC = box_3.getSelectedItem();

            }
        }
    }

    public class KeyAdapter implements KeyListener {

        public void keyTyped(KeyEvent e) {
            if(e.getSource() == text2){
                if (text2.getText().length() >= 8) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }}
        }


        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
