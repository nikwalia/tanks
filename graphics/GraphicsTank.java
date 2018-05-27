package graphics;

import io.TankPacket;
import math.Tank;
import processing.core.PApplet;


/**
 * 
 * Class that draws a tank in the graphics environment
 *
 * @author Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 * @version May 9, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class GraphicsTank
{
    private Tank myTank;

    private PApplet parent;

    private TankPacket dat;


    /**
     * 
     * Constructor for GraphicsTank
     * 
     * @param t
     *            tank to be drawn
     * @param p
     *            graphics environment to draw tank in
     * @param init
     *            initial data about tank
     */
    public GraphicsTank( Tank t, PApplet p, TankPacket init )
    {
        myTank = t;
        parent = p;
        dat = init;
    }


    /**
     * 
     * Updates to the latest tank data
     * 
     * @param dat
     *            latest TankPacket
     */
    protected void update( TankPacket dat )
    {
        this.dat = dat;
    }


    /**
     * 
     * Draws the tank in the graphics environment
     */
    protected void display()
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
    }
}
