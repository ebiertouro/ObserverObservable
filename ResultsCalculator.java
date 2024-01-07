package observerObservable;

public class ResultsCalculator {
	
	private int[] voteNums;
	private String[] voteNames;
	private String[] ecNames = new String[5];
	private int[] ecNums = new int[5];
	private int demVotes, repVotes;
	private String winner;
	private int i; //for loop index
	private int demEC = 0, repEC = 0;
	private String totalECName;
	
	public ResultsCalculator(int[] vNum, String[] vName) {
		this.voteNums = vNum;
		this.voteNames = vName;
	}
	
	public void electoralCollegeCalculate(int[] vNum) {
		
		/*
		 * we find which values represent EC votes, and record how many EC in an array. 
		 * then, we index backwards to find
		 * the rep and dem amounts for that state. these values are compared
		 * and the larger is recorded in an array storing the EC choice (rep or dem)
		 * we index in to that array by subtracting 2 and dividing by 3 to get the accurate index
		 */
		
		for(i = 0; i < voteNums.length; i++) {
			if(voteNames[i].charAt(2) == 'e') {
				ecNums[(i - 2) / 3] = voteNums[i];
				if(voteNums[i - 2] > voteNums[i - 1]) {
					ecNames[(i - 2) / 3] = "Republican";
				}
				else if(voteNums[i - 2] < voteNums[i - 1]) {
					ecNames[(i - 2) / 3] = "Democrat";
				}
				else {
					ecNames[(i - 2) / 3] = "Tied";
				}}}
		
		//we then loop through that array to find the total of how many EC votes go to rep 
		//and how many to dem
		
		for(i = 0; i < ecNums.length; i++) {
			if(ecNames[i].charAt(0) == 'R') {
				repEC += ecNums[i];
			}
			else if (ecNames[i].charAt(0) == 'D') {
				demEC += ecNums[i];
			}
			//if tied, split in half - this is a separate method so that the cheating method can call it
			else {
				splitEC(ecNums[i]);
			}	
		}
		
		//check if dem or rep got more total votes, and record the winner
		if(demEC > repEC)
			totalECName = "the Democrats";
		else if(repEC > demEC)
			totalECName = "the Republicans";
		else
			totalECName = "be tied";
	}
	
	public boolean splitEC(int i) {
		
		//if a state has even votes for rep and dem, we have to split the EC votes
		
		int thisStateEC = i;
		boolean extra = false;
		//check if splits evenly
		
		if(thisStateEC % 2 != 2) {
			thisStateEC -= 1;
			extra = true;
		}
		//if it is not even, the calling class can either add the one odd vote to Dem or Rep
		
		//increment each total by half of the state
		demEC += thisStateEC/2;
		repEC += thisStateEC/2;
		return extra;
	}
	
	public void addToDemifECExtra() {
		demEC++;
	}
	
	public void addToRepifECExtra() {
		repEC++;
	}
	
	public String[] getECresults() {
		return ecNames;
	}
	
	public void setECresults(int i, String favored) {
		
		//change a string value in the EC array
		
		ecNames[i] = favored;
	}
	
	public int getECdem() {
		return demEC;
	}
	
	public int getECrep() {
		return repEC;
	}
	
	public String getECtotal() {
		return totalECName;
	}
	
	public int repVotesCalculator() {
		
		//check if a value represents republican votes. if so, increment it
		
		for(i = 0; i < voteNums.length; i++) {
			if(voteNames[i].charAt(2) == 'r') {
				repVotes += voteNums[i];
			}
		}
		return repVotes;
	}
	
	public int demVotesCalculator() {
		
		//check if a value represents democrat votes. if so, increment it
		
		for(i = 0; i < voteNums.length; i++) {
			if(voteNames[i].charAt(2) == 'd') {
				demVotes += voteNums[i];
			}
		}
		return demVotes;
	}
	
	public String getPopWinner() {
		
		//check who has more popular votes - democrats or republicans
		
		if(demVotes > repVotes) {
			winner = "the Democrats";
		}
		else if (demVotes < repVotes) {
			winner = "the Republicans";
		}
		else
			winner = "be tied";
		return winner;
	}
}
