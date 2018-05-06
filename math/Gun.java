package math;

public class Gun extends Structure3D
{
    private int baseTurnState;

    private Value3D baseCenter;


    public Gun( int angle, int l, int w, int h, Value3D b, int baseHeight )
    {
        super( b.getX(), b.getY() + h / 2 + baseHeight / 2, b.getZ(), angle, l, w, h, Math.PI / 4 );
        baseTurnState = 0;
        baseCenter = b;
        updateCenter();
    }


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


    public void updateCorners()
    {
        baseRectangle[0] = new Value3D(
            getX() + width / 2 * Math.cos( getAngle() ) - length / 2 * Math.sin( getAngle() ),
            getY() + width / 2 * Math.sin( getAngle() ) + length / 2 * Math.cos( getAngle() ),
            0 );
        baseRectangle[1] = new Value3D(
            getX() - width / 2 * Math.cos( getAngle() ) - length / 2 * Math.sin( getAngle() ),
            getY() - width / 2 * Math.sin( getAngle() ) + length / 2 * Math.cos( getAngle() ),
            0 );
        baseRectangle[2] = new Value3D(
            getX() - width / 2 * Math.cos( getAngle() ) + length / 2 * Math.sin( getAngle() ),
            getY() - width / 2 * Math.sin( getAngle() ) - length / 2 * Math.cos( getAngle() ),
            0 );
        baseRectangle[3] = new Value3D(
            getX() + width / 2 * Math.cos( getAngle() ) + length / 2 * Math.sin( getAngle() ),
            getY() + width / 2 * Math.sin( getAngle() ) - length / 2 * Math.cos( getAngle() ),
            0 );
    }


    public boolean hasCollided( Structure3D other )
    {
        double otherX = other.baseRectangle[0].getX();
        double otherY = other.baseRectangle[0].getY();
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
            otherY = other.baseRectangle[i].getY();
            if ( Math.sqrt( Math.pow( otherX - getX(), 2 ) + Math.pow( otherY - getY(), 2 ) ) > Math
                .sqrt( width * width + length * length ) / 2 )
            {
                continue;
            }
            else
            {
                double totalTriangleArea = 0;
                double firstSide = Math.sqrt( Math.pow( otherX - baseRectangle[0].getX(), 2 )
                    + Math.pow( otherY - baseRectangle[0].getY(), 2 ) );
                double secondSide = Math.sqrt( Math.pow( otherX - baseRectangle[1].getX(), 2 )
                    + Math.pow( otherY - baseRectangle[1].getY(), 2 ) );
                double thirdSide = Math.sqrt( Math.pow( baseRectangle[0].getX() - baseRectangle[1].getX(), 2 )
                    + Math.pow( baseRectangle[0].getX() - baseRectangle[1].getY(), 2 ) );
                double semiperimeter = ( firstSide + secondSide + thirdSide ) / 2;
                totalTriangleArea += Math.sqrt( semiperimeter * ( semiperimeter - firstSide )
                    * ( semiperimeter - secondSide ) * ( semiperimeter - thirdSide ) );

                firstSide = Math.sqrt( Math.pow( otherX - baseRectangle[1].getX(), 2 )
                    + Math.pow( otherY - baseRectangle[1].getY(), 2 ) );
                secondSide = Math.sqrt( Math.pow( otherX - baseRectangle[2].getX(), 2 )
                    + Math.pow( otherY - baseRectangle[2].getY(), 2 ) );
                thirdSide = Math.sqrt( Math.pow( baseRectangle[1].getX() - baseRectangle[2].getX(), 2 )
                    + Math.pow( baseRectangle[1].getX() - baseRectangle[2].getY(), 2 ) );
                semiperimeter = ( firstSide + secondSide + thirdSide ) / 2;
                totalTriangleArea += Math.sqrt( semiperimeter * ( semiperimeter - firstSide )
                    * ( semiperimeter - secondSide ) * ( semiperimeter - thirdSide ) );

                firstSide = Math.sqrt( Math.pow( otherX - baseRectangle[2].getX(), 2 )
                    + Math.pow( otherY - baseRectangle[2].getY(), 2 ) );
                secondSide = Math.sqrt( Math.pow( otherX - baseRectangle[3].getX(), 2 )
                    + Math.pow( otherY - baseRectangle[3].getY(), 2 ) );
                thirdSide = Math.sqrt( Math.pow( baseRectangle[2].getX() - baseRectangle[3].getX(), 2 )
                    + Math.pow( baseRectangle[2].getX() - baseRectangle[3].getY(), 2 ) );
                semiperimeter = ( firstSide + secondSide + thirdSide ) / 2;
                totalTriangleArea += Math.sqrt( semiperimeter * ( semiperimeter - firstSide )
                    * ( semiperimeter - secondSide ) * ( semiperimeter - thirdSide ) );

                firstSide = Math.sqrt( Math.pow( otherX - baseRectangle[0].getX(), 2 )
                    + Math.pow( otherY - baseRectangle[0].getY(), 2 ) );
                secondSide = Math.sqrt( Math.pow( otherX - baseRectangle[3].getX(), 2 )
                    + Math.pow( otherY - baseRectangle[3].getY(), 2 ) );
                thirdSide = Math.sqrt( Math.pow( baseRectangle[0].getX() - baseRectangle[3].getX(), 2 )
                    + Math.pow( baseRectangle[0].getX() - baseRectangle[3].getY(), 2 ) );
                semiperimeter = ( firstSide + secondSide + thirdSide ) / 2;
                totalTriangleArea += Math.sqrt( semiperimeter * ( semiperimeter - firstSide )
                    * ( semiperimeter - secondSide ) * ( semiperimeter - thirdSide ) );

                if ( totalTriangleArea < areaOfBase )
                {
                    return true;
                }
                else
                {
                    continue;
                }
            }
        }
        return false;
    }


    public void setBaseTurn( int state )
    {
        baseTurnState = state;
    }


    public void setBaseCenter( Value3D baseCenter )
    {
        this.baseCenter = baseCenter;
    }


    public void updateCenter()
    {
        this.setX( baseCenter.getX() + getLength() / 2 * Math.cos( getAngle() ) );
        this.setZ( baseCenter.getZ() + getLength() / 2 * Math.sin( getAngle() ) );
    }


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

        curTime = newTime;
    }
}
