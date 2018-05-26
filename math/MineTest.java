package math;

import org.junit.*;

import static org.junit.Assert.*;


public class MineTest
{

    @Test
    public void constructorTest()
    {
        Mine m = new Mine( 0, 0, 0, 10 );
        assert ( m.getX() == 0 );
        assert ( m.getY() == 0 );
        assert ( m.getZ() == 0 );
        assert ( m.getAngle() == 0 );
        assert ( m.getLength() == 10 );
        assert ( m.getWidth() == 10 );
        assert ( m.getHeight() == 10 );
        assert ( m.getVelocity() == 0 );
        assert ( m.getAngularVelocity() == 0 );
    }


    @Test
    public void translateTest()
    {
        Mine m = new Mine( 0, 0, 0, 10 );
        m.setMoveDirection( 1 );
        m.setTurnDirection( 1 );
        m.translate();
        assert ( m.getX() == 0 );
        assert ( m.getY() == 0 );
        assert ( m.getZ() == 0 );
        assert ( m.getAngle() == 0 );
        assert ( m.getLength() == 10 );
        assert ( m.getWidth() == 10 );
        assert ( m.getHeight() == 10 );
        assert ( m.getVelocity() == 0 );
        assert ( m.getAngularVelocity() == 0 );
    }


    @Test
    public void collisionTest()
    {
        Mine m = new Mine( 0, 0, 0, 10 );
        Base b = new Base( 0, 0, 0, 0, 10, 10, 10 );
        assert ( m.hasCollided( b ) == 1 );
        Mine m2 = new Mine( 0, 0, 100, 10 );
        assert ( m.hasCollided( m2 ) == 0 );
    }
}
