package sample;

import gameobject.Turret;
/**
 * Player object instantiated when the game is simulated
 * @author Graham McAllister
 * @version 1.0
 */
public class Player {

    private int money;
    private int score;

    /**
     * Buy Turret method that subtracts money when invoked
     * @param t                 turret object
     */
    public void buyTurret(Turret t) {
        this.money -= t.getCost();
    }

    /**
     * no args player constructor, instantiates with hard-coded money and score
     */
    public Player() {
        this.money = 100;
        this.score = 0;
    }

    /**
     * Money getter method
     * @return int              amount of money
     */
    public int getMoney() {
        return money;
    }

    /**
     * increase money method
     * @param delta             amount to increase by
     */
    public void incMoney(int delta) {
        this.money += delta;
    }

    /**
     * decrease money method
     * @param delta             amount to decrease by
     */
    public void decMoney(int delta) {
        this.money -= delta;
    }

    /**
     * Score getter method
     * @return int              player's current score
     */
    public int getScore() {
        return score;
    }

    /**
     * increase score method
     * @param delta             amount to increase by
     */
    public void incScore(int delta) {
        this.score += delta;
    }
}
