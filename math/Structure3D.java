package math;

public class Structure3D extends Value3D
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


    public Structure3D( int x, int y, int z, int angle, int l, int w, int h, double maxangularvel )
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
    }


    protected double getTime()
    {
        return curTime;
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


    protected void changeAngle( double deltaAngle )
    {
        angle += deltaAngle;
    }


    protected void setTurnDirection( int t )
    {
        turnDirection = t;
    }

    protected void setMoveDirection( int m )
    {
        moveDirection = m;
    }


    protected void setVelocity( double v )
    {
        velocity = v;
    }


    protected void setAngularvelocity( double a )
    {
        angularVelocity = a;
    }


    protected double getVelocity()
    {
        return velocity;
    }


    protected double getAngularVelocity()
    {
        return angularVelocity;
    }
    
    public double getTurnDirection()
    {
        return turnDirection;
    }
    
    public double getMoveDirection()
    {
        return moveDirection;
    }


    public void translate()
    {
        //stub method
    }
}
