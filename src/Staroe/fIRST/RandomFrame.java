package Staroe.fIRST;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RandomFrame extends JFrame
{

    private JTextField fromField;
    private JTextField toField;
    private JLabel resultLabel;

    public RandomFrame()
    {
        super("Generator random numbers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        fromField = new JTextField();
        toField = new JTextField();
        resultLabel = new JLabel("Type ot skolki do skolki", SwingConstants.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("ot:"));
        inputPanel.add(fromField);
        inputPanel.add(new JLabel("do:"));
        inputPanel.add(toField);

        JButton button = new JButton("Tap me to sdelatb random");
        button.addActionListener(this::onGenerate);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(button, BorderLayout.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void onGenerate(ActionEvent e)
    {
        try
        {
            int from = Integer.parseInt(fromField.getText().trim());
            int to = Integer.parseInt(toField.getText().trim());

            if (from > to)
            {
                resultLabel.setText("Oshibka: «ot» > «do»");
                resultLabel.setForeground(Color.RED);
                return;
            }

            int value = new Random().nextInt(to - from + 1) + from;
            resultLabel.setText("Number: " + value);
            resultLabel.setForeground(Color.BLACK);
        } catch (NumberFormatException ex)
            {
                resultLabel.setText("Oshibka: type chisla bez .");
                resultLabel.setForeground(Color.RED);
            }
    }

    public static void launch()
    {
        SwingUtilities.invokeLater(() -> new RandomFrame().setVisible(true));
    }
}