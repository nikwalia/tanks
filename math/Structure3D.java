package math;

import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.unit.SI;

import org.jscience.physics.amount.Amount;


public class Structure3D extends Value3D
{
    private final Amount<Length> length;

    private final Amount<Length> width;

    private final Amount<Length> height;

    private Amount<Angle> angle;

    private Value3D[] points;


    public Structure3D( int x, int y, int z, int angle, int l, int w, int h )
    {
        super( x, y, z );
        length = Amount.valueOf( l, SI.METER );
        width = Amount.valueOf( w, SI.METER );
        height = Amount.valueOf( h, SI.METER );
        this.angle = Amount.valueOf( angle, SI.RADIAN );
    }


    protected Amount<Length> getLength()
    {
        return length;
    }


    protected Amount<Length> getWidth()
    {
        return width;
    }


    protected Amount<Length> getHeight()
    {
        return height;
    }


    protected void changeAngle( double dAngle )
    {
        angle.plus( Amount.valueOf( dAngle, SI.RADIAN ) );
    }


    protected Amount<Angle> getAngle()
    {
        return angle;
    }


    protected void calculateAngles()
    {

        float tempX = x - cx;
        float tempY = y - cy;

        // now apply rotation
        double rotatedX = tempX * Math.cos( angle.getExactValue() )
            - tempY * Math.sin( angle.getExactValue() );
        double rotatedY = tempX * Math.sin( angle.getExactValue() )
            + tempY * Math.cos( angle.getExactValue() );

        // translate back
        x = rotatedX + cx;
        y = rotatedY + cy;

    }
}
