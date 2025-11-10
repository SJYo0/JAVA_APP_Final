package Model;

public class Player extends Unit {

    private boolean isHooked = false;
    private double hook_X, hook_Y;
    private double chainLength;
    private double angle;
    private double angle_Velocity = 0;

    Player(double pX, double pY, int pSize){
        unit_X = pX;
        unit_Y = pY;
        size = pSize;
    }

    public void setSwing(double pHook_X,double pHook_Y, double pChainLength,double pAngle){

    }
}
