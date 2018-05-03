package math;

public class Bullet extends Structure3D implements Collidable
{

    public Bullet( int x, int y, int z, int angle, int l, int w, int h )
    {
        super( x, y, z, angle, 1, 1, 1 );
        base[0] = new Value3D( x, y, z );
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
        super.translate();
    }


    public void updateCorners()
    {
        base[0] = new Value3D( getX(), getY(), getZ() );
    }
}
