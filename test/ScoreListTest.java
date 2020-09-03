import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ScoreListTest {

	@Test
	public final void スコアを取得してくる() {
		ScoreList sL = ScoreList.getInstance();
		assertEquals(Score.class,sL.getScore(0).getClass());
		Score s = new Score(0,"00/00/00");
		assertEquals(s.getScore(),sL.getScore(9999).getScore());
		assertEquals(s.getDate(),sL.getScore(9999).getDate());
	}
	
	@Test
	public final void 問題をとき終わったあとにスコアを書き込む() {
		TypingGame tg = new TypingGame();
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
			tg.processKeyTyped("ENTER");
		}
		new VariousState("CLEAR",tg);
		ScoreList sL = ScoreList.getInstance();
		assertEquals(tg.getTime(),sL.getScore(0).getScore());
	}

}
