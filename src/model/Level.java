package model;

import java.util.ArrayList;
import java.util.Random;
import gameobject.Alien;
import gameobject.AlienO;
import gameobject.AlienBrown;
import gameobject.AlienBlue;
import sample.Coords;

/**
 * Level object that generates enemies based on the specified level in the
 *instance.
 * @author Graham McAllister
 * @version 1.0
 */
public class Level {

    private int difficulty;
    private ArrayList<Alien> enemyData;
    private Random rand;

    /**
     * Level constructor that instantiates a Level object.  Only one Level
     *instance should be instantiated
     */
    public Level() {
        this.difficulty = 1;
        rand = new Random();
        enemyData = new ArrayList<Alien>();
    }

    /**
     * getDifficulty method for Level
     * @return int                    returned integer representing
     * current difficulty
     *of the level
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * getEnemies method to be used in GameBoard
     * @return arraylist of enemies that were generated in this class
     */
    public ArrayList<Alien> getEnemies() {
        return enemyData;
    }

    /**
     * setDifficulty method for Level instance
     * @param wave                   difficulty to set the level to
     */
    public void setDifficulty(int wave) {
        this.difficulty = wave;
    }

    /**
     * Simply increases the difficulty by one for the Level instance
     */
    public void incDifficulty() {
        difficulty++;
    }

    /**
     * Generates enemies based on the current difficulty, uses private helper
     *methods to update the arraylist of enemy objects
     * @param wave                 wave specification
     * @return arraylist of generated alien objects
     */
    public ArrayList<Alien> genEnemies(int wave) {
        enemyData.clear();
        if (wave <= 4) {
            genEasy();
        } else if (wave > 5 && wave <= 7) {
            genMed();
        } else if (wave > 8 && wave <= 11) {
            genHarder();
        } else if (wave > 12 && wave <= 14) {
            genHard();
        } else if (wave > 15 && wave <= 18) {
            genExpert();
        }
        difficulty++;
        return enemyData;
    }

    private void genEasy() {
        int numOrange = rand.nextInt(10);
        for (int i = 0; i < numOrange; i++) {
            enemyData.add(new AlienO());
        }
    }

    private void genMed() {
        int numOrange = rand.nextInt(10);
        int numBlue = rand.nextInt(10);
        for (int i = 0; i < numOrange; i++) {
            enemyData.add(new AlienO());
            enemyData.add(new AlienBlue());
        }
    }

    private void genHarder() {
        int numOrange = rand.nextInt(20);
        int numBlue = rand.nextInt(20);
        for (int i = 0; i < numOrange; i++) {
            enemyData.add(new AlienO());
            enemyData.add(new AlienBlue());
        }
    }

    private void genHard() {
        int numOrange = rand.nextInt(20);
        int numBlue = rand.nextInt(20);
        int numBrown = rand.nextInt(10);
        for (int i = 0; i < numOrange; i++) {
            enemyData.add(new AlienO());
            enemyData.add(new AlienBlue());
        }
        for (int i = 0; i < numBrown; i++) {
            enemyData.add(new AlienBrown());
        }
    }

    private void genExpert() {
        int numOrange = rand.nextInt(25);
        int numBlue = rand.nextInt(25);
        int numBrown = rand.nextInt(20);
        for (int i = 0; i < numOrange; i++) {
            enemyData.add(new AlienO());
            enemyData.add(new AlienBlue());
        }
        for (int i = 0; i < numBrown; i++) {
            enemyData.add(new AlienBrown());
        }
    }
}