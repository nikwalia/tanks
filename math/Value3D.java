package math;

import javax.measure.quantity.*;
import javax.measure.unit.*;

import org.jscience.physics.amount.Amount;

public class Value3D
{
    private Amount<Length> x_coordinate;
    private Amount<Length> y_coordinate;
    private Amount<Length> z_coordinate;
    
    public Value3D(int x, int y, int z)
    {
        x_coordinate = Amount.valueOf( x, SI.METER );
        y_coordinate = Amount.valueOf( y, SI.METER );
        z_coordinate = Amount.valueOf( z, SI.METER );
    }
    
    protected void changeX(double dx)
    {
        x_coordinate.plus( Amount.valueOf( dx, SI.METER ) );
    }
    
    protected void changeY(double dy)
    {
        y_coordinate.plus( Amount.valueOf( dy, SI.METER ) );
    }
    
    protected void changeZ(double dz)
    {
        z_coordinate.plus( Amount.valueOf( dz, SI.METER ) );
    }
    
    
    protected Amount<Length> getX()
    {
        return x_coordinate;
    }
    
    protected Amount<Length> getY()
    {
        return y_coordinate;
    }
    
    protected Amount<Length> getZ()
    {
        return z_coordinate;
    }
    

}
