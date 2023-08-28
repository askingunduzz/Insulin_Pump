package gui.Hardware.Battery;

import java.io.*;
import java.net.*;

public class Battery {
    private static String aha; // Declare the aha variable
    private static String class_name;

    public Battery() {
        try {
            String serverHostname = "192.168.43.124";
            int serverPort = 4567;

            Socket socket = new Socket(serverHostname, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            class_name= in.readLine();
            if(class_name=="Battery"){}
            else if(class_name=="InsulinReservoir"){}
            else if(class_name=="ControllerAlgorithms"){}
            



            aha = in.readLine(); // Read the content sent by the server
            String insulin =in.readLine();
            System.out.println("battery: "+ aha);
          //  System.out.println("insulin " +insulin);
            String reversedString = "02";

            System.out.println("Got the String from Send_and_Get:" + aha);
            System.out.println("Reversed the String here: " + reversedString);

            // Send the reversed string back to the server
            out.println(reversedString);

            
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String bat_get(){
       // aha=aha;
       // System.out.println("heheh "+aha);
        return aha;
    }

    public static void main(String[] args) {
        Battery battery = new Battery();
        battery.bat_get();
      //  System.out.println("aha "+ aha);
    }
}
