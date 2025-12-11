package Model.Unit;

import Model.DTO.Point;
import Model.DTO.Size;
import Model.DTO.Velocity;

public abstract class Unit {
    protected Point unit_Point;
    protected Velocity unit_Velocity;
    protected Size unit_Size;

    Unit(Point pPoint, Size pSize){
        unit_Velocity =new Velocity(0,0);
        unit_Point = pPoint;
        unit_Size = pSize;
    }

    public Point getUnit_Point(){
        return unit_Point;
    }

    public void setUnit_Point(Point pPoint){
        unit_Point.x = pPoint.x;
        unit_Point.y = pPoint.y;
    }

    public Size getUnit_Size(){ return unit_Size; }

    public void move(Point pPoint){};

    public boolean interfere(Point pPoint, Size pSize){
        if((unit_Point.x + 10 < pPoint.x + pSize.width) && (pPoint.x < unit_Point.x + unit_Size.width - 10)){
            if((unit_Point.y + 10 < pPoint.y + pSize.height) && (pPoint.y < unit_Point.y + unit_Size.height- 10)){
                return true;
            }
        }
        return false;
    }
}
