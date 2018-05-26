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
        parent.fill( 100 );
        parent.pushMatrix();
        parent.translate( (float)dat.getLoc().getX(),
            (float)dat.getLoc().getY(),
            (float)dat.getLoc().getZ() );
        parent.rotateY( -(float)dat.getAngle() );
        parent.box( myTank.baseLength, myTank.baseHeight, myTank.baseWidth );
        parent.popMatrix();
        parent.fill( 200 );
        parent.pushMatrix();
        parent.translate( (float)dat.getGunLoc().getX(),
            (float)dat.getGunLoc().getY(),
            (float)dat.getGunLoc().getZ() );
        parent.rotateY( -(float)dat.getGunAngle() );
        parent.box( myTank.gunLength, myTank.gunHeight, myTank.gunWidth );
        parent.popMatrix();
        parent.pushMatrix();
        parent.translate( (float)dat.getLoc().getX(),
            (float)dat.getGunLoc().getY(),
            (float)dat.getLoc().getZ() );
        parent.rotateY( -(float)dat.getGunAngle() );
        parent.box( 100 );
        parent.popMatrix();
        
        parent.camera( (float)dat.getGunLoc().getX() - 500,
            (float)dat.getGunLoc().getY() - 250,
            (float)dat.getGunLoc().getZ(),
            (float)( 200 * Math.cos( dat.getGunAngle() ) + dat.getGunLoc().getX() ),
            parent.height / 2,
            (float)( 200 * Math.sin( dat.getGunAngle() ) + dat.getGunLoc().getZ() ),
            0,
            1,
            0 );
    }
}
