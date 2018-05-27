package math;

import org.junit.*;


/**
 * 
 * Testing class for Mine
 *
 * @author Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 * @version May 26, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class MineTest
{

    /**
     * Tests for Mine
     * 
     * ConstructorTest- values are initialized correctly
     * TranslateTest- ensures that stub method does not change any states
     * CollisionTest- Mine returns correct status update for collision checks
     * with different classes
     */

    @Test
    public void ConstructorTest()
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
    public void TranslateTest()
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
    public void CollisionTest()
    {
        Mine m = new Mine( 0, 0, 0, 10 );
        Base b = new Base( 0, 0, 0, 0, 10, 10, 10 );
        assert ( m.hasCollided( b ) == 1 );
        Mine m2 = new Mine( 0, 0, 100, 10 );
        assert ( m.hasCollided( m2 ) == 0 );
    }
}
