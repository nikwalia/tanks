package io;

/**
 * calls Value3D from the math package - Value3D that represents a point in 3D
 * space
 */
import math.Value3D;


/**
 * IO PACKAGE: TankPacket is constantly sent to the runner environment in order
 * to correctly render the tanks in the player window. TankPacket uses Value3D
 * in order to execute and render these tanks properly.
 *
 * @author Maithreyee Vatsan
 * @version May 26, 2018
 * @author Period: 2
 * @author Assignment: Tanks t-34 destroyers final Project
 *
 * @author Sources: Nikash Walia, Roopak Phatak, Maithreyee Vatsan
 */
public class TankPacket
{
    /**
     * Value3D loc - finds the location of the point in space
     */
    private Value3D loc;

    /**
     * Value3D gun loc - finds the location of the gun
     */
    private Value3D gunLoc;

    /**
     * angle variable
     */
    private double angle;

    /**
     * gunAngle variable
     */
    private double gunAngle;

    /**
     * number of hitpoints variable
     */
    private double hitpoints;

    /**
     * firedBullet variable checks if it is fired and then returns fired bullet
     */
    private boolean firedBullet;


    /**
     * TankPacket is constantly sent to the runner environment in order to
     * correctly render the tanks in the player window. TankPacket uses Value3D
     * in order to execute and render these tanks properly.
     * 
     * 
     * constructor for TankPacket
     * 
     * @param l
     *            gets the location of the tank
     * @param gunL
     *            gets/receives gun location
     * @param a
     *            gets the angle of the tank
     * @param g
     *            gets the angle of the gun
     * @param h
     *            receives hit point information
     * @param f
     *            checks if bullet has been fired
     */
    public TankPacket( Value3D l, Value3D gunL, double a, double g, double h, boolean f )
    {
        loc = l;
        gunLoc = gunL;
        angle = a;
        gunAngle = g;
        hitpoints = h;
        firedBullet = f;
    }


    /**
     * 
     * gets the location of the tank on the 3d environment
     * 
     * @return loc - returns geLoc method - 3d space
     */
    public Value3D getLoc()
    {
        return loc;
    }


    /**
     * 
     * gets the gun location in 3d space environment
     * 
     * @return gunLoc - return getGunlocmethod - 3d space environment - gets the
     *         location of the gun
     */
    public Value3D getGunLoc()
    {
        return gunLoc;
    }


    /**
     * 
     * gets the angle of the tank in the 3d space environment
     * 
     * @return angle - return angle - 3d space environment - receives
     *         information about the angle of the tank
     */
    public double getAngle()
    {
        return angle;
    }


    /**
     * 
     * gets the angle of the gun - in 3d space environment
     * 
     * @return gun angle - return gunAngle - 3d space environment - receives
     *         information about the angle of the gun
     */
    public double getGunAngle()
    {
        return gunAngle;
    }


    /**
     * 
     * receives hitpoint information
     * 
     * @return hitpoints - number of time the tank gets hit
     */
    public double getHitpoints()
    {
        return hitpoints;
    }


    /**
     * 
     * checkIfFired method checks whether the gun turret has been fired; returns
     * firedBullet
     * 
     * @return checkIfTired method - checks whether the gun turret has been
     *         fired and returns fired bullet
     */
    public boolean checkIfFired()
    {
        return firedBullet;
    }

}
