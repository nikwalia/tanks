package graphics;

import io.TankPacket;
import math.Tank;
import processing.core.PApplet;

/**
 * 
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  mvats
 *  @version May 26, 2018
 *  @author  Period: TODO
 *  @author  Assignment: Tanks
 *
 *  @author  Sources: TODO
 */
public class CameraTank
{
    Tank myTank;

    PApplet parent;

    TankPacket dat;


    public CameraTank( Tank t, PApplet p, TankPacket init )
    {
        myTank = t;
        parent = p;
        dat = init;
    }


    public void update( TankPacket dat )
    {
        this.dat = dat;
    }


    public void display()
    {
        parent.camera( (float)dat.getGunLoc().getX(),
            (float)dat.getGunLoc().getY(),
            (float)dat.getGunLoc().getZ(),
            (float)( 5000 * Math.cos( dat.getGunAngle() ) + dat.getGunLoc().getX() ),
            parent.height / 2,
            (float)( 5000 * Math.sin( dat.getGunAngle() ) + dat.getGunLoc().getZ() ),
            0,
            1,
            0 );
    }
}
