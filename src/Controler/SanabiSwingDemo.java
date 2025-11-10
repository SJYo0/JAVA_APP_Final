package Controler;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SanabiSwingDemo extends JFrame {

    public SanabiSwingDemo() {
        setTitle("스윙 액션 테스트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        add(panel);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SanabiSwingDemo::new);
    }
}

class GamePanel extends JPanel implements ActionListener {

    // 플레이어 상태
    private double playerX = 400;
    private double playerY = 100;
    private double velocityX = 0;
    private double velocityY = 0;
    private int playerSize = 20;

    // 갈고리(훅) 상태
    private boolean isHooked = false;
    private double hookX;
    private double hookY;
    private double chainLength;

    // 물리 상태 (진자 운동)
    private double angle;
    private double angularVelocity = 0;
    private final double GRAVITY = 0.5; // 중력 가속도

    private Timer timer;

    public GamePanel() {
        this.setBackground(Color.DARK_GRAY);
        timer = new Timer(16, this); // 약 60 FPS
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 1. 갈고리 상태로 전환
                isHooked = true;
                hookX = e.getX();
                hookY = e.getY();

                // 2. 초기 각도(angle)와 거리(chainLength) 계산
                double dx = playerX - hookX;
                double dy = playerY - hookY;
                chainLength = Math.sqrt(dx * dx + dy * dy);
                angle = Math.atan2(dy, dx);

                System.out.println(angle);
                // 3. 초기 각속도
                // (간단하게 0으로 시작. 실제로는 기존 속도를 반영해야 더 자연스러움)
                angularVelocity = 0;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isHooked = false;

                // 4. 갈고리를 풀 때의 속도 계산 (발사!)
                // 현재 각속도를 x, y 속도로 변환
                double tangentialSpeed = angularVelocity * chainLength;
                // 탄젠트 벡터(접선 벡터) 계산
                double tangentX = -Math.sin(angle);
                double tangentY = Math.cos(angle);

                velocityX = tangentX * tangentialSpeed;
                velocityY = tangentY * tangentialSpeed;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isHooked) {
            // --- 진자 운동 (갈고리 사용 중) ---

            // 1. 각가속도 계산 (중력의 영향)
            double angularAcceleration = (GRAVITY * Math.cos(angle)) / chainLength;

            // 2. 속도 및 각도 업데이트
            angularVelocity += angularAcceleration;
            angularVelocity *= 0.998; // 약간의 공기 저항
            angle += angularVelocity;

            // 3. 새 위치 계산 (원의 중심 + 반지름 * cos/sin)
            playerX = hookX + chainLength * Math.cos(angle);
            playerY = hookY + chainLength * Math.sin(angle);

        } else {
            // --- 포물선 운동 (갈고리 사용 안함) ---

            // 1. 중력 적용
            velocityY += GRAVITY;

            // 2. 위치 업데이트
            playerX += velocityX;
            playerY += velocityY;

            // (간단한 바닥 충돌)
            if (playerY > 550) {
                playerY = 550;
                velocityY = 0;
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. 갈고리가 걸려있으면 선 그리기 (drawLine)
        if (isHooked) {
            g.setColor(Color.WHITE);
            g.drawLine((int)playerX, (int)playerY, (int)hookX, (int)hookY);

            // 갈고리 지점 표시
            g.setColor(Color.RED);
            g.fillOval((int)hookX - 5, (int)hookY - 5, 10, 10);
        }

        // 2. 플레이어 그리기
        g.setColor(Color.CYAN);
        g.fillOval((int)playerX - playerSize / 2, (int)playerY - playerSize / 2, playerSize, playerSize);
    }
}

// testestsetseest
// test2222222