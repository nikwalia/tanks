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
    public Gun( int angle, int l, int w, int h, Value3D b, int baseHeight )
    {
        super( b.getX(), b.getY() + h / 2 + baseHeight / 2, b.getZ(), angle, l, w, h, Math.PI / 4 );
        baseTurnState = 0;
        baseCenter = b;
        updateCenter();
    }


    /**
     * 
     * Determines the end behavior of the gun
     * 
     * @param other
     *            Structure3D to interact with
     * @return status update to the system
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
     * Updates the 4 coordinates of the base of the gun
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
     * Checks if the gun has collided with another Structure3D
     * 
     * @param other
     *            Structure3D to check collision with
     * @return whether or not the two structures have collided
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
        setX( baseCenter.getX() + ( getX() - baseCenter.getX() ) * Math.cos( getAngle() )
            - ( getZ() - baseCenter.getZ() ) * Math.sin( getAngle() ) );
        setZ( baseCenter.getZ() + ( getX() - baseCenter.getX() ) * Math.sin( getAngle() )
            + ( getZ() - baseCenter.getZ() ) * Math.cos( getAngle() ) );

        normalizeAngle();
        curTime = newTime;
    }
    
    public int collisionSide(Structure3D other)
    {
        //TODO
        return -1;
    }
}
