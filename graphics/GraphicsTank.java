package graphics;

import io.TankPacket;
import math.Tank;
import processing.core.PApplet;


public class GraphicsTank
{
    Tank myTank;
    PApplet parent;
    TankPacket dat;


    public GraphicsTank( Tank t, PApplet p, TankPacket init )
    {
        myTank = t;
        parent = p;
        dat = init;
    }
    
    public void init (TankPacket dat)
    {
        this.dat = dat;
    }
    
    public void display()
    {
        parent.pushMatrix();
        parent.translate( (float)dat.getLoc().getX(),
            (float)dat.getLoc().getY(),
            (float)dat.getLoc().getZ() );
        parent.rotateY( (float)dat.getAngle() );
    }
}
