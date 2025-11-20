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
    private int w,h;

    private boolean drawPlayer;
    double hook_rangeX,hook_rangeY;
    int hooK_rangeW,hooK_rangeH;

    private BufferedImage BackgroundImage;

    gamePanel(){
        String imagePath = "/Model/image/object/background/sky.png";

        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            BackgroundImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        w = 1920;
        h = 1080;

        setFocusable(true);
        t = new Timer(16, this);

        map = new gameMap();
        nowStage = map.getMap();

        player = new Player(120,850,60,60);

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
                    nowStage = map.getMap();

                    player = new Player(
                            (int)(w/16),
                            (int)(h/1.27),
                            (int)(w/32),
                            (int)(h/18));

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

        drawPlayer = true;
        repaint();
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawPlayer = false;
        if(player.getisHooked()){
            hook_rangeX = (player.getUnit_Point().x + player.getHookPoint().x)/2 - 100;
            hook_rangeY = (player.getUnit_Point().y + player.getHookPoint().y)/2 - 100;
            hooK_rangeW = (int)Math.abs(player.getUnit_Point().x - player.getHookPoint().x);
            hooK_rangeH = (int)Math.abs(player.getUnit_Point().y - player.getHookPoint().y);

            repaint(
                    (int)hook_rangeX - hooK_rangeW/2,
                    (int)hook_rangeY - hooK_rangeH/2,
                    hooK_rangeW + 200,
                    hooK_rangeH + 200
            );
        }
        else {
            repaint(
                    (int) player.getUnit_Point().x,
                    (int) player.getUnit_Point().y,
                    player.getUnit_Size().width + 1,
                    player.getUnit_Size().height + 1
            );
        }

        player.move(GRAVITY);
        for(int i=0; i<nowStage.get(0).length;i++){
            player.isInterfere_Object(nowStage.get(0)[i].getObject_Point(), nowStage.get(0)[i].getObject_Size());
        }

        drawPlayer=true;
        if(player.getisHooked()){
            hook_rangeX = (player.getUnit_Point().x + player.getHookPoint().x)/2 - 100;
            hook_rangeY = (player.getUnit_Point().y + player.getHookPoint().y)/2 - 100;
            hooK_rangeW = (int)Math.abs(player.getUnit_Point().x - player.getHookPoint().x);
            hooK_rangeH = (int)Math.abs(player.getUnit_Point().y - player.getHookPoint().y);

            repaint(
                    (int)hook_rangeX - hooK_rangeW/2,
                    (int)hook_rangeY - hooK_rangeH/2,
                    hooK_rangeW + 200,
                    hooK_rangeH + 200
            );
        }
        else {
            repaint(
                    (int) player.getUnit_Point().x,
                    (int) player.getUnit_Point().y,
                    player.getUnit_Size().width + 1,
                    player.getUnit_Size().height + 1
            );
        }
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

        if (drawPlayer) {
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
}