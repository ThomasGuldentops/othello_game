package esi.atl.g52088.othello.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Observable {

    private final List<Observer> observers = new ArrayList<>();

    /**
     * To add a new observer. This observer will be added on the list observers,
     * this list containt all the observers
     *
     * @param observer the new observer
     */
    public void addObserver(Observer observer) {
        Objects.requireNonNull(observer);
        observers.add(observer);
    }

    /**
     * To notify all the observers present on the list. This methods will be
     * called when a something change
     */
    protected void notifyObservers() {
        observers.forEach((observer) -> {
            observer.update();
        });
    }

}
