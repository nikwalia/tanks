package math;

import javax.measure.quantity.Length;
import javax.measure.unit.SI;

import org.jscience.physics.amount.Amount;

public class Structure3D extends Value3D
{
    private final Amount<Length> length;
    private final Amount<Length> width;
 
    public Structure3D( int x, int y, int z, int angle, int l, int w )
    {
        super( x, y, z, angle );
        length = Amount.valueOf( l, SI.METER );
        width = Amount.valueOf( w, SI.METER );
    }
    
    protected Amount<Length> getLength()
    {
        return length;
    }
    
    protected Amount<Length> getWidth()
    {
        return width;
    }
}
