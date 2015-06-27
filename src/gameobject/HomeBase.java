package gameobject;

/**
 * HomeBase class which is a gameobject.
 * Only one HomeBase may be instantiated, is unique to each game, and is tied
 *to the player object
 * @author Graham McAllister
 * @version 1.0
 */
public class HomeBase extends GameObject {

    private int health;

    /**
     * HomeBase constructor that instantiates constant health = 1,000
     */
    public HomeBase() {
        this.health = 1000;
    }

    /**
     * getHealth method for HomeBase
     * @return int                      health of Alien
     */
    public int getHealth() {
        return health;
    }

    /**
     * setHealth method for HomeBase
     * @param health                      desired health of HomeBase
     */
    public void setHealth(int health) {
        this.health = health;
    }
}
