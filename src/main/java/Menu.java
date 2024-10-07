import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Menu {
    String UNKNOWN_CHARACTER = ".";
    public JMenuBar jMenuBar;
    public JMenu file;
    public JMenuItem fileOpen;
    public JMenuItem fileSave;

    public Menu(JFrame jFrame){
        jMenuBar = new JMenuBar();
        file = new JMenu("File");
        fileOpen = new JMenuItem("Open");
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
        fileSave = new JMenuItem("Save");
        jMenuBar.add(file);
        file.add(fileOpen);
        file.add(fileSave);
        jFrame.setJMenuBar(jMenuBar);
    }

    public JMenuBar getjMenuBar() {
        return jMenuBar;
    }
}
