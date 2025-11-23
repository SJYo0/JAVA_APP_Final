package View;

import Model.DTO.Point;
import Model.DTO.Size;
import Model.MapObject.Land;
import Model.MapObject.Obstacle;
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

    private final double GRAVITY = 1.3;
    private Player player;
    private ArrayList<Land[]> nowStage;
    private ArrayList<Obstacle[]> nowObstacle;
    private gameMap map;
    private Timer t;
    private int w,h, renderRange = 200;

    private BufferedImage BackgroundImage;

    gamePanel(){
        String imagePath = "/Model/image/object/background/sky.png";

        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            BackgroundImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        w = 1080;
        h = 720;
        setSize(w,h);

        setFocusable(true);
        t = new Timer(16, this);

        map = new gameMap();
        nowStage = map.getMap();
        nowObstacle = map.getMapObstacle();
        map.setSize(w,h);
        map.setStartPoint(w,h);
        map.setPlayerSize(w,h);



        player = new Player(
                (int)(w/16),
                (int)(h/1.27),
                (int)(w/32),
                (int)(h/18));

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_P){
                    w = getWidth();
                    h = getHeight();

                    map.setSize(w,h);
                    map.setStartPoint(w,h);
                    map.setPlayerSize(w,h);
                    nowStage = map.getMap();

                    player.setUnit_Point(map.getStartPoint().get(0));
                    player.setUnit_Size(map.getPlayerSize().get(0));

                    repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_R){
                    player.setUnit_Point(map.getStartPoint().get(0));
                    player.setUnit_Size(map.getPlayerSize().get(0));

                    repaint();
                }
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
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        repaint();
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move(GRAVITY);
        for(int i=0; i<nowStage.get(0).length;i++){
            if (Math.abs(player.getUnit_Point().x - nowStage.get(0)[i].getObject_Point().x) > renderRange)
                if(Math.abs(player.getUnit_Point().y - nowStage.get(0)[i].getObject_Point().y) > renderRange)
                    continue;

            player.isInterfere_Object(nowStage.get(0)[i].getObject_Point(), nowStage.get(0)[i].getObject_Size());
        }
        for(int i=0;i<nowObstacle.get(0).length;i++){
            if(Math.abs(player.getUnit_Point().x - nowObstacle.get(0)[i].getObject_Point().x) > renderRange)
                if(Math.abs(player.getUnit_Point().y - nowObstacle.get(0)[i].getObject_Point().y) > renderRange)
                    continue;

            if(nowObstacle.get(0)[i].interfere(player.getUnit_Point(),player.getUnit_Size())){
                player.setUnit_Point(map.getStartPoint().get(0));
            }
        }

        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(BackgroundImage, 0, 0, w, h, this);

        for (int i = 0; i < nowStage.get(0).length; i++) {
            g.drawImage(nowStage.get(0)[i].image,
                    (int) nowStage.get(0)[i].getObject_Point().x,
                    (int) nowStage.get(0)[i].getObject_Point().y,
                    nowStage.get(0)[i].getObject_Size().width,
                    nowStage.get(0)[i].getObject_Size().height,
                    this
                    );
        }

        for (int i =0; i<nowObstacle.get(0).length; i++){
            g.drawImage(nowObstacle.get(0)[i].image,
                    (int) nowObstacle.get(0)[i].getObject_Point().x,
                    (int) nowObstacle.get(0)[i].getObject_Point().y,
                    nowObstacle.get(0)[i].getObject_Size().width,
                    nowObstacle.get(0)[i].getObject_Size().height,
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

        if (player.getisHooked()) {
            g.setColor(Color.YELLOW);
            g.drawLine(
                    (int) player.getUnit_Point().x + player.getUnit_Size().width / 2,
                    (int) player.getUnit_Point().y + player.getUnit_Size().height / 2,
                    (int) player.getHookPoint().x,
                    (int) player.getHookPoint().y
            );
        }
    }
}