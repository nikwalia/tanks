package math;

import org.junit.*;

import static org.junit.Assert.*;


/**
 * 
 * Testing class for Base
 *
 * @author Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 * @version May 12, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class BaseTest
{

    /**
     * Tests for Base class
     * 
     * BasicConstructorTest- checks that all values are initialized correctly
     * AccelerationOneSecondTest- correct velocity after one second of
     * positive motion
     * AccelerationThreeSecondTest- maximum velocity after three seconds of
     * positive motion
     * AccelerationNegativeOneSecondTest- correct velocity after one second of
     * negative motion
     * AccelerationNegativeThreeSecondTest- minimum velocity after three seconds of
     * negative motion
     * DecelerateOneSecondTest- correct velocity after one second of deceleration
     * from max positive
     * DecelerateThreeSecondsTest- 0 velocity after three seconds of deceleration
     * from max positive
     * DeclerateNegativeOneSecondTest- correct velocity after one second of deceleration
     * from max negative
     * DecelerateNegativeThreeSecondsTest- 0 velocity after three seconds of deceleration
     * from max negative
     * RotateTest- correct angular velocity for various different settings
     * LinearDisplacementTest- correct change in position for change in time
     * AngularDisplacementTest- correct change in angle for change in time
     * CollisionTest- various types of collisions
     */

    @Test
    public void BasicConstructorTest()
    {
        Base b = new Base( 0, 0, 0, 0, 10, 20, 30 );
        assert ( b.getVelocity() == 0 );
        assert ( b.getAngularVelocity() == 0 );
        assert ( b.getTime() != 0 );
        assert ( b.getLength() == 10 );
        assert ( b.getWidth() == 20 );
        assert ( b.getHeight() == 30 );
        assert ( b.getAngle() == 0 );
        assert ( b.getTurnDirection() == 0 );
        assert ( b.getMoveDirection() == 0 );
        assertEquals( b.curTime, System.nanoTime() / 1e+9, 0.1 );
        double oldTime = System.nanoTime() / 1e+9;
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        b.updateTime();
        assertEquals( b.curTime, oldTime + 1, 0.1 );
    }


    @Test
    public void AccelerateOneSecondTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 1 );
        double originalTime = System.nanoTime() / 1e+9;
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double curTime = System.nanoTime() / 1e+9;
        b.translate();
        assertEquals( b.ACCELERATION * ( curTime - originalTime ), b.getVelocity(), 0.1 );
    }


    @Test
    public void AccelerateFiveSecondsTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 1 );
        try
        {
            Thread.sleep( 5000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        b.translate();
        assertEquals( b.MAXVELOCITY, b.getVelocity(), 0.1 );
    }


    @Test
    public void AccelerateNegativeOneSecondTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( -1 );
        double originalTime = System.nanoTime() / 1e+9;
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double curTime = System.nanoTime() / 1e+9;
        b.translate();
        assertEquals( -b.ACCELERATION * ( curTime - originalTime ), b.getVelocity(), 0.1 );
    }


    @Test
    public void AccelerateNegativeFiveSecondsTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( -1 );
        try
        {
            Thread.sleep( 5000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        b.translate();
        assertEquals( b.MINVELOCITY, b.getVelocity(), 0.1 );
    }


    @Test
    public void DecelerateOneSecondTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 0 );
        b.setVelocity( b.MAXVELOCITY );
        double originalTime = System.nanoTime() / 1e+9;
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double curTime = System.nanoTime() / 1e+9;
        b.translate();
        assertEquals( b.MAXVELOCITY - b.ACCELERATION * ( curTime - originalTime ),
            b.getVelocity(),
            0.1 );
    }


    @Test
    public void DecelerateFiveSecondsTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 0 );
        b.setVelocity( b.MAXVELOCITY );
        try
        {
            Thread.sleep( 5000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        b.translate();
        assertEquals( 0, b.getVelocity(), 0.1 );
    }


    @Test
    public void DecelerateNegativeOneSecondTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 0 );
        b.setVelocity( b.MINVELOCITY );
        double originalTime = System.nanoTime() / 1e+9;
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double curTime = System.nanoTime() / 1e+9;
        b.translate();
        assertEquals( b.MINVELOCITY + b.ACCELERATION * ( curTime - originalTime ),
            b.getVelocity(),
            0.1 );
    }


    @Test
    public void DecelerateNegativeFiveSecondsTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 0 );
        b.setVelocity( b.MINVELOCITY );
        try
        {
            Thread.sleep( 5000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        b.translate();
        assertEquals( 0, b.getVelocity(), 0.1 );
    }


    @Test
    public void OvershootTest()
    {
        Base b = new Base( 0, 0, 0, 0, 10, 10, 10 );
        b.setVelocity( -1 );
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        b.translate();
        assertEquals( 0, b.getVelocity(), 0.1 );
        b.setVelocity( 1 );
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        b.translate();
        assertEquals( 0, b.getVelocity(), 0.1 );
    }


    @Test
    public void RotateTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setTurnDirection( 1 );
        b.translate();
        assertEquals( b.maxAngularVelocity, b.angularVelocity, 0.1 );
        b.setTurnDirection( 0 );
        b.translate();
        assertEquals( b.ZEROANGULARVELOCITY, b.angularVelocity, 0.1 );
        b.setTurnDirection( -1 );
        b.translate();
        assertEquals( b.minAngularVelocity, b.angularVelocity, 0.1 );
        b.setAngularVelocity( Math.PI / 8 );
        assertEquals( b.maxAngularVelocity, b.angularVelocity, 0.1 );
    }


    @Test
    public void LinearDisplacementTest()
    {
        Base b = new Base( 0, 0, 0, 0, 10, 20, 30 );
        b.setMoveDirection( 1 );
        double originalTime = System.nanoTime() / 1e+9;
        double curTime = System.nanoTime() / 1e+9;
        while ( curTime - originalTime < 1 )
        {
            curTime = System.nanoTime() / 1e+9;
            b.translate();
        }
        b.translate();
        assertEquals( b.ACCELERATION / 2 * ( curTime - originalTime ) * ( curTime - originalTime ),
            b.getX(),
            0.1 );
    }


    @Test
    public void AngularDisplacementTest()
    {
        Base b = new Base( 0, 0, 0, Math.PI / 4, 10, 20, 30 );
        b.setTurnDirection( 1 );
        double originalTime = System.nanoTime() / 1e+9;
        double curTime = System.nanoTime() / 1e+9;
        while ( curTime - originalTime < 1 )
        {
            curTime = System.nanoTime() / 1e+9;
            b.translate();
        }
        assertEquals( b.maxAngularVelocity * ( curTime - originalTime ) + Math.PI / 4,
            b.getAngle(),
            0.1 );
    }


    @Test
    public void CollisionTest()
    {
        Base b = new Base( 0, 0, 0, 0, 20, 20, 20 );
        Base b2 = new Base( 0, 0, 20, 0, 20, 20, 20 );
        assert ( b.hasCollided( b2 ) == 1 );
        b2 = new Base( 0, 0, 30, 0, 20, 20, 20 );
        assert ( b.hasCollided( b2 ) == -1 );
        Bullet bullet = new Bullet( -20, 0, 0, 0 );
        assert ( b.hasCollided( bullet ) == 1 );
        bullet = new Bullet( 0, 0, -30, 0 );
        assert ( b.hasCollided( bullet ) == -1 );
    }
}
