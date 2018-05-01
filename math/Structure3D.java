package math;

public class Structure3D extends Value3D implements Collidable
{

    private final double length;

    private final double width;

    private final double height;

    private double angle;


    public Structure3D( int x, int y, int z, int angle, int l, int w, int h )
    {
        super( x, y, z );
        length = l;
        width = w;
        height = h;
        this.angle = angle;
        updateCorners();
    }


    protected double getLength()
    {
        return length;
    }


    protected double getWidth()
    {
        return width;
    }


    protected double getHeight()
    {
        return height;
    }


    protected double getAngle()
    {
        return angle;
    }


    protected void changeAngle( double deltaAngle )
    {
        angle += deltaAngle;
    }


    public void updateCorners()
    {
        base[0] = new Value3D(
            getX() + width / 2 * Math.cos( angle ) - length / 2 * Math.sin( angle ),
            getY() + width / 2 * Math.sin( angle ) + length / 2 * Math.cos( angle ),
            0 );
        base[1] = new Value3D(
            getX() - width / 2 * Math.cos( angle ) - length / 2 * Math.sin( angle ),
            getY() - width / 2 * Math.sin( angle ) + length / 2 * Math.cos( angle ),
            0 );
        base[2] = new Value3D(
            getX() - width / 2 * Math.cos( angle ) + length / 2 * Math.sin( angle ),
            getY() - width / 2 * Math.sin( angle ) - length / 2 * Math.cos( angle ),
            0 );
        base[3] = new Value3D(
            getX() + width / 2 * Math.cos( angle ) + length / 2 * Math.sin( angle ),
            getY() + width / 2 * Math.sin( angle ) - length / 2 * Math.cos( angle ),
            0 );
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
}
