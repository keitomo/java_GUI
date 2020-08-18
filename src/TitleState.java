import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitleState implements State {
	
	private int NextState = 1;
	private GameFiles file = GameFiles.getInstance();

	@Override
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		if(typed.equals("UP")) {
			NextState--;
			if(NextState == 0)
				NextState = State.HELP;
		}
		else if(typed.equals("DOWN")) {
			NextState++;
			if(NextState == 4)
				NextState = State.GAME;
		}else if(typed.equals("ENTER")) {
			switch(NextState) {
				case State.GAME:
					return new GameState();
				case State.RANKING:
					return new RankingState();
				case State.HELP:
					return new HelpState();
				default:
					break;			
			}
		}
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(file.back,-500,0, null);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.black);
		g.drawString("TypingJump", 275, 100);
		g.drawString("Start", 345, 300);
		g.drawString("Ranking", 315, 400);
		g.drawString("Help", 350, 500);
		switch(NextState) {
		case State.GAME:
			g.setColor(Color.red);
			g.drawString("Start", 345, 300);
			break;
		case State.RANKING:
			g.setColor(Color.red);
			g.drawString("Ranking", 315, 400);
			break;
		case State.HELP:
			g.setColor(Color.red);
			g.drawString("Help", 350, 500);
			break;
		default:
			break;
		}
	}

}
