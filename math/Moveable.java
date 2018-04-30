package math;


public class Moveable extends Structure3D
{
    protected double curTime;

    protected double velocity;

    protected static final double ACCELERATION = 8;

    protected static final double MAXVELOCITY = 20;

    protected static final double MINVELOCITY = -20;

    protected static final double ZEROVELOCITY = 0;

    protected double angularVelocity;

    protected static final double MAXANGULARVELOCITY = Math.PI / 8;

    protected static final double MINANGULARVELOCITY = -Math.PI / 8;

    protected static final double ZEROANGULARVELOCITY = 0;


    public Moveable( int x, int y, int z, int angle, int l, int w, int h )
    {
        super( x, y, z, angle, l, w, h );
        curTime = System.nanoTime();
        velocity = ZEROVELOCITY;
        angularVelocity = ZEROANGULARVELOCITY;
    }


    protected double getTime()
    {
        return curTime;
    }


    protected int turnDirection()
    {
        // TODO: Complete both
        return 0;
    }

    protected int moveDirection()
    {
        // TODO: Complete both
        return 0;
    }


    protected void translate()
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
                && !( moveDirection() == 1 && Math.abs( velocity - MAXVELOCITY ) < 0.1 ) )

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
}