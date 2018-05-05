package math;

import java.util.LinkedList;
import java.util.Queue;

import io.*;

public class Tank
{
    private Gun gun;

    private Base base;

    private int hitPoints;

    private Queue<SystemPacket> data;
    
    private boolean canFire;
    
    private double lastFireTime;
    
    private final static double RELOADTIME = 1e+9;
    
    private boolean hasFired;

    public Tank(
        int x,
        int y,
        int z,
        int angle,
        int gunLength,
        int gunWidth,
        int gunHeight,
        int baseLength,
        int baseWidth,
        int baseHeight,
        int hp )
    {
        gun = new Gun( x, y, z, angle, gunLength, gunWidth, gunHeight );
        base = new Base( x, y, z, angle, baseLength, baseWidth, baseHeight );
        hitPoints = hp;
        data = new LinkedList<SystemPacket>();
        lastFireTime = System.nanoTime();
        canFire = false;
        hasFired = false;
    }


    protected int getHitPoints()
    {
        return hitPoints;
    }


    protected void changeHitPoints()
    {
        hitPoints -= 5;
    }


    protected boolean isDead()
    {
        return hitPoints == 0;
    }
    
    public void update()
    {
        double curTime = System.nanoTime();
        if (curTime - lastFireTime > RELOADTIME)
        {
            canFire = true;
        }
        if (!data.isEmpty())
        {
            SystemPacket temp = data.remove();
            if (temp.calledFire() && canFire)
            {
                canFire = false;
                hasFired = true;
                lastFireTime = curTime;
            }
            base.setTurnDirection( temp.calledTurn() );
            base.setMoveDirection( temp.calledMove() );
            gun.setTurnDirection( temp.calledGunTurn() );
            gun.setBaseTurn( temp.calledTurn() );
        }
        base.translate();
        gun.setBaseVelocity( base.getVelocity() );
        gun.translate();
        gun.updateCorners();
        base.updateCorners();
    }
    
    public TankPacket sendData()
    {
        TankPacket temp = new TankPacket(
            new Value3D(base.getX(), base.getY(), base.getZ()),
            new Value3D(gun.getX(), gun.getY(), gun.getZ()),
            base.getAngle(),
            gun.getAngle(),
            hitPoints,
            hasFired);
        hasFired = false;
        return temp;
    }
}
