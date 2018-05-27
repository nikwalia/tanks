package graphics;

import java.util.LinkedList;
import java.util.Queue;

import io.SystemPacket;
import io.TankPacket;
import math.Mine;
import math.Bullet;
import math.Tank;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


/**
 * 
 * Runner class- responsible for creation of all objects and responsible for all
 * communications
 *
 * @author Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 * @version May 9, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class Main extends PApplet
{
    // private static Base tank;

    private PImage background;

    private Queue<TankPacket> data;

    // indices for player data
    // 0- forward pressed
    // 1- backward pressed
    // 2- left pressed
    // 3- right pressed
    // 4- gun left pressed
    // 5- gun right pressed
    // 6- fire pressed
    private int[] playerOneData;

    private int[] playerTwoData;

    private Tank playerOneTank;

    private Tank playerTwoTank;

    private Bullet playerOneBullet;

    private Bullet playerTwoBullet;

    private RunnerWindow playerOneWindow;

    private RunnerWindow playerTwoWindow;

    private int gameState;

    private int bottom = 425;

    private Mine[] mines = new Mine[10];

    // the ratio of how far apart all the game objects are placed
    private final int distanceConstant = 5000;


    /**
     * Main method- initializes environment
     * 
     * @param args
     *            not used
     */
    public static void main( String[] args )
    {
        PApplet.main( "graphics.Main" );
    }


    /**
     * 
     * Method called by the environment- initializes all objects and fields,
     * opens up both player windows, and provides preliminary information about
     * both tanks to both windows
     */
    public void setup()
    {
        background = loadImage( "tanksactualforproject.jpg" );
        noTint();
        data = new LinkedList<TankPacket>();

        playerOneData = new int[7];
        playerTwoData = new int[7];

        gameState = 0;

        playerOneWindow = new RunnerWindow();
        playerTwoWindow = new RunnerWindow();

        playerOneTank = new Tank( (int)( ( ( Math.random() * 2 ) - 1 ) * 1000 ),
            350,
            (int)( ( ( Math.random() * 2 ) - 1 ) * 2500 ),
            Math.random() * Math.PI * 2,
            500,
            50,
            50,
            500,
            300,
            150,
            1000 );
        playerTwoTank = new Tank( (int)( ( ( Math.random() * 2 ) - 1 ) * 1000 ),
            350,
            (int)( ( ( Math.random() * 2 ) - 1 ) * 2500 ),
            Math.random() * Math.PI * 2,
            500,
            50,
            50,
            500,
            300,
            150,
            1000 );

        // playerOneTank = new Tank( 0, 350, 0, 0, 500, 50, 50, 500, 300, 150,
        // 1000 );
        // playerTwoTank = new Tank( 1000, 350, 0, -Math.PI / 2, 500, 50, 50,
        // 500, 300, 150, 1000 );

        for ( int i = 0; i < mines.length; i++ )
        {
            mines[i] = new Mine( (int)( ( ( Math.random() * 2 ) - 1 ) * distanceConstant ),
                bottom,
                (int)( ( ( Math.random() * 2 ) - 1 ) * distanceConstant ),
                100 );
        }
        playerOneBullet = null;
        playerTwoBullet = null;

        data.add( playerOneTank.sendData() );
        data.add( playerTwoTank.sendData() );
        TankPacket playerOneInit = data.remove();
        TankPacket playerTwoInit = data.remove();

        playerOneWindow.init( playerTwoTank, playerOneInit, playerTwoInit, 1, mines );
        playerTwoWindow.init( playerOneTank, playerTwoInit, playerOneInit, 2, mines );

        String[] args = { "" };
        PApplet.runSketch( args, playerOneWindow );
        PApplet.runSketch( args, playerTwoWindow );
    }


    /**
     * Sets the size of the window
     */
    public void settings()
    {
        size( 960, 540 );
    }


    /**
     * Method called periodically by the environment- either draws the home
     * screen or updates the information, given that all game initialization
     * conditions are met
     */
    public void draw()
    {
        if ( gameState == 0 )
        {
            homeScreen();
        }
        else
        {
            if ( playerOneWindow.initCalled && playerTwoWindow.initCalled
                && playerOneWindow.setupCalled && playerTwoWindow.setupCalled
                && !playerOneWindow.countdownRunning && !playerTwoWindow.countdownRunning )
            {
                update();
            }
        }
    }


    /**
     * draws the home screen on the main screen
     */
    private void homeScreen()
    {
        background( background );
        textSize( 58 );
        text( "T-34", width / 6, 75 );
        fill( 11, 57, 198 );
        text( "        Tank Destroyers", width / 6, 75 );
        fill( 56, 114, 5 );
        PFont font;
        // String[] fontList = PFont.list();
        // printArray( fontList );
        font = createFont( "Verdana Bold", 196 );
        textFont( font );
        textSize( 30 );
        fill( 0 );
        text( "Press space to begin or pause", 250, height / 4 );
        controls();
    }


    /**
     * Lists out all the player controls on the main screen
     */
    private void controls()
    {
        textSize( 20 );
        fill( 5, 22, 231 );
        text( "Player One Controls", width / 2 - 100, height / 3 );
        text( "Forward: UP Backward: DOWN Left: LEFT Right: RIGHT", width / 6, 2 * height / 5 );
        text( "Turret Left: [ Turret Right: ] Turret Fire: \\", width / 4, 10 * height / 21 );
        text( "Player Two Controls", width / 2 - 100, 10 * height / 17 );
        text( "Forward: W Backward: S Left: A Right: D", 2 * width / 7, 2 * height / 3 );
        text( "Turret Left: ` Turret Left: 1 Turret Right: 2", width / 4, 5 * height / 7 );
        text( "Press Q to quit game", width / 2 - 50, 5 * height / 6 );

    }


    // TODO finish
    public void compassView( TankPacket p1, TankPacket p2 )
    {
        background( 255 );
        pushMatrix();
        translate( width / 2, height / 2 );
        fill( 150 );
        rotate( (float)p1.getAngle() );
        rectMode( CENTER );
        rect( 0, 0, 40, 40 );
        fill( 10 );
        popMatrix();
        // pushMatrix();
        // translate( width / 2, height / 2 );
        // fill( 150 );
        // rotate( (float)p2.getAngle() );
        // rectMode( CENTER );
        // rect( 0, 0, 40, 40 );
        // fill( 10 );
        // popMatrix();
    }


    /**
     * Begins the game by allowing the player windows to begin countdowns
     */
    private void startGame()
    {
        playerOneWindow.setGameMode( 1 );
        playerTwoWindow.setGameMode( 1 );
    }


    /**
     * Pauses the game- stops the player windows from rendering
     */
    private void pauseGame()
    {
        playerOneWindow.noLoop();
        playerTwoWindow.noLoop();
    }


    /**
     * Resumes the game by allowing the two player windows to start drawing
     * again, updates the "last time" of the tanks in order to prevent false
     * drifting
     */
    private void resumeGame()
    {
        playerOneWindow.loop();
        playerTwoWindow.loop();
        playerOneTank.resume();
        playerTwoTank.resume();
    }


    /**
     * This is responsible for sending all information back and forth between
     * the system and the tanks. It draws the compass view for both players in
     * the main screen, checks if the two tanks have collided with each other or
     * a mine and ends the game accordingly, and checks if either of the tanks
     * have died, ending the game accordingly
     */
    private void update()
    {
        playerOneTank.receiveData( new SystemPacket( playerOneData[6] == 1,
            playerOneData[3] - playerOneData[2],
            playerOneData[0] - playerOneData[1],
            playerOneData[5] - playerOneData[4] ) );
        playerTwoTank.receiveData( new SystemPacket( playerTwoData[6] == 1,
            playerTwoData[3] - playerTwoData[2],
            playerTwoData[0] - playerTwoData[1],
            playerTwoData[5] - playerTwoData[4] ) );
        playerOneTank.update();
        playerTwoTank.update();
        data.add( playerOneTank.sendData() );
        data.add( playerTwoTank.sendData() );
        TankPacket p1 = data.remove();
        TankPacket p2 = data.remove();

        playerOneWindow.update( p1, p2 );
        playerTwoWindow.update( p2, p1 );

        if ( gameState != -1 )
        {
            compassView( p1, p2 );
        }

        checkBulletState( p1, p2 );

        if ( playerOneTank.onCollision( playerTwoTank ) == -1 )
        {
            gameOver( 0 );
        }

        checkMineState();

        if ( p1.getHitpoints() <= 0 )
        {
            gameOver( 2 );
        }
        else if ( p2.getHitpoints() <= 0 )
        {
            gameOver( 1 );
        }
    }

    /**
     * checks whether or not either of the tanks have collided with a mine, and
     * accordingly ends the game
     */
    private void checkMineState()
    {
        for ( int i = 0; i < mines.length; i++ )
        {
            if ( playerOneTank.onCollision( mines[i] ) == -1 )
            {
                gameOver( 2 );
            }
            if ( playerTwoTank.onCollision( mines[i] ) == -1 )
            {
                gameOver( 1 );
            }
        }
    }


    /**
     * Updates the status of a bullet. Creates a new bullet if it does not
     * already exist and fire is called. If a bullet exists, it is translated.
     * If it has fallen below the ground due to gravity, it is removed. If it
     * has collided with a tank, it is removed and the tank's hitpoints are
     * reduced.
     * 
     * @param one
     *            TankPacket for player one
     * @param two
     *            TankPacket for player two
     */
    private void checkBulletState( TankPacket one, TankPacket two )
    {
        if ( one.checkIfFired() && playerOneBullet == null )
        {
            playerOneBullet = new Bullet( one.getGunLoc().getX(),
                one.getGunLoc().getY(),
                one.getGunLoc().getZ(),
                one.getGunAngle() );
            playerOneWindow.sameBullet = playerOneBullet;
            playerTwoWindow.enemyBullet = playerOneBullet;
        }
        if ( two.checkIfFired() && playerTwoBullet == null )
        {
            playerTwoBullet = new Bullet( two.getGunLoc().getX(),
                two.getGunLoc().getY(),
                two.getGunLoc().getZ(),
                two.getGunAngle() );
            playerOneWindow.enemyBullet = playerTwoBullet;
            playerTwoWindow.sameBullet = playerTwoBullet;
        }
        if ( playerOneBullet != null )
        {
            playerOneBullet.translate();

            if ( playerOneBullet.getY() > bottom )
            {
                playerOneBullet = null;
                playerOneWindow.sameBullet = null;
                playerTwoWindow.enemyBullet = null;
            }
            else if ( playerTwoTank.hasCollided( playerOneBullet ) )
            {
                playerTwoTank.onCollision( playerOneBullet );
                playerOneBullet = null;
                playerOneWindow.sameBullet = null;
                playerTwoWindow.enemyBullet = null;
            }

        }
        if ( playerTwoBullet != null )
        {
            playerTwoBullet.translate();

            if ( playerTwoBullet.getY() > bottom )
            {
                playerTwoBullet = null;
                playerOneWindow.enemyBullet = null;
                playerTwoWindow.sameBullet = null;
            }
            else if ( playerOneTank.hasCollided( playerTwoBullet ) )
            {
                playerOneTank.onCollision( playerTwoBullet );
                playerTwoBullet = null;
                playerOneWindow.enemyBullet = null;
                playerTwoWindow.sameBullet = null;
            }

        }
    }


    /**
     * Text that is displayed on the screen after the game has finished
     * 
     * @param winningPlayer
     *            which player has won; 1 for player one, 2 for player two, and
     *            0 if both tanks collided with each other and "exploded"
     */
    private void gameOver( int winningPlayer )
    {
        gameState = -1;
        background( 255 );
        textSize( 50 );
        if ( winningPlayer != 0 )
        {
            text( "Player " + winningPlayer + " wins!", width / 3, height / 2 );
        }
        else
        {
            text( "Game over", width / 3, height / 2 );
            text( "Both players exploded", width / 4, 2 * height / 3 );
        }
        playerOneWindow.setGameMode( 0 );
        playerTwoWindow.setGameMode( 0 );
        pauseGame();
    }


    /**
     * Method called by the system whenever a new key is pressed. This is used
     * for changing game states (start, pause, quit), as well as to change user
     * instructions. The variable key contains the key that was just pressed.
     */
    public void keyPressed()
    {
        if ( key == CODED )
        {
            if ( keyCode == UP )
            {
                playerOneData[0] = 1;
            }
            else if ( keyCode == DOWN )
            {
                playerOneData[1] = 1;
            }
            else if ( keyCode == LEFT )
            {
                playerOneData[2] = 1;
            }
            else if ( keyCode == RIGHT )
            {
                playerOneData[3] = 1;
            }
        }
        else
        {
            if ( key == '[' )
            {
                playerOneData[4] = 1;
            }
            else if ( key == ']' )
            {
                playerOneData[5] = 1;
            }
            else if ( key == '\\' )
            {
                playerOneData[6] = 1;
            }
            else if ( key == 'w' )
            {
                playerTwoData[0] = 1;
            }
            else if ( key == 's' )
            {
                playerTwoData[1] = 1;
            }
            else if ( key == 'a' )
            {
                playerTwoData[2] = 1;
            }
            else if ( key == 'd' )
            {
                playerTwoData[3] = 1;
            }
            else if ( key == '`' )
            {
                playerTwoData[4] = 1;
            }
            else if ( key == '1' )
            {
                playerTwoData[5] = 1;
            }
            else if ( key == '2' )
            {
                playerTwoData[6] = 1;
            }
            else if ( key == ' ' && gameState == 0 )
            {
                gameState = 1;
                startGame();
            }
            else if ( key == ' ' && gameState == 1 )
            {
                gameState = 2;
                pauseGame();
            }
            else if ( key == ' ' && gameState == 2 )
            {
                gameState = 1;
                resumeGame();
            }
            else if ( key == 'q' )
            {
                playerOneWindow.exit();
                playerTwoWindow.exit();
                exit();
            }

        }
    }


    /**
     * Method called by the system whenever a key is released. This is used to
     * reflect user instruction changes. The variable key contains the key that
     * was just released.
     */
    public void keyReleased()
    {
        if ( key == CODED )
        {
            if ( keyCode == UP )
            {
                playerOneData[0] = 0;
            }
            else if ( keyCode == DOWN )
            {
                playerOneData[1] = 0;
            }
            else if ( keyCode == LEFT )
            {
                playerOneData[2] = 0;
            }
            else if ( keyCode == RIGHT )
            {
                playerOneData[3] = 0;
            }
        }
        else
        {
            if ( key == '[' )
            {
                playerOneData[4] = 0;
            }
            else if ( key == ']' )
            {
                playerOneData[5] = 0;
            }
            else if ( key == '\\' )
            {
                playerOneData[6] = 0;
            }
            else if ( key == 'w' )
            {
                playerTwoData[0] = 0;
            }
            else if ( key == 's' )
            {
                playerTwoData[1] = 0;
            }
            else if ( key == 'a' )
            {
                playerTwoData[2] = 0;
            }
            else if ( key == 'd' )
            {
                playerTwoData[3] = 0;
            }
            else if ( key == '`' )
            {
                playerTwoData[4] = 0;
            }
            else if ( key == '1' )
            {
                playerTwoData[5] = 0;
            }
            else if ( key == '2' )
            {
                playerTwoData[6] = 0;
            }
        }
    }
}
