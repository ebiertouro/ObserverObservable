package observerObservable;

public class PopDemCheating implements Cheating{
	
	private int[] voteNums;
	private String[] voteNames;
	
	public void setVotes(int[] nu, String[] na) {
		this.voteNums = nu;
		this.voteNames = na;
		cheat();
	}
	
	public void cheat() {
		int biggest = 0;
		int index = 0;
		//There is a democrat favoring strategy that simply ignores the state with the most republican votes
		// we check if the value represents republican votes, then loop through those to find the largest
		//that largest is returned as zero
		for(int i = 0; i < voteNums.length; i++) {
			if(voteNames[i].charAt(2) == 'r') {
				if(voteNums[i] > biggest) {
					biggest = voteNums[i];
					index = i;
				}
			}
		}
		voteNums[index] = 0;
		
	}
	
	public int[] getVotes() {
		return voteNums;
	}


}
