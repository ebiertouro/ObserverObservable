package observerObservable;

//ObservingNewsStation extends MyObservable
//see ObservingNewsStation for more comments

public class ECAccurateDemPop implements ObservingNewsStation {
	
	private int[] voteNums;
	private String[] voteNames;
	private String legalMessage;
	private Cheating cheatpop = new PopDemCheating();
	private ResultsCalculator calculator;
	private int[] fudgeNums;
	
	
	public void update(MyObservable el) {
		this.voteNums = el.getVoteNums();
		this.voteNames = el.getVoteNames();
		this.legalMessage = el.getLegalMessage();
		cheat();
		calculate();
	}
	
	public void cheat() {
		// we have to create a deep copy of the vote count, because we are
		// using an accurate version for the EC and a fudged version for the pop. vote
	//and we don't want to contaminate the data 
		this.fudgeNums = new int[voteNums.length];
		for(int i = 0; i < voteNums.length; i++) {
			fudgeNums[i] = voteNums[i];
		}
		//so we only cheat with the deep copy
		cheatpop.setVotes(fudgeNums, voteNames);
		fudgeNums = cheatpop.getVotes();
	}
	
	public void calculate() {
		//we calculate pop with accurate data (original)
		this.calculator = new ResultsCalculator(voteNums, voteNames);
		//we calculate EC with fudged data (deep copy)
		calculator.electoralCollegeCalculate(fudgeNums);
	}
	
	public StringBuilder displayVotes() {
		
		StringBuilder s = new StringBuilder("Reporting live from Slightly Left, an (almost) unbiased news station.\n" + legalMessage + "\n");
		s.append("The total republican vote is: " + calculator.repVotesCalculator() + ".\n");
		s.append("The total democrat vote is: " + calculator.demVotesCalculator() + ".\n");
		s.append("There are " + calculator.getECrep() + " republican electoral college votes and " 
				+ calculator.getECdem() + " democrat electoral college votes.\n");
		s.append("The popular vote looks like it is going to " + calculator.getPopWinner() 
		+ ".\nThe electoral college vote looks like it is going to " 
				+  calculator.getECtotal() + ".\n");
		return s;
	}

}
