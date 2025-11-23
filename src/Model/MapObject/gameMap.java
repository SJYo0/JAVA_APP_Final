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
        setSize(1080,720);
        setStartPoint(1080,720);
//        lands = new Land[8];
//        lands[0] = new Land(100,900,300,30);
//        lands[1] = new Land(500,780,300,30);
//        lands[2] = new Land(100,660,300,30);
//        lands[3] = new Land(500,540,300,30);
//        lands[4] = new Land(900,150,100,90);
//        lands[5] = new Land(1100,540,300,30);
//        lands[6] = new Land(1500,150,100,90);
//        lands[7] = new Land(1700,540,300,30);
//
//        map.add(lands);
    }

    public ArrayList<Land[]> getMap(){
        return map;
    }
    public ArrayList<Obstacle[]> getMapObstacle() {return mapObstacle;}

    public void setSize(int pWidth, int pHeight){
        w = pWidth;
        h = pHeight;

        lands = new Land[9];
        lands[0] = new Land((int)(w/19.2),(int)(h/1.2),(int)(w/7),(int)(h/36));
        lands[1] = new Land((int)(w/3.84),(int)(h/1.35),(int)(w/7),(int)(h/36));
        lands[2] = new Land((int)(w/19.2),(int)(h/1.54),(int)(w/7),(int)(h/36));
        lands[3] = new Land((int)(w/3.84),(int)(h/1.8),(int)(w/7),(int)(h/36));
        lands[4] = new Land((int)(w/2.13),(int)(h/7.2),(int)(w/19.2),(int)(h/12));
        lands[5] = new Land((int)(w/1.74),(int)(h/1.8),(int)(w/7),(int)(h/36));
        lands[6] = new Land((int)(w/1.28),(int)(h/7.2),(int)(w/19.2),(int)(h/12));
        lands[7] = new Land((int)(w/1.12),(int)(h/1.8),(int)(w/7),(int)(h/36));
        lands[8] = new Land((int)(w/19.2),(int)(h/1.9),(int)(w/30),(int)(h/7));

        map.add(0,lands);

        obstacles = new Obstacle[2];
        obstacles[0] = new Obstacle((int)(w/2.13),(int)(h/5.3),60);
        obstacles[1] = new Obstacle((int)(w/2.13),(int)(h/2),60);

        mapObstacle.add(0,obstacles);
    }

    public void setStartPoint(int pWidth, int pHeight){
        w = pWidth;
        h = pHeight;

        Point p = new Point((int)(w/16),(int)(h/1.35));
        startPoint.add(0,p);
    }

    public ArrayList<Point> getStartPoint(){
        return startPoint;
    }

    public void setPlayerSize(int pWidth, int pHeight){
        w = pWidth;
        h = pHeight;

        Size s = new Size((int)(w/32),(int)(h/18));
        playerSize.add(0,s);
    }

    public  ArrayList<Size> getPlayerSize(){
        return playerSize;
    }
}
