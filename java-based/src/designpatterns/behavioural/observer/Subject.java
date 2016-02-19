package designpatterns.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	
	private List<Observer> observers = new ArrayList<Observer>();
	private int state;
	
	public void setState(int num){
		this.state = num;
		notifyAllObservers();
	}
	
	public void attachObserver(Observer observer){
		observers.add(observer);
	}
	
	public int getState() {
		return this.state;
	}
	
	public void notifyAllObservers() {
		for(Observer observer: observers) {
			observer.update();
		}
	}

}
