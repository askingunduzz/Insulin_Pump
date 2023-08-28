package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class start {

    public static void main(String[] args) {
        JFrame enrollmentFrame = new JFrame();
        enrollmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        enrollmentFrame.setTitle("Enrollment");
        enrollmentFrame.getContentPane().setBackground(new Color(0xE0E0E0));
        enrollmentFrame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel heightLabel = new JLabel("Height (cm):");
        heightLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField nameField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField heightField = new JTextField();

        JButton okButton = new JButton("OK");

        int labelWidth = 80;
        int labelHeight = 25;
        int fieldWidth = 150;
        int fieldHeight = 25;
        int buttonWidth = 80;
        int buttonHeight = 30;
        int frameWidth = 300;
        int frameHeight = 250;

        int labelX = 20;
        int startY = 30;
        int fieldX = labelX + labelWidth + 10;
        int startYFields = startY + labelHeight + 5;
        int buttonX = (frameWidth - buttonWidth) / 2;
        int buttonY = startYFields + 3 * fieldHeight + 20;

        nameLabel.setBounds(labelX, startY, labelWidth, labelHeight);
        weightLabel.setBounds(labelX, startYFields + fieldHeight, labelWidth, labelHeight);
        heightLabel.setBounds(labelX, startYFields + 2 * fieldHeight, labelWidth, labelHeight);

        nameField.setBounds(fieldX, startY, fieldWidth, fieldHeight);
        weightField.setBounds(fieldX, startYFields + fieldHeight, fieldWidth, fieldHeight);
        heightField.setBounds(fieldX, startYFields + 2 * fieldHeight, fieldWidth, fieldHeight);

        okButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        enrollmentFrame.add(nameLabel);
        enrollmentFrame.add(weightLabel);
        enrollmentFrame.add(heightLabel);
        enrollmentFrame.add(nameField);
        enrollmentFrame.add(weightField);
        enrollmentFrame.add(heightField);
        enrollmentFrame.add(okButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        enrollmentFrame.setBounds(x, y, frameWidth, frameHeight);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String weight = weightField.getText();
                String height = heightField.getText();

                if (!name.isEmpty() && !weight.isEmpty() && !height.isEmpty()) {
                    JOptionPane.showMessageDialog(enrollmentFrame, "You are successfully enrolled!");
                    enrollmentFrame.dispose(); // Close the enrollment frame

                    // Start the "gui" class
                    gui.main(null);
                } else {
                    JOptionPane.showMessageDialog(enrollmentFrame, "Please fill in all fields.");
                }
            }
        });

        enrollmentFrame.setVisible(true);
    }
}
