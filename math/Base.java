package math;

/**
 * 
 * Class that mathematically represents the base of a tank
 *
 * @author Nikash Walia, Maithreyee Vatsan, Roopak Phatak
 * @version May 5, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 * @author Sources: none
 */
public class Base extends Structure3D
{

    /**
     * Constructor for Base
     * 
     * @param x
     *            x-coordinate of center
     * @param y
     *            y-coordinate of center
     * @param z
     *            z-coordinate of center
     * @param angle
     *            angle of structure
     * @param l
     *            length of base
     * @param w
     *            width of base
     * @param h
     *            height of base
     */
    public Base( int x, int y, int z, double angle, int l, int w, int h )
    {
        super( x, y, z, angle, l, w, h, Math.PI / 8 );
    }


    /**
     * 
     * Translates the base according to its various properties. Changes include
     * setting angular velocity, updating the timestamp, accelerating or
     * decelerating the structure, and then changing its position and angle
     * accordingly.
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

        if ( getMoveDirection() == 0 && Math.abs( velocity - ZEROVELOCITY ) > 0.1 )
        {
            if ( velocity < ZEROVELOCITY )
            {
                velocity += ACCELERATION * deltaTime;
                if ( velocity > ZEROVELOCITY )
                {
                    velocity = ZEROVELOCITY;
                }
            }
            else
            {
                velocity += -ACCELERATION * deltaTime;
                if ( velocity < ZEROVELOCITY )
                {
                    velocity = ZEROVELOCITY;
                }
            }
        }
        else
        {
            if ( !( getMoveDirection() == -1 && Math.abs( velocity - MINVELOCITY ) < 0.1 )
                && !( getMoveDirection() == 1 && Math.abs( velocity - MAXVELOCITY ) < 0.1 ) )
            {
                velocity += ACCELERATION * deltaTime * getMoveDirection();
                if ( Math.abs( velocity ) > MAXVELOCITY )
                {
                    velocity = MAXVELOCITY * getMoveDirection();
                }
            }
        }

        changeAngle( angularVelocity * deltaTime );
        changeX( velocity * deltaTime * Math.cos( getAngle() ) );
        changeZ( velocity * deltaTime * Math.sin( getAngle() ) );

        curTime = newTime;
    }


    /**
     * 
     * method for checking whether or not the base has collided with another.
     * Uses an approximation of distance from center to center and compares it
     * with half of the hypotenuse of the base's bottom rectangle
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
     * Updates the "last time" timestamp of the class- used for pausing
     */
    protected void updateTime()
    {
        curTime = System.nanoTime() / 1e+9;
    }
}
