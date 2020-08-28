import java.awt.Color;
import java.awt.Graphics;

public class GameState implements State {
	/*
	 * ゲーム　State
	 */
	
	private GameFiles file = GameFiles.getInstance();
	private TypingGame game;
	
	GameState() {
		game = new TypingGame();
	}

	@Override
	//時間経過時の処理
	public State processTimeElapsed(int msec) {
		game.processingGame("TIME_ELAPSED");
		return this;
	}

	@Override
	//キー入力されたときの処理
	public State processKeyTyped(String typed) {
		game.processingGame(typed);
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		g.drawImage(file.back, 0-game.getTime(),0, null);
    	g.drawImage(file.back, 1800-game.getTime(),0, null);
		g.drawString("Time:"+Integer.toString(game.getTime()/10),0,50);
		g.drawString("Problem:"+Integer.toString(game.getRemainingProblem()),500,50);
		g.drawString(game.getInput(),300,200);
		g.drawString(game.getProblem(0),500,100);
		g.drawString(game.getProblem(1),100,100);
		if(game.getSelectProblem()==0) {
			g.setColor(Color.red);
			g.drawString(game.getProblem(0),500,100);
		}else if(game.getSelectProblem()==1) {
			g.setColor(Color.red);
			g.drawString(game.getProblem(1),100,100);
		}

	}

}
