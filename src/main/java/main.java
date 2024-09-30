import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;


public class main {
    private static final String UNKNOWN_CHARACTER = ".";
    public static void main(String[] args){
        JFrame jFrame = new JFrame("HEX editor");

        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");

        JMenuItem fileOpen = new JMenuItem("Open");
        fileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                StringBuilder hex = new StringBuilder();
                StringBuilder result = new StringBuilder();
                StringBuilder input = new StringBuilder();
                int count = 0;
                int value;
                int ret = jf.showOpenDialog(null);
                if(ret == JFileChooser.APPROVE_OPTION){
                    File file = jf.getSelectedFile();
                    try (InputStream fis = Files.newInputStream(file.toPath())){
                        while((value = fis.read()) != -1){
                            hex.append(String.format("%02X ", value));

                            if (!Character.isISOControl(value)){
                                input.append((char) value);
                            }
                            else {
                                input.append(UNKNOWN_CHARACTER);
                            }

                            if (count == 15){
                                result.append(String.format("%-60s | %s%n", hex, input));
                                hex.setLength(0);
                                input.setLength(0);
                                count = 0;
                            }
                            else{
                                count++;
                            }
                        }

                        if (count > 0){
                            result.append(String.format("%-60s | %s%n", hex, input));
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println(result);

                    /*Метод выше позволяет открыть файл в консоли,
                     но НЕОБХОДИМО найти иной, дабы он открывался в окне приложения!!!
                     */

                }
            }
        });
        JMenuItem fileSave = new JMenuItem("Save");

        jMenuBar.add(file);

        file.add(fileOpen);
        file.add(fileSave);

        jFrame.setJMenuBar(jMenuBar);

        JPanel contentPane = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.5;
        constraints.weighty = 1.0;

        JPanel hexPanel = new JPanel();
        hexPanel.setBackground(Color.GRAY);
        constraints.gridx = 0;
        constraints.gridy = 0;
        contentPane.add(hexPanel, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.CYAN);
        constraints.gridx = 1;
        constraints.gridy = 0;
        contentPane.add(buttonPanel, constraints);

        jFrame.add(contentPane);

        jFrame.setSize(800, 700);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);



    }
}
