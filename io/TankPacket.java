package io;

import math.Value3D;


public class TankPacket
{
    private Value3D loc;

    private Value3D gunLoc;

    private double angle;

    private double gunAngle;

    private double hitpoints;

    private boolean firedBullet;


    public TankPacket( Value3D l, Value3D gunL, double a, double g, double h, boolean f )
    {
        loc = l;
        gunLoc = gunL;
        angle = a;
        gunAngle = g;
        hitpoints = h;
        firedBullet = f;
    }


    public Value3D getLoc()
    {
        return loc;
    }


    public Value3D getGunLoc()
    {
        return gunLoc;
    }


    public double getAngle()
    {
        return angle;
    }


    public double getGunAngle()
    {
        return gunAngle;
    }


    public double getHitpoints()
    {
        return hitpoints;
    }


    public boolean checkIfFired()
    {
        return firedBullet;
    }

}
