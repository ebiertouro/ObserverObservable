package observerObservable;

public class ProDems implements ObservingNewsStation {
	
	private int[] voteNums;
	private String[] voteNames;
	private String legalMessage;
	private Cheating cheatpop = new PopDemCheating();
	private Cheating cheatec = new ECDemCheating();
	private ResultsCalculator calculator;
	
	
	public void update(MyObservable el) {
		this.voteNums = el.getVoteNums();
		this.voteNames = el.getVoteNames();
		this.legalMessage = el.getLegalMessage();
		cheat();
		calculate();
	}
	
	public void cheat() {
		cheatec.setVotes(voteNums, voteNames);
		cheatpop.setVotes(voteNums, voteNames);
		voteNums = cheatec.getVotes();
		voteNums = cheatpop.getVotes();
	}

	public void calculate() {
		this.calculator = new ResultsCalculator(voteNums, voteNames);
		calculator.electoralCollegeCalculate(voteNums);
	}
	
	public StringBuilder displayVotes() {
		
		StringBuilder s = new StringBuilder("Reporting live from LibsLive, a proudly democrat news station.\n" + legalMessage + "\n");
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
