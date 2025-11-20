package Model.MapObject;

import java.util.ArrayList;
import java.util.List;

public class gameMap {
    private ArrayList<Land[]> map = new ArrayList<>();
    private Land[] lands;
    private int w,h;

    public gameMap(){
        lands = new Land[8];
        lands[0] = new Land(100,900,300,30);
        lands[1] = new Land(500,780,300,30);
        lands[2] = new Land(100,660,300,30);
        lands[3] = new Land(500,540,300,30);
        lands[4] = new Land(900,150,100,90);
        lands[5] = new Land(1100,540,300,30);
        lands[6] = new Land(1500,150,100,90);
        lands[7] = new Land(1700,540,300,30);

        map.add(lands);
    }

    public ArrayList<Land[]> getMap(){
        return map;
    }

    public void setSize(int pWidth, int pHeight){
        w = pWidth;
        h = pHeight;

        System.out.println(w + "  " + h);

        lands = new Land[8];
        lands[0] = new Land((int)(w/19.2),(int)(h/1.2),(int)(w/6.4),(int)(h/36));
        lands[1] = new Land((int)(w/3.84),(int)(h/1.4),(int)(w/6.4),(int)(h/36));
        lands[2] = new Land((int)(w/19.2),(int)(h/1.6),(int)(w/6.4),(int)(h/36));
        lands[3] = new Land((int)(w/3.84),(int)(h/2),(int)(w/6.4),(int)(h/36));
        lands[4] = new Land((int)(w/2.13),(int)(h/7.2),(int)(w/19.2),(int)(h/12));
        lands[5] = new Land((int)(w/1.74),(int)(h/2),(int)(w/6.4),(int)(h/36));
        lands[6] = new Land((int)(w/1.28),(int)(h/7.2),(int)(w/19.2),(int)(h/12));
        lands[7] = new Land((int)(w/1.12),(int)(h/2),(int)(w/6.4),(int)(h/36));

        map.add(0,lands);
    }
}
