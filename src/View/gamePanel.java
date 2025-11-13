package View;

import Model.DTO.Point;
import Model.DTO.Size;
import Model.MapObject.Land;
import Model.Unit.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gamePanel extends JPanel implements ActionListener {

    private final double GRAVITY = 0.5;
    private Player player;
    private Land[] lands;
    private Timer t;

    gamePanel(){
        setBackground(Color.lightGray);



        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    protected void painComponent(Graphics g){
        super.paintComponent(g);



    }
}
