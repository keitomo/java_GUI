public class TypingGame {
	/*
	 * ゲームの主要処理をするクラス
	 */
	
	public static final int HOLE = 0;
	public static final int WOOD = 1;
	public static final int GOAL = 2;
	
	private RandomNumGen random;
	private static final int MAP=10; //MAPの長さを表す
	private Text text;
	String input = ""; //入力された文字列を保持
	String[] problem = {"",""}; //問題を保持
	private int inputNum=0; //入力された回数
	private int checkNum=0; //何文字目をチェックするか
	private int matchNum=0; //正解タイプ率
	private int remainingStepNum=0; //ゴールまであと何マスかを表す
	private int time=0; //経過時間を表す
	private int timeLimit=3000;
	private int selectProblem = -1; //選択してる問題を表す
	private int problemCount=0; //解いた問題数
	private boolean problemFlag=false; //問題を解いたときにたつフラグ
	private int[] stageMap; //マップを保持する配列

	public TypingGame() { //コンストラクタ
		text = new Text();
		stageMap = new int[MAP+1]; //マップをマス目+ゴールで初期化
		random = RandomNumGen.getInstance();
		setGame(); //ゲームをセット
	}

	private void setGame() {
		remainingStepNum=MAP; //ゴールまでのマス目で初期化
		setMap(); //マップをセット
		setProblem(0); //問題0をセット
		setProblem(1); //問題1をセット
	}
	
	private void setMap() { //マップを初期化
		stageMap[0]=WOOD; //最初のマスは絶対に木
		for(int i=1;i<MAP;i++) {
			stageMap[i]=WOOD;
			int value=random.nextInt(100);
			if(value<30)
				stageMap[i]=HOLE; //30％の確率で穴になる
			if(stageMap[i-1]==HOLE && stageMap[i] == HOLE) {//穴が2マス連続で繋がらないようにする
				stageMap[i] = WOOD;
			}
		}
		stageMap[MAP]=GOAL; //最後のマスは絶対にゴール
	}
	
	private void setProblem(int i) { //i番目に問題をセットする
		do{
			problem[i] = text.getRandomWord();
		}while(problem[0].equals(problem[1])); //問題がかぶってたら
	}
		
	public int getTime() {
		return time;
	}
	
	public int getTimeLimit() {
		return timeLimit;
	}
		
	public String getProblem(int i) {
		return problem[i];
	}
	
	public int getRemainingStep() {
		return remainingStepNum;
	}
	
	public int getNowPos() {
		return MAP-remainingStepNum;
	}

	public String getInput() {
		return input;
	}
	
	public int getInputNum() {
		return inputNum;
	}

	public int getMatchNum() {
		return matchNum;
	}
	
	public int getSelectProblem() {
		return selectProblem;
	}
	
	public int getProblemCount() {
		return problemCount;
	}
	
	public boolean getProblemFlag() {
		return problemFlag;
	}
	
	public void resetProblemFlag() {
		problemFlag = false;
	}
	
	public int[] getMap() {
		return stageMap;
	}
	
	private int checkSelect(String typed) { //どっちの問題を選択したか調べる
		if(checkNum<Text.duplicationText(problem[0], problem[1])) {
			return -1;
		}else if(Text.checkText(problem[0], typed.charAt(0), checkNum)) {
			return 0;
		}else if(Text.checkText(problem[1], typed.charAt(0), checkNum)) {
			return 1;
		}
		return -1;
	}
	
	public void processKeyTyped(String typed) {
		inputNum++;
		switch(selectProblem) {
		case -1:
			selectProblem = checkSelect(typed);	
			if(Text.checkText(problem[0], typed.charAt(0), checkNum) ||Text.checkText(problem[1], typed.charAt(0), checkNum)) {
				checkNum++;
				matchNum++;
				input += typed;
			}
			break;
		case 0 :
		case 1 :
			if(Text.matchText(problem[selectProblem],input) && typed.equals("ENTER") && remainingStepNum!=0) {
				setProblem(selectProblem);
				int checkMap = MAP-remainingStepNum+selectProblem+1;
				try {
					if(stageMap[checkMap]!=HOLE) {
						remainingStepNum-=selectProblem+1;
						problemFlag = true;
					}else if(stageMap[checkMap]==GOAL) {
						remainingStepNum=0;
						problemFlag = true;
					}
				}catch(ArrayIndexOutOfBoundsException e) {
					problemFlag=false;
				}
				checkNum=0;
				input = "";
				selectProblem=-1;
				problemCount+=1;
			}else if(Text.checkText(problem[selectProblem], typed.charAt(0), checkNum)) {
				checkNum++;
				matchNum++;
				input += typed;
			}
			break;
		default:
			break;
		}
	}

	public void processTimeElapsed() {
		time++;
		timeLimit--;
	}
	
}

