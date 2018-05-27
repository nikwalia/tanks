package io;

/**
 * 
 * IO Package: SystemPacket is constantly sent to the tanks in order to provide
 * state change information to the tanks in order to account for user
 * manipulations.
 * 
 * SystemPacket receives data from the class Main when the keyboard input given
 * is pressed/released by the user from player one or player two. Once it is
 * received system packet calls each method depending on which key is
 * pressed/released by the user. Once it calls the method, SystemPacket sends
 * back information to the Main, helping player one and two to function properly
 * depending on the command.
 *
 * @author Maithreyee Vatsan
 * @version May 26, 2018
 * @author Period: 2
 * @author Assignment: Tanks t-34 destroyers final Project
 *
 * @author Sources: Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 */
public class SystemPacket
{
    /**
     * boolean variable - call Fire is used to check when the gun turret needs
     * to be fired on the tank(for either player one or player two) from main
     * when called
     * 
     */
    private boolean callFire;

    /**
     * int variable - call Turn used to check when the tank needs to be turned
     * (for either player one or player two) when the system packet receives
     * data from main when called
     *
     */
    private int callTurn;

    /**
     * int variable - callMove used to check when the tank needs to be moved
     * (for either player one or two) when system packet receives data from main
     * when called
     */
    private int callMove;

    /**
     * int variable - callGunTurn used to check when the gun turrets needs to be
     * turned (for either player one or two) when system packet receives data
     * from main when called
     */
    private int callGunTurn;


    /**
     * SystemPacket is constantly sent to the tanks in order to provide state
     * change information to the tanks in order to account for user
     * manipulations.
     * 
     * SystemPacket receives data from the class Main when the keyboard input
     * given is pressed/released by the user from player one or player two. Once
     * it is received system packet calls each method depending on which key is
     * pressed/released by the user. Once it calls the method, SystemPacket
     * sends back information to the Main, helping player one and two to
     * function properly depending on the command.
     * 
     * 
     * constructor for SystemPacket
     * 
     * @param fire
     *            boolean variable, fire, used to assess whether the gun should
     *            fire when called
     * @param turn
     *            int variable, turn, used to assess whether the tank should
     *            turn when called
     * @param move
     *            int variable, move, used to assess whether the tank should
     *            move when called
     * @param gunTurn
     *            int variable, gunTurn, used to assess whether the gun turret
     *            should turn when called
     */
    public SystemPacket( boolean fire, int turn, int move, int gunTurn )
    {
        callFire = fire;
        callTurn = turn;
        callMove = move;
        callGunTurn = gunTurn;
    }


    /**
     * 
     * the method, calledFire(), is called when the system packet receives data
     * to fire the gun
     *
     * @return calledFire method fires the gun
     */
    public boolean calledFire()
    {
        return callFire;
    }


    /**
     * 
     * the method, calledTurn(), is called when the system packet receives data
     * to turn the tank
     * 
     * @return calledTurn method turns the tank
     */
    public int calledTurn()
    {
        return callTurn;
    }


    /**
     * 
     * the method, calledMove(), is called when the system packet receives data
     * to move the tank
     * 
     * @return calledMove method moves the tank
     */
    public int calledMove()
    {
        return callMove;
    }


    /**
     * 
     * the method, calledGunTurn(), is called when the system packet receives
     * data to turn the gun turret (the gun on top of the tank)
     * 
     * @return calledGunTurn method turns the gun turret (gun on top of the
     *         tank)
     */
    public int calledGunTurn()
    {
        return callGunTurn;
    }

}
