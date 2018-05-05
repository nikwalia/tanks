package math;

public class Base extends Structure3D implements Collidable
{
    private int hitPoints;

    private Structure3D obj;

    private Value3D[] base;


    public Base( int x, int y, int z, int angle, int l, int w, int h )
    {
        super( x, y, z, angle, l, w, h, Math.PI / 4 );
    }


    public void updateCorners()
    {
        base[0] = new Value3D(
            getX() + width / 2 * Math.cos( getAngle() ) - length / 2 * Math.sin( getAngle() ),
            getY() + width / 2 * Math.sin( getAngle() ) + length / 2 * Math.cos( getAngle() ),
            0 );
        base[1] = new Value3D(
            getX() - width / 2 * Math.cos( getAngle() ) - length / 2 * Math.sin( getAngle() ),
            getY() - width / 2 * Math.sin( getAngle() ) + length / 2 * Math.cos( getAngle() ),
            0 );
        base[2] = new Value3D(
            getX() - width / 2 * Math.cos( getAngle() ) + length / 2 * Math.sin( getAngle() ),
            getY() - width / 2 * Math.sin( getAngle() ) - length / 2 * Math.cos( getAngle() ),
            0 );
        base[3] = new Value3D(
            getX() + width / 2 * Math.cos( getAngle() ) + length / 2 * Math.sin( getAngle() ),
            getY() + width / 2 * Math.sin( getAngle() ) - length / 2 * Math.cos( getAngle() ),
            0 );
    }


    public int onCollision( Collidable other )
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


    public boolean hasCollided( Collidable other )
    {
        double otherX = other.base[0].getX();
        double otherY = other.base[0].getY();
        double areaOfBase = width * length;
        int max;
        if ( other.base[2] == null )
        {
            max = 1;
        }
        else
        {
            max = 4;
        }

        for ( int i = 0; i < max; i++ )
        {
            otherX = other.base[i].getX();
            otherY = other.base[i].getY();
            if ( Math.sqrt( Math.pow( otherX - getX(), 2 ) + Math.pow( otherY - getY(), 2 ) ) > Math
                .sqrt( width * width + length * length ) / 2 )
            {
                continue;
            }
            else
            {
                double totalTriangleArea = 0;
                double firstSide = Math.sqrt( Math.pow( otherX - base[0].getX(), 2 )
                    + Math.pow( otherY - base[0].getY(), 2 ) );
                double secondSide = Math.sqrt( Math.pow( otherX - base[1].getX(), 2 )
                    + Math.pow( otherY - base[1].getY(), 2 ) );
                double thirdSide = Math.sqrt( Math.pow( base[0].getX() - base[1].getX(), 2 )
                    + Math.pow( base[0].getX() - base[1].getY(), 2 ) );
                double semiperimeter = ( firstSide + secondSide + thirdSide ) / 2;
                totalTriangleArea += Math.sqrt( semiperimeter * ( semiperimeter - firstSide )
                    * ( semiperimeter - secondSide ) * ( semiperimeter - thirdSide ) );

                firstSide = Math.sqrt( Math.pow( otherX - base[1].getX(), 2 )
                    + Math.pow( otherY - base[1].getY(), 2 ) );
                secondSide = Math.sqrt( Math.pow( otherX - base[2].getX(), 2 )
                    + Math.pow( otherY - base[2].getY(), 2 ) );
                thirdSide = Math.sqrt( Math.pow( base[1].getX() - base[2].getX(), 2 )
                    + Math.pow( base[1].getX() - base[2].getY(), 2 ) );
                semiperimeter = ( firstSide + secondSide + thirdSide ) / 2;
                totalTriangleArea += Math.sqrt( semiperimeter * ( semiperimeter - firstSide )
                    * ( semiperimeter - secondSide ) * ( semiperimeter - thirdSide ) );

                firstSide = Math.sqrt( Math.pow( otherX - base[2].getX(), 2 )
                    + Math.pow( otherY - base[2].getY(), 2 ) );
                secondSide = Math.sqrt( Math.pow( otherX - base[3].getX(), 2 )
                    + Math.pow( otherY - base[3].getY(), 2 ) );
                thirdSide = Math.sqrt( Math.pow( base[2].getX() - base[3].getX(), 2 )
                    + Math.pow( base[2].getX() - base[3].getY(), 2 ) );
                semiperimeter = ( firstSide + secondSide + thirdSide ) / 2;
                totalTriangleArea += Math.sqrt( semiperimeter * ( semiperimeter - firstSide )
                    * ( semiperimeter - secondSide ) * ( semiperimeter - thirdSide ) );

                firstSide = Math.sqrt( Math.pow( otherX - base[0].getX(), 2 )
                    + Math.pow( otherY - base[0].getY(), 2 ) );
                secondSide = Math.sqrt( Math.pow( otherX - base[3].getX(), 2 )
                    + Math.pow( otherY - base[3].getY(), 2 ) );
                thirdSide = Math.sqrt( Math.pow( base[0].getX() - base[3].getX(), 2 )
                    + Math.pow( base[0].getX() - base[3].getY(), 2 ) );
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

        if ( getMoveDirection() == 0 && Math.abs( velocity - ZEROVELOCITY ) < 0.1 )
        {
            if ( velocity < ZEROVELOCITY )
            {
                velocity += ACCELERATION * deltaTime;
            }
            else
            {
                velocity += ACCELERATION * deltaTime * -1;
            }
        }
        else
        {
            if ( !( getMoveDirection() == -1 && Math.abs( velocity - MINVELOCITY ) < 0.1 )
                && !( getMoveDirection() == 1 && Math.abs( velocity - MAXVELOCITY ) < 0.1 ) )
            {
                velocity += ACCELERATION * deltaTime * getMoveDirection();
            }
        }

        changeAngle( angularVelocity * deltaTime );
        changeX( velocity * deltaTime * Math.cos( getAngle() ) );
        changeZ( velocity * deltaTime * Math.sin( getAngle() ) );

        curTime = newTime;
    }
}
