package graphics;

import java.util.LinkedList;
import java.util.Queue;

import io.SystemPacket;
import io.TankPacket;
import math.Tank;
import processing.core.PApplet;


public class Main extends PApplet
{
    // private static Base tank;

    private Queue<TankPacket> data;

    // indices
    // 0- forward pressed
    // 1- backward pressed
    // 2- left pressed
    // 3- right pressed
    // 4- gun left pressed
    // 5- gun right pressed
    // 6- fire pressed
    private boolean[] playerOneData;
    private boolean[] playerTwoData;
    private Tank playerOneTank;
    private Tank playerTwoTank;
    private static GraphicsTank playerOneGraphicsTank;
    private static GraphicsTank playerTwoGraphicsTank;
    private static CameraTank playerOneCameraTank;
    private static CameraTank playerTwoCameraTank;

    public Main()
    {
        data = new LinkedList<TankPacket>();
        playerOneData = new boolean[7];
        playerTwoData = new boolean[7];
        playerOneTank = new Tank(0, 0, 0, 0, 500, 50, 50, 5000, 3000, 1500, 1000);
        playerTwoTank = new Tank(100, 0, 100, 0, 500, 50, 50, 5000, 3000, 1500, 1000);
        playerOneGraphicsTank = new GraphicsTank(playerOneTank);
        playerTwoGraphicsTank = new GraphicsTank(playerTwoTank);
        playerOneCameraTank = new CameraTank(playerOneTank);
        playerTwoCameraTank = new CameraTank(playerTwoTank);
    }


    public static void main( String[] args )
    {
        PApplet.main( "graphics.Main" );
        RunnerWindow w1 = new RunnerWindow(playerTwoGraphicsTank, playerOneCameraTank);
        RunnerWindow w2 = new RunnerWindow(playerOneGraphicsTank, playerTwoCameraTank);
        w1.main( args );
        w2.main( args );
    }


    public void setup()
    {

    }


    public void settings()
    {

    }


    public void draw()
    {
        update();
    }
    
    public void update()
    {
        
    }


    public void keyPressed()
    {
        if ( key == CODED )
        {
            if ( keyCode == UP )
            {
                playerOneData[0] = true;
            }
            else if ( keyCode == DOWN )
            {
                playerOneData[1] = true;
            }
            else if ( keyCode == LEFT )
            {
                playerOneData[2] = true;
            }
            else if ( keyCode == RIGHT )
            {
                playerOneData[3] = true;
            }
        }
        else
        {
            if ( key == '[' )
            {
                playerOneData[4] = true;
            }
            else if ( key == ']' )
            {
                playerOneData[5] = true;
            }
            else if ( key == '\\' )
            {
                playerOneData[6] = true;
            }
            else if (key == 'w')
            {
                playerTwoData[0] = true;
            }
            else if (key == 's')
            {
                playerTwoData[1] = true;
            }
            else if (key == 'a')
            {
                playerTwoData[2] = true;
            }
            else if (key == 'd')
            {
                playerTwoData[3] = true;
            }
            else if (key == '`')
            {
                playerTwoData[4] = true;
            }
            else if (key == '1')
            {
                playerTwoData[5] = true;
            }
            else if (key == '2')
            {
                playerTwoData[6] = true;
            }

        }
    }


    public void keyReleased()
    {
        if ( key == CODED )
        {
            if ( keyCode == UP )
            {
                playerOneData[0] = false;
            }
            else if ( keyCode == DOWN )
            {
                playerOneData[1] = false;
            }
            else if ( keyCode == LEFT )
            {
                playerOneData[2] = false;
            }
            else if ( keyCode == RIGHT )
            {
                playerOneData[3] = false;
            }
        }
        else
        {
            if ( key == '[' )
            {
                playerOneData[4] = false;
            }
            else if ( key == ']' )
            {
                playerOneData[5] = false;
            }
            else if ( key == '\\' )
            {
                playerOneData[6] = false;
            }
            else if (key == 'w')
            {
                playerTwoData[0] = false;
            }
            else if (key == 's')
            {
                playerTwoData[1] = false;
            }
            else if (key == 'a')
            {
                playerTwoData[2] = false;
            }
            else if (key == 'd')
            {
                playerTwoData[3] = false;
            }
            else if (key == '`')
            {
                playerTwoData[4] = false;
            }
            else if (key == '1')
            {
                playerTwoData[5] = false;
            }
            else if (key == '2')
            {
                playerTwoData[6] = false;
            }
        }
    }

}
