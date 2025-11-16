package Model.MapObject;

import java.util.ArrayList;
import java.util.List;

public class gameMap {
    private ArrayList<Land[]> map = new ArrayList<>();
    private Land[] lands;

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
}
