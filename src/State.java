import java.awt.Graphics;

public interface State{
	public static final int TITLE = 0;
	public static final int GAME = 1;
	public static final int RANKING = 2;
	public static final int HELP = 3;
	public static final int CLEAR = 4;
	public static final int BOSS = 5;
	public State processTimeElapsed(int msec);
	public State processKeyTyped(String typed);
	public void paintComponent(Graphics g);
}
