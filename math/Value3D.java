package math;

public class Value3D
{
    private double x_coordinate;

    private double y_coordinate;

    private double z_coordinate;


    public Value3D( double x, double y, double z )
    {
        x_coordinate = x;
        y_coordinate = y;
        z_coordinate = y;
    }


    protected void changeX( double dx )
    {
        x_coordinate += dx;
    }


    protected void changeY( double dy )
    {
        y_coordinate += dy;
    }


    protected void changeZ( double dz )
    {
        z_coordinate += dz;
    }


    public double getX()
    {
        return x_coordinate;
    }


    public double getY()
    {
        return y_coordinate;
    }


    public double getZ()
    {
        return z_coordinate;
    }
}
