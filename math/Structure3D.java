package math;

/**
 * 
 * Class that represents a structure in 3D space- has properties for motion, and
 * also has dimension
 *
 * @author Nikash Walia
 * @version May 5, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public abstract class Structure3D extends Value3D
{

    protected final double length;

    protected final double width;

    private final double height;

    private double angle;

    protected double curTime;

    protected double velocity;

    protected final double ACCELERATION = 8;

    protected final double MAXVELOCITY = 20;

    protected final double MINVELOCITY = -20;

    protected final double ZEROVELOCITY = 0;

    protected double angularVelocity;

    protected double maxAngularVelocity = Math.PI / 8;

    protected double minAngularVelocity = -Math.PI / 8;

    protected final double ZEROANGULARVELOCITY = 0;

    private int turnDirection;

    private int moveDirection;
    
    protected Value3D[] baseRectangle;


    /**
     * Constructor for Structure3D
     * 
     * @param x
     *            x-coordinate of center
     * @param y
     *            y-coordinate of center
     * @param z
     *            z-coordinate of center
     * @param angle
     *            angle of structure
     * @param l
     *            length of structure
     * @param w
     *            width of structure
     * @param h
     *            height of structure
     * @param maxangularvel
     *            the rate at which the structure can rotate
     */
    public Structure3D(
        double x,
        double y,
        double z,
        double angle,
        int l,
        int w,
        int h,
        double maxangularvel )
    {
        super( x, y, z );
        length = l;
        width = w;
        height = h;
        this.angle = angle;
        curTime = System.nanoTime() / 1e+9;
        velocity = ZEROVELOCITY;
        angularVelocity = ZEROANGULARVELOCITY;
        turnDirection = 0;
        moveDirection = 0;
        maxAngularVelocity = maxangularvel;
        minAngularVelocity = -maxangularvel;
        baseRectangle = new Value3D[4];
        
    }


    /**
     * 
     * Gets the last time at which the structure's location was updated
     * @return time in seconds
     */
    protected double getTime()
    {
        return curTime;
    }


    /**
     * 
     * Gets the length of the structure
     * @return length
     */
    public double getLength()
    {
        return length;
    }


    /**
     * 
     * Gets the width of the structure
     * @return width
     */
    public double getWidth()
    {
        return width;
    }


    /**
     * 
     * Gets the height of the structure
     * @return height
     */
    public double getHeight()
    {
        return height;
    }


    /**
     * 
     * Gets the angle of the structure
     * @return angle
     */
    protected double getAngle()
    {
        return angle;
    }


    /**
     * 
     * Rotates the structure by the given angle
     * @param deltaAngle change in angle
     */
    protected void changeAngle( double deltaAngle )
    {
        angle += deltaAngle;
    }


    /**
     * 
     * Sets the turn direction variable
     * -1 = clockwise
     * 0 = not turning
     * 1 = counter-clockwise
     * @param t direction to turn
     */
    protected void setTurnDirection( int t )
    {
        turnDirection = t;
    }


    /**
     * 
     * Sets the move direction variable
     * -1 = backward
     * 0 = no movement
     * 1 = forward
     * @param m direction to move
     */
    protected void setMoveDirection( int m )
    {
        moveDirection = m;
    }


    /**
     * 
     * Sets the velocity of the structure
     * @param v velocity
     */
    protected void setVelocity( double v )
    {
        velocity = v;
    }


    /**
     * 
     * Sets the angular velocity of the structure
     * @param a angular velocity
     */
    protected void setAngularVelocity( double a )
    {
        angularVelocity = a;
    }


    /**
     * 
     * Gets the velocity of the structure
     * @return velocity
     */
    protected double getVelocity()
    {
        return velocity;
    }


    /**
     * 
     * Gets the angular velocity of the structure
     * @return angular velocity
     */
    protected double getAngularVelocity()
    {
        return angularVelocity;
    }


    /**
     * 
     * Gets the turn direction of the structure
     * @return turn direction
     */
    public double getTurnDirection()
    {
        return turnDirection;
    }


    /**
     * 
     * Gets the move direction of the structure
     * @return move direction
     */
    public double getMoveDirection()
    {
        return moveDirection;
    }


    /**
     * 
     * Stub method- translates the system according to its various properties
     */
    public abstract void translate();
    
    /**
     * 
     * method for updating the coordinates of the 4 corners in the base
     */
    public abstract void updateCorners();
    
    /**
     * 
     * method for checking whether or not the structure has collided with another
     * @param other structure to check against
     * @return whether it has collided or not
     */
    public abstract boolean hasCollided(Structure3D other);
    
    /**
     * 
     * method for action after collision
     * @param other structure to interact with
     * @return status update to system
     */
    public abstract int onCollision(Structure3D other);
}
