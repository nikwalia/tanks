package graphics;

import java.awt.event.KeyListener;

import io.SystemPacket;
import io.TankPacket;
import math.Base;

import java.awt.event.KeyEvent;


public class Main
{
//    private static Base tank;

    private SystemPacket sys;
    private TankPacket pack;


    public Main()
    {
//        tank = t;
    }


    public static void keyPressed( KeyEvent e )
    {

        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_LEFT )
        {
//            sys.calledTurn();
            System.out.println( "left" );
        }

        if ( key == KeyEvent.VK_RIGHT )
        {
//            sys.calledTurn();
            System.out.println( "right" );
        }

        if ( key == KeyEvent.VK_UP )
        {
//            sys.calledMove();
            System.out.println( "up" );
        }

        if ( key == KeyEvent.VK_DOWN )
        {
//            sys.calledMove();
            System.out.println( "down" );
        }
    }


    public void keyReleased( KeyEvent e )
    {

        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_LEFT )
        {
            System.out.println( "left released" );
        }

        if ( key == KeyEvent.VK_RIGHT )
        {
//            tank.translate();
//            tank.updateCorners();
            System.out.println( "right released" );
        }

        if ( key == KeyEvent.VK_UP )
        {
//            tank.translate();
//            tank.updateCorners();
            System.out.println( "up released" );
        }

        if ( key == KeyEvent.VK_DOWN )
        {
//            tank.translate();
//            tank.updateCorners();
            System.out.println( "down released" );
        }
    }
}
