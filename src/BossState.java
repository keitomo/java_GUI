import java.awt.Graphics;

public class BossState implements State {
	
	private GameFiles file = GameFiles.getInstance();
	private State before_state;
	private Game game;
	
	public BossState(State s,Game game){
		this.before_state=s;
		this.game = game;
	}

	@Override
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		if(typed.equals("RIGHT")) {
			game.resetWindow();
			return before_state;
		}
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(file.bossImg,0,0, null);
	}

}
