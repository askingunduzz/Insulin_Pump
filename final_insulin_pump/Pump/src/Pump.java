import java.io.*;
import java.net.*;

public class Pump {
    public Pump() {
        try {
            String serverHostname = "192.168.43.124";
            int serverPort = 4567;
            Socket socket = new Socket(serverHostname, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Pump");
            out.println("pumpingerror");
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
      Pump pump = new Pump();
    }
}
