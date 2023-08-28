package gui.Algorithms_Controllers;

import java.io.*;
import java.net.*;

public class Listener {
    private static int batteryLevel;
    private static int insulinLevel;
    private static int last3;
    private static int last2;
    private static int last1;
    private static String last3_bl;
    private static String last2_bl;
    private static String last1_bl;
    private static String battery;
    private static String insulin;
    private static String alarm;
    public Listener() {}
    public static void main(String[] args) {
        ControllerAlgorithms control = new ControllerAlgorithms();
        try {
            String serverHostname = "192.168.43.124";
            int serverPort = 4567;
            Socket socket = new Socket(serverHostname, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String read;
            while ((read = in.readLine()) != null) {
                System.out.println("read: " + read);
                if ("b".equals(read)) {
                    battery = in.readLine();
                    batteryLevel = Integer.parseInt(battery);
                    System.out.println(control.batteryChecker(batteryLevel));
                    out.println(control.batteryChecker(batteryLevel));
                } else if ("i".equals(read)) {
                    insulin = in.readLine();
                    insulinLevel = Integer.parseInt(insulin);
                    System.out.println(control.InsulinChecker(insulinLevel));
                    out.println(control.InsulinChecker(insulinLevel));
                } else if ("bl".equals(read)) {
                    last3_bl = in.readLine();
                    last2_bl = in.readLine();
                    last1_bl = in.readLine();
                    last3 = Integer.parseInt(last3_bl);
                    last2 = Integer.parseInt(last2_bl);
                    last1 = Integer.parseInt(last1_bl);
                    System.out.println(control.AvgMaker(last1, last2, last3));
                    out.println(control.AvgMaker(last1, last2, last3));
                } else if ("ala".equals(read)) {
                    alarm = in.readLine();
                } else {
                    System.out.println("oh no " + read);
                }
            }
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}