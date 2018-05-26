package math;

/**
 * 
 * Class that mathematically represents the gun of a tank
 *
 * @author Nikash Walia
 * @version May 9, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class Gun extends Structure3D
{
    private int baseTurnState;

    private Value3D baseCenter;


    /**
     * Constructor for Gun class
     * 
     * @param angle
     *            initial angle of the gun
     * @param l
     *            length of the gun
     * @param w
     *            width of the gun
     * @param h
     *            height of the gun
     * @param b
     *            location of the base of the tank
     * @param baseHeight
     *            height of the base
     */
    public Gun( double angle, int l, int w, int h, Value3D b, int baseHeight )
    {
        super( b.getX(), b.getY() - h / 2 - baseHeight / 2, b.getZ(), angle, l, w, h, Math.PI / 4 );
        baseTurnState = 0;
        baseCenter = b;
        updateCenter();
    }


    /**
     * 
     * Translates the gun
     */
    public void translate()
    {
        double newTime = System.nanoTime() / 1e+9;
        double deltaTime = newTime - curTime;

        if ( getTurnDirection() == 0 )
        {
            angularVelocity = ZEROANGULARVELOCITY;
        }
        else if ( getTurnDirection() == -1 )
        {
            angularVelocity = minAngularVelocity;
        }
        else
        {
            angularVelocity = maxAngularVelocity;
        }
        angularVelocity += baseTurnState * Math.PI / 8;
        changeAngle( angularVelocity * deltaTime );

        updateCenter();
        curTime = newTime;
    }


    /**
     * 
     * method for checking whether or not the base has collided with another
     * 
     * @param other
     *            structure to check against
     * @return corner that has collided, or -1 if no collision
     */
    public int hasCollided( Structure3D other )
    {
        if ( Math.sqrt(
            Math.pow( other.getX() - getX(), 2 ) + Math.pow( other.getZ() - getZ(), 2 ) ) < 1.5
                * Math.sqrt( Math.pow( length / 2, 2 ) + Math.pow( width / 2, 2 ) ) )
        {
            return 1;
        }
        return -1;
    }


    /**
     * 
     * Sets the state of the base's turn
     * 
     * @param state
     *            the direction of the base's turn
     */
    public void setBaseTurn( int state )
    {
        baseTurnState = state;
    }


    /**
     * 
     * Sets the center of the base so that the gun's position can be calculated
     * 
     * @param baseCenter
     *            center of base
     */
    public void setBaseCenter( Value3D baseCenter )
    {
        this.baseCenter = baseCenter;
    }


    /**
     * 
     * Updates the center of the gun based on its angle, it's length, and the
     * base's center
     */
    public void updateCenter()
    {
        this.setX( baseCenter.getX() + getLength() / 2 * Math.cos( getAngle() ) );
        this.setZ( baseCenter.getZ() + getLength() / 2 * Math.sin( getAngle() ) );
    }


    public void updateTime()
    {
        curTime = System.nanoTime() / 1e+9;
    }
}
