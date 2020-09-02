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
	private ArrayList<String> ScoreString ;
	private ArrayList<Score> ScoreList ;
	private static ScoreList scoreList;
	
	private ScoreList() {
		ScoreString = new ArrayList<String>();
		ScoreList = new ArrayList<Score>();
	}	
	
	public static ScoreList getInstance() {
		if (scoreList == null)
			scoreList = new ScoreList();
		return scoreList;
	}
	
	private void loadScore() {
		ScoreString = new ArrayList<String>();
		ScoreList = new ArrayList<Score>();
		URL url = getClass().getResource("text/score.txt");
		BufferedReader br = null;
		
	    try{
	    		br = new BufferedReader(new FileReader(new File(url.getPath())));
	    		String str;
	    		while((str = br.readLine()) != null){
	        		ScoreString.add(str);
	    		}
	    	}catch(IOException e){
	    		System.out.println("ファイルに書き込みできませんでした");
	    		System.exit(0);
	    	}finally {
	    		try {
					br.close();
				} catch (IOException e) {
					System.out.println("ファイルに書き込みできませんでした");
		    		System.exit(0);
				}
	    	}
		
			for(int i=0;i<ScoreString.size();i++) {
				String Temp = ScoreString.get(i);
				ScoreList.add(new Score( Integer.parseInt(Temp.split(" ")[0]),Temp.split(" ")[1]));
			}
		
		    
		}
	
	
	public void writeScore(Score s) {
		URL url = getClass().getResource("text/score.txt");
		BufferedWriter bw = null;
	    try{
	    	bw = new BufferedWriter(new FileWriter(new File(url.getPath()),true));
	    	bw.write(""+s.getScore()+" "+s.getDate());
	       bw.newLine();
	    }catch(IOException e){
	    	System.out.println("ファイルを見つけることができませんでした");
	    	System.exit(0);
	    }finally {
	    	try {
				bw.close();
			} catch (IOException e) {
				System.out.println("ファイルに書き込みできませんでした");
		    	System.exit(0);
			}
	    }
	}

	
	private void sortScoreList() {
		Collections.sort(ScoreList, new ScoreComparator());
	}
	
	public Score getScore(int rank) {
		loadScore();
		sortScoreList();
		try {
			return ScoreList.get(rank);
		}catch(IndexOutOfBoundsException e) {
			return new Score(0,"00/00/00");
		}
	}
		
}
