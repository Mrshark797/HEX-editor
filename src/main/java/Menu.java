import javax.swing.*;
public class Menu {
    JMenuBar jMenuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem fileOpen = new JMenuItem("Open");
    JMenuItem fileSave= new JMenuItem("Save");

    public Menu(JFrame jFrame) { // Добавляем JFrame в конструктор
        jMenuBar.add(file);
        file.add(fileOpen);
        file.add(fileSave);
        jFrame.setJMenuBar(jMenuBar); // Добавляем меню в JFrame
    }

    public JMenuBar getjMenuBar(){
        return jMenuBar;
    };
}
