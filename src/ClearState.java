import java.awt.Graphics;

public class ClearState implements State {
	/*
	 * ゲームクリア　State
	 */
	
	private TypingGame gameData;
	private ScoreList scoreList = ScoreList.getInstance();
	private GameFiles file = GameFiles.getInstance();
	public ClearState(TypingGame game) {
		this.gameData=game;
		scoreList.writeScore(new Score(game.getTime()/10,Score.getNowDate()));	
	}

	@Override
	//時間経過時の処理
	public State processTimeElapsed() {
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
		View.drawStringCenter(g, "クリアタイム："+Integer.toString(gameData.getTime()/10)+"s", 400, 200);
		View.drawStringCenter(g, "解いた問題数："+Integer.toString(gameData.getProblemCount())+"問", 400, 300);
		View.drawStringCenter(g, "正確率："+Double.toString((Math.round((double)gameData.getMatchNum()/(double)gameData.getInputNum()*100)))+"%", 400, 400);
		g.drawString("スペースキーで戻る", 400, 550);
	}

}
