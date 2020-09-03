import java.awt.Color;
import java.awt.Graphics;

public class VariousState implements State {

	private ScoreList scoreList = ScoreList.getInstance();
	private GameFiles file = GameFiles.getInstance();
	private TypingGame gameData;
	private String stateName;

	public VariousState(String state,TypingGame game) {
		stateName=state;
		this.gameData=game;
		if(state.equals("CLEAR"))
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
		if(typed.equals(" "))
			return new TitleState();
		return this;
	}
	
	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		g.drawImage(file.back,-500,0, null);
		switch(stateName) {
		case "CLEAR":
			View.drawStringCenter(g, "クリアタイム："+Integer.toString(gameData.getTime()/10)+"s", 400, 200);
			View.drawStringCenter(g, "解いた問題数："+Integer.toString(gameData.getProblemCount())+"問", 400, 300);
			View.drawStringCenter(g, "正確率："+Double.toString((Math.round((double)gameData.getMatchNum()/(double)gameData.getInputNum()*100)))+"%", 400, 400);
			break;
		case "RANKING":
			g.drawString("Ranking", 315, 100);
			for(int i = 0; i<3;i++) {
				View.drawStringCenter(g, Integer.toString(i+1)+".  "+
				Integer.toString(scoreList.getScore(i).getScore())+"s    "+
				scoreList.getScore(i).getDate(), 400, 200+100*i);
			}
			break;
		case "HELP":
			View.drawStringCenter(g, "遊び方", 400, 100);
			double small = 0.7;
			g.fillRect(403-(int)(782*small)/2,113,(int)(782*small),(int)(560*small));
			g.setColor(Color.white);
			g.fillRect(397-(int)(782*small)/2,107,(int)(782*small),(int)(560*small));
			g.setColor(Color.black);
			g.drawImage(file.help, 400-(int)(782*small)/2,110,(int)(782*small),(int)(560*small),null);
			break;
		default:
			break;
		}
		
		
		g.drawString("スペースキーで戻る", 400, 550);
	}

}
