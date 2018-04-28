package math;


public class Structure3D extends Value3D
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
    
    protected void changeAngle(double deltaAngle)
    {
        angle += deltaAngle;
    }
}
