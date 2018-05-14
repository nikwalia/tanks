package math;

/**
 * 
 * Class that mathematically represents the base of a tank
 *
 * @author Nikash Walia
 * @version May 5, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
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
     * @param d
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
        super( x, y, z, angle, l, w, h, Math.PI / 4 );
        updateCorners();
    }


    /**
     * 
     * updates the corners of the base; implements method from Collidable
     * interface
     */
    public void updateCorners()
    {
        baseRectangle[0] = new Value3D(
            getX() + length / 2 * Math.cos( getAngle() ) - width / 2 * Math.sin( getAngle() ),
            0,
            getZ() + length / 2 * Math.sin( getAngle() ) + width / 2 * Math.cos( getAngle() ) );
        baseRectangle[1] = new Value3D(
            getX() - length / 2 * Math.cos( getAngle() ) - width / 2 * Math.sin( getAngle() ),
            0,
            getZ() - length / 2 * Math.sin( getAngle() ) + width / 2 * Math.cos( getAngle() ) );
        baseRectangle[2] = new Value3D(
            getX() - length / 2 * Math.cos( getAngle() ) + width / 2 * Math.sin( getAngle() ),
            0,
            getZ() - length / 2 * Math.sin( getAngle() ) - width / 2 * Math.cos( getAngle() ) );
        baseRectangle[3] = new Value3D(
            getX() + length / 2 * Math.cos( getAngle() ) + width / 2 * Math.sin( getAngle() ),
            0,
            getZ() + length / 2 * Math.sin( getAngle() ) - width / 2 * Math.cos( getAngle() ) );
    }


    /**
     * 
     * Behavior after collision with other Collidable
     * 
     * @param other
     *            Collidable to interact with
     * @return status update to outside system
     */
    public int onCollision( Structure3D other )
    {
        if ( other instanceof Bullet )
        {
            other.onCollision( this );
            return -1;
        }
        else
        {
            return 1;
        }
    }


    /**
     * 
     * Checks if this has collided with another Collidable
     * 
     * @param other
     *            Collidable to check against for collision
     * @return whether or not the two have collided
     */
    public boolean hasCollided( Structure3D other )
    {
        double otherX;
        double otherZ;
        double areaOfBase = width * length;
        int max;
        if ( other.baseRectangle[2] == null )
        {
            max = 1;
        }
        else
        {
            max = 4;
        }

        for ( int i = 0; i < max; i++ )
        {
            otherX = other.baseRectangle[i].getX();
            otherZ = other.baseRectangle[i].getZ();

            if ( Math.sqrt( Math.pow( otherX - getX(), 2 ) + Math.pow( otherZ - getZ(), 2 ) ) > Math
                .sqrt( Math.pow( width / 2, 2 ) + Math.pow( length / 2, 2 ) ) )
            {
                continue;
            }
            double totalTriangleArea = 0;
            double s;
            double centerToFirstCorner = Math
                .sqrt( Math.pow( this.baseRectangle[0].getX() - otherX, 2 )
                    + Math.pow( this.baseRectangle[0].getZ() - otherZ, 2 ) );
            double centerToSecondCorner = Math
                .sqrt( Math.pow( this.baseRectangle[1].getX() - otherX, 2 )
                    + Math.pow( this.baseRectangle[1].getZ() - otherZ, 2 ) );
            double centerToThirdCorner = Math
                .sqrt( Math.pow( this.baseRectangle[2].getX() - otherX, 2 )
                    + Math.pow( this.baseRectangle[2].getZ() - otherZ, 2 ) );
            double centerToFourthCorner = Math
                .sqrt( Math.pow( this.baseRectangle[3].getX() - otherX, 2 )
                    + Math.pow( this.baseRectangle[3].getZ() - otherZ, 2 ) );

            double firstCornerToSecondCorner = Math
                .sqrt( Math.pow( this.baseRectangle[0].getX() - this.baseRectangle[1].getX(), 2 )
                    + Math.pow( this.baseRectangle[0].getZ() - this.baseRectangle[1].getZ(), 2 ) );
            double secondCornerToThirdCorner = Math
                .sqrt( Math.pow( this.baseRectangle[1].getX() - this.baseRectangle[2].getX(), 2 )
                    + Math.pow( this.baseRectangle[1].getZ() - this.baseRectangle[2].getZ(), 2 ) );
            double thirdCornerToFourthCorner = Math
                .sqrt( Math.pow( this.baseRectangle[2].getX() - this.baseRectangle[3].getX(), 2 )
                    + Math.pow( this.baseRectangle[2].getZ() - this.baseRectangle[3].getZ(), 2 ) );
            double fourthCornerToFirstCorner = Math
                .sqrt( Math.pow( this.baseRectangle[3].getX() - this.baseRectangle[0].getX(), 2 )
                    + Math.pow( this.baseRectangle[3].getZ() - this.baseRectangle[0].getZ(), 2 ) );

            s = ( centerToFirstCorner + centerToSecondCorner + firstCornerToSecondCorner ) / 2;
            totalTriangleArea += Math.sqrt( s * ( s - centerToFirstCorner )
                * ( s - centerToSecondCorner ) * ( s - firstCornerToSecondCorner ) );

            s = ( centerToSecondCorner + centerToThirdCorner + secondCornerToThirdCorner ) / 2;
            totalTriangleArea += Math.sqrt( s * ( s - centerToSecondCorner )
                * ( s - centerToThirdCorner ) * ( s - secondCornerToThirdCorner ) );

            s = ( centerToThirdCorner + centerToFourthCorner + thirdCornerToFourthCorner ) / 2;
            totalTriangleArea += Math.sqrt( s * ( s - centerToThirdCorner )
                * ( s - centerToFourthCorner ) * ( s - thirdCornerToFourthCorner ) );

            s = ( centerToFourthCorner + centerToFirstCorner + fourthCornerToFirstCorner ) / 2;
            totalTriangleArea += Math.sqrt( s * ( s - centerToFourthCorner )
                * ( s - centerToFirstCorner ) * ( s - fourthCornerToFirstCorner ) );

            if ( Math.abs( totalTriangleArea - areaOfBase ) < 0.01 )
            {
                return true;
            }
        }

        return false;
    }


    /**
     * 
     * Translates the base
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
                velocity += ACCELERATION * deltaTime * -1;
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
}
