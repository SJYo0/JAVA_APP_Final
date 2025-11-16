package View;

import Model.DTO.Point;
import Model.DTO.Size;
import Model.MapObject.Land;
import Model.MapObject.gameMap;
import Model.Unit.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class gamePanel extends JPanel implements ActionListener {

    private final double GRAVITY = 2;
    private Player player;
    private ArrayList<Land[]> nowStage;
    private gameMap map;
    private Timer t;

    private BufferedImage BackgroundImage;

    gamePanel(){
        String imagePath = "/Model/image/object/background/sky.png";

        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            BackgroundImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        setFocusable(true);
        t = new Timer(16, this);

        map = new gameMap();
        nowStage = map.getMap();

        player = new Player(120,850,60,60);

        repaint();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                player.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                player.mouseReleased(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move(GRAVITY);
        for(int i=0; i<nowStage.get(0).length;i++){
            player.isInterfere_Object(nowStage.get(0)[i].getObject_Point(), nowStage.get(0)[i].getObject_Size());
        }
        repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(BackgroundImage, 0, 0, 1920, 1080, this);

        for(int i=0; i<nowStage.get(0).length; i++){
            g.drawImage(nowStage.get(0)[i].image,
                    (int) nowStage.get(0)[i].getObject_Point().x,
                    (int) nowStage.get(0)[i].getObject_Point().y,
                    nowStage.get(0)[i].getObject_Size().width,
                    nowStage.get(0)[i].getObject_Size().height,
                    this
            );
        }

        g.drawImage(player.image,
                (int) player.getUnit_Point().x,
                (int) player.getUnit_Point().y,
                player.getUnit_Size().width,
                player.getUnit_Size().height,
                this
                );

        if(player.getisHooked()){
            g.setColor(Color.YELLOW);
            g.drawLine(
                    (int) player.getUnit_Point().x + player.getUnit_Size().width/2,
                    (int) player.getUnit_Point().y + player.getUnit_Size().height/2,
                    (int) player.getHookPoint().x,
                    (int) player.getHookPoint().y
                    );
        }
    }
}
