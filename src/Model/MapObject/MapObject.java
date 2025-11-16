package Model.MapObject;

import Model.DTO.Point;
import Model.DTO.Size;

public class MapObject {
    protected Point object_Point;
    protected Size object_Size;

    MapObject(Point pObject_Point, Size pObject_Size){
        object_Point = pObject_Point;
        object_Size =  pObject_Size;
    }

    public Point getObject_Point(){
        return object_Point;
    }

    public Size getObject_Size(){
        return object_Size;
    }
}
