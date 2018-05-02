package math;

public class Bullet extends Structure3D implements Collidable
{
    private Value3D bullet;


    public Bullet()
    {
        bullet = new Value3D( 0, 0, 0 );
    }


    public boolean hasCollided( Collidable other )
    {
        Base obj = new Base( 0, 0, 0, 0, 0, 0, 0, 0 );
        return (obj.getHitPoints() == 0);
        
    }


    public void updateCorners()
    {
        return;
    }
}
