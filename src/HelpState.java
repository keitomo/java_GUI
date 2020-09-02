import java.awt.Graphics;

public class HelpState implements State {
	/*
	 * ヘルプ　State
	 */
	private GameFiles file = GameFiles.getInstance();
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
		g.drawImage(file.back,-700,0, null);
		View.drawStringCenter(g, "遊び方", 400, 100);
		g.drawString("スペースキーで戻る", 400, 550);

	}

}
