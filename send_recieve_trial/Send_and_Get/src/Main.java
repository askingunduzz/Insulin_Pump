import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        String aha = "abcdf";

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4567);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 4567");
            System.exit(-1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4567");
            System.exit(-1);
        }

        PrintWriter out;
        BufferedReader in;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error creating PrintWriter or BufferedReader: " + e.getMessage());
            return;
        }

        out.println(aha); // Send the content of aha to the client
        System.out.println("Send_and_Get is sending: "+aha);
        // Receive the reversed string from the client
        try {
            String reversedFromApp = in.readLine();
            System.out.println("Reversed from Get_Process_and_Send: " + reversedFromApp);
        } catch (IOException e) {
            System.out.println("Error reading from socket: " + e.getMessage());
        }

        // Close connections
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error closing connections: " + e.getMessage());
        }
    }
}
