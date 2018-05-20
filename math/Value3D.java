package math;

/**
 * 
 * Class that represents a point in 3D space.
 *
 * @author Nikash Walia
 * @version May 5, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: none
 */
public class Value3D
{
    private double x_coordinate;

    private double y_coordinate;

    private double z_coordinate;


    /**
     * Constructor- creates a new point in 3D space
     * 
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @param z
     *            z-coordinate
     */
    public Value3D( double x, double y, double z )
    {
        x_coordinate = x;
        y_coordinate = y;
        z_coordinate = z;
    }


    /**
     * 
     * Translates the point in the x direction by the amount specified by the
     * parameter
     * 
     * @param dx
     *            change in x
     */
    protected void changeX( double dx )
    {
        x_coordinate += dx;
    }


    /**
     * 
     * Translates the point in the y direction by the amount specified by the
     * parameter
     * 
     * @param dy
     *            change in y
     */
    protected void changeY( double dy )
    {
        y_coordinate += dy;
    }


    /**
     * 
     * Translates the point in the z direction by the amount specified by the
     * parameter
     * 
     * @param dz
     *            change in z
     */
    protected void changeZ( double dz )
    {
        z_coordinate += dz;
    }


    /**
     * 
     * Setter method for x-coordinate
     * 
     * @param x
     *            new x-coordinate
     */
    protected void setX( double x )
    {
        x_coordinate = x;
    }


    /**
     * 
     * Setter method for y-coordinate
     * 
     * @param y
     *            new y-coordinate
     */
    protected void setY( double y )
    {
        y_coordinate = y;
    }


    /**
     * 
     * Setter method for z-coordinate
     * 
     * @param z
     *            new z-coordinate
     */
    protected void setZ( double z )
    {
        z_coordinate = z;
    }


    /**
     * 
     * Getter method for x-coordinate
     * 
     * @return x-coordinate
     */
    public double getX()
    {
        return x_coordinate;
    }


    /**
     * 
     * Getter method for y-coordinate
     * 
     * @return y-coordinate
     */
    public double getY()
    {
        return y_coordinate;
    }


    /**
     * 
     * Getter method for z-coordinate
     * 
     * @return z-coordinate
     */
    public double getZ()
    {
        return z_coordinate;
    }
}
