import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    public Frame(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,600);
        setLocationRelativeTo(null);

        // Создаем объект Menu и передаем в него JFrame
        Menu menu = new FileOpenInHEX(this);


        // Устанавливаем меню для JFrame
        setJMenuBar(menu.getjMenuBar());


        setVisible(true);
    }
}
