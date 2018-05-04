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

    protected final double MAXANGULARVELOCITY = Math.PI / 8;

    protected final double MINANGULARVELOCITY = -Math.PI / 8;

    protected final double ZEROANGULARVELOCITY = 0;

    private Value3D[] base;

    public Structure3D( int x, int y, int z, int angle, int l, int w, int h )
    {
        super( x, y, z );
        length = l;
        width = w;
        height = h;
        this.angle = angle;
        curTime = System.nanoTime();
        velocity = ZEROVELOCITY;
        angularVelocity = ZEROANGULARVELOCITY;
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
    public int turnDirection()
    {
        // TODO: Complete both
        return 0;
    }


    public int moveDirection()
    {
        // TODO: Complete both
        return 0;
    }

    public void translate()
    {
        double newTime = System.nanoTime();
        double deltaTime = newTime - curTime;

        if ( turnDirection() == 0 )
        {
            angularVelocity = ZEROANGULARVELOCITY;
        }
        else if ( turnDirection() == -1 )
        {
            angularVelocity = MINANGULARVELOCITY;
        }
        else
        {
            angularVelocity = MAXANGULARVELOCITY;
        }

        if ( moveDirection() == 0 && Math.abs(velocity - ZEROVELOCITY) < 0.1 )
        {
            if ( velocity < ZEROVELOCITY )
            {
                velocity += ACCELERATION * deltaTime;
            }
            else
            {
                velocity += ACCELERATION * deltaTime * -1;
            }
        }
        else
        {
            if ( !( moveDirection() == -1 && Math.abs( velocity - MINVELOCITY ) < 0.1 )
                && !( moveDirection() == 1 && Math.abs( velocity - MAXVELOCITY) < 0.1 ) )
            {
                velocity += ACCELERATION * deltaTime * moveDirection();
            }
        }

        changeAngle( angularVelocity * deltaTime );
        changeX( velocity * deltaTime * Math.cos( getAngle() ) );
        changeZ( velocity * deltaTime * Math.sin( getAngle() ) );

        curTime = newTime;
        updateCorners();
    }
    
    public void updateCorners()
    {
        base[0] = new Value3D(
            getX() + width / 2 * Math.cos( angle ) - length / 2 * Math.sin( angle ),
            getY() + width / 2 * Math.sin( angle ) + length / 2 * Math.cos( angle ),
            0 );
        base[1] = new Value3D(
            getX() - width / 2 * Math.cos( angle ) - length / 2 * Math.sin( angle ),
            getY() - width / 2 * Math.sin( angle ) + length / 2 * Math.cos( angle ),
            0 );
        base[2] = new Value3D(
            getX() - width / 2 * Math.cos( angle ) + length / 2 * Math.sin( angle ),
            getY() - width / 2 * Math.sin( angle ) - length / 2 * Math.cos( angle ),
            0 );
        base[3] = new Value3D(
            getX() + width / 2 * Math.cos( angle ) + length / 2 * Math.sin( angle ),
            getY() + width / 2 * Math.sin( angle ) - length / 2 * Math.cos( angle ),
            0 );
    }
}
