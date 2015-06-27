package gameobject;

/**
 * Concrete Turret class which is a type of gameobject with cost, damage,
 *and radius instance variables.
 * Does not contain setters, since the instance variables are constant
 * @author Graham McAllister
 * @version 1.0
 */
public class Turret extends GameObject {

    private int cost;
    private int damage;
    private double range; //Radius of range circle

    /**
     * Turret constructor that instantiates constant attributes
     */
    public Turret() {
        this.cost = 100;
        this.damage = 50;
        this.range = 50.0;
    }

    /**
     * getCost method for Turret
     * @return int                      cost to purchase turret
     */
    public int getCost() {
        return cost;
    }

    /**
     * getDamage method for Turret
     * @return int                      damage output of the turret
     */
    public int getDamage() {
        return damage;
    }

    /**
     * getRange method for Turret
     * @return int                      radius of Turret instance
     */
    public double getRange() {
        return range;
    }
}
