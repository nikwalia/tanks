package math;

import org.junit.*;

import io.SystemPacket;
import io.TankPacket;

import static org.junit.Assert.*;


/**
 * Testing class for Tank
 * 
 * @author Nikash Walia
 * @version 5/17/18
 * @author Period: 2
 * @author Assignment: Tanks
 * 
 * @author Sources: None
 */
public class TankTest
{
    @Test
    public void ConstructorTest()
    {
        Tank tank = new Tank( 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 1000 );
        TankPacket p = tank.sendData();
        assert ( p.getLoc().getX() == 0 );
        assert ( p.getLoc().getY() == 0 );
        assert ( p.getLoc().getZ() == 0 );
        assert ( p.getAngle() == 0 );
        assert ( p.getGunLoc().getX() == 5 );
        assert ( p.getGunLoc().getY() == -10 );
        assert ( p.getGunLoc().getZ() == 0 );
        assert ( p.getGunAngle() == 0 );
        assert ( tank.baseLength == 10 );
        assert ( tank.baseWidth == 10 );
        assert ( tank.baseHeight == 10 );
        assert ( tank.gunLength == 10 );
        assert ( tank.gunWidth == 10 );
        assert ( tank.gunHeight == 10 );
        assert ( tank.data.isEmpty() );
        assert ( tank.getHitPoints() == 1000 );
        assert ( !tank.isDead() );
        tank.changeHitPoints();
        assert ( tank.getHitPoints() == 750 );
    }


    @Test
    public void ReceiveDataTest()
    {
        Tank tank = new Tank( 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 100 );
        SystemPacket sys = new SystemPacket( false, 0, 0, 0 );
        tank.receiveData( sys );
        assert ( !tank.data.isEmpty() );
    }


    @Test
    public void TankCollisionTest()
    {
        Tank tank = new Tank( 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 100 );
        SystemPacket sys = new SystemPacket( false, 1, 1, 1 );
        tank.receiveData( sys );
        tank.update();
        Tank t2 = new Tank( 8, 0, 0, 0, 10, 10, 10, 10, 10, 10, 100 );
        assert ( tank.hasCollided( t2 ) );
        tank.onCollision( t2 );
        assert ( tank.base.getAngularVelocity() == 0 );
        assert ( tank.base.getVelocity() == 0 );
        assert ( tank.gun.getAngularVelocity() == 0 );
        t2 = new Tank( 15, 0, 0, 0, 10, 10, 10, 10, 10, 10, 100 );
        assert ( !tank.hasCollided( t2 ) );
        t2 = new Tank(-15, 0, 0, 0, 20, 10, 10, 10, 10, 10, 100);
    }


    @Test
    public void BulletCollisionTest()
    {
        Tank tank = new Tank( 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 1000 );
        Bullet b = new Bullet( 5, 5, 5, 0 );
        assert ( tank.hasCollided( b ) );
        tank.onCollision( b );
        assert ( tank.getHitPoints() == 750 );
        b = new Bullet(10, 0, 0, 0);
        assert(tank.hasCollided( b ));
        tank.onCollision( b );
        assert(tank.getHitPoints() == 500);
        tank.onCollision( b );
        tank.onCollision( b );
        assert ( tank.isDead() );
    }


    @Test
    public void FireTest()
    {
        Tank tank = new Tank( 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 100 );
        SystemPacket sys = new SystemPacket( true, 0, 0, 0 );
        tank.receiveData( sys );
        tank.update();
        TankPacket t = tank.sendData();
        assert ( !t.checkIfFired() );
        try
        {
            Thread.sleep( 5000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        tank.receiveData( sys );
        tank.update();
        t = tank.sendData();
        assert ( t.checkIfFired() );
    }
}
