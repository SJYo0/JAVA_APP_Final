package Model.Unit;

import Model.DTO.Point;
import Model.DTO.Size;

import javax.imageio.ImageIO;
import java.awt.*;
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
    private boolean canJump = false;
    private boolean isPressed[] = {false,false,false};
    private boolean isMousePressed[] = {false,false};
    private boolean canDash = false;
    private boolean isDashed = false;
    private boolean isGrabObject = false;
    private boolean canHook = false;
    private boolean direction = false;
    private int count = 0, cut =0, nowMoving=0 ;

    public BufferedImage originalImage = null;
    public Image image = null;

    private Image[] stand = null;
    private Image[][] move = null;
    private Image[][] jump = null;
    private Image[][] swing = null;

    public Player(double pX, double pY, int pWidth, int pHeight){
        super(new Point(pX,pY),new Size(pWidth,pHeight));

        String imagePath = "/Model/image/curby.png";

        // 상대경로로 읽어주는 클래스
        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            originalImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        image = originalImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);

        loadImage();
    }

    private void loadImage() {
        // 일반 이미지
        String imagePath = "/Model/image/curby.png";

        // 상대경로로 읽어주는 클래스
        try (InputStream read_path = getClass().getResourceAsStream(imagePath)) {
            originalImage = ImageIO.read(read_path);
        } catch (IOException e) {
            System.out.println(e);
        }

        stand = new Image[2];

        stand[0] = originalImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        stand[1] = filpImage(stand[0]);

        move = new Image[2][8];
        jump = new Image[2][8];

        for(int i=0; i<8; i++){
            // 일반 이미지
            String imagePath_1 = "/Model/image/action/move/walking_"+i+".png";
            String imagePath_2 = "/Model/image/action/jump/jumping_"+i+".png";

            // 상대경로로 읽어주는 클래스
            try (InputStream read_path = getClass().getResourceAsStream(imagePath_1)) {
                originalImage = ImageIO.read(read_path);
            } catch (IOException e) {
                System.out.println(e);
            }

            move[0][i] = originalImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            move[1][i] = filpImage(move[0][i]);


            try (InputStream read_path = getClass().getResourceAsStream(imagePath_2)) {
                originalImage = ImageIO.read(read_path);
            } catch (IOException e) {
                System.out.println(e);
            }

            jump[0][i] = originalImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            jump[1][i] = filpImage(jump[0][i]);
        }

        swing = new Image[2][4];

        for(int i=0;i<4;i++){
            // 일반 이미지
            String imagePath_1 = "/Model/image/action/swing/swing_"+i+".png";

            // 상대경로로 읽어주는 클래스
            try (InputStream read_path = getClass().getResourceAsStream(imagePath_1)) {
                originalImage = ImageIO.read(read_path);
            } catch (IOException e) {
                System.out.println(e);
            }

            swing[0][i] = originalImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            swing[1][i] = filpImage(swing[0][i]);
        }
    }

    private Image filpImage(Image pImage){
        int w = pImage.getWidth(null);
        int h = pImage.getHeight(null);

        BufferedImage filpImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics g = filpImage.createGraphics();
        g.drawImage(pImage,w,0,-w,h,null);
        g.dispose();

        return filpImage;
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_D) {
            isPressed[2] = true;
            direction = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A) {
            isPressed[1] = true;
            direction =false;
        }
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

        isGrabObject = false;
    }

    public void mousePressed(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1) {
            isMousePressed[0] = true;

            /*hook_X = e.getX();
            hook_Y = e.getY();*/

            double dx = unit_Point.x - hook_X;
            double dy = unit_Point.y - hook_Y;
            chainLength = Math.sqrt(dx * dx + dy * dy);

            if((chainLength <= 500) && canHook) {
                isHooked = true;
                canDash = true;
                canJump = false;
                canHook = false;

                angle = Math.atan2(dy, dx);

                angle_Velocity = -unit_Velocity.Velocity_X / chainLength * 3;
            }
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            isMousePressed[1] = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1) {
            isMousePressed[0] = false;
            canHook = false;
            if(isHooked) {
                isHooked = false;
                isDashed = false;
                double speed = angle_Velocity * chainLength;
                double xVector = -Math.sin(angle);
                double yVector = Math.cos(angle);

                unit_Velocity.Velocity_X = xVector * speed;
                unit_Velocity.Velocity_Y = yVector * speed;
            }
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            isMousePressed[1] = false;
        }
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
                    isGrabObject = true;
                    canJump = true;
                } else { // 왼쪽으로 적게 겹쳤다면
                    unit_Point.x = pPoint.x - unit_Size.width; // 왼쪽으로 밀어냄
                    isGrabObject = true;
                    canJump = true;
                }
                unit_Velocity.Velocity_X = 0;
            } else { // Y축으로 밀어내기
                if (dy > 0) { // 아래쪽으로 적게 겹침
                    unit_Point.y = pPoint.y + pSize.height;
                    unit_Velocity.Velocity_Y = 0;
                    //isGrapObject = true;
                } else { // 위쪽으로 적게 겹침
                    unit_Point.y = pPoint.y - unit_Size.height;
                    unit_Velocity.Velocity_Y = 0;
                    canJump = true; // 점프 가능
                }
            }
        }
    }

    private void updateImage(){
        int nextMoving;

        if((isPressed[1] || isPressed[2]) && canJump && !isHooked){
            nextMoving = 1;
        }else if(!canJump && !isHooked){
            nextMoving =2;
        }else if(isHooked){
            nextMoving = 3;
        }else{
            nextMoving =0;
        }

        if(nowMoving != nextMoving){
            nowMoving = nextMoving;
            cut =0;
            count =0;
        }

        count++;
        if(count>4){
            cut++;
            if(cut>7){
                cut=0;
            }
            count =0;
        }

        if(direction){
            if(nowMoving==1) image = move[0][cut];
            else if(nowMoving==2) image = jump[0][cut];
            else if(nowMoving == 3) {image = swing[0][cut/2];}
            else image = stand[0];
        }else{
            if(nowMoving==1) image = move[1][cut];
            else if(nowMoving==2) image = jump[1][cut];
            else if(nowMoving == 3) image = swing[1][cut/2];
            else image = stand[1];
        }
    }

    public void move(double pGravity){
        if (isHooked){
            swing(pGravity);
            if(canDash && isDashed){
                if(angle_Velocity >= 0){
                    angle_Velocity = 30 / chainLength;
                    canDash = false;
                }else{
                    angle_Velocity = -30 / chainLength;
                    canDash = false;
                }
            }
        }
        else if(isGrabObject){
            unit_Velocity.Velocity_X = 0;
            unit_Velocity.Velocity_Y = 0;
        }
        else {
            if (isPressed[2] && !isPressed[1]) {
                unit_Velocity.Velocity_X = 5;
            } else if (isPressed[1] && !isPressed[2]) {
                unit_Velocity.Velocity_X = -5;
            } else {
                unit_Velocity.Velocity_X = 0;
            }

            if (isPressed[0]) {
                if (canJump) {
                    unit_Velocity.Velocity_Y = -15;
                    canJump = false;
                }
            }

            unit_Point.x += unit_Velocity.Velocity_X;
            unit_Velocity.Velocity_Y += pGravity;
            if (unit_Velocity.Velocity_Y >= 30) {
                unit_Velocity.Velocity_Y = 30;
            }
            unit_Point.y += unit_Velocity.Velocity_Y;
        }

        updateImage();
    }

    public void canHook(Point pPoint, Size pSize, double pX, double pY){
        if((pPoint.x <= pX)&&(pX <= pPoint.x+pSize.width)){
            if((pPoint.y <= pY)&&(pY <= pPoint.y+pSize.height)){
                canHook = true;
            }
        }
        hook_X = pX;
        hook_Y = pY;
    }

    private void swing(double pGravity){
        double angle_Acceleration = (pGravity * Math.cos(angle)) / chainLength;

        angle_Velocity += angle_Acceleration;
        angle_Velocity *= 0.995;
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

    public void setisHooked(boolean pBool){
        isHooked = pBool;
    }
}
