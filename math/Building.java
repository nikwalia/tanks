package math;

public class Building extends Structure3D
{

    public Building(
        double x,
        double y,
        double z,
        double angle,
        int l,
        int w,
        int h,
        double maxangularvel )
    {
        super( x, y, z, angle, l, w, h, maxangularvel );
    }

    public void translate()
    {
        
    }

    public void updateCorners()
    {
        baseRectangle[0] = new Value3D(
            getX() + length / 2 * Math.cos( getAngle() ) - width / 2 * Math.sin( getAngle() ),
            0,
            getZ() + length / 2 * Math.sin( getAngle() ) + width / 2 * Math.cos( getAngle() ) );
        baseRectangle[1] = new Value3D(
            getX() - length / 2 * Math.cos( getAngle() ) - width / 2 * Math.sin( getAngle() ),
            0,
            getZ() - length / 2 * Math.sin( getAngle() ) + width / 2 * Math.cos( getAngle() ) );
        baseRectangle[2] = new Value3D(
            getX() - length / 2 * Math.cos( getAngle() ) + width / 2 * Math.sin( getAngle() ),
            0,
            getZ() - length / 2 * Math.sin( getAngle() ) - width / 2 * Math.cos( getAngle() ) );
        baseRectangle[3] = new Value3D(
            getX() + length / 2 * Math.cos( getAngle() ) + width / 2 * Math.sin( getAngle() ),
            0,
            getZ() + length / 2 * Math.sin( getAngle() ) - width / 2 * Math.cos( getAngle() ) );
    }

    public int hasCollided( Structure3D other )
    {
        return 0;
    }

    public int onCollision( Structure3D other )
    {
        return 0;
    }

    public int collisionSide( Structure3D other, int corner )
    {
        return 0;
    }

}
