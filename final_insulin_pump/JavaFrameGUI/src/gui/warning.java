package gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.Timer;

public class warning {

    public static void createWarning(int warningNumber, String message) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Warning " + warningNumber);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = Math.min(610, screenWidth - 20); // Limit frame width to screen width - 20
        int frameHeight = Math.min(330, screenHeight - 20); // Limit frame height to screen height - 20

        JLabel warningLabel = new JLabel(message);
        warningLabel.setForeground(Color.RED);
        warningLabel.setFont(new Font("Arial", Font.BOLD, 34)); // Set a fixed font size for readability
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center horizontally
        warningLabel.setVerticalAlignment(SwingConstants.CENTER);   // Center vertically

        int labelWidth = frameWidth - 20; // Adjusted label width
        int labelHeight = frameHeight - 20; // Adjusted label height

        warningLabel.setBounds(10, 10, labelWidth, labelHeight); // No need to calculate labelX and labelY
        frame.add(warningLabel);

        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setBounds(x, y, frameWidth, frameHeight);

        frame.setAlwaysOnTop(true);

        frame.setVisible(true);

        Timer timer = new Timer(5000, (e) -> {
            frame.dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }
}
