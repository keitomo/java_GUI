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
		g.drawString("Ranking", 315, 100);
		g.drawString("1:記録なし", 315, 300);
		g.drawString("2:記録なし", 315, 400);
		g.drawString("3:記録なし", 315, 500);
		g.drawString("スペースキーで戻る", 400, 550);
	}

}
