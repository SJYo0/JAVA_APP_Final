package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class introPanel extends JPanel {
    private gameFrame frame;
    private JButton startButton;

    private BufferedImage BackgroundImage;
    private Image image;

    introPanel(gameFrame pFrame,stagePanel pPanel){
        frame = pFrame;

        setLayout(null);
        setSize(1440,820);

        String imagePath = "/Model/image/panel/introImage.png";

        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            BackgroundImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        image = BackgroundImage.getScaledInstance(1440, 820, Image.SCALE_SMOOTH);

        startButton = new JButton("게임 시작");
        startButton.setBounds(750,500,400,100);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changePanel(pPanel);
            }
        });

        add(startButton);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(image, 0, 0, this);
    }
}
