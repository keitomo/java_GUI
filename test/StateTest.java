import static org.junit.Assert.*;

import java.awt.Graphics;

import org.junit.Test;

public class StateTest {

	@Test
	public final void タイトル画面のテスト() {
		State title = new TitleState();
		State next = null;
		for(int i = 0; i<4;i++) {
			title.processKeyTyped("DOWN");
		}
		for(int i = 0; i<4;i++) {
			title.processKeyTyped("UP");
		}
		next = title.processTimeElapsed();
		assertEquals(next,title);
		next = title.processKeyTyped("ENTER");
		assertEquals(GameState.class,next.getClass());
		title.processKeyTyped("DOWN");
		next = title.processKeyTyped("ENTER");
		assertEquals(VariousState.class,next.getClass());
		title.processKeyTyped("DOWN");
		next = title.processKeyTyped("ENTER");
		assertEquals(VariousState.class,next.getClass());
	}
	
	@Test
	public final void 様々な画面のテスト() {
		State various = new VariousState("RANKING",null);
		State next = various.processKeyTyped(" ");
		assertEquals(TitleState.class,next.getClass());
		next = various.processTimeElapsed();
		assertEquals(next,various);
		next = various.processKeyTyped("A");
		assertEquals(next,various);
	}
	
	@Test
	public final void ボスが来た画面のテスト() {
		Game g = new Game();
		State before = new TitleState();
		State boss = new BossState(before,g);
		State next = boss.processKeyTyped("ESC");
		assertEquals(before,next);
		next = boss.processTimeElapsed();
		assertEquals(next,boss);
		next = boss.processKeyTyped("A");
		assertEquals(next,boss);
	}
	
	@Test
	public final void ゲーム画面のテスト() {
		State game = new GameState();
		State next = game.processKeyTyped("ESC");
		assertEquals(TitleState.class,next.getClass());
		next = game.processTimeElapsed();
		assertEquals(next,game);
		next = game.processKeyTyped("a");
		assertEquals(next,game);
	}
	
	
}
