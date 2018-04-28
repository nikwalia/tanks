package math;
import math.Structure3D;

public abstract class Moveable extends Structure3D
{
    private double curTime;

    private double velocity;

    private static final double ACCELERATION = 8;

    private static final double MAXVELOCITY = 20;

    private static final double MINVELOCITY = -20;

    private static final double ZEROVELOCITY = 0;

    private double angularVelocity;

    private static final double MAXANGULARVELOCITY = Math.PI / 8;

    private static final double MINANGULARVELOCITY = -Math.PI / 8;

    private static final double ZEROANGULARVELOCITY = 0;


    public Moveable( int x, int y, int z, int angle, int l, int w )
    {
        super( x, y, z, angle, l, w );
        curTime = System.nanoTime();
        velocity = ZEROVELOCITY;
        angularVelocity = ZEROANGULARVELOCITY;
    }


    protected double getTime()
    {
        return curTime;
    }


    abstract int turnDirection();

    abstract int moveDirection();


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
                && !( moveDirection() == 1 && Math.abs( velocity - MAXVELOCITY) < 0.1 ) )
            {
                velocity += ACCELERATION * deltaTime * moveDirection();
            }
        }

        super.changeAngle( angularVelocity * deltaTime );
        super.changeX( velocity * deltaTime * Math.cos( getAngle() ) );
        super.changeY( velocity * deltaTime * Math.sin( getAngle() ) );
        
        curTime = newTime;
    }
}
