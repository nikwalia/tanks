package math;

public class Bullet extends Structure3D implements Collidable
{

    public Bullet( double x, double y, double z, double angle )
    {
        super( x, y, z, angle, 1, 1, 1, 0 );
        base[0] = new Value3D( x, y, z );
        setVelocity( 100 );
        setMoveDirection( 1 );
    }


    public boolean hasCollided( Collidable other )
    {
        return other.hasCollided( this );
    }


    public int onCollision( Collidable other )
    {
        setVelocity( 0 );
        super.changeX( -getX() );
        super.changeY( -getY() );
        super.changeZ( -getZ() );
        super.changeAngle( -getAngle() );
        return 0;
    }


    public void translate()
    {
        double newTime = System.nanoTime() / 1e+9;
        double deltaTime = newTime - curTime;
        changeX( velocity * deltaTime * Math.cos( getAngle() ) );
        changeZ( velocity * deltaTime * Math.sin( getAngle() ) );
        curTime = newTime;
    }


    public void updateCorners()
    {
        base[0] = new Value3D( getX(), getY(), getZ() );
    }
}
