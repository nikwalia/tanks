package math;

public class Tank
{
    private Gun gun;

    private Base base;

    private int hitPoints;


    public Tank(
        int x,
        int y,
        int z,
        int angle,
        int gunLength,
        int gunWidth,
        int gunHeight,
        int baseLength,
        int baseWidth,
        int baseHeight,
        int hp )
    {
        gun = new Gun( x, y, z, angle, gunLength, gunWidth, gunHeight );
        base = new Base( x, y, z, angle, baseLength, baseWidth, baseHeight );
        hitPoints = hp;
    }


    protected int getHitPoints()
    {
        return hitPoints;
    }


    protected void changeHitPoints()
    {
        hitPoints -= 5;
    }


    protected boolean isDead()
    {
        return hitPoints == 0;
    }


    protected void update()
    {
        gun.translate();
        base.translate();
        gun.updateCorners();
        base.updateCorners();
    }
}
