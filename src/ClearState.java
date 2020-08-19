import java.awt.Graphics;

public class ClearState implements State {
	/*
	 * ゲームクリア　State
	 */

	@Override
	//時間経過時の処理
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	//キー入力されたときの処理
	public State processKeyTyped(String typed) {
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
	}

}
