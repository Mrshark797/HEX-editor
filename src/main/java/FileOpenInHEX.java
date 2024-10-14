import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;

public class FileOpenInHEX extends Menu{
    String UNKNOWN_CHARACTER = ".";
    public FileOpenInHEX(JFrame jFrame) {
        super(jFrame);
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
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = jf.getSelectedFile();
                    try (InputStream fis = Files.newInputStream(file.toPath())) {
                        while ((value = fis.read()) != -1) {
                            hex.append(String.format("%02X ", value));

                            if (!Character.isISOControl(value)) {
                                input.append((char) value);
                            } else {
                                input.append(UNKNOWN_CHARACTER);
                            }

                            if (count == 15) {
                                result.append(String.format("%-60s | %s%n", hex, input));
                                hex.setLength(0);
                                input.setLength(0);
                                count = 0;
                            } else {
                                count++;
                            }
                        }

                        if (count > 0) {
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
    }
}