import java.awt.Color;
import java.awt.Graphics;

public class GameState implements State {
	/*
	 * ゲーム　State
	 */
	private GameFiles file = GameFiles.getInstance();
	private TypingGame gameData;
	private int backPosX = 0;
	private int animePos=0;
	
	GameState() {
		gameData = new TypingGame();
	}

	@Override
	//時間経過時の処理
	public State processTimeElapsed() {
		gameData.processTimeElapsed();
		backPosX+=1;
		if(backPosX==1800) backPosX=0;
		if(gameData.getProblemFlag()) {
			animePos+=24;
			if(animePos==120*gameData.getNowPos()) {
				gameData.resetProblemFlag();
			}
		}
		return this;
	}

	@Override
	//キー入力されたときの処理
	public State processKeyTyped(String typed) {
		if(typed.equals("ESC")){
			return new TitleState();
		}
		gameData.processKeyTyped(typed);
		if(gameData.getClearFlag()) {
			return new VariousState("CLEAR",gameData);
		}
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		g.drawImage(file.back, 0-backPosX,0, null);
    	g.drawImage(file.back, 1800-backPosX,0, null);
		g.drawString("Time:"+Integer.toString(gameData.getTime()/10),0,50);
		g.drawString("Problem:"+Integer.toString(gameData.getRemainingProblem()),500,50);
		View.drawStringCenter(g,gameData.getInput(),400,200);
		View.drawStringCenter(g,gameData.getProblem(0),200,100);
		View.drawStringCenter(g,gameData.getProblem(1),600,100);
		switch(gameData.getSelectProblem()) {
			case 0:
				g.setColor(Color.red);
				View.drawStringCenter(g,gameData.getProblem(0),200,100);
				break;
			case 1:
				g.setColor(Color.red);
				View.drawStringCenter(g,gameData.getProblem(1),600,100);
				break;
			default:
				break;
		}
		for(int i = 0; i < gameData.getMap().length ; i++) {
			switch(gameData.getMap()[i]) {
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
		}
		g.drawImage(file.mainChar,375 ,(int)(375+Math.abs(Math.sin((animePos*Math.PI)/120))*-100), null);

		

	}

}
