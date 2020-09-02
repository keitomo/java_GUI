import java.awt.Graphics;

public class ClearState implements State {
	/*
	 * ゲームクリア　State
	 */
	
	private TypingGame game;
	private GameFiles file = GameFiles.getInstance();
	private ScoreList scoreList = ScoreList.getInstance();
	
	public ClearState(TypingGame game) {
		this.game=game;
		scoreList.writeScore(new Score(this.game.getTime()/10,Score.getNowDate()));	
	}

	@Override
	//時間経過時の処理
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	//キー入力されたときの処理
	public State processKeyTyped(String typed) {
		if(typed.equals(" ")) {
			return new TitleState();
		}
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		g.drawImage(file.back,-500,0, null);
		g.drawString(Integer.toString(scoreList.getScore(0).getScore()),100,100);
	}

}
