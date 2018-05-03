package io;

import math.Value3D;


public class TankPacket
{
    private Value3D loc;

    private double angle;

    private double gunAngle;

    private double hitpoints;


    public TankPacket( Value3D l, double a, double g, double h )
    {
        loc = l;
        angle = a;
        gunAngle = g;
        hitpoints = h;
    }


    public Value3D getLoc()
    {
        return loc;
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

}
