package math;

/**
 * 
 * Class that mathematically represents a bullet
 *
 * @author Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 * @version May 9, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class Bullet extends Structure3D
{

    private static final double FALL_ACCELERATION = 20;

    private double fallVelocity = 0;


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
        setVelocity( 1000 );
        setMoveDirection( 1 );
    }


    /**
     * 
     * Translates the bullet. The bullet always moves in a straight line through
     * the XZ plane, however, it falls through the Y plane due to gravity.
     */
    public void translate()
    {
        double newTime = System.nanoTime() / 1e+9;
        double deltaTime = newTime - curTime;
        changeX( velocity * deltaTime * Math.cos( getAngle() ) );
        fallVelocity += FALL_ACCELERATION * deltaTime;
        changeY( fallVelocity * deltaTime );
        changeZ( velocity * deltaTime * Math.sin( getAngle() ) );
        curTime = newTime;
    }


    /**
     * 
     * Checks if the bullet has collided with another Structure3D
     * 
     * @param other
     *            the Structure3D to check against
     * @return whether or not the two have collided
     */
    public int hasCollided( Structure3D other )
    {
        return other.hasCollided( this );
    }


    /**
     * 
     * Getter method for the fall velocity, used only for testing
     * 
     * @return fall velocity
     */
    protected double getFallVelocity()
    {
        return fallVelocity;
    }
}
