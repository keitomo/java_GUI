import java.awt.Color;
import java.awt.Graphics;

public class GameState implements State {
	/*
	 * ゲーム　State
	 */
	
	private TypingGame game;
	private int backPosX = 0;
	private int animePos=0;
	
	GameState() {
		game = new TypingGame();
	}

	@Override
	//時間経過時の処理
	public State processTimeElapsed(int msec) {
		game.processingGame("TIME_ELAPSED");
		backPosX+=1;
		if(backPosX==1800) backPosX=0;
		if(game.getProblemFlag()) {
			animePos+=24;
			if(animePos==120*game.getClearProblem()) {
				game.resetProblemFlag();
			}
		}
		return this;
	}

	@Override
	//キー入力されたときの処理
	public State processKeyTyped(String typed) {
		game.processingGame(typed);
		if(game.getClearFlag()) {
			return new ClearState(game);
		}
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		g.drawImage(file.back, 0-backPosX,0, null);
    	g.drawImage(file.back, 1800-backPosX,0, null);
		g.drawString("Time:"+Integer.toString(game.getTime()/10),0,50);
		g.drawString("Problem:"+Integer.toString(game.getRemainingProblem()),500,50);
		g.drawString(game.getInput(),300,200);
		g.drawString(game.getProblem(0),100,100);
		g.drawString(game.getProblem(1),500,100);
		switch(game.getSelectProblem()) {
			case 0:
				g.setColor(Color.red);
				g.drawString(game.getProblem(0),100,100);
				break;
			case 1:
				g.setColor(Color.red);
				g.drawString(game.getProblem(1),500,100);
				break;
			default:
				break;
		}
		for(int i = 0; i < game.getMap().length ; i++) {
			switch(game.getMap()[i]) {
			case TypingGame.WOOD:
				g.setColor(new Color(92,67,38));
				break;
			case TypingGame.GOAL:
				g.setColor(Color.white);
				break;
			default:
				g.setColor(new Color(0,0,0,0));
				break;
			}
			g.fillRect(i*120-animePos+350, 420, 100, 30);
			//g.setColor(Color.red);
			//g.drawString(Integer.toString(i),i*120-animePos+390,450);
		}
		g.drawImage(file.mainChar,375 ,(int)(375+Math.abs(Math.sin((animePos*Math.PI)/120))*-100), null);

		

	}

}
