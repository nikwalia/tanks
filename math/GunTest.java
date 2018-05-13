package math;

import org.junit.*;

import static org.junit.Assert.*;


/**
 * Testing class for gun
 * 
 * @author Nikash Walia
 * @version 5/12/18
 * @author Period: 2
 * @author Assignment: Tanks
 * 
 * @author Sources: None
 */
public class GunTest
{
    
    /**
     * 
     * Unit tests for Gun
     * 
     * ConstructorTest- all values are initialized correctly
     * LinearTranslationTest- the gun's position changes correctly based on the base's
     * PositiveAngularRotationTest- correct increase in angle
     * NegativeAngularRotationTest- correct decrease in angle
     * BasePositiveTurnZeroAngularVelocityTest- correct velocity and increase in angle
     * BaseNegativeTurnZeroAngularVelocityTest- correct velocity and decrease in angle
     * BasePositiveTurnPositiveAngularVelocityTest- correct velocity and increase in angle
     * BasePositiveTurnNegativeAngularVelocityTest- correct velocity and decrease in angle
     * BaseNegativeTurnPositiveAngularVelocityTest- correct velocity and increase in angle
     * BaseNegativeTurnNegativeAngularVelocityTest- correct velocity and decrease in angle
     * SimpleBulletCollisionTest- bullet correctly registers as collided / not collided
     * GunToGunCollisionTest- collision with gun correctly registers
     * OnCollisionTest- collision results in correct behavior
     * CollisionSideTest- collision registers on correct side
     */
    
    @Test
    public void ConstructorTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        assert ( g.getX() == 5 );
        assert ( g.getY() == -10 );
        assert ( g.getZ() == 0 );
        assert ( g.getAngle() == 0 );
        Gun g2 = new Gun( Math.PI / 4, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        assertEquals( g2.getX(), 5 * Math.cos( Math.PI / 4 ), 0.1 );
        assert ( g.getY() == -10 );
        assertEquals( g2.getZ(), 5 * Math.sin( Math.PI / 4 ), 0.1 );
        assert ( g.baseRectangle[0].getX() == 10 );
        assert ( g.baseRectangle[0].getZ() == 5 );
        assert ( g.baseRectangle[1].getX() == 0 );
        assert ( g.baseRectangle[1].getZ() == 5 );
        assert ( g.baseRectangle[2].getX() == 0 );
        assert ( g.baseRectangle[2].getZ() == -5 );
        assert ( g.baseRectangle[3].getX() == 10 );
        assert ( g.baseRectangle[3].getZ() == -5 );
    }


    @Test
    public void LinearTranslationTest()
    {
        Base b = new Base( 0, 0, 0, 0, 10, 10, 10 );
        b.setMoveDirection( 1 );
        Gun g = new Gun( Math.PI / 4, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        double curTime = System.nanoTime() / 1e+9;
        double newTime = System.nanoTime() / 1e+9;
        while ( newTime - curTime < 1 )
        {
            newTime = System.nanoTime() / 1e+9;
            b.translate();
            g.setBaseCenter( new Value3D( b.getX(), b.getY(), b.getZ() ) );
            g.translate();
        }
        assertEquals( 4 * ( newTime - curTime ) + 5 * Math.cos( Math.PI / 4 ), g.getX(), 0.1 );
        assertEquals( b.getX() + 5 * Math.cos( Math.PI / 4 ), g.getX(), 0.1 );
        assertEquals( -10, g.getY(), 0.1 );
        assertEquals( 5 * Math.sin( Math.PI / 4 ), g.getZ(), 0.1 );
    }


    @Test
    public void PositiveAngularRotationTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        g.setTurnDirection( 1 );
        double curTime = System.nanoTime() / 1e+9;
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double newTime = System.nanoTime() / 1e+9;
        g.translate();
        assertEquals( g.maxAngularVelocity, g.getAngle(), 0.1 );
        assertEquals( Math.PI / 4 * ( newTime - curTime ), g.getAngle(), 0.1 );
        assertEquals( 5 * Math.cos( Math.PI / 4 * ( newTime - curTime ) ), g.getX(), 0.1 );
        assertEquals( -10, g.getY(), 0.1 );
        assertEquals( 5 * Math.sin( Math.PI / 4 * ( newTime - curTime ) ), g.getZ(), 0.1 );
    }


    @Test
    public void NegativeAngularRotationTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        g.setTurnDirection( -1 );
        double curTime = System.nanoTime() / 1e+9;
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double newTime = System.nanoTime() / 1e+9;
        g.translate();
        assertEquals( g.minAngularVelocity, g.getAngularVelocity(), 0.1 );
        assertEquals( -Math.PI / 4 * ( newTime - curTime ), g.getAngle(), 0.1 );
        assertEquals( 5 * Math.cos( -Math.PI / 4 * ( newTime - curTime ) ), g.getX(), 0.1 );
        assertEquals( -10, g.getY(), 0.1 );
        assertEquals( 5 * Math.sin( -Math.PI / 4 * ( newTime - curTime ) ), g.getZ(), 0.1 );
    }


    @Test
    public void BasePositiveTurnZeroAngularVelocityTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        double curTime = System.nanoTime() / 1e+9;
        g.setBaseTurn( 1 );
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double newTime = System.nanoTime() / 1e+9;
        g.translate();
        assertEquals( Math.PI / 8, g.getAngularVelocity(), 0.1 );
        assertEquals( Math.PI / 8 * ( newTime - curTime ), g.getAngle(), 0.1 );
        assertEquals( 5 * Math.cos( Math.PI / 8 * ( newTime - curTime ) ), g.getX(), 0.1 );
        assertEquals( -10, g.getY(), 0.1 );
        assertEquals( 5 * Math.sin( Math.PI / 8 * ( newTime - curTime ) ), g.getZ(), 0.1 );
    }


    @Test
    public void BaseNegativeTurnZeroAngularVelocityTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        double curTime = System.nanoTime() / 1e+9;
        g.setBaseTurn( -1 );
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double newTime = System.nanoTime() / 1e+9;
        g.translate();
        assertEquals( -Math.PI / 8, g.getAngularVelocity(), 0.1 );
        assertEquals( -Math.PI / 8 * ( newTime - curTime ), g.getAngle(), 0.1 );
        assertEquals( 5 * Math.cos( -Math.PI / 8 * ( newTime - curTime ) ), g.getX(), 0.1 );
        assertEquals( -10, g.getY(), 0.1 );
        assertEquals( 5 * Math.sin( -Math.PI / 8 * ( newTime - curTime ) ), g.getZ(), 0.1 );
    }


    @Test
    public void BasePositiveTurnPositiveAngularVelocityTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        double curTime = System.nanoTime() / 1e+9;
        g.setBaseTurn( 1 );
        g.setTurnDirection( 1 );
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double newTime = System.nanoTime() / 1e+9;
        g.translate();
        assertEquals( 3 * Math.PI / 8, g.getAngularVelocity(), 0.1 );
        assertEquals( 3 * Math.PI / 8 * ( newTime - curTime ), g.getAngle(), 0.1 );
        assertEquals( 5 * Math.cos( 3 * Math.PI / 8 * ( newTime - curTime ) ), g.getX(), 0.1 );
        assertEquals( -10, g.getY(), 0.1 );
        assertEquals( 5 * Math.sin( 3 * Math.PI / 8 * ( newTime - curTime ) ), g.getZ(), 0.1 );
    }


    @Test
    public void BasePositiveTurnNegativeAngularVelocityTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        double curTime = System.nanoTime() / 1e+9;
        g.setBaseTurn( 1 );
        g.setTurnDirection( -1 );
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double newTime = System.nanoTime() / 1e+9;
        g.translate();
        assertEquals( -Math.PI / 8, g.getAngularVelocity(), 0.1 );
        assertEquals( -Math.PI / 8 * ( newTime - curTime ), g.getAngle(), 0.1 );
        assertEquals( 5 * Math.cos( -Math.PI / 8 * ( newTime - curTime ) ), g.getX(), 0.1 );
        assertEquals( -10, g.getY(), 0.1 );
        assertEquals( 5 * Math.sin( -Math.PI / 8 * ( newTime - curTime ) ), g.getZ(), 0.1 );
    }


    @Test
    public void BaseNegativeTurnPositiveAngularVelocityTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        double curTime = System.nanoTime() / 1e+9;
        g.setBaseTurn( -1 );
        g.setTurnDirection( 1 );
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double newTime = System.nanoTime() / 1e+9;
        g.translate();
        assertEquals( Math.PI / 8, g.getAngularVelocity(), 0.1 );
        assertEquals( Math.PI / 8 * ( newTime - curTime ), g.getAngle(), 0.1 );
        assertEquals( 5 * Math.cos( Math.PI / 8 * ( newTime - curTime ) ), g.getX(), 0.1 );
        assertEquals( -10, g.getY(), 0.1 );
        assertEquals( 5 * Math.sin( Math.PI / 8 * ( newTime - curTime ) ), g.getZ(), 0.1 );
    }


    @Test
    public void BaseNegativeTurnNegativeAngularVelocityTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        double curTime = System.nanoTime() / 1e+9;
        g.setBaseTurn( -1 );
        g.setTurnDirection( -1 );
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        double newTime = System.nanoTime() / 1e+9;
        g.translate();
        assertEquals( -3 * Math.PI / 8, g.getAngularVelocity(), 0.1 );
        assertEquals( -3 * Math.PI / 8 * ( newTime - curTime ), g.getAngle(), 0.1 );
        assertEquals( 5 * Math.cos( -3 * Math.PI / 8 * ( newTime - curTime ) ), g.getX(), 0.1 );
        assertEquals( -10, g.getY(), 0.1 );
        assertEquals( 5 * Math.sin( -3 * Math.PI / 8 * ( newTime - curTime ) ), g.getZ(), 0.1 );
    }


    @Test
    public void SimpleBulletCollisionTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        Bullet bullet = new Bullet( 0, 0, 0, 0 );
        assert ( g.hasCollided( bullet ) == 10 );
        bullet = new Bullet( 20, 20, 20, 0 );
        assert ( g.hasCollided( bullet ) == -1 );
    }


    @Test
    public void GunToGunCollisionTest()
    {
        Gun g1 = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        Gun g2 = new Gun( Math.PI, 10, 10, 10, new Value3D( 10, 0, 0 ), 10 );
        assert ( g1.hasCollided( g2 ) >= 0 );
        g2 = new Gun( 0, 10, 10, 10, new Value3D( 20, 20, 20 ), 10 );
        assert ( g1.hasCollided( g2 ) < 0 );
    }


    @Test
    public void OnCollisionTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        g.setAngularVelocity( g.maxAngularVelocity );
        Bullet bullet = new Bullet( 0, 0, 0, 0 );
        assert ( g.onCollision( bullet ) == -1 );
        assert ( g.angularVelocity == g.maxAngularVelocity );

        Gun g2 = new Gun( Math.PI, 10, 10, 10, new Value3D( 10, 0, 0 ), 10 );
        assert ( g.onCollision( g2 ) == 1 );
        assert ( g.angularVelocity == 0 );
    }


    @Test
    public void CollisionSideTest()
    {
        Gun g = new Gun( 0, 10, 10, 10, new Value3D( 0, 0, 0 ), 10 );
        Gun g2 = new Gun( 3 * Math.PI / 2, 10, 2, 10, new Value3D( 0, 0, 15 ), 10 );
        assert ( g.collisionSide( g2, g.hasCollided( g2 ) ) == 0 );
        g2 = new Gun( 0, 10, 2, 10, new Value3D( -10, 0, 0 ), 10 );
        assert ( g.collisionSide( g2, g.hasCollided( g2 ) ) == 1 );
        g2 = new Gun( Math.PI / 2, 10, 2, 10, new Value3D( 5, 0, -15 ), 10 );
        assert ( g.collisionSide( g2, g.hasCollided( g2 ) ) == 2 );
        g2 = new Gun( Math.PI, 10, 2, 10, new Value3D( 20, 0, 0 ), 10 );
        assert ( g.collisionSide( g2, g.hasCollided( g2 ) ) == 3 );
    }
}
