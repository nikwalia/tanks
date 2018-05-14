package math;

import org.junit.*;


/**
 * 
 * Tester class for the Value3D class
 *
 * @author Nikash Walia
 * @version May 5, 2018
 * @author Period: 2
 * @author Assignment: Tanks
 *
 * @author Sources: None
 */
public class Value3DTest
{

    /**
     * 
     * Value3D Tests
     * 
     * TestTranslate- giving a distance to translate by results in correct
     * coordinate TestSet- giving a new coordinate results in correct coordinate
     */

    @Test
    public void TestTranslate()
    {
        Value3D test = new Value3D( 0, 0, 0 );
        assert ( test.getX() == 0 );
        assert ( test.getY() == 0 );
        assert ( test.getZ() == 0 );
        test.changeX( 10 );
        test.changeY( 10 );
        test.changeZ( 10 );
        assert ( test.getX() == 10 );
        assert ( test.getY() == 10 );
        assert ( test.getZ() == 10 );
    }


    @Test
    public void TestSet()
    {
        Value3D test = new Value3D( 0, 0, 0 );
        test.setX( 10 );
        test.setY( 10 );
        test.setZ( 10 );
        assert ( test.getX() == 10 );
        assert ( test.getY() == 10 );
        assert ( test.getZ() == 10 );
    }
}
