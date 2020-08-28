import java.io.IOException;

public class TypingGame {
	/*
	 * ゲームの主要処理をするクラス（予定）
	 */

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
	
	public TypingGame() {
		text = new Text();
		try {
			text.setWordList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setGame();
	}

	private void setGame() {
		remainingProblemNum=20;
		setProblem(0);
		setProblem(1);
	}
	
	private void setProblem(int i) {
		do {
			problem[i] = text.getRandomWord();
		}while(problem[0]==problem[1]);
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
	
	public double getScore() {
		double missType = (double)inputNum - (double)matchNum;
		double score = (((double)inputNum-missType)/(double)inputNum)*100;
		return ((double)Math.round(score * 10))/10;
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
					break;
				default:
					if(Text.matchText(problem[selectProblem],input) && event.equals("ENTER") && remainingProblemNum!=0) {
						setProblem(selectProblem);
						remainingProblemNum--;
						checkNum=0;
						input = "";
						selectProblem=-1;
					}
					else if(checkNum<problem[selectProblem].length()&&Text.checkText(problem[selectProblem], event.charAt(0), checkNum)) {
						checkNum++;
						matchNum++;
						input += event;
					}
					break;
				}
				

			
		}
	}
}
