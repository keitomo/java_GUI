import java.awt.Graphics;

public class RankingState implements State {
	
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
		View.drawStringCenter(g, " 1st "+Integer.toString(scoreList.getScore(0).getScore())+"s    "+scoreList.getScore(0).getDate(), 400, 200);
		View.drawStringCenter(g, "2nd "+Integer.toString(scoreList.getScore(1).getScore())+"s    "+scoreList.getScore(1).getDate(), 400, 300);
		View.drawStringCenter(g, "3rd "+Integer.toString(scoreList.getScore(2).getScore())+"s    "+scoreList.getScore(2).getDate(), 400, 400);

		g.drawString("スペースキーで戻る", 400, 550);
	}

}
