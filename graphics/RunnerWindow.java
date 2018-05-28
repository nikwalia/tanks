package graphics;

import io.TankPacket;
import math.Mine;
import math.Bullet;
import math.Tank;
import processing.core.PApplet;


/**
 * 
 * Window in which camera is placed and tank is drawn
 *
 * @author Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 * @version May 9, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class RunnerWindow extends PApplet
{
    private Tank enemy;

    private GraphicsTank enemyGraphics;

    private CameraTank sameCamera;

    private TankPacket initE;

    private TankPacket initS;

    /**
     * whether or not the init method has been called
     */
    public boolean initCalled = false;

    /**
     * whether or not the setup method has been called
     */
    public boolean setupCalled = false;

    /**
     * whether or not the countdown is running
     */
    public boolean countdownRunning = true;

    private double initTime = System.nanoTime() / 1e+9;

    private int playerNumber;

    private int gameMode;

    /**
     * bullet fired by same tank
     */
    protected Bullet sameBullet;

    /**
     * bullet fired by enemy tank
     */
    protected Bullet enemyBullet;

    private double worldCenterX;

    private double worldCenterZ;

    private double worldAngle;

    private Mine[] buildings;


    /**
     * 
     * Initializes the various fields of the window from externally received
     * data
     * 
     * @param e
     *            enemy tank
     * @param initSame
     *            initial TankPacket of player whose view is represented by this
     *            window
     * @param initEnemy
     *            initial TankPacket of player whose tank is visible in this
     *            window
     * @param playerNumber
     *            which player window this is
     * @param b
     *            array of mines
     */
    public void init(
        Tank e,
        TankPacket initSame,
        TankPacket initEnemy,
        int playerNumber,
        Mine[] b )
    {
        enemy = e;
        initE = initEnemy;
        initS = initSame;
        initCalled = true;
        this.playerNumber = playerNumber;
        gameMode = 0;
        worldCenterX = initSame.getLoc().getX();
        worldCenterZ = initSame.getLoc().getZ();
        worldAngle = initSame.getGunAngle();
        buildings = b;
    }


    /**
     * 
     * Opens the window
     * 
     * @param args
     *            not used
     */
    public static void main( String[] args )
    {
        PApplet.main( "graphics.RunnerWindow" );
    }


    /**
     * Updates the two GUI tanks, as well as relative world information
     * 
     * @param same
     *            TankPacket for the tank for this player
     * @param enemy
     *            TankPacket for the tank for the opposite player
     */
    public void update( TankPacket same, TankPacket enemy )
    {
        sameCamera.update( same );
        enemyGraphics.update( enemy );
        worldCenterX = same.getLoc().getX();
        worldCenterZ = same.getLoc().getZ();
        worldAngle = same.getGunAngle();
    }


    /**
     * 
     * Setup method- called by the environment, initializes the tanks
     */
    public void setup()
    {
        enemyGraphics = new GraphicsTank( enemy, this, initE );
        sameCamera = new CameraTank( this, initS );
        setupCalled = true;
        surface.setTitle( "PLAYER " + playerNumber );
    }


    /**
     * 
     * Defines the window's dimensions and renderer
     */
    public void settings()
    {
        size( 500, 500, P3D );
    }


    /**
     * 
     * Draws the ground relative to the position of the camera
     */
    private void drawGround()
    {
        pushMatrix();
        rectMode( CENTER );
        translate( -100 + (float)worldCenterX, 425, -100 + (float)worldCenterZ );
        fill( 139, 69, 19 );
        noStroke();
        rotateX( (float)Math.PI / 2 );
        rotateZ( (float)worldAngle );
        rect( 0, 0, 1000000, 1000000 );
        popMatrix();
    }


    /**
     * 
     * Draws the bullets, if they exist
     */
    private void drawBullets()
    {
        noStroke();
        if ( sameBullet != null )
        {
            pushMatrix();
            translate( (float)sameBullet.getX(),
                (float)sameBullet.getY(),
                (float)sameBullet.getZ() );
            sphere( 10 );
            fill( 255 );
            popMatrix();
        }
        if ( enemyBullet != null )
        {
            pushMatrix();
            translate( (float)enemyBullet.getX(),
                (float)enemyBullet.getY(),
                (float)enemyBullet.getZ() );
            sphere( 10 );
            fill( 255 );
            popMatrix();
        }
    }


    /**
     * 
     * Draws the various mines
     */
    private void drawMines()
    {
        for ( int i = 0; i < buildings.length; i++ )
        {
            pushMatrix();
            translate( (float)buildings[i].getX(),
                (float)buildings[i].getY(),
                (float)buildings[i].getZ() );
            fill( 255, 0, 0 );
            stroke( 10 );
            sphere( (float)buildings[i].getLength() );
            popMatrix();
        }
    }


    /**
     * 
     * Runs the countdown at the start of the game
     */
    private void countdown()
    {
        textSize( 50 );
        text( "Player " + playerNumber, width / 2 - 50, height / 2, 0 );
        text( (int)( 20 - ( System.nanoTime() / 1e+9 - initTime ) ),
            width / 2,
            height / 2 + 50,
            0 );
    }


    /**
     * 
     * Draw method, periodically called by the environment, calls all methods
     * that draw things
     */
    public void draw()
    {
        background( 135, 206, 235 );
        if ( gameMode != 0 )
        {
            if ( initCalled && setupCalled )
//                            && System.nanoTime() / 1e+9 - initTime > 20 )
            {
                countdownRunning = false;
                drawGround();
                stroke( 10 );
                enemyGraphics.display();
                sameCamera.display();
                drawBullets();
                drawMines();
            }
            else
            {
                countdown();
            }
        }
        else
        {
            text( "Player " + playerNumber, width / 2 - 50, height / 2, 0 );
            initTime = System.nanoTime() / 1e+9;
        }
    }


    /**
     * 
     * Changes the game mode to run or to not run
     * 
     * @param g
     *            gameMode
     */
    protected void setGameMode( int g )
    {
        if ( g == 1 && gameMode == 0 )
        {
            initTime = System.nanoTime() / 1e+9;
        }
        gameMode = g;
    }
}
