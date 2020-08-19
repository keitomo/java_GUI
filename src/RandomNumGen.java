import java.util.Random;

public class RandomNumGen extends Random{
	/**
	 *  乱数を生成する関数
	 *  Singleton　パターンを利用
	 */
	private static final long serialVersionUID = 1L;
	private static RandomNumGen generator;
	private RandomNumGen() {}
	
	public static RandomNumGen getInstance() {
		if(generator == null)
			generator = new RandomNumGen();
		return generator;
	}

}
