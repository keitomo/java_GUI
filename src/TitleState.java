import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitleState implements State {
	
	private int NextState = 1;
	GameFiles file = GameFiles.getInstance();
	RandomNumGen r = RandomNumGen.getInstance();
	private final int backx = -1*r.nextInt(1000);

	@Override
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		if(typed.equals("UP")) {
			NextState--;
			if(NextState == 0)
				NextState = State.Help;
		}
		else if(typed.equals("DOWN")) {
			NextState++;
			if(NextState == 4)
				NextState = State.Game;
		}
		return this;
	}

	@Override
	public State processMousePressed(int x, int y) {
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(file.back,backx,0, null);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.black);
		g.drawString("TypingJump", 275, 100);
		g.drawString("Start", 345, 300);
		g.drawString("Ranking", 315, 400);
		g.drawString("Help", 350, 500);
		switch(NextState) {
		case State.Game:
			g.setColor(Color.red);
			g.drawString("Start", 345, 300);
			break;
		case State.Ranking:
			g.setColor(Color.red);
			g.drawString("Ranking", 315, 400);
			break;
		case State.Help:
			g.setColor(Color.red);
			g.drawString("Help", 350, 500);
			break;
		}
		//g.drawString(Integer.toString(NextState), 400, 200);
	}

}
