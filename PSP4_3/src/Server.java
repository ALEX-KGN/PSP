import java.net.*;

import java.io.*;

class Server {

    static int countclients = 0;//счетчик подключившихся клиентов

    public static void main(String args[]) throws IOException {


        ServerSocket sock = new ServerSocket(1024, 12);
        try (Socket client = sock.accept();
             ObjectInputStream sois = new ObjectInputStream(client.getInputStream());
             ObjectOutputStream soos = new ObjectOutputStream(client.getOutputStream());) {


            while (true) {


                countclients++;

                System.out.println("=======================================");

                System.out.println("Client " + countclients + " connected");


                boolean flag = true;

                while (flag) {
                    try {
                        String str = (String) sois.readObject();
                        String result = "";

                        boolean isPalindrome = true;
                        str = str.toLowerCase();
                        for (int i = 0; i < str.length() / 2; i++) {
                            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                                isPalindrome = false;
                                break;
                            }
                        }
                        if (isPalindrome)
                            result = "Палиндром";
                        else
                            result = "Не палиндром";

                        soos.writeObject(result);
                    } catch (Exception e) {
                        flag = false;
                        System.out.println("Client " + countclients + " disconnected");
                    }
                }

            }

        } catch (Exception e) {

            System.out.println("Error " + e.toString());

        }

    }
}