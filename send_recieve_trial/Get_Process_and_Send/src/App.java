import java.io.*;
import java.net.*;

public class App {
    public static void main(String[] args) throws Exception {
        String serverHostname = "192.168.43.124";
        int serverPort = 4567;

        Socket socket = new Socket(serverHostname, serverPort);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String aha = in.readLine(); // Read the content sent by the server

        StringBuilder reverseAha = new StringBuilder();
        for (int i = aha.length() - 1; i >= 0; i--) {
            reverseAha.append(aha.charAt(i));
        }

        String reversedString = reverseAha.toString();

        System.out.println("Got the String from Send_and_Get:"+aha);
        System.out.println("Reversed the String here: "+ reversedString);

        // Send the reversed string back to the server
        out.println(reversedString);

        out.close();
        in.close();
        socket.close();
    }
}
