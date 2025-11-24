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

    private final double GRAVITY = 1;
    private Player player;
    private ArrayList<Land[]> nowStage;
    private ArrayList<Obstacle[]> nowObstacle;
    private gameMap map;
    private Timer t;
    private int w,h, renderRange = 200;

    private BufferedImage BackgroundImage;
    private Image image;

    // -------------------------실험-----------------
    long lastTime = System.currentTimeMillis();
    int frames = 0;
    // --------------------------------------------


    gamePanel(){
        String imagePath = "/Model/image/object/background/sky.png";

        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            BackgroundImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        setSize(1440,820);

        image = BackgroundImage.getScaledInstance(1440, 820, Image.SCALE_SMOOTH);

        setFocusable(true);
        t = new Timer(16, this);

        map = new gameMap();
        nowStage = map.getMap();
        nowObstacle = map.getMapObstacle();

        player = new Player(90, 645,45,45);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_R){
                    player.setUnit_Point(map.getStartPoint().get(0));
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

        g.drawImage(image, 0, 0, this);

        for (int i =0; i<nowObstacle.get(0).length; i++){
            g.drawImage(nowObstacle.get(0)[i].image,
                    (int) nowObstacle.get(0)[i].getObject_Point().x,
                    (int) nowObstacle.get(0)[i].getObject_Point().y,
                    this
            );
        }

        for (int i = 0; i < nowStage.get(0).length; i++) {
            g.drawImage(nowStage.get(0)[i].image,
                    (int) nowStage.get(0)[i].getObject_Point().x,
                    (int) nowStage.get(0)[i].getObject_Point().y,
                    this
                    );
        }

        g.drawImage(player.image,
                (int) player.getUnit_Point().x,
                (int) player.getUnit_Point().y,
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
        // AI 제안 실험----------------------------------
        // FPS 계산 로직
        frames++;
        if (System.currentTimeMillis() - lastTime >= 1000) {
            System.out.println("FPS: " + frames); // 콘솔에 출력
            frames = 0;
            lastTime = System.currentTimeMillis();
        }
        //-------------------------------------
    }
}