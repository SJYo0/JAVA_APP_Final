package Model.Unit;

import Model.DTO.Point;
import Model.DTO.Size;

public class Laser extends Unit {
    public Laser(Point pPoint, Size pSize) {
        super(pPoint, pSize);

        unit_Velocity.Velocity_X =1.4;
        unit_Velocity.Velocity_Y=0;
    }

    public void move(){
            unit_Point.x += unit_Velocity.Velocity_X;
    }
}
