package graphics;

import io.TankPacket;
import math.Tank;
import processing.core.PApplet;


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
        parent.camera( 0,
            parent.height / 2,
            (float)dat.getLoc().getZ(),
            (float)dat.getLoc().getX(),
            parent.height / 2,
            0,
            0,
            1,
            0 );
    }
}
