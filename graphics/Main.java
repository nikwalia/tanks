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

    private static RunnerWindow playerOneWindow;

    private static RunnerWindow playerTwoWindow;


    public static void main( String[] args )
    {
        PApplet.main( "graphics.Main" );
        playerOneWindow.main( args );
        playerTwoWindow.main( args );
    }


    public void setup()
    {
        background = loadImage( "\\tanksactualforproject.jpg" );
        noTint();
        data = new LinkedList<TankPacket>();
        playerOneData = new int[7];
        playerTwoData = new int[7];
        playerOneTank = new Tank( 0, 0, 0, 0, 500, 50, 50, 5000, 3000, 1500, 1000 );
        playerTwoTank = new Tank( 100, 0, 100, 0, 500, 50, 50, 5000, 3000, 1500, 1000 );
        playerOneBullet = null;
        playerTwoBullet = null;
        data.add( playerOneTank.sendData() );
        data.add( playerTwoTank.sendData() );
        TankPacket playerOneInit = data.remove();
        System.out.println( "player one init: " + playerOneInit );
        TankPacket playerTwoInit = data.remove();
        System.out.println( "player two init: " + playerTwoInit );
        playerOneWindow = new RunnerWindow();
        playerOneWindow.init( playerOneTank, playerTwoTank, playerOneInit, playerTwoInit );
        playerTwoWindow = new RunnerWindow();
        playerTwoWindow.init( playerTwoTank, playerOneTank, playerTwoInit, playerOneInit );
    }


    public void settings()
    {
        size( 1920, 1080 );
    }


    public void draw()
    {
        background( background );
        textSize( 109 );
        text( "T-34", width / 6, 155 );
        fill( 11, 57, 198 );
        text( "        Tank Destroyers", width / 6, 155 );
        fill( 56, 114, 5 );
        PFont font;
        // String[] fontList = PFont.list();
        // printArray( fontList );
        font = createFont( "Verdana Bold", 196 );
        textFont( font );
        // font = createFont("LeagueGothic-Regular.otf",30);
        // text( "word", 10, 60 );
        // fill( 0, 102, 153, 51 );
        // text( "word", 10, 90 );]
        if ( playerOneWindow.initCalled && playerTwoWindow.initCalled && playerOneWindow.setupCalled
            && playerTwoWindow.setupCalled )
        {
            update();
        }
    }


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

        if ( p1.checkIfFired() && playerOneBullet == null )
        {
            playerOneBullet = new Bullet( p1.getGunLoc().getX(),
                p1.getGunLoc().getY(),
                p1.getGunLoc().getZ(),
                p1.getGunAngle() );
        }
        if ( p2.checkIfFired() && playerTwoBullet == null )
        {
            playerTwoBullet = new Bullet( p2.getGunLoc().getX(),
                p2.getGunLoc().getY(),
                p2.getGunLoc().getZ(),
                p2.getGunAngle() );
        }
        if ( playerOneBullet != null )
        {
            playerOneBullet.translate();
            if ( playerTwoTank.hasCollided( playerOneBullet ) )
            {
                playerTwoTank.onCollision( playerOneBullet );
                playerOneBullet = null;
            }

        }
        if ( playerTwoBullet != null )
        {
            playerTwoBullet.translate();
            if ( playerOneTank.hasCollided( playerTwoBullet ) )
            {
                playerOneTank.onCollision( playerTwoBullet );
                playerTwoBullet = null;
            }

        }

        if ( p1.getHitpoints() <= 0 )
        {
            gameOver( 2 );
        }
        else if ( p2.getHitpoints() <= 0 )
        {
            gameOver( 1 );
        }
    }


    public void gameOver( int winningPlayer )
    {
        playerOneWindow = null;
        playerTwoWindow = null;
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

        }
    }


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

    // public void textColor()
    // {
    // empty
    // }

    /**
     *
     * 
     * 
     * TEST THIS SECTION ONCE FINISHED WITH SCREEN INTEGRATION TEST THIS SECTION
     * ONCE FINISHED WITH SCREEN INTEGRATION TEST THIS SECTION ONCE FINISHED
     * WITH SCREEN INTEGRATION
     * 
     * 
     * 
     */

    // need to test once done with screen integration
    // int x;
    // include if key ! not coded - space bar is ascii (white space)
    // need to test once done with screen integration
    // boolean[] keys = new boolean[128];

    // need to test once done with screen integration
    // public void pressedKey()
    // {
    // if ( key == ' ' )
    // {
    // x++;
    // }
    // }

    // need to test once done with screen integration
    // public void keyPressed()
    // {
    // keys[key] = true;
    // }

    // public void keyReleased()
    // {
    // keys[key] = false;
    //
    // }

}
