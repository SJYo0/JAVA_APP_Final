package Model.MapObject;

import Model.DTO.Point;
import Model.DTO.Size;

import java.util.ArrayList;

public class gameMap {
    private ArrayList<Land[]> map = new ArrayList<>();
    private ArrayList<Obstacle[]> mapObstacle = new ArrayList<>();
    private ArrayList<Point> startPoint = new ArrayList<>();
    private ArrayList<Size> mapSize = new ArrayList<>();
    private ArrayList<Goal> mapGoal = new ArrayList<>();
    private Land[] lands;
    private Obstacle[] obstacles;

    public gameMap(){
        // 스테이지 1 세팅 ==========================================
        Size s = new Size(3000,1500);
        mapSize.add(s);

        lands = new Land[13];
        lands[0] = new Land(75,1425,200,30);
        lands[1] = new Land(375,1365,200,30);
        lands[2] = new Land(75,1285,200,30);
        lands[3] = new Land(375,1205,200,30);
        lands[4] = new Land(675,850,80,70);
        lands[5] = new Land(825,1205,200,30);
        lands[6] = new Land(1150,1400, 1620,100);
        lands[7] = new Land(1600,1100, 100,400);
        lands[8] = new Land(2700,0,300,1500);
        lands[9] = new Land(0,550,2450,300);
        lands[10] = new Land(2450,550,100,550);
        lands[11] = new Land(0,0,3000,100);
        lands[12] = new Land(0,450,200,30);

        map.add(lands);

        obstacles = new Obstacle[15];
        obstacles[0] = new Obstacle(650,1370,130);
        obstacles[1] = new Obstacle(650,1260,130);
        obstacles[2] = new Obstacle(650,1150,130);
        obstacles[3] = new Obstacle(200,450,200);
        obstacles[4] = new Obstacle(400,450,200);
        obstacles[5] = new Obstacle(600,450,200);
        obstacles[6] = new Obstacle(800,450,200);
        obstacles[7] = new Obstacle(1000,450,200);
        obstacles[8] = new Obstacle(1200,450,200);
        obstacles[9] = new Obstacle(1400,450,200);
        obstacles[10] = new Obstacle(1600,450,200);
        obstacles[11] = new Obstacle(1800,450,200);
        obstacles[12] = new Obstacle(2000,450,200);
        obstacles[13] = new Obstacle(2200,450,200);
        obstacles[14] = new Obstacle(2400,450,100);

        mapObstacle.add(obstacles);

        Goal goal = new Goal(50,380,50,50);

        mapGoal.add(goal);

        Point p = new Point(85,1300);
        startPoint.add(p);
        //=================================================================

        // 스테이지 2 세팅 ==========================================
        s = new Size(3000,1500);
        mapSize.add(s);

        lands = new Land[14];
        lands[0] = new Land(0,1450,3000,50);
        lands[1] = new Land(220,1350,200,30);
        lands[2] = new Land(610,1000,70,70);
        lands[3] = new Land(1170,1350,200,30);
        lands[4] = new Land(1630,1000,70,70);
        lands[5] = new Land(2200,1100,70,70);
        lands[6] = new Land(2500,0,700,800);
        lands[7] = new Land(2100,300,200,30);
        lands[8] = new Land(1800,500,200,30);
        lands[9] = new Land(1550,200,70,70);
        lands[10] = new Land(1150,200,70,70);
        lands[11] = new Land(650,500,200,30);
        lands[12] = new Land(600,200,50,300);
        lands[13] = new Land(200,500,200,30);

        map.add(lands);

        obstacles = new Obstacle[15];
        obstacles[0] = new Obstacle(520,1370,130);
        obstacles[1] = new Obstacle(650,1370,130);
        obstacles[2] = new Obstacle(1500,1370,130);
        obstacles[3] = new Obstacle(1630,1370,130);
        obstacles[4] = new Obstacle(1760,1370,130);
        obstacles[5] = new Obstacle(0,700,200);
        obstacles[6] = new Obstacle(200,700,200);
        obstacles[7] = new Obstacle(400,700,200);
        obstacles[8] = new Obstacle(600,700,200);
        obstacles[9] = new Obstacle(800,700,200);
        obstacles[10] = new Obstacle(1000,700,200);
        obstacles[11] = new Obstacle(1200,700,200);
        obstacles[12] = new Obstacle(1400,700,200);
        obstacles[13] = new Obstacle(1600,700,200);
        obstacles[14] = new Obstacle(400,400,200);

        mapObstacle.add(obstacles);

        goal = new Goal(250,400,50,50);

        mapGoal.add(goal);

        p = new Point(50,1370);
        startPoint.add(p);
        //=================================================================

        // 스테이지 3 세팅 ==========================================
        s = new Size(3000,1500);
        mapSize.add(s);

        lands = new Land[10];
        lands[0] = new Land(0,1450,3000,50);
        lands[1] = new Land(500,700,80,600);
        lands[2] = new Land(580,1220,700,80);
        lands[3] = new Land(780,1000,1000,80);
        lands[4] = new Land(1480,1080,80,500);
        lands[5] = new Land(1650,1220,350,80);
        lands[6] = new Land(2000,700,400,600);
        lands[7] = new Land(2550,0,500,1500);
        lands[8] = new Land(2000,620,400,80);
        lands[9] = new Land(0,0,2000,700);


        map.add(lands);

        obstacles = new Obstacle[4];
        obstacles[0] = new Obstacle(2360,1220,80);
        obstacles[1] = new Obstacle(2360,1140,80);
        obstacles[2] = new Obstacle(2510,910,80);
        obstacles[3] = new Obstacle(2510,830,80);

        mapObstacle.add(obstacles);

        goal = new Goal(2100,520,50,50);

        mapGoal.add(goal);

        p = new Point(50,1370);
        startPoint.add(p);

    }

    public ArrayList<Land[]> getMap(){
        return map;
    }
    public ArrayList<Obstacle[]> getMapObstacle() {return mapObstacle;}
    public ArrayList<Point> getStartPoint(){return startPoint;}
    public ArrayList<Size> getMapSize() {return mapSize;}
    public ArrayList<Goal> getMapGoal() {return mapGoal;}
}
