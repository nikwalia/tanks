package math;

public class Tank
{
    private int hitPoints;
    private Moveable base;
    
    public Tank( int x, int y, int z, int angle, int l, int w, int h, int hitPts )
    {
        base = new Moveable( x, y, z, angle, l, w, h );
        hitPoints = hitPts;
    }


    protected int getHitPoints()
    {
        return hitPoints;
    }


    protected void changeHitPoints()
    {
        Bullet bullet = new Bullet();
        if ( base.hasCollided( bullet ) )
        {
            hitPoints -= 5;
        }
    }
    protected boolean isDead()
    {
        return hitPoints == 0;
    }
}
