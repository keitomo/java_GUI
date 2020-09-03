import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreComparatorTest {

	@Test
	public void testCompare() {
		ScoreComparator sc= new ScoreComparator();
		Score s1 = new Score(10,"");
		Score s2 = new Score(20,"");
		Score s3 = new Score(30,"");
		Score s4 = new Score(40,"");
		assertEquals(-1,sc.compare(s1, s2));
		assertEquals(1,sc.compare(s4, s3));
		assertEquals(0,sc.compare(s2, s2));
	}

}
