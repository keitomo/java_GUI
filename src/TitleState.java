import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitleState implements State {
	/*
	 * タイトル　State
	 */
	
	private int nextState = 1; //次のState番号

	@Override
	//時間経過時の処理
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	//キー入力されたときの処理
	public State processKeyTyped(String typed) {
		if(typed.equals("UP")) {
			nextState--;
			if(nextState == 0)
				nextState = 4;
		}
		else if(typed.equals("DOWN")) {
			nextState++;
			if(nextState == 5)
				nextState = State.GAME;
		}else if(typed.equals("ENTER")) {
			switch(nextState) {
				case State.GAME:
					return new GameState();
				case State.RANKING:
					return new RankingState();
				case State.HELP:
					return new HelpState();
				case 4:
					System.exit(0);
				default:
					break;			
			}
		}
		return this;
	}

	@Override
	//画面描画処理
	public void paintComponent(Graphics g) {
		g.drawImage(file.back,-500,0, null);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		View.drawStringCenter(g, "TypingJump",400, 100);
		View.drawStringCenter(g, "=Press Enter=",400, 150);
		View.drawStringCenter(g, "Start", 400,250);
		View.drawStringCenter(g, "Ranking", 400,325);
		View.drawStringCenter(g, "Help", 400,400);
		View.drawStringCenter(g, "Exit", 400,475);
		switch(nextState) {
			case State.GAME:
				g.setColor(Color.red);
				View.drawStringCenter(g, "Start",400, 250);
				break;
			case State.RANKING:
				g.setColor(Color.red);
				View.drawStringCenter(g, "Ranking",400, 325);
				break;
			case State.HELP:
				g.setColor(Color.red);
				View.drawStringCenter(g, "Help",400, 400);
				break;
			case 4:
				g.setColor(Color.red);
				View.drawStringCenter(g, "Exit",400, 475);
				break;
			default:
				break;
		}
	}

}
