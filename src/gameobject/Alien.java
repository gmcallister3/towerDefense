package gameobject;

/**
 * Abstract alien classes that contains concrete methods for alien instances
 * Alien instances are considered enemies of the player in TowerDefense
 * @author Graham McAllister
 * @version 1.0
 */
public abstract class Alien extends GameObject {

    private int health;
    private int speed;

    /**
     * Alien constructor that instantiates constant attributes from each
     *concrete alien
     * @param health                health from sub class
     * @param speed                 speed from subclass
     */
    public Alien(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }

    /**
     * getHealth method for Alien, inherited by other Aliens
     * @return int                      health of Alien
     */
    public int getHealth() {
        return health;
    }

    /**
     * setHealth method for Alien, inherited by other Aliens
     * @param health                      health of Alien to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * getSpeed method for Alien, inherited by other Aliens
     * @return int                      speed of Alien
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * setSpeed method for Alien, inherited by other Aliens
     * @param speed                     speed of Alien
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}