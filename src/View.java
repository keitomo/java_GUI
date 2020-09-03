import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

    private Model model;
    public View(Model model) {
        this.model = model;
        GameFiles.getInstance();
    }

    /**
     * 画面を描画する
     * @param g  描画用のグラフィックスオブジェクト
     */
    @Override
    public void paintComponent(Graphics g) {
        // 画面をいったんクリア
       clear(g);
       g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.black);       
       State state = model.getState();
       state.paintComponent(g);//
       Toolkit.getDefaultToolkit().sync();
     }
    
    /*
     * 画面を黒色でクリア
     * @param g  描画用のグラフィックスオブジェクト
     */
    public void clear(Graphics g) {
        Dimension size = getSize();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);
    }
    
    public static void drawStringCenter(Graphics g,String str,int x,int y) {
    	g.drawString(str, x-(g.getFontMetrics().stringWidth(str)/2), y);
    }
    
    public static void drawStringRight(Graphics g,String str,int x,int y) {
    	g.drawString(str, x-(g.getFontMetrics().stringWidth(str)), y);
    }
          
}
