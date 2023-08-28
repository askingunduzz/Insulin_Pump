package gui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.net.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.JSlider;
import javax.swing.ImageIcon;

public class gui {
        public static int alarm_no=0;
        public static boolean pumpingError=false;

        public static void displayWarnings() {
            if (alarm_no == 2) {
                warning.createWarning(2, "Warning! Low Battery Level!");
            } else if (alarm_no == 3) {
                warning.createWarning(3, "Warning! High Blood Pressure!");
            } else if (alarm_no == 4) {
                warning.createWarning(4, "Warning! Low Blood Pressure!");
            } else if (alarm_no == 5) {
                warning.createWarning(5, "Warning! Low Insulin Reservoir!");
            } else if (alarm_no == 7) {
                warning.createWarning(7, "Warning! User Pressed Alarm!");
            }
            else if (pumpingError) {
                warning.createWarning(6, "Pumping Error!");
                pumpingError = false; 
            } 
        }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        JLabel label = new JLabel();
        label.setText("Insulin Pump Interface");
        label.setForeground(new Color(0x004080));
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Simulator");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); 
        frame.setLayout(null);

        JLabel timeLabel = new JLabel("Time: " + LocalTime.now().toString());
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        timeLabel.setBounds(330, 25, 160, 20);
        frame.add(timeLabel);

        Timer timeTimer = new Timer();
        timeTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLabel.setText("Time: " + LocalTime.now().toString());
            }
        }, 0, 1000);


		int last_pump = 15; // <20
		int last3_bl = 80; // 70-120 arası galiba
		int last2_bl = 90;
		int last1_bl = 75;
        JButton button1 = new JButton("Last Pump: "+ last_pump+ " units");
        JButton button2 = new JButton("Last 3 CGM Readings: "+last3_bl+"-"+last2_bl+"-"+last1_bl+" units");
        int buttonWidth = 300;
        int buttonHeight = 60;
        int frameWidth = 700;
        int frameHeight = 350;

        int buttonX = (frameWidth - 2 * buttonWidth - 20) / 2; 
        int buttonY = (frameHeight - buttonHeight) - 50; 
        button1.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        button2.setBounds(buttonX + buttonWidth + 20, buttonY, buttonWidth, buttonHeight); 
        button1.setBackground(new Color(0x007ACC)); 
        button2.setBackground(new Color(0x007ACC));
        frame.add(button1);
        frame.add(button2);
        frame.add(label);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setBounds(x, y, frameWidth, frameHeight);


        final int battery_level = 20; // <100
        final int insulin_reservoir = 250; // <300 >50 olmazsa low

		JProgressBar insulinProgressBar = new JProgressBar(0, 300); 
		insulinProgressBar.setValue(insulin_reservoir); 
		frame.add(insulinProgressBar);

        JLabel insulinLabel = new JLabel("Insulin Reservoir:");
        insulinLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        insulinLabel.setBounds(20, 20, 250, 20);
        frame.add(insulinLabel);

        JLabel batteryLabel = new JLabel("Battery Level:");
        batteryLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        batteryLabel.setBounds(20, 70, 150, 20); // Adjusted Y-coordinate for the battery label
        frame.add(batteryLabel);

        JProgressBar batteryProgressBar = new JProgressBar(0, 100);
        batteryProgressBar.setValue(battery_level);
        frame.add(batteryProgressBar); 

        JSlider batterySlider = new JSlider(0, 100); // Set the min and max values
        batterySlider.setValue(battery_level); // Set initial value
        batterySlider.setBounds(20, 100, 250, 40); // Adjusted dimensions
        batterySlider.setBackground(Color.RED); // Set the background color of the slider to red
        frame.add(batterySlider);
        
        JSlider insulinSlider = new JSlider(0, 300); // Set the min and max values
        insulinSlider.setValue(insulin_reservoir); // Set initial value
        insulinSlider.setBounds(20, 40, 250, 40); // Adjusted dimensions
        insulinSlider.setBackground(Color.RED); // Set the background color of the slider to red
        frame.add(insulinSlider);
        
        batteryLabel.setText("Battery Level: " + battery_level);
        insulinLabel.setText("Insulin Reservoir: " + insulin_reservoir);
    

        batterySlider.addChangeListener(e -> {
            int newValue = batterySlider.getValue();
            batteryProgressBar.setValue(newValue);
            batteryProgressBar.setString(newValue + " units");
            batteryLabel.setText("Battery Level: " + newValue);
            if (newValue < 20) {
                batterySlider.setBackground(Color.RED);
                alarm_no = 2; 
            } else {
                batterySlider.setBackground(Color.WHITE);
                alarm_no = 0; 
            }
            displayWarnings(); 
        });
        
        insulinSlider.addChangeListener(e -> {
            int newValue = insulinSlider.getValue();
            insulinProgressBar.setValue(newValue);
            insulinProgressBar.setString(newValue + " units");
            insulinLabel.setText("Insulin Reservoir: " + newValue);
            if (newValue < 50) {
                insulinSlider.setBackground(Color.RED);
                alarm_no = 5; 
            } else {
                insulinSlider.setBackground(Color.WHITE);
                alarm_no = 0; 
            }
            displayWarnings(); 
        });

                JLabel powerLabel = new JLabel("Power:");
				powerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
				powerLabel.setBounds(20, 160, 150, 20);
				frame.add(powerLabel);
		
				JButton powerOnButton = new JButton("On");
				JButton powerOffButton = new JButton("Off");
				int powerButtonWidth = 60;
				int powerButtonHeight = 30;
				int powerButtonX = 80;
				int powerButtonY = 160;
		
				powerOnButton.setBounds(powerButtonX, powerButtonY, powerButtonWidth, powerButtonHeight);
				powerOffButton.setBounds(powerButtonX + powerButtonWidth + 10, powerButtonY, powerButtonWidth, powerButtonHeight);
				powerOnButton.setBackground(new Color(0x00FF00)); // Green color
				powerOffButton.setBackground(new Color(0xFF0000)); // Red color
				frame.add(powerOnButton);
				frame.add(powerOffButton);
		
				JLabel screenLabel = new JLabel("Screen:");
				screenLabel.setFont(new Font("Arial", Font.PLAIN, 16));
				screenLabel.setBounds(20, 200, 150, 20);
				frame.add(screenLabel);
				JButton screenOnButton = new JButton("On");
				JButton screenOffButton = new JButton("Off");
		
		int screenButtonX = 80;
        int screenButtonY = 200;
		
		screenOnButton.setBounds(screenButtonX, screenButtonY, powerButtonWidth, powerButtonHeight);
		screenOffButton.setBounds(screenButtonX + powerButtonWidth + 10, screenButtonY, powerButtonWidth, powerButtonHeight);
		screenOnButton.setBackground(new Color(0x00FF00)); 
		screenOffButton.setBackground(new Color(0xFF0000));
		frame.add(screenOnButton);
		frame.add(screenOffButton);

        JLabel foodLabel = new JLabel("Last Glucose Level:");
        foodLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        foodLabel.setForeground(Color.CYAN); // Set the font color to pink
        foodLabel.setBounds(320, 65, 250, 20);
        frame.add(foodLabel);

        JLabel foodLabel2 = new JLabel("Last 2nd Glucose Level:");
        foodLabel2.setFont(new Font("Arial", Font.PLAIN, 16));
        foodLabel2.setForeground(Color.DARK_GRAY); // Set the font color to pink
        foodLabel2.setBounds(320, 105, 250, 20);
        frame.add(foodLabel2);

        JLabel foodLabel3 = new JLabel("Last 3rd Glucose Level:");
        foodLabel3.setFont(new Font("Arial", Font.PLAIN, 16));
        foodLabel3.setForeground(Color.MAGENTA); // Set the font color to pink
        foodLabel3.setBounds(320, 145, 250, 20);
        frame.add(foodLabel3);

        JLabel foodLabel4 = new JLabel("Given Insulin Today:");
        foodLabel4.setFont(new Font("Arial", Font.PLAIN, 16));
        foodLabel4.setForeground(Color.GREEN); // Set the font color to pink
        foodLabel4.setBounds(320, 185, 250, 20);
        frame.add(foodLabel4);

            ImageIcon alarmIcon = new ImageIcon("alarm.png");

            JLabel alarmLabel = new JLabel(alarmIcon);
            alarmLabel.setBounds(frameWidth - 215, 20, 230, frameHeight - 140); 
            frame.add(alarmLabel);

            MouseAdapter alarmClickListener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    warning.createWarning(7, "Warning! User Pressed Alarm!");
                    displayWarnings(); 
                }
            };
            alarmLabel.addMouseListener(alarmClickListener);

            frame.setVisible(true);
            displayWarnings();

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
                scan.close();
                return;
            }

               out.println("b");
               System.out.println("JavaFrameGUI is sending: "+battery_level);
               out.println(battery_level);

              out.println("i");
              System.out.println("JavaFrameGUI is sending: "+ insulin_reservoir);
              out.println(insulin_reservoir);
 
            out.println("bl");
            System.out.println("JavaFrameGUI is sending: "+last3_bl);
            out.println(last3_bl);
            System.out.println("JavaFrameGUI is sending: "+last2_bl);
            out.println(last2_bl);
            System.out.println("JavaFrameGUI is sending: "+last1_bl);
            out.println(last1_bl);
            out.println("ala");

            
              scan.close();
              out.println();
 
        try {
                String answer=in.readLine();
              if(answer.equals("Pump")){in.readLine();
                                                      pumpingError=true;
                                                      displayWarnings();
                }
    
            String batteryAnswer = answer;
            System.out.println("Answer from Battery: " + batteryAnswer);
            if(batteryAnswer.equals("true")){
                alarm_no=2;  
                displayWarnings();                              
            }

            String insulinAnswer = in.readLine();
            System.out.println("Answer from Insulin: " + insulinAnswer);
            if(insulinAnswer.equals("true")){alarm_no=5; displayWarnings();}

            String bloodlevelAnswer = in.readLine();
            if(bloodlevelAnswer.equals("high")){alarm_no=3; displayWarnings();}
            else if(bloodlevelAnswer.equals("low")){alarm_no=4; displayWarnings();}

        } catch (IOException e) {
            System.out.println("Error reading from socket: " + e.getMessage());
        } 

        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error closing connections: " + e.getMessage());
        }
        frame.setVisible(true);
    }
}