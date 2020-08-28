import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public class Controller implements ActionListener, KeyListener, MouseListener {

    private static final int DELAY = 100; // ミリ秒
    private Model model;
    private Timer timer;

    public Controller(Model model) {
        // モデルを保持（イベントの通知先）
        this.model = model;
        timer = new Timer(DELAY, this);
    }

    public void start() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.processTimeElapsed(DELAY);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    	model.processKeyTyped(Character.toString(e.getKeyChar()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	switch(e.getKeyCode()) {
    	case KeyEvent.VK_ENTER://Enterが押されたら
    		model.processKeyTyped("ENTER");
    		break;
    	case KeyEvent.VK_UP://矢印上を押されたら
    		model.processKeyTyped("UP");
    		break;
    	case KeyEvent.VK_DOWN://矢印下を押されたら
    		model.processKeyTyped("DOWN");
    		break;
    	case KeyEvent.VK_LEFT://矢印左を押されたら
    		model.processKeyTyped("LEFT");
    		break;
    	case KeyEvent.VK_RIGHT://矢印右を押されたら
    		model.processKeyTyped("RIGHT");
    		break;
    	case KeyEvent.VK_ALT://ALTが押されたら
    		model.processKeyTyped("ALT");
    		break;
    	case KeyEvent.VK_ESCAPE://ESCが押されたら
    		model.processKeyTyped("ESC");
    		break;
    	default:
    		break;
    	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	// do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // do nothing
    }
}