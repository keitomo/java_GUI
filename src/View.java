import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

    private Model model;

    // Sample instance variables:
    private Image image;
    private AudioClip sound;

    public View(Model model) {
        this.model = model;

        // 画像を読み込む．画像ファイルは src においておくと bin に自動コピーされる
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("robot.png"));
        // サウンドを読み込む
        sound = Applet.newAudioClip(getClass().getResource("bomb.wav"));
    }

    /**
     * 画面を描画する
     * @param g  描画用のグラフィックスオブジェクト
     */
    @Override
    public void paintComponent(Graphics g) {
        // 画面をいったんクリア
       clear(g);
       //日本国旗
       g.setColor(Color.white);
       g.fillRect(10, 10, 300, 200);
       g.setColor(Color.RED);
       g.fillOval(310/2-60,210/2-60, 120, 120);
       
       //アイスランド国旗
       g.setColor(Color.BLUE);
       g.fillRect(250, 250 ,300, 200);
       g.setColor(Color.WHITE);
       g.fillRect(325, 250, 50, 200);
       g.fillRect(250, 325, 300, 50);
       g.setColor(Color.RED);
       g.fillRect(337, 250, 25, 200);
       g.fillRect(250, 337, 300, 25);
        // 描画する
        /*g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        g.setColor(Color.WHITE);
        g.drawString("Time: " + model.getTime(), 100, 200);
        g.drawString("Key Typed: " + model.getTypedChar(), 100, 250);
        g.drawString("Mouse Pressed: " + model.getMX() + "," + model.getMY(), 100, 300);*/

        // 画像の表示例
        //g.drawImage(image, model.getMX(), model.getMY(), this);
    }

    /**
     * 画面を黒色でクリア
     * @param g  描画用のグラフィックスオブジェクト
     */
    public void clear(Graphics g) {
        Dimension size = getSize();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);
    }

    public void playBombSound() {
        sound.stop(); // まず音を停めてから
        sound.play(); // 再生する
    }
    
    public void drawJFlag(Graphics g,int x,int y,int w) {
    	
    }

}
