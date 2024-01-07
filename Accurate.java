package observerObservable;

public class Accurate implements ObservingNewsStation{

	private int[] voteNums;
	private String[] voteNames;
	private String legalMessage;
	private Cheating noCheat = new NoCheating();
	private ResultsCalculator calculator;
	
	
	public void update(MyObservable el) {
		this.voteNums = el.getVoteNums();
		this.voteNames = el.getVoteNames();
		this.legalMessage = el.getLegalMessage();
		cheat();
		calculate();
	}
	
	//since i am using the MyObservable interface, i must cheat
	//so, my cheating method calls a cheating object which does nothing (returns the same int array)
	public void cheat() {
		noCheat.setVotes(voteNums, voteNames);
		voteNums = noCheat.getVotes();
	}
	
	//i create a calculator object and calculate both electoral college and popular votes
	public void calculate() {
		this.calculator = new ResultsCalculator(voteNums, voteNames);
		calculator.electoralCollegeCalculate(voteNums);
	}
	
	public StringBuilder displayVotes() {
		
		StringBuilder s = new StringBuilder("Reporting live from AccuNews, the only totally accurate news station.\n"
		+ legalMessage + "\n");
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
