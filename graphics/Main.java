package graphics;

import java.util.LinkedList;
import java.util.Queue;

import io.SystemPacket;
import io.TankPacket;
import math.Bullet;
import math.Tank;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class Main extends PApplet
{
    // private static Base tank;

    PImage background;

    private Queue<TankPacket> data;

    // indices
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


    // finished
    public static void main( String[] args )
    {
        PApplet.main( "graphics.Main" );
    }


    // finished
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

        playerOneTank = new Tank( 0, 350, 0, 0, 500, 50, 50, 500, 300, 150, 1000 );
        playerTwoTank = new Tank( 1000, 350, 0, Math.PI, 500, 50, 50, 500, 300, 150, 1000 );

        playerOneBullet = null;
        playerTwoBullet = null;

        data.add( playerOneTank.sendData() );
        data.add( playerTwoTank.sendData() );
        TankPacket playerOneInit = data.remove();
        TankPacket playerTwoInit = data.remove();

        playerOneWindow.init( playerOneTank, playerTwoTank, playerOneInit, playerTwoInit, 1 );
        playerTwoWindow.init( playerTwoTank, playerOneTank, playerTwoInit, playerOneInit, 2 );

        String[] args = { "" };
        PApplet.runSketch( args, playerOneWindow );
        PApplet.runSketch( args, playerTwoWindow );
    }


    // finished
    public void settings()
    {
        size( 960, 540 );
    }


    // finished
    public void draw()
    {
        if ( gameState == 0 )
        {
            homeScreen();
        }
        else
        {
            if ( playerOneWindow.initCalled && playerTwoWindow.initCalled
                && playerOneWindow.setupCalled && playerTwoWindow.setupCalled )
            {
                update();
            }
        }
    }


    // finished
    public void homeScreen()
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


    // finished
    public void controls()
    {
        textSize( 20 );
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


    // finished
    public void startGame()
    {
        playerOneWindow.setGameMode( 1 );
        playerTwoWindow.setGameMode( 1 );
    }


    // finished
    public void pauseGame()
    {
        playerOneWindow.noLoop();
        playerTwoWindow.noLoop();
    }


    // finished
    public void resumeGame()
    {
        playerOneWindow.loop();
        playerTwoWindow.loop();
        playerOneTank.resume();
        playerTwoTank.resume();
    }


    // finished
    public void update()
    {
        playerOneTank.receiveData( new SystemPacket( playerOneData[6] == 1,
            playerOneData[2] - playerOneData[3],
            playerOneData[0] - playerOneData[1],
            playerOneData[5] - playerOneData[4] ) );
        playerTwoTank.receiveData( new SystemPacket( playerTwoData[6] == 1,
            playerTwoData[2] - playerTwoData[3],
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

        checkTankState();

        if ( p1.getHitpoints() <= 0 )
        {
            gameOver( 2 );
        }
        else if ( p2.getHitpoints() <= 0 )
        {
            gameOver( 1 );
        }
    }


    // TODO finish
    public void checkTankState()
    {
        if ( playerOneTank.hasCollided( playerTwoTank ) )
        {
            playerOneTank.onCollision( playerTwoTank );
            playerTwoTank.onCollision( playerOneTank );
        }
    }


    // finished
    public void checkBulletState( TankPacket one, TankPacket two )
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


    // finished
    public void gameOver( int winningPlayer )
    {
        gameState = -1;
        background( 255 );
        textSize( 50 );
        text( "Player " + winningPlayer + " wins!", width / 3, height / 2 );
        playerOneWindow.setGameMode( 0 );
        playerTwoWindow.setGameMode( 0 );
        pauseGame();
    }


    // finished
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


    // finished
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
