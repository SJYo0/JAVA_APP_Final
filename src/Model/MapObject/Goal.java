package Model.MapObject;

import Model.DTO.Point;
import Model.DTO.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Goal extends MapObject{
    public BufferedImage originalImage = null;
    public Image image = null;

    public Goal(int pX, int pY, int pWidth, int pHeight) {
        super(new Point(pX,pY),new Size(pWidth,pHeight));

        String imagePath = "/Model/image/object/goal/goal.png";

        // 상대경로로 읽어주는 클래스
        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            originalImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        image = originalImage.getScaledInstance(pWidth, pHeight, Image.SCALE_SMOOTH);

    }

    public boolean interfere(Point pPoint, Size pSize){
        if((object_Point.x + 10 < pPoint.x + pSize.width) && (pPoint.x < object_Point.x + object_Size.width - 10)){
            if((object_Point.y + 10 < pPoint.y + pSize.height) && (pPoint.y < object_Point.y + object_Size.height - 10)){
                return true;
            }
        }
        return false;
    }
}
