package Model.Utility;

import Model.DTO.Point;
import Model.DTO.Size;

public class Camera {
    private Point cameraPoint;
    private Size panelSize;
    private Size mapSize;

    public Camera(){
        cameraPoint = new Point(0,0);
        panelSize = new Size(0,0);
        mapSize = new Size(0,0);
    };

    public void setPanelSize(Size pSize){
        panelSize.width = pSize.width;
        panelSize.height = pSize.height;
    }

    public  void  setMapSize(Size pSize){
        mapSize.width = pSize.width;
        mapSize.height = pSize.height;
    }

    public void setCameraPoint(Point pPoint){
        cameraPoint.x = pPoint.x - (panelSize.width/2);
        cameraPoint.y = pPoint.y - (panelSize.height/2);

        // 화면 끝 검사
        if(cameraPoint.x < 0) cameraPoint.x =0;
        if(cameraPoint.y < 0) cameraPoint.y =0;
        if(cameraPoint.x > mapSize.width - panelSize.width) cameraPoint.x = mapSize.width - panelSize.width;
        if(cameraPoint.y > mapSize.height - panelSize.height) cameraPoint.y = mapSize.height - panelSize.height;
    }

    public Point getCameraPoint(){return cameraPoint;}
}
