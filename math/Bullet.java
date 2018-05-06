package math;

public class Bullet extends Structure3D
{

    public Bullet( double x, double y, double z, double angle )
    {
        super( x, y, z, angle, 1, 1, 1, 0 );
        baseRectangle[0] = new Value3D( x, y, z );
        setVelocity( 100 );
        setMoveDirection( 1 );
        baseRectangle[1] = null;
        baseRectangle[2] = null;
        baseRectangle[3] = null;
    }


    public boolean hasCollided( Structure3D other )
    {
        return other.hasCollided( this );
    }


    public int onCollision( Structure3D other )
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
        baseRectangle[0] = new Value3D( getX(), getY(), getZ() );
    }
}
