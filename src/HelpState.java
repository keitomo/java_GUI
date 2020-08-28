import java.awt.Graphics;

public class HelpState implements State {
	/*
	 * ヘルプ　State
	 */

	private GameFiles file = GameFiles.getInstance();
	private int page = 1;
	
	@Override
	//時間経過時の処理
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	//キー入力されたときの処理
	public State processKeyTyped(String typed) {
		if(typed.equals(" "))
			return new TitleState();
		else if(typed.equals("RIGHT") && page < 4) {
			page++;
		}else if(typed.equals("LEFT") && page > 1) {
			page--;
		}
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		g.drawImage(file.back,-700,0, null);
		g.drawString("遊び方", 340, 100);
		g.drawString(Integer.toString(page)+"/4",370, 500);
		g.drawString("スペースキーで戻る", 400, 550);
		switch(page) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				break;
		}

	}

}
