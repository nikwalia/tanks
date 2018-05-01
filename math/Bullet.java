package math;

public class Bullet implements Collidable
{
    private Value3D bullet;


    public Bullet()
    {
        bullet = new Value3D( 0, 0, 0 );
    }


    public boolean hasCollided( Collidable other )
    {
        Tank obj = new Tank( 0, 0, 0, 0, 0, 0, 0, 0 );
        return (obj.getHitPoints() == 0);
        
    }


    public void updateCorners()
    {
        return;
    }
}
