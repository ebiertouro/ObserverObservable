package observerObservable;

public class NoCheating implements Cheating{
	
	private int[] voteNums;
	@SuppressWarnings("unused")
	private String[] voteNames;
	
	public void setVotes(int[] nu, String[] na) {
		this.voteNums = nu;
		this.voteNames = na;
		cheat();
	}
	
	//this method does nothing - just to obey the interface
	public void cheat() {
		
	}
	
	//we simply return the original data
	public int[] getVotes() {
		return voteNums;
	}


}
