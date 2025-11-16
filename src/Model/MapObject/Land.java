package Model.MapObject;

import Model.DTO.Point;
import Model.DTO.Size;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Land extends MapObject {
    public BufferedImage image = null;

    public Land(int pX, int pY, int pWidth, int pHeight){
        super(new Point(pX,pY),new Size(pWidth,pHeight));

        String imagePath = "/Model/image/object/container/container.png";

        // 상대경로로 읽어주는 클래스
        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            image = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
