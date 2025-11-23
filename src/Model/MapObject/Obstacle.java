package Model.MapObject;

import Model.DTO.Point;
import Model.DTO.Size;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Obstacle extends MapObject {
    private int radius;
    public BufferedImage image = null;

    Obstacle(int pX,int pY, int pRange){
        super(new Point(pX,pY),new Size(pRange,pRange));
        radius = pRange/2;

        String imagePath = "/Model/image/object/obstacle/obstacle.png";

        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            image = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean interfere(Point pPoint, Size pSize){
        double dx = (pPoint.x+(pSize.width/2)) - (object_Point.x + radius);
        double dy = (pPoint.y+(pSize.height/2)) - (object_Point.y + radius);
        double length = Math.sqrt(dx*dx + dy*dy);

        if(length <= radius+pSize.width/2){
            return true;
        }
        else{
            return false;
        }
    }
}
