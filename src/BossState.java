import java.awt.Graphics;

public class BossState implements State {
	
	private GameFiles file = GameFiles.getInstance();
	private State before_state;
	
	public BossState(State s){
		this.before_state=s;
	}

	@Override
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		if(typed.equals("RIGHT")) {
			return before_state;
		}
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(file.bossImg,0,0, null);
	}

}
