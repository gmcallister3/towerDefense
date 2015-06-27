package sample;

import gameobject.Turret;

/**
 * Coords class which wraps two doubles into a Coords object
 * @author Graham McAllister
 * @version 1.0
 */
public class Coords {

    private Double y, x;

    /**
     * Coords constructor that instantiates a coordinate object with two
     *doubleegers
     * @param y                     y-dimension (vertical) specification
     * @param x                     x-dimension (horizontal) specification
     */
    public Coords(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Overridden equals method for a coordinate. Based on both x and y
     * @param other                     Object to compare to
     */
    @Override
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }
        if (this == other) {
            return true;
        } else if (!(other instanceof Coords)) {
            return false;
        }
        Coords that = (Coords) (other);
        return that.x == this.x && that.y == this.y;
    }

    /**
     * Overridden hashCode method for a coordinate. Based on both x and y values
     * @return double                  unique hashCode number
     */
    @Override
    public int hashCode() {
        int out = 11;
        out = out + 17 * x.hashCode();
        out = out + 17 * y.hashCode();
        return out;
    }

    /**
     * getX coordinate method for a coordinate instance.
     * @return double                   the x coordinate of Coords instance
     */
    public Double getX() {
        return x;
    }

    /**
     * getY coordinate method for a coordinate instance.
     * @return double                   the y coordinate of Coords instance
     */
    public Double getY() {
        return y;
    }

    /**
     * setX coordinate method for a coordinate. Allows mutation of coordinate
     * @param x                  the x coordinate of Coords instance
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * setY coordinate method for a coordinate. Allows mutation of coordinate
     * @param y                   the y coordinate of Coords instance
     */
    public void setY(Double y) {
        this.y = y;
    }
    /**
     * Checks coordinate pair of turret and alien to determine if in range
     * It is in range if sqrt(x^2 +y^2) <= range
     * @param turretCoords                    coordinates of turret
     * @return boolean                  true if the coordinates are within range
     */
    public boolean withinRange(Turret t, Double x, Double y) {
        double range = t.getRange();
        //Takes difference of alien and turret x and y coordinates
        double xDiff = this.getX() - x;
        double yDiff = this.getY() - y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff) <= range;

        // if (this.y == center.getY() - range && this.x == center.getX()) {
        //     return true;
        // }
        // if (this.y == center.getY() - range && this.x == center.getX() + 1) {
        //     return true;
        // }
        // if (this.y == center.getY() && this.x == center.getX() + 1) {
        //     return true;
        // }
        // if (this.y == center.getY() + 1 && this.x == center.getX() + 1) {
        //     return true;
        // }
        // if (this.y == center.getY() + 1 && this.x == center.getX()) {
        //     return true;
        // }
        // if (this.y == center.getY() + 1 && this.x == center.getX() - 1) {
        //     return true;
        // }
        // if (this.y == center.getY() && this.x == center.getX() - 1) {
        //     return true;
        // }
        // if (this.y == center.getY() - 1 && this.x == center.getX() - 1) {
        //     return true;
        // }
        // return false;
    }
}
