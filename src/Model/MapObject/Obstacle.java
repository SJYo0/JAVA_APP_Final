package Model.MapObject;

import Model.DTO.Point;
import Model.DTO.Size;

public class Obstacle extends MapObject {
    private int damage;

    Obstacle(Point pObstacle_Point, Size pObstacle_Size){
        super(pObstacle_Point,pObstacle_Size);
    }
}
