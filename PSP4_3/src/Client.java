import java.awt.Button;

import java.awt.Frame;

import java.awt.Label;

import java.awt.TextArea;

import java.awt.TextField;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;

import java.awt.event.WindowListener;

import java.io.*;

import java.net.*;

class Client extends Frame implements ActionListener, WindowListener {

    TextField tf, tf1, tf2;

    TextArea ta;

    Label la, la1, la2, la3, la4;

    Socket sock = null;

    Button btn, btn1, btn2;

    ObjectOutputStream coos = null;
    ObjectInputStream cois = null;


    public static void main(String args[]) {

        Client c = new Client();

        c.GUI();

    }
    private void GUI() {

        setTitle("КЛИЕНТ");

        tf = new TextField("127.0.0.1");//ip adress клиента

        tf1 = new TextField("1024");// port клиента

        tf2 = new TextField();

        ta = new TextArea();
        ta.setEditable(false);

        la = new Label("IP ADRESS");

        la1 = new Label("port");

        la2 = new Label("sending date");

        la3 = new Label("result ");

        la4 = new Label(" ");

        btn = new Button("connect ");

        btn1 = new Button("send ");
        btn1.setEnabled(false);

        btn2 = new Button("disconnect ");
        btn2.setEnabled(false);

        tf.setBounds(200, 50, 70, 25);

        tf1.setBounds(330, 50, 70, 25);

        tf2.setBounds(150, 200, 250, 25);

        ta.setBounds(150, 300, 250, 100);

        btn.setBounds(50, 50, 70, 25);

        btn2.setBounds(50,100,70,25);

        btn1.setBounds(50, 200, 70, 25);

        la.setBounds(130, 50, 150, 25);

        la1.setBounds(280, 50, 150, 25);

        la2.setBounds(150, 150, 150, 25);

        la3.setBounds(160, 250, 150, 25);

        la4.setBounds(600, 10, 150, 25);

        add(tf);

        add(tf1);

        add(tf2);

        add(btn);

        add(btn1);

        add(btn2);

        add(ta);

        add(la);

        add(la1);

        add(la2);

        add(la3);

        add(la4);

        setSize(600, 450);

        setVisible(true);

        addWindowListener(this);

        btn.addActionListener(al);

        btn2.addActionListener(al2);

        btn1.addActionListener(this);

        tf2.getText();

    }

    public void windowClosing(WindowEvent we) {

        if (sock != null && !sock.isClosed()) {

            try {
                cois.close();
                coos.close();
                sock.close();

            } catch (IOException e) {

            }

        }

        this.dispose();

    }

    public void windowActivated(WindowEvent we) {}   ;

    public void windowClosed(WindowEvent we) {};

    public void windowDeactivated(WindowEvent we) {};

    public void windowDeiconified(WindowEvent we) {}   ;

    public void windowIconified(WindowEvent we) {};

    public void windowOpened(WindowEvent we) { } ;

    public void actionPerformed(ActionEvent e) {

        if (sock == null) {
            return;
        }

        try {

            String word = tf2.getText();

            coos.writeObject(word); // отправляем введенные данные. Тип string переводим в byte

            String result;

            result = (String) cois.readObject(); //получаем назад информацию,которую послал сервер

            ta.append(result + "\n"); // в text area записываем полученные данные


        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

    public void actionPerformed2(ActionEvent e) {}

    ActionListener al = new ActionListener() { //событие на нажатие кнопки

        @Override

        public void actionPerformed(ActionEvent arg0) {

            try {

                sock = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                coos = new ObjectOutputStream(sock.getOutputStream());
                cois = new ObjectInputStream(sock.getInputStream());

                btn.setEnabled(false);
                btn2.setEnabled(true);
                btn1.setEnabled(true);

            } catch (NumberFormatException e) {

            } catch (UnknownHostException e) {

            } catch (IOException e) {

            }} };

    ActionListener al2 = new ActionListener() {

        @Override

        public void actionPerformed(ActionEvent arg0) {
            try {
                cois.close();
                coos.close();
                sock.close();

                btn.setEnabled(true);
                btn2.setEnabled(false);
                btn1.setEnabled(false);

            } catch (IOException e) {

            }
        } };
}