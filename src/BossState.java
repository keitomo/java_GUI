import java.awt.Graphics;

public class BossState implements State {
	/*
	 * ボスが来た　State
	 */

	//ボスが来たに遷移してくる前のStateを保持する変数
	private State beforState;
	//画面をボスが来た仕様にするためにgameを保持する
	private Game game;
	
	public BossState(State s,Game game){
		this.beforState=s;
		this.game = game;
	}

	@Override
	//時間経過時の処理
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	//キー入力されたときの処理
	public State processKeyTyped(String typed) {
		if(typed.equals("ESC")) {
			game.resetWindow();
			return beforState;
		}
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		g.drawImage(file.bossImg,0,0,1280,720, null);
	}

}
