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

    private int bottom = 100;


    // finished
    public static void main( String[] args )
    {
        PApplet.main( "graphics.Main" );
    }


    // TODO finish
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

        playerOneTank = new Tank( 0, 0, 0, 0, 50, 5, 5, 500, 300, 150, 1000 );
        playerTwoTank = new Tank( 100, 0, 100, 0, 50, 5, 5, 500, 300, 150, 1000 );

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


    public void settings()
    {
        size( 960, 540 );
    }


    // TODO finish
    public void draw()
    {
        // font = createFont("LeagueGothic-Regular.otf",30);
        // text( "word", 10, 60 );
        // fill( 0, 102, 153, 51 );
        // text( "word", 10, 90 );]

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
                compassView();
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
    public void compassView()
    {
        background( 255 );
    }


    // finished
    public void startGame()
    {
        playerOneTank.reset();
        playerTwoTank.reset();
        playerOneWindow.setGameMode( 1 );
        playerTwoWindow.setGameMode( 1 );
    }


    public void pauseGame()
    {
        playerOneWindow.noLoop();
        playerTwoWindow.noLoop();
    }


    public void resumeGame()
    {
        playerOneWindow.loop();
        playerTwoWindow.loop();
    }


    public void resetGame()
    {
        playerOneTank.reset();
        playerTwoTank.reset();
        data.add( playerOneTank.sendData() );
        data.add( playerTwoTank.sendData() );
        TankPacket one = data.remove();
        TankPacket two = data.remove();
        playerOneWindow.update( one, two );
        playerTwoWindow.update( two, one );
        playerOneWindow.setGameMode( 0 );
        playerTwoWindow.setGameMode( 0 );
    }


    // finished
    public void update()
    {
        playerOneTank.receiveData( new SystemPacket( playerOneData[6] == 1,
            playerOneData[2] - playerOneData[3],
            playerOneData[0] - playerOneData[1],
            playerOneData[4] - playerOneData[5] ) );
        playerTwoTank.receiveData( new SystemPacket( playerTwoData[6] == 1,
            playerTwoData[2] - playerTwoData[3],
            playerTwoData[0] - playerTwoData[1],
            playerTwoData[4] - playerTwoData[5] ) );
        playerOneTank.update();
        playerTwoTank.update();
        data.add( playerOneTank.sendData() );
        data.add( playerTwoTank.sendData() );
        TankPacket p1 = data.remove();
        TankPacket p2 = data.remove();

        playerOneWindow.update( p1, p2 );
        playerTwoWindow.update( p2, p1 );

        checkBulletState( p1, p2 );

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
    public void checkBulletState( TankPacket one, TankPacket two )
    {
        if ( one.checkIfFired() && playerOneBullet == null )
        {
            playerOneBullet = new Bullet( one.getGunLoc().getX(),
                one.getGunLoc().getY(),
                one.getGunLoc().getZ(),
                one.getGunAngle() );
        }
        if ( two.checkIfFired() && playerTwoBullet == null )
        {
            playerTwoBullet = new Bullet( two.getGunLoc().getX(),
                two.getGunLoc().getY(),
                two.getGunLoc().getZ(),
                two.getGunAngle() );
        }
        if ( playerOneBullet != null )
        {
            playerOneBullet.translate();

            if ( playerOneBullet.getY() > bottom )
            {
                playerOneBullet = null;
            }

            if ( playerTwoTank.hasCollided( playerOneBullet ) )
            {
                playerTwoTank.onCollision( playerOneBullet );
                playerOneBullet = null;
            }

        }
        if ( playerTwoBullet != null )
        {
            playerTwoBullet.translate();

            if ( playerTwoBullet.getY() > bottom )
            {
                playerTwoBullet = null;
            }

            if ( playerOneTank.hasCollided( playerTwoBullet ) )
            {
                playerOneTank.onCollision( playerTwoBullet );
                playerTwoBullet = null;
            }

        }
    }


    // TODO finish
    public void gameOver( int winningPlayer )
    {
        playerOneTank.reset();
        playerTwoTank.reset();
    }


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
