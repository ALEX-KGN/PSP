import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientAccepted     = null;
        ObjectInputStream  sois   = null;
        ObjectOutputStream soos   = null;
        try {
            System.out.println("Сервер начал работу)");
            serverSocket = new ServerSocket(2525);//создание сокета сервера для заданного порта
            clientAccepted = serverSocket.accept();//выполнение метода, который обеспечивает реальное подключение сервера к клиенту
            System.out.println("Соединение установлено)");

            sois = new ObjectInputStream(clientAccepted.getInputStream()); //создание потока ввода
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());//создание потока вывода
            String clientMessageRecieved = (String)sois.readObject();//объявление строки и присваивание ей данных потока ввода, представленных в виде строки (передано клиентом)
            while(!clientMessageRecieved.equals("Стоп"))
            {
                System.out.println("Полученное сообщение: '"+clientMessageRecieved+"'");
                soos.writeObject(calculateTax(clientMessageRecieved));//потоку вывода присваивается значение строковой переменной (передается клиенту)
                clientMessageRecieved = (String)sois.readObject();//строке присваиваются данные потока ввода, представленные в виде строки (передано клиентом)
            }   }catch(Exception e)	{
        } finally {
            try {
                sois.close();//закрытие потока ввода
                soos.close();//закрытие потока вывода
                clientAccepted.close();//закрытие сокета, выделенного для клиента
                serverSocket.close();//закрытие сокета сервера
            } catch(Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }

    }

    public static String calculateTax(String money){
        try {
            Integer cash = Integer.parseInt(money);
            if(cash<0){
                throw new NumberFormatException();
            }
            else if(cash < 100000){
                return String.valueOf(cash*0.05);
            }
            else if(cash >= 100000 && cash < 500000){
                return String.valueOf(cash*0.1);
            }
            else{
                return String.valueOf(cash*0.15);
            }
        } catch (NumberFormatException e) {
            return "Ошибка ввода";
        }
    }
}