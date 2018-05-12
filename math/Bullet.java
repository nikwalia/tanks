package math;

/**
 * 
 * Class that mathematically represents a bullet
 *
 * @author Nikash Walia
 * @version May 9, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: 2
 */
public class Bullet extends Structure3D
{

    /**
     * Constructor for bullet
     * 
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @param z
     *            z-coordinate
     * @param angle
     *            angle of movement
     */
    public Bullet( double x, double y, double z, double angle )
    {
        super( x, y, z, angle, 1, 1, 1, 0 );
        baseRectangle[0] = new Value3D( x, y, z );
        setVelocity( 100 );
        setMoveDirection( 1 );
        baseRectangle[1] = null;
        baseRectangle[2] = null;
        baseRectangle[3] = null;
    }


    /**
     * 
     * Checks if the bullet has collided with another Structure3D
     * 
     * @param other
     *            the Structure3D to check against
     * @return whether or not the two have collided
     */
    public boolean hasCollided( Structure3D other )
    {
        return other.hasCollided( this );
    }


    /**
     * 
     * End behavior after collision
     * 
     * @param other
     *            Structure3D to interact with
     * @return update to system
     */
    public int onCollision( Structure3D other )
    {
        setVelocity( 0 );
        super.changeX( -getX() );
        super.changeY( -getY() );
        super.changeZ( -getZ() );
        super.changeAngle( -getAngle() );
        return 0;
    }


    /**
     * 
     * Translates the bullet
     */
    public void translate()
    {
        double newTime = System.nanoTime() / 1e+9;
        double deltaTime = newTime - curTime;
        changeX( velocity * deltaTime * Math.cos( getAngle() ) );
        changeZ( velocity * deltaTime * Math.sin( getAngle() ) );
        curTime = newTime;
    }


    /**
     * 
     * Updates the location of the bullet
     */
    public void updateCorners()
    {
        baseRectangle[0] = new Value3D( getX(), getY(), getZ() );
    }
}
