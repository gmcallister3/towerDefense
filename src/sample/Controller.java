package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import model.GameBoard;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;
import javafx.beans.value.ChangeListener;
import model.Level;
import gameobject.*;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Path;
import javafx.scene.shape.Line;
import javafx.beans.property.DoubleProperty;
import javafx.animation.Timeline;


/**
 * Controller class, the main driver for the TowerDefense game
 * In the MVC concept, this class is both controller and viewer
 * @author Graham McAllister
 * @version 1.0
 */
public class Controller extends Application {

    //Coordinates
    private static final Double ORIGIN_X = 0.0;
    private static final Double ORIGIN_Y = 290.0;
    private static final Double END_X = 565.0;
    private static final Double END_Y = 180.0;


    //Setting up global variables

    //Observable data-backing objects
    private IntegerProperty score;
    private IntegerProperty money;
    private IntegerProperty wave;
    private IntegerProperty health;

    //Game Layers and images
    private Pane gamePane;
    private ImageView boardView;
    private ImageView alienO;
    private ImageView alienBrown;
    private ImageView alienBlue;
    private ImageView turret;

    //Game Objects/Values
    private Level level;
    private static GameBoard game;
    private Player player;
    private boolean decision = true;
    private DoubleProperty alienX;
    private DoubleProperty alienY;
    private AlienO alien;

    //Buttons
    private Button startButton;
    private Button turretButton;

    /**
     * Overridden start method to initialize the GUI
     * @param primaryStage          stage object to show
     */
    @Override
    public void start(Stage primaryStage) {

        game = new GameBoard();
        player = new Player();
        level = new Level();
        score = new SimpleIntegerProperty(0);
        money = new SimpleIntegerProperty(200);
        wave = new SimpleIntegerProperty(1);
        health = new SimpleIntegerProperty(1000);
        Text scoreText = new Text("-");
        Text moneyText = new Text("-");
        Text waveText = new Text("-");
        Text healthText = new Text("-");
        scoreText.textProperty().bind(score.asString());
        moneyText.textProperty().bind(money.asString());
        waveText.textProperty().bind(wave.asString());
        healthText.textProperty().bind(health.asString());


        //Make gameboard image and nodes(game objects)
        // Image gameBoard = new Image("MapCropped.png");
        // Image alien1 = new Image("Pixel-Alien-1.png");
        // Image alien2 = new Image("Pixel-Alien-2.png");
        // Image alien3 = new Image("Pixel-Alien-3.png");
        // Image launcher = new Image("launcher.png");

        //Converting images to imageViews
        boardView = new ImageView("MapCropped.png");
        // alienO = new ImageView("Pixel-Alien-1.png");
        // alienBlue = new ImageView("Pixel-Alien-2.png");
        alienBrown = new ImageView("Pixel-Alien-3.png");

        //Resizes images
        boardView.setFitHeight(500);
        boardView.setFitWidth(700);
        // alienO.setFitHeight(40);
        // alienO.setFitWidth(40);
        // alienO.setX(0);
        // alienO.setY(270);
        // alienO.setPreserveRatio(true);
        // alienBlue.setFitHeight(40);
        // alienBlue.setFitWidth(40);
        // alienBlue.setPreserveRatio(true);
        alienBrown.setFitHeight(40);
        alienBrown.setFitWidth(40);
        alienBrown.setX(550);
        alienBrown.setY(175);
        alienBrown.setPreserveRatio(true);


        // TilePane board = new TilePane();
        // board.setPrefColumns(10);
        // board.setPrefRows(10);
        // board.setPrefWidth(300);
        // board.setPrefHeight(300);

        //Buttons and items in the menu column
        Label stats = new Label("Stats");
        stats.setCenterShape(true);
        HBox scoreBar = new HBox();
        Label scoreLabel = new Label("Score: ");
        scoreBar.getChildren().addAll(scoreLabel, scoreText);
        HBox moneyBar = new HBox();
        Label moneyLabel1 = new Label("Money: ");
        Label moneyLabel = new Label(" $");
        moneyBar.getChildren().addAll(moneyLabel1, moneyText, moneyLabel);
        HBox waveBar = new HBox();
        Label waveLabel = new Label("Wave: ");
        waveBar.getChildren().addAll(waveLabel, waveText);
        HBox healthBar = new HBox();
        healthBar.getChildren().addAll(new Label("Base Health: "), healthText);
        Label buy = new Label("Buy");
        turretButton = new Button("Turret: 100$");
        startButton = new Button("Start Wave");
        VBox menu = new VBox(stats, scoreBar, moneyBar, waveBar, healthBar, buy,
            turretButton, startButton);
        menu.getChildren().addAll();
        gamePane = new Pane();
        gamePane.getChildren().add(boardView);
        // gamePane.getChildren().add(alienO);
        gamePane.getChildren().add(alienBrown);
        boardView.toBack();
        GridPane root = new GridPane();
        root.getColumnConstraints().add(new ColumnConstraints(100));
        root.addColumn(0, menu);
        root.addColumn(1, gamePane);
        root.setMinSize(800, 500);
        root.setMaxSize(800, 500);


        //Starts wave with new homeBase (reset health)
        startButton.setOnAction(e -> {
                game.setHealth(1000);
                startWave(wave.get());
                wave.set(wave.get() + 1);
                startButton.disarm();
            });

        //Mouse listener to track coordinates
        gamePane.setOnMouseClicked(e -> {
                System.out.println(e.getX() + ", " + e.getY());
            });

        //Handles buy turret button
        turretButton.setOnAction(e -> {
            //Follow cursor to find where to place turret
            gamePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    double x = mouseEvent.getX();
                    double y = mouseEvent.getY();
                    ImageView turret = new ImageView("launcher.png");
                    turret.setFitHeight(20);
                    turret.setFitWidth(20);
                    turret.setPreserveRatio(true);
                    turret.setX(x);
                    turret.setY(y);
                    //If player has enough money, can buy turret and subtract money
                    if (money.get() >= 100) {
                        gamePane.getChildren().add(turret);
                        money.set(money.get() - 100);
                        game.addTurret(new Turret(), x, y);
                    }
                }
            });
        });




        Scene rootScene = new Scene(root, 800, 500);
        primaryStage.setTitle("TowerDefense - Graham McAllister");
        primaryStage.setScene(rootScene);
        primaryStage.show();
    }

    /**
     * Main method for the Controller class, no arguments needed to initialize
     * @param args          arguments from command line
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Getter method for the wave number
     * @return IntegerProperty              current wave
     */
    public IntegerProperty getWave() {
        return wave;
    }

    /**
     * startWave method to be called from the controller
     * @param wave                  integer of wave number
     */
    public void startWave(int wave) {
        //Clear existing gameobject data other than turrets
        System.out.println("Wave: " + wave);
        //Generates enemies based on wave
        // ArrayList<Alien> enemies = level.genEnemies(wave);

        //Create one alien initially
        alienO = new ImageView("Pixel-Alien-1.png");
        alienO.setFitHeight(40);
        alienO.setFitWidth(40);
        alienO.setX(ORIGIN_X);
        alienO.setY(ORIGIN_Y);
        alienO.setPreserveRatio(true);
        gamePane.getChildren().add(alienO);

        //Add alien into model to initiate tracking
        AlienO alien = new AlienO();
        game.addAlien(alien);

        //Create timeline with keyFrames based on gameBoard movements
        // Timeline timeline = new Timeline();
        // timeline.setCycleCount(1);
        //Create keyFrames

        //Begins animation
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //If alien is at end of path or dead, delete
                if (game.isAtEnd(alien)) {
                    //If at the end, damage base
                    gamePane.getChildren().removeAll(alienO);
                    health.set(health.get() - 100);
                } else if (game.isDead(alien)) {
                    gamePane.getChildren().removeAll(alienO);
                    //Increment score and money if the alien has been shot dead
                    score.set(score.get() + 100);
                    money.set(money.get() + 25);


                //If alien isn't dead or at the base, keep moving
                //Move by updating model data coordinates then translate according to them
                } else {
                    game.move(alien);
                    //System.out.println("Model Coords: " + game.getX(alien) + ", " + game.getY(alien));
                    //Account for rendering to put image centered on coordinate from model
                    alienO.setX(Controller.game.getX(alien) - 20);
                    alienO.setY(Controller.game.getY(alien) - 20);
                    //System.out.println("View Coords: " + alienO.xProperty().toString() + ", " + alienO.yProperty().toString());
                }

                //Loops through all turrets checking if there are aliens in range
                ArrayList<Turret> turrets = game.getTurrets();
                for (Turret t : turrets) {
                    ArrayList<Alien> enemiesInRange = game.getInRange(t);
                    //System.out.println(enemiesInRange.isEmpty());
                    if (!(enemiesInRange.isEmpty())) {
                        //Could be multiple aliens in range of a single turret, so nested loop
                        for (Alien curAlien : enemiesInRange) {
                            //Only update model and animate if there is health to damage
                            if (curAlien.getHealth() > 0) {
                                //Calls animation function which will update model as well
                                bulletAnimation(t, curAlien);
                            }
                        }
                    }
                }
            }
        }.start();
    }


        // PathTransition animation = new PathTransition();
        // animation.setPath(new Polyline(
        //     0.0, 290.0,
        //     43.0, 290.0,
        //     43.0, 55.0,
        //     135.0, 55.0,
        //     135.0, 475.0,
        //     180.0, 475.0,
        //     390.0, 335.0,
        //     410.0, 270.0,
        //     565.0, 270.0,
        //     565.0, 180.0)
        // );
        // animation.setDuration(Duration.seconds(15));
        // animation.setNode(alienO);
        // animation.play();

    public void bulletAnimation(Turret turret, Alien alien) {
        Line bullet = new Line();
        Line path = new Line();
        PathTransition transition = new PathTransition();
        //Get position data from model and calculate path points
        double xAlien = game.getX(alien);
        double yAlien = game.getY(alien);
        double xTurret = game.getTurretX(turret);
        double yTurret = game.getTurretY(turret);
        double slope = (yTurret - yAlien) / (xTurret - xAlien);
        path.setStartX(xTurret);
        path.setStartY(yTurret);
        path.setEndX(xAlien);
        path.setEndY(yAlien);
        //Draws line node starting at turret and extending slightly
        bullet.setStartX(xTurret);
        bullet.setStartY(yTurret);
        bullet.setEndX(xTurret - 5);
        bullet.setEndY(yTurret - 5);
        //Puts together transition
        transition.setNode(bullet);
        transition.setPath(path);
        transition.setDuration(Duration.seconds(2));
        transition.play();
        game.shoot(turret, alien);
    }

        // new AnimationTimer() {
        //     /**
        //      * Overridden handle method to act during the animation timer.
        //      * Calls helper methods to move enemies, attack, etc
        //      * Turrets also attack anything in their range
        //      * @param time                   time for the timer to act
        //      */
        //     @Override
        //     public void handle(long now) {
        //         //Need to move an alien image along path and time it

        //         //Then start spacing out all aliens in wave over some interval
        //     }
        // }.start();
        // for (int i = 0; i < enemies.size(); i++) {
        //     if (enemies.get(i) instanceof AlienO) {
        //         alienO = new ImageView("Pixel-Alien-1.png");
        //         alienO.setFitHeight(40);
        //         alienO.setFitWidth(40);
        //         alienO.setX(ORIGIN_X);
        //         alienO.setY(ORIGIN_Y);
        //         alienO.setPreserveRatio(true);
        //         gamePane.getChildren().add(alienO);
        //     }
        //     if (enemies.get(i) instanceof AlienBlue) {
        //         alienBlue = new ImageView("Pixel-Alien-2.png");
        //         alienBlue.setFitHeight(40);
        //         alienBlue.setFitWidth(40);
        //         alienBlue.setX(ORIGIN_X);
        //         alienBlue.setY(ORIGIN_Y);
        //         alienBlue.setPreserveRatio(true);
        //         gamePane.getChildren().add(alienBlue);
        //     }
        //     if (enemies.get(i) instanceof AlienBrown) {
        //         alienBrown = new ImageView("Pixel-Alien-3.png");
        //         alienBrown.setFitHeight(40);
        //         alienBrown.setFitWidth(40);
        //         alienBrown.setX(END_X);
        //         alienBrown.setY(END_Y);
        //         alienBrown.setPreserveRatio(true);
        //         gamePane.getChildren().add(alienBrown);
        //     }
        // }
}


//TODO

//Turrets shoot
    //Fix incrementing score/money
    //Work on transition timing
//Aliens damage base
    //Fix decrementing health
//Space menu out better

//Add sets of aliens