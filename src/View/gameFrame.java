package View;

import javax.swing.*;

public class gameFrame extends JFrame {
    gameFrame() {
        add(new gamePanel());

        setSize(1440, 840);
        setTitle("Swing Action Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
