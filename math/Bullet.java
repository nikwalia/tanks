package math;

public class Bullet extends Structure3D implements Collidable
{
    private Value3D bullet;


    public Bullet(int x, int y, int z, int angle, int l, int w, int h)
    {
        super(x, y, z, angle, 1, 1, 1);
        bullet = new Value3D( x, y, z );
    }


    public boolean hasCollided( Collidable other )
    {
        Base obj = new Base( 0, 0, 0, 0, 0, 1, 1, 1 );
        return (obj.getHitPoints() == 0);
        
    }


    public void updateCorners()
    {
        return;
    }
}
