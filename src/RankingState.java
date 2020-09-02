import java.awt.Graphics;

public class RankingState implements State {
	
	private ScoreList scoreList = ScoreList.getInstance();

	@Override
	//時間経過時の処理
	public State processTimeElapsed() {
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
		for(int i = 0; i<3;i++) {
			View.drawStringCenter(g, Integer.toString(i+1)+".  "+
			Integer.toString(scoreList.getScore(i).getScore())+"s    "+
			scoreList.getScore(i).getDate(), 400, 200+100*i);
		}

		g.drawString("スペースキーで戻る", 400, 550);
	}

}
