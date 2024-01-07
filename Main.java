package observerObservable;

public class Main {
	public static void main(String[] args) {
		
		/*instantiate two arrays which will be 
		 * used side- by -side - amounts for votes and their labels
		 * e.g. New York republican votes = 3000
		 * the votes will be reset, but not the labels
		*/
		int[] voteNums = {3000, 8000, 28, 
			6000, 4000, 21,
			11000, 1500, 35,
			2000, 7000, 12,
			1000, 10000, 31};
		
		String[] voteNames = {"NYr", "NYd", "NYe", 
				"NJr", "NJd", "NJe",
				"Flr", "FLdem", "FLe",
				"MAr", "MAd", "MAe",
				"CAr", "CAd", "CAe"};
		
		//instantiate one Observable
		MyObservable e = new MyObservable(voteNums, voteNames);
		
		//instantiate five observers
		Accurate a = new Accurate();
		ProReps r = new ProReps();
		ProDems d = new ProDems();
		ECAccurateDemPop dp = new ECAccurateDemPop();
		RepECDemPop rec = new RepECDemPop();
		
		//ObservingNewsStation is an extension of the MyObserver interface
		ObservingNewsStation[] observers = {a, dp, d, r, rec};
		
		//add them to the Observer
		for(ObservingNewsStation o : observers) {
			e.addObserver(o);
		}
		
		//notify all observers
		e.notifyObservers();
		
		//print results
		for(ObservingNewsStation o : observers) {
			System.out.println("********************************************************************\n" 
		+ o.displayVotes() + "********************************************************************\n");
		}
		
		//change the votes and repeat
		int[] changedVotes = {2000, 9000, 28, 
			5000, 5000, 21,
			12000, 1500, 35,
			2000, 3000, 12,
			3500, 6000, 31};
			
		e.setVotes(changedVotes);
		
		e.notifyObservers();
		
		for(ObservingNewsStation o : observers) {
			System.out.println("********************************************************************\n" 
		+ o.displayVotes() + "********************************************************************\n");
		}
		
		int[] changedVotesAgain = {8700, 3900, 28, 
			9500, 9400, 21,
			6990, 1800, 35,
			1000, 15000, 12,
			78000, 4300, 31};
			
		e.setVotes(changedVotesAgain);
		
		e.notifyObservers();
		
		for(ObservingNewsStation o : observers) {
			System.out.println("********************************************************************\n" 
		+ o.displayVotes() + "********************************************************************\n");
		}
	
	}
	
	

}
