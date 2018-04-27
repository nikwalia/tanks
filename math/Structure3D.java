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
        points = new Value3D[8];
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


    protected Value3D[] getPoints()
    {
        return points;
    }


    protected void calculatePoints()
    {
        points[0] = new Value3D( (int)super.getX().getExactValue(),
            (int)super.getY().getExactValue(),
            (int)super.getZ().getExactValue() );
        
        points[1] = new Value3D( (int)( super.getX().getExactValue() + length.getExactValue() ),
            (int)super.getY().getExactValue(),
            (int)super.getZ().getExactValue() );
        
        points[2] = new Value3D( (int)( super.getX().getExactValue() + length.getExactValue() ),
            (int)( super.getY().getExactValue() + height.getExactValue() ),
            (int)( super.getZ().getExactValue() ) );
        
        points[3] = new Value3D( (int)( super.getX().getExactValue() + length.getExactValue() ),
            (int)( super.getY().getExactValue() + height.getExactValue() ),
            (int)( super.getZ().getExactValue() + width.getExactValue() ) );
        
        points[4] = new Value3D( (int)( super.getX().getExactValue() ),
            (int)( super.getY().getExactValue() + height.getExactValue() ),
            (int)( super.getZ().getExactValue() ) );
        
        points[5] = new Value3D( (int)( super.getX().getExactValue() ),
            (int)( super.getY().getExactValue() + height.getExactValue() ),
            (int)( super.getZ().getExactValue() + width.getExactValue() ) );
        
        points[6] = new Value3D( (int)( super.getX().getExactValue() ),
            (int)( super.getY().getExactValue() ),
            (int)( super.getZ().getExactValue() + width.getExactValue() ) );
        
        points[7] = new Value3D( (int)( super.getX().getExactValue() + length.getExactValue()),
            (int)( super.getY().getExactValue()),
            (int)( super.getZ().getExactValue() + width.getExactValue() ) );


    }
}
