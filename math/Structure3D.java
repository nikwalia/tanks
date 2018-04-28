package math;
import math.Value3D;

public class Structure3D extends Value3D
{
    private final double length;
    private final double width;
 
    public Structure3D( int x, int y, int z, int angle, int l, int w )
    {
        super( x, y, z, angle );
        length = l;
        width = w;
    }
    
    protected double getLength()
    {
        return length;
    }
    
    protected double getWidth()
    {
        return width;
    }
}
