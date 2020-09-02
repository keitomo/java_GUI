import java.awt.Graphics;

public interface State{
	//各Stateに番号を割り振っておく
	public static final int TITLE = 0;
	public static final int GAME = 1;
	public static final int RANKING = 2;
	public static final int HELP = 3;
	public static final int CLEAR = 4;
	public static final int BOSS = 5;
	
	
	GameFiles file = GameFiles.getInstance();
	public State processTimeElapsed(int msec);//時間経過時の処理
	public State processKeyTyped(String typed);//キー入力されたときの処理
	public void paintComponent(Graphics g);//画面描画処理
}
