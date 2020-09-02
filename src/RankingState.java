import java.awt.Graphics;

public class RankingState implements State {
	
	private GameFiles file = GameFiles.getInstance();
	private ScoreList scoreList = ScoreList.getInstance();

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
		g.drawString(Integer.toString(scoreList.getScore(0).getScore()),100,200);
		g.drawString(Integer.toString(scoreList.getScore(1).getScore()),100,300);
		g.drawString(Integer.toString(scoreList.getScore(2).getScore()),100,400);
		g.drawString("スペースキーで戻る", 400, 550);
	}

}
