package io;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * 
 * TODO Write a one-sentence summary of your class here. TODO Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author Maithreyee Vatsan
 * @version May 26, 2018
 * @author Period: TODO
 * @author Assignment: Tanks
 *
 * @author Sources: TODO
 */
public class STPacketTest
{
    @Test
    public void SystemPacketTest()
    {
        SystemPacket packet = new SystemPacket( true, 0, 0, 0 );
        assert ( packet.calledFire() == true );
        assert ( packet.calledTurn() == 0 );
        assert ( packet.calledMove() == 0 );
        assert ( packet.calledGunTurn() == 0 );

    }


    @Test
    public void TankPacketTest()
    {
        TankPacket tankpacket = new TankPacket( null, null, 0, 0, (double)0, false );
        assert ( tankpacket.getLoc() == null );
        assert ( tankpacket.getGunLoc() == null );
        assert ( tankpacket.getAngle() == 0 );
        assert ( tankpacket.getGunAngle() == 0 );
        assert ( tankpacket.getHitpoints() == 0 );
        assert ( tankpacket.checkIfFired() == false );

    }

}