package model;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import gameobject.GameObject;
import gameobject.Alien;
import gameobject.Turret;
import sample.Coords;
import sample.Controller;
import java.util.Random;
import javafx.animation.AnimationTimer;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Iterator;

/**
 * GameBoard class which acts as the data-backing model for the game.
 *Tracks gameobjects as well
 * @author Graham McAllister
 * @version 1.0
 */
public class GameBoard {

    private int width;
    private int height;
    //Aliens can only move along path, so map to int of path index
    private Map<Alien, Integer> enemyData;
    private Map<Turret, Coords> turretData;
    private Random rand;
    private long now;
    private LinkedList<Coords> path;
    private Level level;
    private final Coords ORIGIN;
    private final Coords ENEMY_END;
    private int homeHealth;

    /**
     * GameBoard constructor that instantiates a 10x10 gameBoard and empty map
     */
    public GameBoard() {
        this.width = 700;
        this.height = 500;
        enemyData = new HashMap<>();
        turretData = new HashMap<>();
        rand = new Random();
        now = System.currentTimeMillis();
        path = new LinkedList<Coords>();
        //Hard code path Coords into path list
        //1 list node for each possible spot
        path.add(0, new Coords(0.0, 290.0));
        int index = 1;
        for (double i = 1.0; i < 43.0; i++) {
            path.add(index, new Coords(i, 290.0));
            index++;
        }
        path.add(index, new Coords(43.0, 290.0));
        index++;
        for (double i = 289.0; i > 55.0; i--) {
            path.add(index, new Coords(43.0, i));
            index++;
        }
        path.add(index, new Coords(43.0, 55.0));
        index++;
        for (double i = 44.0; i < 135.0; i++) {
            path.add(index, new Coords(i, 55.0));
            index++;
        }
        path.add(index, new Coords(135.0, 55.0));
        index++;
        for (double i = 56.0; i < 475.0; i++) {
            path.add(index, new Coords(135.0, i));
            index++;
        }
        path.add(index, new Coords(135.0, 475.0));
        index++;
        for (double i = 136.0; i < 180.0; i++) {
            path.add(index, new Coords(i, 475.0));
            index++;
        }
        path.add(index, new Coords(180.0, 475.0));
        index++;
        for (double i = 474.0; i > 450; i--) {
            path.add(index, new Coords(180.0, i));
            index++;
        }
        path.add(index, new Coords(390.0, 335.0));
        for (double i = 334.0; i > 270.0; i--) {
            path.add(index, new Coords(390.0, i));
            index++;
        }
        path.add(index, new Coords(390.0, 270.0));
        index++;
        for (double i = 391.0; i < 410.0; i++) {
            path.add(index, new Coords(i, 270.0));
            index++;
        }
        path.add(index, new Coords(410.0, 270.0));
        index++;
        for (double i = 411.0; i < 565.0; i++) {
            path.add(index, new Coords(i, 270.0));
            index++;
        }
        path.add(index, new Coords(565.0, 270.0));
        index++;
        for (double i = 269.0; i > 180.0; i--) {
            path.add(index, new Coords(565.0, i));
            index++;
        }
        path.add(index, new Coords(565.0, 180.0));
        ORIGIN = new Coords(0.0, 290.0);
        ENEMY_END = new Coords(565.0, 180.0);
        homeHealth = 1000;
        level = new Level();
    }

    /**
     * getPosition method for GameBoard.  Used to get the coordinates of the
     *specified gameobject
     * @param g                     gameobject whose position is wanted
     * @return Coords               coordinates object of y, x location
     */
    // public Coords getPosition(GameObject g) {
    //     return mapData.get(g);
    // }


    /**
     * Move method to move all movable gameobjects (Aliens)
     */
    // public void move() {
    //     for (int i = 0; i < enemies.size(); i++) {
    //         Alien curEnemy = enemies.get(i);
    //         int speed = curEnemy.getSpeed();
    //         Coords curCoords = mapData.get(curEnemy);
    //         //Calls moveAlien method in Coords class that has map specified
    //         Coords newCoords = moveAlien(curCoords, speed);
    //         mapData.put(curEnemy, newCoords);
    //         //When Aliens get to homeBase, they will damage and then disappear
    //         Object[] keys = mapData.keySet().toArray();
    //         if (mapData.containsValue(ENEMY_END)) {
    //             for (int j = 0; j < keys.length; j++) {
    //                 if (keys[j] instanceof Alien) {
    //                     mapData.remove(keys[j]);
    //                 }
    //             }
    //         }
    //     }
    // }

    /**
     * Attack method called in the animation timer for turrets to inflict damage
     * to enemies
     */
    // public void attack() {
    //     Object[] keys = mapData.keySet().toArray();
    //     for (int i = 0; i < keys.length; i++) {
    //         if (keys[i] instanceof Turret) {
    //             Turret curTurret = (Turret) (keys[i]);
    //             Coords turretLoc = mapData.get(curTurret);
    //             int damage = curTurret.getDamage();
    //             damageAliens(turretLoc, damage);
    //         }
    //     }
    // }

    /**
     * Changes coordinates according to a path specified by a doubly linked list
     * @param curCoords                 the objects current location
     * @param speed                     the objects speed
     * @return Coords           the objects new location coordinates
     */
    public Coords moveAlien(Coords curCoords, int speed) {
        int position = path.indexOf(curCoords);
        return path.get(position + (1 * speed));
    }

    /**
     * Damages Alien objects given the coordinates of
     * @param center                    the location of object dealing damage
     * @param damage                    the amount of damage to inflict
     */
    // public void damageAliens(Coords center, int damage) {
    //     ArrayList<Alien> aliensToDamage = new ArrayList<Alien>();
    //     Object[] mapKeys = mapData.keySet().toArray();
    //     for (int i = 0; i < mapKeys.length; i++) {
    //         GameObject curObject = (GameObject) (mapKeys[i]);
    //         Coords curCoords = (Coords) (mapData.get(curObject));
    //         if (curCoords.withinRange(center) && curObject instanceof Alien) {
    //             Alien target = (Alien) (curObject);
    //             int oldHealth = target.getHealth();
    //             target.setHealth(oldHealth - damage);
    //             int newHealth = target.getHealth();
    //             if (newHealth <= 0) {
    //                 mapData.remove(target);
    //             }
    //         }
    //     }
    // }

    /**
     * Place turret object, action initiated from Controller class
     * @param t               the turret object to be added
     * @param x                x coord of added position
     * @param y                y coord of added position
     */
    public void addTurret(Turret t, Double x, Double y) {
        Coords coords = new Coords(x, y);
        turretData.put(t, coords);
    }

    /**
     * Move the alien object's mapped coordinates based on speed and the linkedList path
     * Only updates the enemyData map, aliens can only move forward
     * @param alien              the alien object to be moved
     */
    public void move(Alien alien) {
        int speed = alien.getSpeed();
        int curInd = enemyData.get(alien);
        if (curInd < 1190) {
            enemyData.put(alien, curInd + (1 * speed));
        }
    }

    /**
     * Adds the specified alien object to gameBoard
     * Default starting position is the first path coords
     * @param alien               the alien object to be moved
     */
    public void addAlien(Alien alien) {
        enemyData.put(alien, 0);
    }

    public void removeAlien(Alien alien) {
        //Last index of path =
        enemyData.remove(alien, 1190);
    }

    public void setHealth(int health) {
        this.homeHealth = health;
    }

    public Double getX(Alien alien) {
        int ind = enemyData.get(alien);
        Coords pos = path.get(ind);
        return pos.getX();
    }

    public Double getY(Alien alien) {
        int ind = enemyData.get(alien);
        Coords pos = path.get(ind);
        return pos.getY();
    }

    public boolean isDead(Alien alien) {
        if (alien.getHealth() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAtEnd(Alien alien) {
        int curInd = 1191;
        if (enemyData.containsKey(alien)) {
            curInd = enemyData.get(alien);
        }
        if (curInd >= 1190) {
            removeAlien(alien);
            return true;
        } else {
            return false;
        }
    }

    public double getTurretX(Turret t) {
        Coords turretCoords = turretData.get(t);
        return turretCoords.getX();
    }

    public double getTurretY(Turret t) {
        Coords turretCoords = turretData.get(t);
        return turretCoords.getY();
    }

    /**
     * Checks if there are any enemies within range of a turret
     * @return boolean             true if there are any enemies within range
     */
    public ArrayList<Alien> getInRange(Turret t) {
        ArrayList<Alien> out = new ArrayList<>();
        //Get turret coords
        Coords turretCoords = turretData.get(t);
        double x = turretCoords.getX();
        double y = turretCoords.getY();
        //Get a set of alien coords & iterate through
        //Possible coords are within 50 pixels (sqrt(x^2 + y^2) <= 50)
        Iterator setIter = enemyData.entrySet().iterator();
        while (setIter.hasNext()) {
            Map.Entry entry = (Map.Entry) (setIter.next());
            Alien curAlien = (Alien) (entry.getKey());
            int coordInd = (int) (entry.getValue());
            //curCoords are alien coordinates
            //x and y are turret coordinates
            Coords curCoords = path.get(coordInd);
            if (curCoords.withinRange(t, x, y)) {
                out.add(curAlien);
            }
        }
        return out;
    }

    public void shoot(Turret t, Alien alien) {
        int d = t.getDamage();
        alien.setHealth(alien.getHealth() - d);
        //Checks health to remove alien if less than or equal to 0
        if (alien.getHealth() <= 0) {
            removeAlien(alien);
        }
    }

    public ArrayList<Turret> getTurrets() {
        ArrayList<Turret> out = new ArrayList<Turret>();
        if (!(turretData.isEmpty())) {
            Iterator i = turretData.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) (i.next());
                Turret curTurret = (Turret) (entry.getKey());
                out.add(curTurret);
            }
        }
        return out;
    }


}