package math;

import javax.measure.quantity.*;
import javax.measure.unit.SI;

import org.jscience.geography.coordinates.Time;
import org.jscience.physics.amount.Amount;


public abstract class Moveable extends Structure3D
{
    private Time curTime;

    private Amount<Velocity> velocity;

    private static final Amount<Acceleration> ACCELERATION = Amount.valueOf( 8,
        SI.METERS_PER_SQUARE_SECOND );

    private static final Amount<Velocity> MAXVELOCITY = Amount.valueOf( 20, SI.METERS_PER_SECOND );

    private static final Amount<Velocity> MINVELOCITY = Amount.valueOf( -20, SI.METERS_PER_SECOND );

    private static final Amount<Velocity> ZEROVELOCITY = Amount.valueOf( 0, SI.METERS_PER_SECOND );

    private Amount<AngularVelocity> angularVelocity;

    private static final Amount<AngularVelocity> MAXANGULARVELOCITY = Amount.valueOf( Math.PI / 8,
        SI.RADIAN.divide( SI.SECOND ).asType( AngularVelocity.class ) );

    private static final Amount<AngularVelocity> MINANGULARVELOCITY = Amount.valueOf( -Math.PI / 8,
        SI.RADIAN.divide( SI.SECOND ).asType( AngularVelocity.class ) );

    private static final Amount<AngularVelocity> ZEROANGULARVELOCITY = Amount.valueOf( 0,
        SI.RADIAN.divide( SI.SECOND ).asType( AngularVelocity.class ) );


    public Moveable( int x, int y, int z, int angle, int l, int w )
    {
        super( x, y, z, angle, l, w );
        curTime = new Time( System.nanoTime(), SI.SECOND );
        velocity = ZEROVELOCITY;
        angularVelocity = ZEROANGULARVELOCITY;
    }


    protected Time getTime()
    {
        return curTime;
    }


    abstract int turnDirection();

    abstract int moveDirection();


    protected void translate()
    {
        Time newTime = new Time( System.nanoTime(), SI.SECOND );
        Time deltaTime = new Time( newTime.longValue( SI.SECOND ) - curTime.longValue( SI.SECOND ),
            SI.SECOND );

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

        if ( moveDirection() == 0 && !velocity.approximates( ZEROVELOCITY ) )
        {
            if ( velocity.isLessThan( ZEROVELOCITY ) )
            {
                velocity.plus( ACCELERATION.times( deltaTime.doubleValue( SI.SECOND ) ) );
            }
            else
            {
                velocity
                    .plus( ACCELERATION.times( deltaTime.doubleValue( SI.SECOND ) ).times( -1 ) );
            }
        }
        else
        {
            if ( !( moveDirection() == -1 && velocity.approximates( MINVELOCITY ) )
                && !( moveDirection() == 1 && velocity.approximates( MAXVELOCITY ) ) )
            {
                velocity.plus( ACCELERATION.times( deltaTime.doubleValue( SI.SECOND ) )
                    .times( moveDirection() ) );
            }
        }

        super.changeAngle( angularVelocity.times( deltaTime.doubleValue( SI.SECOND ) )
            .doubleValue( SI.RADIAN.divide( SI.SECOND ).asType( AngularVelocity.class ) ) );
        super.changeX( ( velocity.times( deltaTime.doubleValue( SI.SECOND ) )
            .times( Math.cos( getAngle().longValue( SI.RADIAN ) ) )
            .doubleValue( SI.METERS_PER_SECOND ) ) );
        super.changeY( ( velocity.times( deltaTime.doubleValue( SI.SECOND ) )
            .times( Math.sin( getAngle().longValue( SI.RADIAN ) ) )
            .doubleValue( SI.METERS_PER_SECOND ) ) );
        
        curTime = newTime;
    }
}
