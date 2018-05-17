package math;

import org.junit.*;

import io.TankPacket;

import static org.junit.Assert.*;

/**
 * Testing class for Tank
 * 
 * @author  Nikash Walia
 * @version 5/17/18
 * @author  Period: 2
 * @author  Assignment: Tanks
 * 
 * @author Sources: None
 */
public class TankTest
{
    @Test
    public void ConstructorTest()
    {
        Tank tank = new Tank(0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 100);
        TankPacket p = tank.sendData();
        assert(p.getLoc().getX() == 0);
        assert(p.getLoc().getY() == 0);
        assert(p.getLoc().getZ() == 0);
        assert(p.getAngle() == 0);
        assert(tank.baseLength == 10);
        assert(tank.baseWidth == 10);
        assert(tank.baseHeight == 10);
        assert(tank.gunLength == 10);
        assert(tank.gunWidth == 10);
        assert(tank.gunHeight == 10);
        assert(tank.getHitPoints() == 100);
        assert(!tank.isDead());
    }
}
