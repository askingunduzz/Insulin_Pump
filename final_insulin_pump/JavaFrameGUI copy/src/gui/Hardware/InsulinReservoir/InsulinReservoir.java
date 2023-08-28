package gui.Hardware.InsulinReservoir;

import java.io.BufferedReader;
import java.io.*;
import java.net.*;


public class InsulinReservoir {
    private static String insulin;

    public InsulinReservoir(){
         try {
            String serverHostname = "192.168.43.124";
            int serverPort = 4567;

            Socket socket = new Socket(serverHostname, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  
            insulin = in.readLine();
            System.out.println("got the insulin: "+ insulin);

             in.close();
            socket.close();
               out.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public String insulin_get(){
       // System.out.println("nn "+insulin);
        return insulin;
    }
    public static void main(String[] args){
       // System.out.println(insulin);
        InsulinReservoir ins = new InsulinReservoir();
      //  ins.insulin_get();
        
    }
}
/*

        System.out.println("aha "+ aha);

 */