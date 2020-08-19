import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class RankingState implements State {
	
	private GameFiles file = GameFiles.getInstance();

	@Override
	//時間経過時の処理
	public State processTimeElapsed(int msec) {
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
		g.drawImage(file.back,-500,0, null);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.black);
		g.drawString("Ranking", 350, 100);
		g.drawString("スペースキーで戻る", 400, 550);
	}

}
