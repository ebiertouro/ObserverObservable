package observerObservable;

public class ECRepCheating implements Cheating {
	
	private int[] voteNums;
	private String[] voteNames;
	private ResultsCalculator fudgeEC;
	
	public void setVotes(int[] nu, String[] na) {
		this.voteNums = nu;
		this.voteNames = na;
		fudgeEC = new ResultsCalculator(voteNums, voteNames);
		cheat();
		cheat2();
	}
	
	public void cheat() {
		/* There is a republican favoring strategy that assumes one of the states 
		 * (always the same one, this should
	be hardcoded in) will go republican regardless of the polling data
		 */
		
		//here we are not worried about deep copies because we are merely setting into
		//a string array, not affecting the vote data
		
		//we check if the value represents FLORIDA electoral college
		//if so, we set it as republican
		for(int i = 0; i < voteNums.length; i++) {
			if(voteNames[i].charAt(0) == 'F' && voteNames[i].charAt(2) == 'e') {
				int k = (i - 2) / 3;
				fudgeEC.setECresults(k, "Republican");
			}
		}
		}
	
	
	public void cheat2() {
		/*there is a second republican favoring strategy that considers the state where the democrat has the
smallest lead to be "too close to call" and splits the electoral votes giving half to the democrat and half to
the republican (and in the event of an odd number it gives the extra vote to the republican of course)
		 */
		
		//here we are not worried about deep copies because we are merely affecting the EC 
		//data, not the original vote data
		
		int lead = 0;
		int smallest = 100000;
		int index = 0;
		
		//we check if a value represents democrat votes. if so, we 
		//loop through to find the smallest and split those electoral votes
		for(int i = 0; i < voteNums.length; i++) {
			if(voteNames[i].charAt(2) == 'd') {
				lead = voteNums[i] - voteNums[i - 1];
				if(lead < smallest) {
					smallest = lead;
					//the value after the dem votes is the ec votes, which we want to split
					index = i + 1;
				}
			}
		}
		boolean extra = fudgeEC.splitEC(index);
		if(extra) {
			fudgeEC.addToRepifECExtra();
		}
		
	}
	
	public int[] getVotes() {
		return voteNums;
	}


}
