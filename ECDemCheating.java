package observerObservable;

public class ECDemCheating implements Cheating{
	
	private int[] voteNums;
	private String[] voteNames;
	private ResultsCalculator fudgeEC;
	
	public void setVotes(int[] nu, String[] na) {
		this.voteNums = nu;
		this.voteNames = na;
		this.fudgeEC = new ResultsCalculator(voteNums, voteNames);
		cheat();
		cheat2();
	}

	public void cheat() {
		/*
		 * There is a democrat favoring strategy that takes 2% of all republican votes 
		 * in every state and makes them democrat votes instead (note that this only 
		 * affects the electoral votes if there is a state within this margin)
		 */
		
		//we have to make a deep copy so that the data is not contaminated, as we are
		//changing the data of the pop vote but only want to change how the EC is displayed
		//we do NOT want to change how the pop vote is displayed
		
		int[] fudgedVotes = new int[voteNums.length];
		for(int i = 0; i < voteNums.length; i++) {
			fudgedVotes[i] = voteNums[i];
		}
		
		//we check if a value represents republican votes
		for(int i = 0; i < fudgedVotes.length; i++) {
			if(voteNames[i].charAt(2) == 'r') {
				fudgedVotes[i] *= .98;
				fudgedVotes[i+1] *= 1.02;
				}
			}
		fudgeEC.electoralCollegeCalculate(fudgedVotes);
		
		}
	
	public void cheat2() {
		/*There is a democrat favoring strategy that assumes that the 
		 * state with the most electoral votes will go to
		the democrat regardless of polling data
		 */
	
		//here we are not worried about deep copies because we are merely setting into
		//a string array, not affecting the vote data
		
		int biggest = 0;
		int index = 0;
		
		//we check if a value represents electoral votes
		for(int i = 0; i < voteNums.length; i++) {
			if(voteNames[i].charAt(2) == 'e') {
				if(voteNums[i] > biggest) {
					biggest = voteNums[i];
					index = i;
				}
			}
		}
		int k = (index - 2) / 3;
		fudgeEC.setECresults(k, "Democrat");
	}
	public int[] getVotes() {
		return voteNums;
	}
}
