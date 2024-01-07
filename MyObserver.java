package observerObservable;

public interface MyObserver {
	
	//because the observer/observable pattern is deprecated,
	//i chose to create my own interface
	
	void update(MyObservable e);
	
}
