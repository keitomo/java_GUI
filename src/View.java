import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

    private Model model;
    private GameFiles file;

    public View(Model model) {
        this.model = model;
        file = GameFiles.getInstance();
    }

    /**
     * 画面を描画する
     * @param g  描画用のグラフィックスオブジェクト
     */
    @Override
    public void paintComponent(Graphics g) {
        // 画面をいったんクリア
       clear(g);
       
       State state = model.getState();
       state.paintComponent(g);//
       
       /*g.setColor(Color.red);
       g.drawLine(400, 0, 400, 600);
       g.drawLine(0, 300, 800, 300);
       
       g.drawImage(file.back, 0,0, this);
       
       g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
       g.setColor(Color.WHITE);
       g.drawString("Time: " + model.getTime(), 100, 200);
       g.drawString("Key Typed: " + s, 100, 250);
       g.setColor(Color.orange);
       g.fillRect(10, 420, 100, 30);
        // 画像の表示例
       g.drawImage(file.mainChar, 35, 390, this);//*/
        
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
    
    /*
    public void playBombSound() {
        sound.stop(); // まず音を停めてから
        sound.play(); // 再生する
    }*/
    
}
