import java.io.IOException;

public class TypingGame {
	/*
	 * ゲームの主要処理をするクラス
	 */
	
	public static final int HOLE = 0;
	public static final int WOOD = 1;
	public static final int GOAL = 2;
	
	private RandomNumGen random;
	private static final int MAP=10;
	private Text text;
	String input = "";
	String problem[] = {"",""};
	private int inputNum=0;
	private int checkNum=0;
	private int matchNum=0;
	private int remainingProblemNum=0;
	private int time=0;
	private int timeLimit=3000;
	private int selectProblem = -1;
	private int problemCount=0;
	private boolean clearFlag=false;
	private boolean problemFlag=false;
	private int map[];

	public TypingGame() {
		text = new Text();
		try {
			text.setWordList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map = new int[MAP+1];
		random = RandomNumGen.getInstance();
		setGame();
	}

	private void setGame() {
		remainingProblemNum=MAP;
		setMap();
		setProblem(0);
		setProblem(1);
	}
	
	private void setMap() {
		map[0]=WOOD;
		for(int i=1;i<MAP;i++) {
			map[i]=WOOD;
			int value=random.nextInt(100);
			if(value<30)
				map[i]=HOLE;
			if(map[i-1]==HOLE && map[i] == HOLE) {
				map[i] = WOOD;
			}
		}
		map[MAP]=GOAL;
	}
	
	private void setProblem(int i) {
		do{
			problem[i] = text.getRandomWord();
		}while(problem[0].equals(problem[1]));
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
	
	public int getRemainingProblem() {
		return remainingProblemNum;
	}
	
	public int getNowPos() {
		return MAP-remainingProblemNum;
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
	
	public double getScore() {
		double missType = (double)inputNum - (double)matchNum;
		double score = (((double)inputNum-missType)/(double)inputNum)*100;
		return ((double)Math.round(score * 10))/10;
	}
	
	public boolean getClearFlag() {
		return clearFlag;
	}
	
	public boolean getProblemFlag() {
		return problemFlag;
	}
	
	public void resetProblemFlag() {
		problemFlag = false;
	}
	
	public int[] getMap() {
		return map;
	}

	
	public void processingGame(String event) {
		if (event.equals("TIME_ELAPSED")) {
			time++;
			timeLimit--;
		}else {
			inputNum++;
			switch(selectProblem) {
				case -1:
					if(Text.checkText(problem[0],problem[1].charAt(checkNum),checkNum)&&
						Text.checkText(problem[0], event.charAt(0), checkNum)) {	
						selectProblem = -1;
					}else if(Text.checkText(problem[0], event.charAt(0), checkNum)) {
						selectProblem = 0;
					}else if(Text.checkText(problem[1], event.charAt(0), checkNum)) {
						selectProblem = 1;
					}else {
						break;
					}
					checkNum++;
					matchNum++;
					input += event;
					for(int i=0;i<problem.length;i++) {
						if(Text.matchText(problem[i],input)) {
							selectProblem=i;
						}
					}	
					break;
				default:
					if(Text.matchText(problem[selectProblem],input) && event.equals("ENTER") && remainingProblemNum!=0) {
						setProblem(selectProblem);
						int checkMap = MAP-remainingProblemNum+selectProblem+1;
						if(checkMap >= 0 && checkMap <= MAP && 	map[checkMap]!=HOLE) {
							remainingProblemNum-=selectProblem+1;
							problemFlag = true;
						}
						checkNum=0;
						input = "";
						selectProblem=-1;
						problemCount+=1;
					}
					else if(checkNum<problem[selectProblem].length()&&Text.checkText(problem[selectProblem], event.charAt(0), checkNum)) {
						checkNum++;
						matchNum++;
						input += event;
					}if(remainingProblemNum<=0) {
						clearFlag=true;
					}
					break;
				}
						
		}
	}
}
