import java.awt.Graphics;

public interface State{
	public final static int Title = 0;
	public final static int Game = 1;
	public final static int Ranking = 2;
	public final static int Help = 3;
	public final static int Clear = 4;
	public final static int Boss = 5;
	public State processTimeElapsed(int msec);
	public State processKeyTyped(String typed);
	public State processMousePressed(int x,int y);
	public void paintComponent(Graphics g);
}
