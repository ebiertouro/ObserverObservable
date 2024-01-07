package observerObservable;

public interface ObservingNewsStation extends MyObserver{
	
	//this method uses push - the Observable method notifyObservers forces the observers to update
		void update(MyObservable e);
		
		void cheat();
		
		void calculate();
		
		StringBuilder displayVotes();
}
