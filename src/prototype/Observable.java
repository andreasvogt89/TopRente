package prototype;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable {

    List<Observer> oberservers = new LinkedList<>();


    public void addObserver(Observer observer) {
        oberservers.add(observer);
    }

    protected void notifyObservers() {
        for (Observer i : oberservers) {
            i.update();
        }
    }

}



