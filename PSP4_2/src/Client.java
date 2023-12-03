import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws SocketException {//метод main
        DatagramSocket socket = new DatagramSocket();
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите x: ");
            int x = scanner.nextInt();
            System.out.print("Введите y: ");
            int y = scanner.nextInt();
            System.out.print("Введите z: ");
            int z = scanner.nextInt();
            // Создание объекта DatagramPacket для отправки данных
            byte[] data = ByteBuffer.allocate(12).putInt(x).putInt(y).putInt(z).array();
            InetAddress address = InetAddress.getLocalHost();
            int port = 12345;
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);

            socket.send(packet);

            byte[] buffer = new byte[8];
            packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);

            double res = ByteBuffer.wrap(packet.getData()).getDouble();

            System.out.println("Результат: " + res);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(socket!=null){
                socket.close();
            }
        }
    }
}