import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameState implements State {
	/*
	 * ゲーム　State
	 */
	
	private GameFiles file = GameFiles.getInstance();
	private int limitTime = 0;
	private int time=0;

	@Override
	//時間経過時の処理
	public State processTimeElapsed(int msec) {
		limitTime++;
		time+=18;
		if(time==1800) {
			time=0;
		}
		return this;
	}

	@Override
	//キー入力されたときの処理
	public State processKeyTyped(String typed) {
		if(typed.equals(" "))
			return new TitleState();
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		View.scrollBack(g, time);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.black);
		g.drawString("Time:"+Integer.toString(limitTime/10),0,50);
		g.drawString("スペースキーで戻る", 400, 550);
	}

}
