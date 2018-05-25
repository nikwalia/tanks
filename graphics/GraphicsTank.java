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


    public void update( TankPacket dat )
    {
        this.dat = dat;
    }


    public void display()
    {
        parent.fill( 255 );
        parent.pushMatrix();
        parent.translate( (float)dat.getLoc().getX(),
            (float)dat.getLoc().getY(),
            (float)dat.getLoc().getZ() );
        parent.rotateY( (float)dat.getAngle() );
        parent.box( myTank.baseLength, myTank.baseHeight, myTank.baseWidth );
        parent.popMatrix();
        parent.pushMatrix();
        parent.translate( (float)dat.getGunLoc().getX(),
            (float)dat.getGunLoc().getY(),
            (float)dat.getGunLoc().getZ() );
        parent.rotateY( -(float)dat.getGunAngle() );
        parent.box( myTank.gunLength, myTank.gunHeight, myTank.gunWidth );
        parent.popMatrix();
    }
}
