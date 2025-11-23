package View;

import javax.swing.*;

public class gameFrame extends JFrame {
    gameFrame() {
        add(new gamePanel());

        setSize(1080, 740);
        setTitle("Swing Action Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
