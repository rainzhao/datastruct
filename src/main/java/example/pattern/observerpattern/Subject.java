package example.pattern.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyu
 * @date 2019-02-25
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObserver();
    }

    protected void attach(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
