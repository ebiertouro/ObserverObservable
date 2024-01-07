package observerObservable;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class MyObservable{
	
	private int[] voteNums;
	private String[] voteNames;
	private ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

	
	public MyObservable(int[] vNum, String[] vName) {
		this.voteNums = vNum;
		this.voteNames = vName;
	}
	
	//a constructor for the opposite order
	public MyObservable(String[] vName, int[] vNum) {
		this.voteNums = vNum;
		this.voteNames = vName;
		
	}
	
	public void setVotes(int[] vNum) {
		this.voteNums = vNum;
	}

	public void addObserver(MyObserver o) {
		observers.add(o);
		
	}
	
	public void deleteObserver(MyObserver o) {
		observers.remove(o);
	}

	//this uses push - forcing all observers to get new info
	public void notifyObservers() {
		for(MyObserver o: observers) {
			o.update(this);
			
		}
	}
	
	public int[] getVoteNums() {
		int[] v = new int[voteNums.length];
		for(int i = 0; i < voteNums.length; i++) {
			v[i] = voteNums[i];
		}
		return v;
	}
	
	public String[] getVoteNames() {
		return voteNames;
	}
	
	public void setVoteNums(int[] newVotes) {
		voteNums = newVotes;
	}
	
	public String getLegalMessage() {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();    
		String s = "Current time: " + dtf.format(now) +
		   "\nAll reports are purely observational and not legally binding in any way.";
		return s;
	}
	
	public StringBuilder displayVotes() {
		StringBuilder s = new StringBuilder("");
		for (int i = 0; i < voteNames.length; i++) {
			if(i %3 == 0) {
				s.append("\n");
			}
			s.append(voteNames[i] + ": " + voteNums[i] + " ");
		}
		return s;
	}
}
