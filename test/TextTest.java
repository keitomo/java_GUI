import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class TextTest {

	@Test
	public void 指定箇所と文字が一致しているならtrue() {
		assertEquals(true,Text.checkText("abcd",'a',0));
		assertEquals(true,Text.checkText("abcd",'b',1));
		assertEquals(true,Text.checkText("abcd",'c',2));
		assertEquals(true,Text.checkText("abcd",'d',3));
	}
	
	@Test
	public void 指定箇所と文字が一致していないならfalse() {
		assertEquals(false,Text.checkText("abcd",'b',0));
		assertEquals(false,Text.checkText("abcd",'c',1));
		assertEquals(false,Text.checkText("abcd",'d',2));
		assertEquals(false,Text.checkText("abcd",'a',3));
	}
	
	@Test
	public void 文字列が一致しているならtrue() {
		assertEquals(true,Text.matchText("abcd","abcd"));
	}
	
	@Test
	public void 文字列が一致していないならfalse() {
		assertEquals(false,Text.matchText("abcd","efgh"));
	}
	
	
	@Test
	public void 文字列をローマ字に変換する() {
		Text text = new Text();
		assertEquals("?!?!?!?",text.kana2rome("?!?!?!?"));
		assertEquals("konnpyu-ta",text.kana2rome("こんぴゅーた"));
		assertEquals("firumu",text.kana2rome("ふぃるむ"));
		assertEquals("teiramisu",text.kana2rome("てぃらみす"));
		assertEquals("kurikku",text.kana2rome("くりっく"));
	}
	
	@Test
	public void ファイルがあるかどうか検知する() {
		Text text = new Text();
		try {
			text.setWordList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		text.getRandomWord();	
	}

}
