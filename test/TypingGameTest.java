import static org.junit.Assert.*;

import org.junit.Test;

public class TypingGameTest {

	@Test
	public final void 問題文が取得できてるか() {
		TypingGame tg = new TypingGame();
		String temp="";
		assertEquals(temp.getClass(),tg.getProblem(0).getClass());
		assertEquals(temp.getClass(),tg.getProblem(1).getClass());
	}

	@Test
	public final void 問題0を選択したとき() {
		TypingGame tg;
		do {
			tg = new TypingGame();
		}while(tg.getMap()[1]==0);
		int firstStep=tg.getRemainingStep();
		String firstProblem = tg.getProblem(0);
		for(int i=0;i<firstProblem.length();i++) {
			tg.processKeyTyped(String.valueOf(firstProblem.charAt(i)));
		}
		assertEquals(firstProblem,tg.getInput());
		assertEquals(0,tg.getSelectProblem());
		tg.processKeyTyped("ENTER");
		assertEquals("",tg.getInput());
		assertEquals(-1,tg.getSelectProblem());
		assertNotEquals(firstStep,tg.getRemainingStep());
		assertNotEquals(firstProblem,tg.getProblem(0));
		assertEquals(true,tg.getProblemFlag());
		tg.resetProblemFlag();
		assertEquals(false,tg.getProblemFlag());
		assertEquals(firstStep-1,tg.getRemainingStep());
		assertEquals(1,tg.getNowPos());
		assertEquals(1,tg.getProblemCount());
	}
	
	public final void 問題1を選択したとき() {
		TypingGame tg;
		do {
			tg = new TypingGame();
		}while(tg.getMap()[2]==0);
		int firstStep=tg.getRemainingStep();
		String firstProblem = tg.getProblem(1);
		for(int i=0;i<firstProblem.length();i++) {
			tg.processKeyTyped(String.valueOf(firstProblem.charAt(i)));
		}
		assertEquals(firstProblem,tg.getInput());
		assertEquals(1,tg.getSelectProblem());
		tg.processKeyTyped("ENTER");
		assertEquals("",tg.getInput());
		assertEquals(-1,tg.getSelectProblem());
		assertNotEquals(firstStep,tg.getRemainingStep());
		assertNotEquals(firstProblem,tg.getProblem(1));
		assertEquals(true,tg.getProblemFlag());
		tg.resetProblemFlag();
		assertEquals(false,tg.getProblemFlag());
		assertEquals(firstStep-1,tg.getRemainingStep());
		assertEquals(2,tg.getNowPos());
		assertEquals(1,tg.getProblemCount());
	}

	@Test
	public final void 入力を受け付けているか() {
		TypingGame tg = new TypingGame();
		String s = String.valueOf(tg.getProblem(0).charAt(0));
		tg.processKeyTyped(s);
		assertEquals(s, tg.getInput());
		tg = new TypingGame();
		for(int i=0;i<10;i++) {
			tg.processKeyTyped("a");
		}
		assertEquals(10,tg.getInputNum());
	}
	
	@Test
	public final void 時間経過を記録できているか() {
		TypingGame tg = new TypingGame();
		int firstTimeLimit=tg.getTimeLimit();
		for(int i=1;i<100;i++) {
			tg.processTimeElapsed();
			assertEquals(i,tg.getTime());
			assertEquals(firstTimeLimit-i,tg.getTimeLimit());
		}
	}
	
	@Test
	public final void 穴に差し掛かったとき() {
		TypingGame tg;
		do {
			tg = new TypingGame();
		}while(tg.getMap()[1]==1);
		int firstStep=tg.getRemainingStep();
		String firstProblem = tg.getProblem(0);
		for(int i=0;i<firstProblem.length();i++) {
			tg.processKeyTyped(String.valueOf(firstProblem.charAt(i)));
		}
		tg.processKeyTyped("ENTER");
		assertEquals(firstStep,tg.getRemainingStep());
		assertNotEquals(firstProblem,tg.getProblem(0));
		assertEquals(false,tg.getProblemFlag());
		assertEquals(0,tg.getNowPos());
		assertEquals(1,tg.getProblemCount());
	}
	
	@Test
	public final void ゴールできるか() {
		TypingGame tg = new TypingGame();
		int count=0;
		while(tg.getRemainingStep()!=0) {
			String p1=tg.getProblem(0);
			String p2=tg.getProblem(1);
			if(tg.getMap()[tg.getNowPos()+1]==0) {
				for(int i=0;i<p2.length();i++) {
					tg.processKeyTyped(String.valueOf(p2.charAt(i)));
				}
			}else {
				for(int i=0;i<p1.length();i++) {
					tg.processKeyTyped(String.valueOf(p1.charAt(i)));
				}
			}
			count++;
			tg.processKeyTyped("ENTER");
		}
		assertEquals(0,tg.getRemainingStep());
		assertEquals(count,tg.getProblemCount());
	}

}
