import java.awt.Color;
import java.awt.Graphics;

public class GameState implements State {
	/*
	 * ゲーム　State
	 */
	private GameFiles file = GameFiles.getInstance();
	private TypingGame gameData;
	private int backPosX = 0; //背景を動かすようの変数
	private int animePosX=0;  //キャラクターを動かすようの変数X
	private int animePosY=0;  //キャラクターを動かすようの変数Y
	private boolean clearFlag = false; //ゲームをクリアしたときに立つフラグ
	private int wait=0; //アニメーションが終わってから少し待つようにするためにつかう変数
	
	GameState() {
		gameData = new TypingGame();
	}

	@Override
	//時間経過時の処理
	public State processTimeElapsed() {
		if(clearFlag) { //クリアフラグが立っていたら
			wait++;
			if(wait>3)
				return new VariousState("CLEAR",gameData);
		}else { //クリアフラグが立っていなかったら
			gameData.processTimeElapsed(); //ゲームに時間を通知
		}
		backPosX+=1;
		if(backPosX==file.back.getWidth(null)) backPosX=0; //画像が端まで行ったらリセット
		if(gameData.getProblemFlag()) { //問題を解いたフラグが立っていたら
			animePosX+=24;
			animePosY+=24;
			if(animePosX==120*gameData.getNowPos()) {
				animePosY=0;
				gameData.resetJump();
				gameData.resetProblemFlag();
				if(gameData.getRemainingStep()==0) //残りのステップ数が0なら
					clearFlag=true; //クリアフラグを立てる
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
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		g.drawImage(file.back, 0-backPosX,0, null);
    	g.drawImage(file.back, 1800-backPosX,0, null);
		g.drawString("Time:"+Integer.toString(gameData.getTime()/10),10,50);
		View.drawStringRight(g,"残り"+String.format("%02d", gameData.getRemainingStep())+"マス",790,50);
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
			g.fillRect(i*120-animePosX+350, 420, 100, 30);
		}
		switch(gameData.getJump()) {
		case 0:
			g.drawImage(file.mainChar,375 ,(int)(375+Math.abs(Math.sin((animePosY*Math.PI)/120))*-100), null);
			break;
		case 1:
			g.drawImage(file.mainChar,375 ,(int)(375+Math.abs(Math.sin((animePosY*Math.PI)/240))*-100), null);
			break;
		default:
			g.drawImage(file.mainChar,375 ,375, null);
			break;
		}

	}

}
