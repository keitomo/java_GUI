import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.Test;

public class ModelTest {

    @Test
    public void モデルの管理する時刻データが時間経過とともに増えるかテスト() {
       Model model = new Model(null);
       int time = model.getTime();
       model.processTimeElapsed();
       assertEquals(time + 1, model.getTime());

        // その他のイベントでは増えない
       model.processKeyTyped("a");
       assertEquals(time + 1, model.getTime());
       assertNotEquals(time + 2, model.getTime());
    }

    @Test
    public void モデルがタイプされた文字を保持しているかテスト() {
       Model model = new Model(null);
       View view = model.getView();
       Controller controller = model.getController();
       // View オブジェクトが存在すること
       assertNotEquals(view, null);
       // Controler オブジェクトが存在すること
       assertNotEquals(controller, null);
        
       controller.keyTyped(new KeyEvent(view, 1, 1, 0, KeyEvent.VK_A, 'a'));
       assertEquals("a", model.getTypedChar());
    }
    
    @Test
    public void ボスが来た画面への遷移() {
    	Game g = new Game();
    	Model model = new Model(g);
    	assertEquals(TitleState.class,model.getState().getClass());
    	model.processKeyTyped("ALT");
    	assertEquals(BossState.class,model.getState().getClass());
    }
    
    
    public void 各画面への遷移() {
    	Game g = new Game();
    	Model model = new Model(g);
    	assertEquals(TitleState.class,model.getState().getClass());
    	model.processKeyTyped("ALT");
    	assertEquals(BossState.class,model.getState().getClass());
    	model.processKeyTyped("ESC");
    	assertEquals(TitleState.class,model.getState().getClass());
    	model.processKeyTyped("ENTER");
    	assertEquals(GameState.class,model.getState().getClass());
    	model.processKeyTyped("ESC");
    	assertEquals(TitleState.class,model.getState().getClass());
    	model.processKeyTyped("DOWN");
    	model.processKeyTyped("ENTER");
    	assertEquals(VariousState.class,model.getState().getClass());
    	model.processKeyTyped(" ");
    	assertEquals(TitleState.class,model.getState().getClass());
    }
    
    

}
