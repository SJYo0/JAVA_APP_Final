package Model.Unit;

import Model.DTO.Point;
import Model.DTO.Size;
import Model.DTO.Velocity;

public class Unit {
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

    public Size getUnit_Size(){
        return unit_Size;
    }

    public void setUnit_Velocity(double pVelocity_X, double pVelocity_Y){
        unit_Velocity.Velocity_X = pVelocity_X;
        unit_Velocity.Velocity_Y = pVelocity_Y;
    }

    public Velocity getUnit_Velocity(){
        return unit_Velocity;
    }

    public void move(double pGravity){}
}
