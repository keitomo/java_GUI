import java.awt.Graphics;

public interface State{	
	public State processTimeElapsed();//時間経過時の処理
	public State processKeyTyped(String typed);//キー入力されたときの処理
	public void paintComponent(Graphics g);//画面描画処理
}
