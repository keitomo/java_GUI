import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class RankingState implements State {
	
	private GameFiles file = GameFiles.getInstance();

	@Override
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		if(typed.equals(" "))
			return new TitleState();
		return this;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(file.back,-500,0, null);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.black);
		g.drawString("開発中", 350, 100);
		g.drawString("ここはランキング画面", 350, 200);
		g.drawString("スペースキーで戻る", 400, 550);
	}

}
