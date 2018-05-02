package math;

public class Base extends Structure3D
{
    private int hitPoints;

    private Structure3D base;


    public Base( int x, int y, int z, int angle, int l, int w, int h, int hitPts )
    {
        super( x, y, z, angle, l, w, h );
        hitPoints = hitPts;
    }


    protected int getHitPoints()
    {
        return hitPoints;
    }


    protected void changeHitPoints()
    {
        Bullet bullet = new Bullet();
        if ( base.hasCollided( bullet ) )
        {
            hitPoints -= 5;
        }
    }


    protected boolean isDead()
    {
        return hitPoints == 0;
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
