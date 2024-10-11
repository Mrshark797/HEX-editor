import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLOutput;
import java.util.*;
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
                /*
                StringBuilder hex = new StringBuilder();
                StringBuilder result = new StringBuilder();
                StringBuilder input = new StringBuilder();
                int count = 0;
                int value;
                 */
                int ret = jf.showOpenDialog(null);
                if(ret == JFileChooser.APPROVE_OPTION) {
                    File file = jf.getSelectedFile();
                    HexReader.readHexFromFile(String.valueOf(file));
                    HexReader.convertHexToList(HexReader.readHexFromFile(String.valueOf(file)));
                    createTable(HexReader.readHexFromFile(String.valueOf(file)));


                    /*
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
                    */
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

    public static class HexReader {
        public static List<String> readHexFromFile(String filepath) {
            List<String> hexLines = new ArrayList<>();
            try (InputStream fis = Files.newInputStream(new File(filepath).toPath())) {
                byte[] buffer = new byte[16];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    StringBuilder hexLine = new StringBuilder();
                    for (int i = 0; i < bytesRead; i++) {
                        hexLine.append(String.format("%02X", buffer[i]));
                    }
                    hexLines.add(hexLine.toString().trim());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return hexLines;
        }

        public static String[][] convertHexToList(List<String> hexLines) {
            String[][] hexData = new String[hexLines.size()][1];
            for (int i = 0; i < hexLines.size(); i++) {
                hexData[i][0] = hexLines.get(i);
            }
            return hexData;
        }
    }
    public static JTable createTable(List<String> hexLines){
        String[][] hexData = HexReader.convertHexToList(hexLines);
        String[] columnNames = {"HEX"};
        return new JTable(hexData, columnNames);
    }


}
