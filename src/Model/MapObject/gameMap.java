package Model.MapObject;

import Model.DTO.Point;
import Model.DTO.Size;

import java.util.ArrayList;

public class gameMap {
    private ArrayList<Land[]> map = new ArrayList<>();
    private ArrayList<Obstacle[]> mapObstacle = new ArrayList<>();
    private ArrayList<Point> startPoint = new ArrayList<>();
    private ArrayList<Size> playerSize = new ArrayList<>();
    private Land[] lands;
    private Obstacle[] obstacles;
    private int w,h;

    public gameMap(){
        lands = new Land[9];
        lands[0] = new Land(75,683,206,28);
        lands[1] = new Land(375,607,206,28);
        lands[2] = new Land(75,532,206,28);
        lands[3] = new Land(375,455,206,28);
        lands[4] = new Land(676,114,75,68);
        lands[5] = new Land(828,455,206,28);
        lands[6] = new Land(1125,114,75,68);
        lands[7] = new Land(1286,455,206,28);
        lands[8] = new Land(75,432,48,117);

        map.add(lands);

        obstacles = new Obstacle[2];
        obstacles[0] = new Obstacle(676,155,80);
        obstacles[1] = new Obstacle(676,410,80);

        mapObstacle.add(0,obstacles);

        Point p = new Point(90,607);
        startPoint.add(0,p);
    }

    public ArrayList<Land[]> getMap(){
        return map;
    }
    public ArrayList<Obstacle[]> getMapObstacle() {return mapObstacle;}

    /*public void setSize(int pWidth, int pHeight){
        w = pWidth;
        h = pHeight;

        System.out.println(w + "  "+ h);

        *//*lands = new Land[9];
        lands[0] = new Land((int)(w/19.2),(int)(h/1.2),(int)(w/7),(int)(h/36));
        lands[1] = new Land((int)(w/3.84),(int)(h/1.35),(int)(w/7),(int)(h/36));
        lands[2] = new Land((int)(w/19.2),(int)(h/1.54),(int)(w/7),(int)(h/36));
        lands[3] = new Land((int)(w/3.84),(int)(h/1.8),(int)(w/7),(int)(h/36));
        lands[4] = new Land((int)(w/2.13),(int)(h/7.2),(int)(w/19.2),(int)(h/12));
        lands[5] = new Land((int)(w/1.74),(int)(h/1.8),(int)(w/7),(int)(h/36));
        lands[6] = new Land((int)(w/1.28),(int)(h/7.2),(int)(w/19.2),(int)(h/12));
        lands[7] = new Land((int)(w/1.12),(int)(h/1.8),(int)(w/7),(int)(h/36));
        lands[8] = new Land((int)(w/19.2),(int)(h/1.9),(int)(w/30),(int)(h/7));

        map.add(0,lands);*//*

        obstacles = new Obstacle[2];
        obstacles[0] = new Obstacle((int)(w/2.13),(int)(h/5.3),60);
        obstacles[1] = new Obstacle((int)(w/2.13),(int)(h/2),60);

        mapObstacle.add(0,obstacles);
    }*/

    /*public void setStartPoint(int pWidth, int pHeight){
        w = pWidth;
        h = pHeight;

        Point p = new Point((int)(w/16),(int)(h/1.35));
        startPoint.add(0,p);
    }*/

    public ArrayList<Point> getStartPoint(){
        return startPoint;
    }
}
