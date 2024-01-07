package observerObservable;

public class PopRepCheating implements Cheating{
	
	private int[] voteNums;
	private String[] voteNames;
	
	public void setVotes(int[] nu, String[] na) {
		this.voteNums = nu;
		this.voteNames = na;
		cheat();
	}
	
	public void cheat() {
		// There is a republican favoring strategy that reports 5% fewer of the democrat votes 
		//we loop through to check if a value represents democrat votes. if so, adjust to 5% less
		for(int i = 0; i < voteNums.length; i++) {
			if(voteNames[i].charAt(2) == 'd') {
				voteNums[i] *= .95;
				}
			}
		}
	
	public int[] getVotes() {
		return voteNums;
	}

}
