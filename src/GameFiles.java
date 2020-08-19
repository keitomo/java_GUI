import java.awt.Image;
import java.awt.Toolkit;

public class GameFiles {
	
	private static GameFiles gamefiles;
	public final Image back = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/background.png"));
	public final Image mainChar = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/robot.png"));
	public final Image bossImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/boss.png"));
	
	private GameFiles() {}
	
	public static GameFiles getInstance() {
		if (gamefiles == null)
			gamefiles = new GameFiles();
		return gamefiles;
	}
}
