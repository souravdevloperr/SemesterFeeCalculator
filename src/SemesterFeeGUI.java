import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SemesterFeeGUI {
    private static JTextField coreCourseField;
    private static JTextField genCourseField;
    private static JTextField labCourseField;
    private static JRadioButton waiverYes;
    private static JRadioButton waiverNo;
    private static JTextField waiverPercentageField;
    private static JLabel resultLabel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Semester Fee Calculator For 231 Batch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(531, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Load the icon from resources
        ImageIcon icon = new ImageIcon(SemesterFeeGUI.class.getResource("/resources/logo.jpg"));
        frame.setIconImage(icon.getImage());  // Set the icon for JFrame

        // Load background image
        JLabel background = new JLabel(new ImageIcon(SemesterFeeGUI.class.getResource("/resources/backGround1.jpg")));
        frame.setContentPane(background);
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Color lightGray = new Color(211, 211, 211); // Light gray color
        Font modernFont = new Font("Segoe UI ", Font.PLAIN, 15); // Set Segoe UI font here

        JLabel coreCourseLabel = new JLabel("How many Core courses in this semester?");
        coreCourseLabel.setForeground(lightGray);
        coreCourseLabel.setFont(modernFont);
        coreCourseField = new JTextField();
        coreCourseField.setFont(modernFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        background.add(coreCourseLabel, gbc);
        gbc.gridx = 1;
        background.add(coreCourseField, gbc);

        JLabel genCourseLabel = new JLabel("How many General courses in this semester?");
        genCourseLabel.setForeground(lightGray);
        genCourseLabel.setFont(modernFont);
        genCourseField = new JTextField();
        genCourseField.setFont(modernFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        background.add(genCourseLabel, gbc);
        gbc.gridx = 1;
        background.add(genCourseField, gbc);

        JLabel labCourseLabel = new JLabel("How many Lab courses in this semester?");
        labCourseLabel.setForeground(lightGray);
        labCourseLabel.setFont(modernFont);
        labCourseField = new JTextField();
        labCourseField.setFont(modernFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        background.add(labCourseLabel, gbc);
        gbc.gridx = 1;
        background.add(labCourseField, gbc);

        JLabel waiverLabel = new JLabel("Do you have any waiver?");
        waiverLabel.setForeground(lightGray);
        waiverLabel.setFont(modernFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        background.add(waiverLabel, gbc);

        JPanel waiverPanel = new JPanel(new FlowLayout());
        waiverPanel.setOpaque(false);
        waiverYes = new JRadioButton("YES");
        waiverYes.setFont(modernFont);
        waiverNo = new JRadioButton("NO");
        waiverNo.setFont(modernFont);
        waiverYes.setForeground(lightGray);
        waiverNo.setForeground(lightGray);
        ButtonGroup waiverGroup = new ButtonGroup();
        waiverGroup.add(waiverYes);
        waiverGroup.add(waiverNo);
        waiverPanel.add(waiverYes);
        waiverPanel.add(waiverNo);
        gbc.gridx = 1;
        background.add(waiverPanel, gbc);

        waiverYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waiverPercentageField.setEditable(true);
            }
        });

        waiverNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waiverPercentageField.setEditable(false);
                waiverPercentageField.setText("");
            }
        });

        JLabel waiverPercentageLabel = new JLabel("Percentage of Waiver:");
        waiverPercentageLabel.setForeground(lightGray);
        waiverPercentageLabel.setFont(modernFont);
        waiverPercentageField = new JTextField();
        waiverPercentageField.setFont(modernFont);
        waiverPercentageField.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        background.add(waiverPercentageLabel, gbc);
        gbc.gridx = 1;
        background.add(waiverPercentageField, gbc);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(modernFont);
        calculateButton.setBackground(new Color(58, 175, 169));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(new CalculateListener());
        gbc.gridx = 1;
        gbc.gridy = 5;
        background.add(calculateButton, gbc);

        resultLabel = new JLabel("");
        resultLabel.setForeground(lightGray);
        resultLabel.setFont(modernFont);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        background.add(resultLabel, gbc);

        frame.setVisible(true);
    }

    static class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int coreCourse = Integer.parseInt(coreCourseField.getText());
            int genCourse = Integer.parseInt(genCourseField.getText());
            int labCourse = Integer.parseInt(labCourseField.getText());
            boolean hasWaiver = waiverYes.isSelected();
            int semesterFee = 20250;
            float totalFee = (coreCourse * 3 * 4900) + (genCourse * 3 * 3300) + (labCourse * 1.5f * 5000);

            if (hasWaiver) {
                int percentage = Integer.parseInt(waiverPercentageField.getText());
                totalFee = totalFee - (totalFee * percentage / 100) + semesterFee;
            } else {
                totalFee = totalFee + semesterFee;
            }

            float registrationFee = 32250;
            float midExamFee = (totalFee - registrationFee) / 2;
            float finalExamFee = (totalFee - registrationFee) / 2;

            String resultText = String.format("You need to pay %.2f in Registration time.\nBefore Mid Exam %.2f\nBefore Final Exam %.2f\nTotal you will pay: %.2f",
                    registrationFee, midExamFee, finalExamFee, totalFee);
            resultLabel.setText("<html>" + resultText.replace("\n", "<br>") + "</html>");
        }
    }
}
