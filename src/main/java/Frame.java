import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public JMenuBar jMenuBar;


    public Frame(String title) {
        super(title);
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setJMenuBar(new Menu(this).getjMenuBar());


        JPanel hexPanel = new JPanel();
        hexPanel.setBackground(Color.GRAY);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.GREEN);

        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.5;
        constraints.weighty = 1;

        constraints.gridx = 0;
        constraints.gridy = 0;
        contentPane.add(hexPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        contentPane.add(rightPanel, constraints);

        add(contentPane);

        JTable jTable = new JTable(16, 16);

        jTable.setSize(400, 100);
        hexPanel.add(jTable);
        setVisible(true);
   }

}
