import java.io.*;//импорт пакета, содержащего классы для ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для работы в сети


public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Подключение к серверу");
            Socket clientSocket = new Socket("127.0.0.1",2525);//установление //соединения между локальной машиной и указанным портом узла сети
            System.out.println("Соединение установлено");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));//создание буферизированного символьного потока ввода
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());//создание потока вывода
            ObjectInputStream cois = new ObjectInputStream(clientSocket.getInputStream());//создание потока ввода
            System.out.println("Введите строку ('Стоп' - выход):");
            String clientMessage = stdin.readLine();
            while(!clientMessage.equals("Стоп")) {//выполнение цикла, пока строка
                coos.writeObject(clientMessage);//потоку вывода присваивается значение строковой переменной (передается серверу)
                System.out.println("Отправленная сумма - "+clientMessage +", подоходный налог составляет - "+cois.readObject());
                clientMessage = stdin.readLine();//ввод текста с клавиатуры
            }
            coos.close();//закрытие потока вывода
            cois.close();//закрытие потока ввода
            clientSocket.close();//закрытие сокета
            System.out.println("Отключение от сервера");
        }catch(Exception e)	{
            e.printStackTrace();//выполнение метода исключения е
        }
    }
}