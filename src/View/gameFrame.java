package View;

import javax.swing.*;

public class gameFrame extends JFrame {
    private JPanel nowPanel = null;
    private stagePanel StagePanel = new stagePanel(this);
    private gamePanel GamePanel;

    gameFrame() {
        setSize(1440, 840);
        setTitle("Swing Action Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        introPanel intro = new introPanel(this, StagePanel);
        changePanel(intro);

        setVisible(true);
    }

    public void changePanel(JPanel pPanel){

        if(nowPanel != null){
            remove(nowPanel);
        }

        nowPanel = pPanel;
        add(nowPanel);

        //revalidate();
        repaint();

        nowPanel.setFocusable(true);
    }

    public void selectStage(int pStageNum){
        if(nowPanel != null){
            remove(nowPanel);
        }

        GamePanel = new gamePanel(this,pStageNum,StagePanel);
        nowPanel = GamePanel;

        add(nowPanel);

        nowPanel.setFocusable(true);
        nowPanel.requestFocusInWindow();
    }
}
