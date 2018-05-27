package graphics;

import io.TankPacket;
import processing.core.PApplet;


/**
 * 
 * Class that places a camera in the graphics environment
 *
 * @author Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 * @version May 9, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class CameraTank
{
    private PApplet parent;

    private TankPacket dat;


    /**
     * 
     * Constructor for CameraTank class
     * 
     * @param p
     *            environment to place camera in
     * @param init
     *            initial data of tank
     */
    public CameraTank( PApplet p, TankPacket init )
    {
        parent = p;
        dat = init;
    }


    /**
     * 
     * Updates to the latest data
     * 
     * @param dat
     *            new TankPacket
     */
    protected void update( TankPacket dat )
    {
        this.dat = dat;
    }


    /**
     * 
     * Method called by environment to place the camera
     */
    protected void display()
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
