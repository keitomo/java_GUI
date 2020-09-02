import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreList {
	/*
	 * ゲームのスコアのリストクラス
	 * Singleton　パターンを利用
	 */
	private ArrayList<String> scoreString ;
	private ArrayList<Score> scoreDataList ;
	private static ScoreList scoreListInstance;
	
	private ScoreList() {
		scoreString = new ArrayList<>();
		scoreDataList = new ArrayList<>();
	}	
	
	public static ScoreList getInstance() {
		if (scoreListInstance == null)
			scoreListInstance = new ScoreList();
		return scoreListInstance;
	}
	
	private void loadScore() {
		scoreString = new ArrayList<>();
		scoreDataList = new ArrayList<>();
		URL url = getClass().getResource("text/score.txt");
		File file = new File(url.getPath());
	    try(BufferedReader brScore = new BufferedReader(new FileReader(file));){    	  
	        String str;
	        while((str = brScore.readLine()) != null){
	        	scoreString.add(str);
	        }
	    	}catch(FileNotFoundException e){
	    		System.out.println("ファイルを見つけることができませんでした");
	    		System.exit(0);
	    	}catch(IOException e){
	    		System.out.println("ファイルを読み込みできませんでした");
	    		System.exit(0);
	    	}		
			for(int i=0;i<scoreString.size();i++) {
				String temp = scoreString.get(i);
				scoreDataList.add(new Score( Integer.parseInt(temp.split(" ")[0]),temp.split(" ")[1]));
			}	    
		}
	
	
	public void writeScore(Score s) {
		URL url = getClass().getResource("text/score.txt");
		File file = new File(url.getPath());
	    try(BufferedWriter bwScore = new BufferedWriter(new FileWriter(file,true));){
	    	bwScore.write(""+s.getScore()+" "+s.getDate());
	       bwScore.newLine();
	    	}catch(FileNotFoundException e){
	    		System.out.println("ファイルを見つけることができませんでした");
	    		System.exit(0);
	    	}catch(IOException e){
	    		System.out.println("ファイルに書き込みできませんでした");
	    		System.exit(0);
	    	}
	}

	
	private void sortScoreList() {
		Collections.sort(scoreDataList, new ScoreComparator());
	}
	
	public Score getScore(int rank) {
		loadScore();
		sortScoreList();
		try {
			return scoreDataList.get(rank);
		}catch(IndexOutOfBoundsException e) {
			return new Score(0,"00/00/00");
		}
	}
		
}
