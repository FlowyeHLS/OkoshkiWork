package Staroe.fIRST.SECOND;
import javax.swing.*;

public class Main extends JFrame
{
    private int clicks = 0;
    private JLabel label;

    public Main()
    {
        super("Clicks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(250, 150);
        setLocationRelativeTo(null);

        label = new JLabel("Click: 0", SwingConstants.CENTER);

        JButton clickBtn = new JButton("Click");
        JButton resetBtn = new JButton("Delete");

        clickBtn.addActionListener(e ->
        {
            clicks++;
            if (clicks >= 14)
            {
                label.setText("Stop!");
            } else
            {
                label.setText("Clicks: " + clicks);
            }
        });

        resetBtn.addActionListener(e ->
        {
            clicks = 0;
            label.setText("Clicks: " + clicks);
        });

        JPanel panel = new JPanel();
        panel.add(clickBtn);
        panel.add(resetBtn);

        add(label, "Center");
        add(panel, "South");
    }

    public static void main()
    {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}