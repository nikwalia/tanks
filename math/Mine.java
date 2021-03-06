package math;


/**
 * 
 * Class that represents a Mine
 *
 * @author Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 * @version May 25, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class Mine extends Structure3D
{

    /**
     * Constructor for mine
     * 
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @param z
     *            z-coordinate
     * @param r
     *            radius
     */
    public Mine( double x, double y, double z, int r )
    {
        super( x, y, z, 0, r, r, r, 0 );
    }


    /**
     * 
     * Stub method- required to extend abstract class Structure3D
     */
    public void translate()
    {
        setVelocity( 0 );
        setAngularVelocity( 0 );
    }


    /**
     * 
     * Checks whether or not the mine has collided with another Structure3D
     * @param other Structure3D to check collision with
     * @return status update
     */
    public int hasCollided( Structure3D other )
    {
        if (other instanceof Mine)
        {
            return 0;
        }
        return other.hasCollided( this );
    }
}
