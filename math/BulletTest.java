package math;

import org.junit.*;

import static org.junit.Assert.*;


/**
 * 
 * Testing class for Bullet
 *
 * @author Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 * @version May 12, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class BulletTest
{

    /**
     * Tests for Bullet
     * 
     * ConstructorTest- all values are initialized correctly
     * TranslateTest- the bullet's position is correctly updated
     * HasCollidedTest- correctly returns status update
     */

    @Test
    public void ConstructorTest()
    {
        Bullet b = new Bullet( 0, 0, 0, 0 );
        assert ( b.getAngle() == 0 );
        assert ( b.getVelocity() == 1000 );
        assert ( b.getAngularVelocity() == 0 );
        assert ( b.getMoveDirection() == 1 );
        assert ( b.getTurnDirection() == 0 );
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
        assertEquals( 10 + ( newTime - curTime ) * b.getVelocity() * Math.cos( Math.PI / 4 ),
            b.getX(),
            0.1 );
        assertEquals( 20 + b.ACCELERATION / 2 * ( newTime - curTime ), b.getY(), 0.1 );
        assertEquals( 30 + ( newTime - curTime ) * b.getVelocity() * Math.sin( Math.PI / 4 ),
            b.getZ(),
            0.1 );
        assertEquals( b.ACCELERATION * ( newTime - curTime ), b.getFallVelocity(), 0.1 );
    }


    @Test
    public void HasCollidedTest()
    {
        Bullet bullet = new Bullet( 0, 0, 0, 0 );
        Base b = new Base( 0, 0, 0, 0, 10, 10, 10 );
        assert ( bullet.hasCollided( b ) == 1 );
    }
}
