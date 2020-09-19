package application.controller;

import application.model.Card;

import java.util.Observable;

public class Controller extends Observable {
    public static final String CHECK = "check";

    private Card firstCard;
    private Card secondCard;
    private boolean shouldCheck;

    public Controller() {
    }

    public Card getFirstCard() {
        return firstCard;
    }

    public void setFirstCard(final Card firstCard) {
        this.firstCard = firstCard;
    }

    public Card getSecondCard() {
        return secondCard;
    }

    public void setSecondCard(final Card secondCard) {
        this.secondCard = secondCard;
    }

    public void check() {
        setChanged();
        notifyObservers(CHECK);
    }

    public boolean shouldCheck() {
        return shouldCheck;
    }

    public void setShouldCheck(final boolean shouldCheck) {
        this.shouldCheck = shouldCheck;
    }

}
