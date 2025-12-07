package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class stagePanel extends JPanel {
    private gameFrame frame;
    private JButton[] stageButton;

    private BufferedImage BackgroundImage;
    private Image image;

    stagePanel(gameFrame pFrame){
        frame = pFrame;

        setLayout(null);
        setSize(1440,820);

        String imagePath = "/Model/image/panel/selectImage.png";

        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            BackgroundImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        image = BackgroundImage.getScaledInstance(1440, 820, Image.SCALE_SMOOTH);

        stageButton = new JButton[3];

        for(int i=0;i<3;i++){
            stageButton[i] = new JButton("Stage" + (i+1));
        }

        stageButton[0].addActionListener(e -> { frame.selectStage(0); });
        stageButton[1].addActionListener(e -> { frame.selectStage(1); });
        stageButton[2].addActionListener(e -> { frame.selectStage(2); });

        stageButton[0].setBounds(200,400,100,100);
        stageButton[1].setBounds(600,400,100,100);
        stageButton[2].setBounds(1000,400,100,100);

        for(int i=0;i<3;i++) {
            add(stageButton[i]);
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(image, 0, 0, this);
    }
}
