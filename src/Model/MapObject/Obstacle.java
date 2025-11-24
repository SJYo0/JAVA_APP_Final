package Model.MapObject;

import Model.DTO.Point;
import Model.DTO.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Obstacle extends MapObject {
    private int radius;
    public BufferedImage originalImage = null;
    public Image image = null;

    Obstacle(int pX,int pY, int pRange){
        super(new Point(pX,pY),new Size(pRange,pRange));
        radius = pRange/2;

        String imagePath = "/Model/image/object/obstacle/obstacle.png";

        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            originalImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        image = originalImage.getScaledInstance(pRange, pRange, Image.SCALE_SMOOTH);
    }

    public boolean interfere(Point pPoint, Size pSize){
        double dx = (pPoint.x+(pSize.width/2)) - (object_Point.x + radius);
        double dy = (pPoint.y+(pSize.height/2)) - (object_Point.y + radius);
        double length = Math.sqrt(dx*dx + dy*dy);

        if(length <= radius){
            return true;
        }
        else{
            return false;
        }
    }
}
