package Model.Unit;

import Model.DTO.Point;
import Model.DTO.Size;
import Model.DTO.Vector;
import Model.MapObject.Land;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Unit {

    private boolean isHooked = false;
    private double hook_X, hook_Y;
    private double chainLength;
    private double angle;
    private double angle_Velocity;
    private Vector playerVector;
    private boolean canJump = false;
    private boolean isPressed[] = {false,false,false};
    private boolean canDash = false;
    private boolean isDashed = false;

    public BufferedImage image = null;

    public Player(double pX, double pY, int pWidth, int pHeight){
        super(new Point(pX,pY),new Size(pWidth,pHeight));

        String imagePath = "/Model/image/curby.png";

        // 상대경로로 읽어주는 클래스
        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            image = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_D)
            isPressed[2] = true;
        else if(e.getKeyCode() == KeyEvent.VK_A)
            isPressed[1] = true;
        else if((e.getKeyCode() == KeyEvent.VK_W)|| (e.getKeyCode() == KeyEvent.VK_SPACE))
            isPressed[0] = true;
        else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            isDashed = true;
        }
    }

    public void  keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_D)
            isPressed[2] = false;
        else if(e.getKeyCode() == KeyEvent.VK_A)
            isPressed[1] = false;
        else if((e.getKeyCode() == KeyEvent.VK_W) || (e.getKeyCode() == KeyEvent.VK_SPACE))
            isPressed[0] = false;
    }

    public void mousePressed(MouseEvent e){
        isHooked = true;
        canDash = true;
        canJump = false;
        hook_X = e.getX();
        hook_Y = e.getY();

        double dx = unit_Point.x - hook_X;
        double dy = unit_Point.y - hook_Y;
        chainLength = Math.sqrt(dx*dx + dy*dy);
        angle = Math.atan2(dy,dx);

        angle_Velocity = -unit_Velocity.Velocity_X / chainLength * 2;
    }

    public void mouseReleased(MouseEvent e) {
        isHooked = false;
        isDashed = false;
        double speed = angle_Velocity * chainLength;
        double xVector = -Math.sin(angle);
        double yVector = Math.cos(angle);

        unit_Velocity.Velocity_X = xVector * speed;
        unit_Velocity.Velocity_Y = yVector * speed;

    }

    public void isInterfere_Object(Point pPoint, Size pSize){

        // 현재 장애물과 플레이어의 중심 사이의 x, y 거리
        double dx = (unit_Point.x + unit_Size.width / 2) - (pPoint.x + pSize.width / 2);
        double dy = (unit_Point.y + unit_Size.height / 2) - (pPoint.y + pSize.height / 2);

        // 장애물과 플레이어가 겹치지 않는 최소 x, y 거리
        double minimumWidth = unit_Size.width / 2 + pSize.width / 2;
        double minimumHeight = unit_Size.height / 2 + pSize.height / 2;

        // 만약 객체끼리 겹쳤다면, overlapX > 0, overlapY > 0 가 되야함
        double overlapX = minimumWidth - Math.abs(dx);
        double overlapY = minimumHeight - Math.abs(dy);

        if (overlapX > 0 && overlapY > 0) { // 만약 겹쳤다면 더 적게 겹친쪽으로 위치 초기화
            if (overlapX < overlapY) {  // X축쪽이 적게 겹쳐서 X축으로 밀어냄
                if (dx > 0) { // 오른쪽으로 적게 겹쳤다면
                    unit_Point.x = pPoint.x + pSize.width; // 오른쪽으로 밀어냄
                } else { // 왼쪽으로 적게 겹쳤다면
                    unit_Point.x = pPoint.x - unit_Size.width; // 왼쪽으로 밀어냄
                }
                unit_Velocity.Velocity_X = 0;
            } else { // Y축으로 밀어내기
                if (dy > 0) { // 아래쪽으로 적게 겹침
                    unit_Point.y = pPoint.y + pSize.height; // 아래쪽으로 밀어냄
                    unit_Velocity.Velocity_Y = 0;
                } else { // 위쪽으로 적게 겹침
                    unit_Point.y = pPoint.y - unit_Size.height; // 위쪽으로 밀어냄
                    unit_Velocity.Velocity_Y = 0;
                    canJump = true; // 점프 가능
                }
            }
        }
    }

    public void move(double pGravity){
        if (isHooked){
            swing(pGravity);
            if(canDash && isDashed){
                if(angle_Velocity >= 0){
                    angle_Velocity = 47 / chainLength;
                    canDash = false;
                }else{
                    angle_Velocity = -47 / chainLength;
                    canDash = false;
                }
            }
        }else {
            if (isPressed[2] && !isPressed[1]) {
                unit_Velocity.Velocity_X = 9;
            } else if (isPressed[1] && !isPressed[2]) {
                unit_Velocity.Velocity_X = -9;
            } else {
                unit_Velocity.Velocity_X = 0;
            }

            if (isPressed[0]) {
                if (canJump) {
                    unit_Velocity.Velocity_Y = -25;
                    canJump = false;
                }
            }

            unit_Point.x += unit_Velocity.Velocity_X;
            unit_Velocity.Velocity_Y += pGravity;
            if (unit_Velocity.Velocity_Y >= 40) {
                unit_Velocity.Velocity_Y = 40;
            }
            unit_Point.y += unit_Velocity.Velocity_Y;
        }
    }

    public void swing(double pGravity){
        double angle_Acceleration = (pGravity * Math.cos(angle)) / chainLength;

        angle_Velocity += angle_Acceleration;
        angle_Velocity *= 0.98;
        angle += angle_Velocity;

        unit_Point.x = hook_X + chainLength * Math.cos(angle);
        unit_Point.y = hook_Y + chainLength * Math.sin(angle);
    }

    public Point getHookPoint(){
        Point p = new Point(hook_X,hook_Y);
        return p;
    }

    public boolean getisHooked(){
        return isHooked;
    }
}
