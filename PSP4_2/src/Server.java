import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static java.lang.Math.*;


public class Server {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(12345);
        try {
            boolean flag = true;
            byte[] buffer = new byte[1024];

            while (flag){
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                byte[] data = packet.getData();

                double result = 0;

                int x = ByteBuffer.wrap(data, 0, 4).getInt();
                int y = ByteBuffer.wrap(data, 4, 4).getInt();
                int z = ByteBuffer.wrap(data, 8, 4).getInt();
                result = ( (2*Math.cos(x-Math.PI/6))*(1+(pow(z, 2 )/(3-pow( z, 5)/5)))/(Math.exp(0.5)+pow(Math.sin(y),2)));

                byte[] response = new byte[8];
                ByteBuffer.wrap(response).putDouble(result);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(response, response.length, address, port);
                socket.send(packet);
                FileWriter file = new FileWriter("results");
                file.write("Исходные значения: x=" + x + ", y=" + y + ", z=" + z + "; Результат = " + result + "\n");
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(socket!=null){
                socket.close();
            }
        }
    }
}
