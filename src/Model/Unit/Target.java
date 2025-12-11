package Model.Unit;

import Model.DTO.Point;
import Model.DTO.Size;

public class Target extends Unit {
    private double angle;
    private double distance;

    public Target(Point pPoint, Size pSize) {
        super(pPoint, pSize);

        angle = 0;
        distance = 200;
    }

    @Override
    public void move(Point pPoint) {
        angle += 0.03;

        unit_Point.x = pPoint.x + distance*Math.cos(angle);
        unit_Point.y = pPoint.y + distance*Math.sin(angle);
    }
}
