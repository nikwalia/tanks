package math;

import org.junit.*;

import static org.junit.Assert.*;


public class BulletTest
{

    /**
     * Tests for Bullet
     * 
     * ConstructorTest- all values are initialized correctly
     * TranslateTest- the bullet's position is correctly updated
     * HasCollidedTest- correctly returns status update
     * OnCollisionTest- correctly zeroes all values
     * CollisionSideTest- stub method tester, always returns 0
     */

    @Test
    public void ConstructorTest()
    {
        Bullet b = new Bullet( 0, 0, 0, 0 );
        assert ( b.baseRectangle[0].getX() == 0 );
        assert ( b.baseRectangle[0].getY() == 0 );
        assert ( b.baseRectangle[0].getZ() == 0 );
        assert ( b.getAngle() == 0 );
        assert ( b.getVelocity() == 100 );
        assert ( b.getAngularVelocity() == 0 );
        assert ( b.getMoveDirection() == 1 );
        assert ( b.getTurnDirection() == 0 );
        assert ( b.baseRectangle[1] == null );
        assert ( b.baseRectangle[2] == null );
        assert ( b.baseRectangle[3] == null );
    }


    @Test
    public void TranslateTest()
    {
        Bullet b = new Bullet( 10, 20, 30, Math.PI / 4 );
        double curTime = System.nanoTime() / 1e+9;
        double newTime = System.nanoTime() / 1e+9;
        while ( newTime - curTime < 1 )
        {
            newTime = System.nanoTime() / 1e+9;
            b.translate();
        }
        assertEquals( 10 + ( newTime - curTime ) * 100 * Math.cos( Math.PI / 4 ), b.getX(), 0.1 );
        assertEquals( 20 + 4.9 * ( newTime - curTime ), b.getY(), 0.1 );
        assertEquals( 30 + ( newTime - curTime ) * 100 * Math.sin( Math.PI / 4 ), b.getZ(), 0.1 );
        assertEquals( 10 + ( newTime - curTime ) * 100 * Math.cos( Math.PI / 4 ),
            b.baseRectangle[0].getX(),
            0.1 );
        assertEquals( 20 + 4.9 * ( newTime - curTime ), b.baseRectangle[0].getY(), 0.1 );
        assertEquals( 30 + ( newTime - curTime ) * 100 * Math.sin( Math.PI / 4 ),
            b.baseRectangle[0].getZ(),
            0.1 );
        assertEquals( 9.8 * ( newTime - curTime ), b.getFallVelocity(), 0.1 );
    }


    @Test
    public void HasCollidedTest()
    {
        Bullet bullet = new Bullet( 0, 0, 0, 0 );
        Base b = new Base( 0, 0, 0, 0, 10, 10, 10 );
        assert ( bullet.hasCollided( b ) == 10 );
    }


    @Test
    public void OnCollisionTest()
    {
        Bullet bullet = new Bullet( 0, 0, 0, 0 );
        Base b = new Base( 0, 0, 0, 0, 10, 10, 10 );
        assert ( bullet.onCollision( b ) == 0 );
        assert ( b.getVelocity() == 0 );
        assert ( b.getX() == 0 );
        assert ( b.getY() == 0 );
        assert ( b.getZ() == 0 );
        assert ( b.getAngle() == 0 );
        assert ( b.getMoveDirection() == 0 );
    }


    @Test
    public void CollisionSideTest()
    {
        Bullet bullet = new Bullet( 0, 0, 0, 0 );
        Base b = new Base( 0, 0, 0, 0, 10, 10, 10 );
        assert ( bullet.collisionSide( b, 0 ) == 0 );
    }
}
