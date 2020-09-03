import java.awt.Image;
import java.awt.Toolkit;

public class GameFiles {
	/*
	 * ゲームで使用するファイルを一括で読み込むクラス
	 * Singleton　パターンを利用
	 */
	
	private static GameFiles gamefiles;
	//背景画像
	public final Image back = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/background.png"));
	
	//主人公
	public final Image mainChar = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/robot.png"));
	
	//help画面用
	public final Image help= Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/help.png"));
	
	//ボスが来たときに使用する背景画像
	public final Image bossImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/boss.png"));
	
	private GameFiles() {}
	
	public static GameFiles getInstance() {
		if (gamefiles == null)
			gamefiles = new GameFiles();
		return gamefiles;
	}
}
