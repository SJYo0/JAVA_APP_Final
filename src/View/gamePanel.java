package View;

import Model.DTO.Point;
import Model.DTO.Size;
import Model.MapObject.Goal;
import Model.MapObject.Land;
import Model.MapObject.Obstacle;
import Model.MapObject.gameMap;
import Model.Unit.Player;
import Model.Utility.Camera;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class gamePanel extends JPanel implements ActionListener {

    private gameFrame frame;
    private final double GRAVITY = 1;
    private Player player;
    private ArrayList<Land[]> nowStage;
    private ArrayList<Obstacle[]> nowObstacle;
    private ArrayList<Goal> nowGoal;

    private gameMap map;
    private Timer t;
    private int renderRange = 200;
    private int stageNum;
    private double click_x, click_y;
    private Camera camera;

    private BufferedImage BackgroundImage;
    private Image image;

    private stagePanel panel;

    // -------------------------실험-----------------
    /*long lastTime = System.currentTimeMillis();
    int frames = 0;*/
    // --------------------------------------------


    gamePanel(gameFrame pFrame, int pStageNum, stagePanel pPanel){
        frame = pFrame;
        panel = pPanel;
        stageNum = pStageNum;

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
        nowGoal = map.getMapGoal();


        player = new Player(0, 0,45,45);

        camera = new Camera();
        Size s = new Size(1440,820);
        camera.setPanelSize(s);
        camera.setMapSize(map.getMapSize().get(stageNum));

        player.setUnit_Point(map.getStartPoint().get(stageNum));

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_R){
                    player.setUnit_Point(map.getStartPoint().get(stageNum));
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    frame.changePanel(pPanel);
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
                click_x = e.getX() + camera.getCameraPoint().x;
                click_y = e.getY() + camera.getCameraPoint().y;
                checkCanHook();

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

    public void checkCanHook(){
        for(int i=0; i<nowStage.get(stageNum).length;i++){
            player.canHook(nowStage.get(stageNum)[i].getObject_Point(), nowStage.get(stageNum)[i].getObject_Size(),click_x,click_y);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move(GRAVITY);
        for(int i=0; i<nowStage.get(stageNum).length;i++){
//            if (Math.abs(player.getUnit_Point().x - nowStage.get(stageNum)[i].getObject_Point().x) > renderRange)
//                if(Math.abs(player.getUnit_Point().y - nowStage.get(stageNum)[i].getObject_Point().y) > renderRange)
//                    continue;

            player.isInterfere_Object(nowStage.get(stageNum)[i].getObject_Point(), nowStage.get(stageNum)[i].getObject_Size());
        }
        for(int i=0;i<nowObstacle.get(stageNum).length;i++){
//            if(Math.abs(player.getUnit_Point().x - nowObstacle.get(stageNum)[i].getObject_Point().x) > renderRange)
//                if(Math.abs(player.getUnit_Point().y - nowObstacle.get(stageNum)[i].getObject_Point().y) > renderRange)
//                    continue;

            if(nowObstacle.get(stageNum)[i].interfere(player.getUnit_Point(),player.getUnit_Size())){
                player.setUnit_Point(map.getStartPoint().get(stageNum));
                player.setisHooked(false);
            }
        }

        if(nowGoal.get(stageNum).interfere(player.getUnit_Point(),player.getUnit_Size())){
            frame.changePanel(panel);
        }

        camera.setCameraPoint(player.getUnit_Point());

        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, this);

        for (int i =0; i<nowObstacle.get(stageNum).length; i++){
            g.drawImage(nowObstacle.get(stageNum)[i].image,
                    (int) nowObstacle.get(stageNum)[i].getObject_Point().x - (int) camera.getCameraPoint().x,
                    (int) nowObstacle.get(stageNum)[i].getObject_Point().y - (int) camera.getCameraPoint().y,
                    this
            );
        }

        for (int i = 0; i < nowStage.get(stageNum).length; i++) {
            g.drawImage(nowStage.get(stageNum)[i].image,
                    (int) nowStage.get(stageNum)[i].getObject_Point().x- (int) camera.getCameraPoint().x,
                    (int) nowStage.get(stageNum)[i].getObject_Point().y- (int) camera.getCameraPoint().y,
                    this
                    );
        }

        g.drawImage(nowGoal.get(stageNum).image,
                (int) nowGoal.get(stageNum).getObject_Point().x- (int) camera.getCameraPoint().x,
                (int) nowGoal.get(stageNum).getObject_Point().y- (int) camera.getCameraPoint().y,
                this
        );

        g.drawImage(player.image,
                (int) player.getUnit_Point().x- (int) camera.getCameraPoint().x,
                (int) player.getUnit_Point().y- (int) camera.getCameraPoint().y,
                this
        );

        if (player.getisHooked()) {
            g.setColor(Color.YELLOW);
            g.drawLine(
                    (int) player.getUnit_Point().x + player.getUnit_Size().width / 2- (int) camera.getCameraPoint().x,
                    (int) player.getUnit_Point().y + player.getUnit_Size().height / 2- (int) camera.getCameraPoint().y,
                    (int) player.getHookPoint().x- (int) camera.getCameraPoint().x,
                    (int) player.getHookPoint().y- (int) camera.getCameraPoint().y
            );
        }
        // AI 제안 실험----------------------------------
        // FPS 계산 로직
        /*frames++;
        if (System.currentTimeMillis() - lastTime >= 1000) {
            System.out.println("FPS: " + frames); // 콘솔에 출력
            frames = 0;
            lastTime = System.currentTimeMillis();
        }*/
        //-------------------------------------
    }
}