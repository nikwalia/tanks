package graphics;

import math.Base;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class GraphicsTank
{

    private Base tank;


    public GraphicsTank( Base t )
    {
        tank = t;
    }


    public void keyPressed( KeyEvent e )
    {

        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_LEFT )
        {
            tank.translate();
            tank.updateCorners();
        }

        if ( key == KeyEvent.VK_RIGHT )
        {
            tank.translate();
            tank.updateCorners();
        }

        if ( key == KeyEvent.VK_UP )
        {
            tank.translate();
            tank.updateCorners();
        }

        if ( key == KeyEvent.VK_DOWN )
        {
            tank.translate();
            tank.updateCorners();
        }
    }


    public void keyReleased( KeyEvent e )
    {

        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_LEFT )
        {
            tank.translate();
            tank.updateCorners();
        }

        if ( key == KeyEvent.VK_RIGHT )
        {
            tank.translate();
            tank.updateCorners();
        }

        if ( key == KeyEvent.VK_UP )
        {
            tank.translate();
            tank.updateCorners();
        }

        if ( key == KeyEvent.VK_DOWN )
        {
            tank.translate();
            tank.updateCorners();
        }
    }

}
