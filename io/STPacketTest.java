package io;

// junit tests
import org.junit.*;
import static org.junit.Assert.*;


/**
 * 
 * JUNIT TESTS - IO Package
 * 
 * The class, STPacketTest, tests both the SystemPacket class and TankPacket
 * class.
 * 
 * SystemPacket class function: SystemPacket receives data from the class Main
 * when the keyboard input given is pressed/released by the user from player one
 * or player two. Once it is received system packet calls each method depending
 * on which key is pressed/released by the user. Once it calls the method,
 * SystemPacket sends back information to the Main, helping player one and two
 * to function properly depending on the command.
 * 
 * TankPacket is constantly sent to the runner environment in order to correctly
 * render the tanks in the player window. TankPacket uses Value3D in order to
 * execute and render these tanks properly.
 *
 * @author Maithreyee Vatsan
 * @version May 26, 2018
 * @author Period: TODO
 * @author Assignment: Tanks
 *
 * @author Sources: Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 */
public class STPacketTest

{

    /**
     * SystemPacketTest - checks each method(calledFire, calledTurn, calledMove,
     * calledGunTurn) - tests each method from SystemPacket; SystemPacket is
     * constantly sent to the tanks in order to provide state change information
     * to the tanks in order to account for user manipulations.
     * 
     * 
     * TankPacketTest - checks each method(getLoc, getGunLoc, getAngle,
     * getGunAngle, getHitpoints, checkIfFired) - tests each method from
     * TankPacket, TankPacket is constantly sent to the runner environment in
     * order to correctly render the tanks in the player window. TankPacket uses
     * Value3D in order to execute and render these tanks properly.
     * 
     */

    /**
     * 
     * SystemPacketTest - checks each method(calledFire, calledTurn, calledMove,
     * calledGunTurn) - tests each method from SystemPacket; SystemPacket is
     * constantly sent to the tanks in order to provide state change information
     * to the tanks in order to account for user manipulations.
     * 
     */
    @Test
    public void SystemPacketTest()
    {
        SystemPacket packet = new SystemPacket( true, 0, 0, 0 );
        assert ( packet.calledFire() == true );
        assert ( packet.calledTurn() == 0 );
        assert ( packet.calledMove() == 0 );
        assert ( packet.calledGunTurn() == 0 );

    }


    /**
     * 
     * TankPacketTest - checks each method(getLoc, getGunLoc, getAngle,
     * getGunAngle, getHitpoints, checkIfFired) - tests each method from
     * TankPacket, TankPacket is constantly sent to the runner environment in
     * order to correctly render the tanks in the player window. TankPacket uses
     * Value3D in order to execute and render these tanks properly.
     */
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