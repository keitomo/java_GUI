import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Score {
	private String date;
	private int scoreSec;
	
	public Score(int score,String date) {
		this.date = date;
		this.scoreSec = score;
	}

	public String getDate() {
		return date;
	}

	public static String getNowDate() {
		Calendar cl = Calendar.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		return sdf.format(cl.getTime());
	}

	public int getScore() {
		return scoreSec;
	}	
}
