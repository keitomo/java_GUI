import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame {

    /** 初期ウィンドウサイズ（高さ） */
    public static final int WIN_HEIGHT = 600;
    /** 初期ウィンドウサイズ（幅） */
    public static final int WIN_WIDTH = 800;
    
    private int DIS_WIDTH;
    private int DIS_HEIGHT;

    private Model model = null;

    public Game() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	DIS_WIDTH = screenSize.width;
    	DIS_HEIGHT = screenSize.height;
    	//Windowのタイトルとサイズを設定
    	resetWindow();
        // Window を閉じるボタンを有効にする
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // モデルの生成
        model = new Model(this);

        // この Window に view を登録
        setContentPane(model.getView());
        // キーボードイベントのリスナとして controller を登録
        addKeyListener(model.getController());
        // マウスイベントのリスナとして controller を登録
        model.getView().addMouseListener(model.getController());
        // この Window を表示
        setVisible(true);
    }

    public void start() {
        // ゲーム開始
        model.start();
    }

    /** 起動用 main 関数 */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
    
    //ボスが来たときにタイトルと画面サイズを変える
    public void setBossWindow() {
    	setTitle("Llbre0ffice wr1ter");
    	setSize(DIS_WIDTH, DIS_HEIGHT);
    }
    
    //タイトルと画面サイズをゲーム用に変える
    public void resetWindow() {
    	setTitle("TypingJump");
    	setSize(WIN_WIDTH, WIN_HEIGHT);
    }
}
