package math;

import org.junit.*;

import static org.junit.Assert.*;


public class BaseTest
{
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
        assert ( b.baseRectangle[0].getX() == 5 );
        assert ( b.baseRectangle[0].getZ() == 10 );
        assert ( b.baseRectangle[1].getX() == -5 );
        assert ( b.baseRectangle[1].getZ() == 10 );
        assert ( b.baseRectangle[2].getX() == -5 );
        assert ( b.baseRectangle[2].getZ() == -10 );
        assert ( b.baseRectangle[3].getX() == 5 );
        assert ( b.baseRectangle[3].getZ() == -10 );
    }


    @Test
    public void CornersTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        assertEquals( 6.464, b.baseRectangle[0].getX(), 0.1 );
        assertEquals( 40.607, b.baseRectangle[0].getZ(), 0.1 );
        assertEquals( -0.607, b.baseRectangle[1].getX(), 0.1 );
        assertEquals( 33.536, b.baseRectangle[1].getZ(), 0.1 );
        assertEquals( 13.536, b.baseRectangle[2].getX(), 0.1 );
        assertEquals( 19.393, b.baseRectangle[2].getZ(), 0.1 );
        assertEquals( 20.607, b.baseRectangle[3].getX(), 0.1 );
        assertEquals( 26.464, b.baseRectangle[3].getZ(), 0.1 );
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
        assertEquals( 8 * ( curTime - originalTime ), b.getVelocity(), 0.1 );
    }


    @Test
    public void AccelerateThreeSecondsTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 1 );
        try
        {
            Thread.sleep( 3000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        b.translate();
        assertEquals( 20, b.getVelocity(), 0.1 );
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
        assertEquals( -8 * ( curTime - originalTime ), b.getVelocity(), 0.1 );
    }


    @Test
    public void AccelerateNegativeThreeSecondsTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( -1 );
        try
        {
            Thread.sleep( 3000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        b.translate();
        assertEquals( -20, b.getVelocity(), 0.1 );
    }


    @Test
    public void DecelerateOneSecondTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 0 );
        b.setVelocity( 20 );
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
        assertEquals( 20 - 8 * ( curTime - originalTime ), b.getVelocity(), 0.1 );
    }


    @Test
    public void DecelerateThreeSecondsTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 0 );
        b.setVelocity( 20 );
        try
        {
            Thread.sleep( 3000 );
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
        b.setVelocity( -20 );
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
        assertEquals( -20 + 8 * ( curTime - originalTime ), b.getVelocity(), 0.1 );
    }


    @Test
    public void DecelerateNegativeThreeSecondsTest()
    {
        Base b = new Base( 10, 20, 30, Math.PI / 4, 10, 20, 30 );
        b.setMoveDirection( 0 );
        b.setVelocity( -20 );
        try
        {
            Thread.sleep( 3000 );
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
        b.setAngularVelocity( Math.PI / 4 );
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
        assertEquals( 4 * ( curTime - originalTime ) * ( curTime - originalTime ), b.getX(), 0.1 );
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
    public void SimpleBulletCollisionTest()
    {
        Base base = new Base( 0, 0, 0, 0, 10, 10, 10 );
        Bullet bullet = new Bullet( 0, 0, 0, 0 );
        assert ( base.hasCollided( bullet ) );
    }


    @Test
    public void TranslatedBaseCollisionTest()
    {
        Base base = new Base( 30, 0, 25, Math.PI / 4, 20, 10, 0 );
        Bullet b1 = new Bullet( 34, 0, 32, Math.PI / 4 );
        assert ( base.hasCollided( b1 ) );
        Bullet b2 = new Bullet( 29, 0, 32, Math.PI / 4 );
        assert ( !base.hasCollided( b2 ) );
    }


    @Test
    public void BaseToBaseCollisionTest()
    {
        Base b1 = new Base( 0, 0, 0, 0, 10, 10, 10 );
        Base b2 = new Base( 0, 0, 10, 0, 10, 10, 10 );
        Base b3 = new Base( 0, 0, 12, 0, 10, 10, 10 );
        assert ( b1.hasCollided( b2 ) );
        assert ( !b1.hasCollided( b3 ) );
    }


    @Test
    public void OnCollisionTest()
    {
        Base base = new Base( 0, 0, 0, 0, 10, 10, 10 );
        Bullet bullet = new Bullet( 0, 0, 0, 0 );
        assert ( base.onCollision( bullet ) == -1 );
        Base b2 = new Base( 0, 0, 10, 0, 10, 10, 10 );
        assert ( base.onCollision( b2 ) == 1 );
    }

}
