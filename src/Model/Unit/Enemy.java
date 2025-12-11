package Model.Unit;

import Model.DTO.Point;
import Model.DTO.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Enemy extends Unit{
    private double speed;
    private int count;

    public BufferedImage originalImage = null;
    public Image image = null;

    public Enemy(Point pPoint, Size pSize) {
        super(pPoint, pSize);

        String imagePath = "/Model/image/ghost.png";

        // 상대경로로 읽어주는 클래스
        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            originalImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        image = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        speed = 3;
        count = 0;
    }

    @Override
    public void move(Point pPoint) {
        count++;

        if(count>=90){
            if(speed==3){
                setSpeed(10);
            }
            else if(speed==10) {
                setSpeed(3);
            }
            count=0;
        }

        double dx = pPoint.x - unit_Point.x;
        double dy = pPoint.y - unit_Point.y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        unit_Velocity.Velocity_X = (dx/distance)*speed;
        unit_Velocity.Velocity_Y = (dy/distance)*speed;

        unit_Point.x += unit_Velocity.Velocity_X;
        unit_Point.y += unit_Velocity.Velocity_Y;
    }

//    public boolean interfere(Point pPoint, Size pSize){
//        if((unit_Point.x + 20 < pPoint.x + pSize.width) && (pPoint.x < unit_Point.x + unit_Size.width - 20)){
//            if((unit_Point.y + 20 < pPoint.y + pSize.height) && (pPoint.y < unit_Point.y + unit_Size.height - 20)){
//                return true;
//            }
//        }
//        return false;
//    }

    public void setSpeed(double pSpeed){
        speed = pSpeed;
    }
}
