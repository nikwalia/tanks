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

    private final static double RELOADTIME = 5e+9;

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
        gun = new Gun( angle, gunLength, gunWidth, gunHeight, new Value3D(x, y, z), baseHeight );
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


    public void changeHitPoints()
    {
        hitPoints -= 250;
    }


    protected boolean isDead()
    {
        return hitPoints == 0;
    }


    public void receiveData( SystemPacket p )
    {
        data.add( p );
    }


    public boolean hasCollided( Structure3D other )
    {
        return gun.hasCollided( other ) || base.hasCollided( other );
    }


    public void onCollision( Structure3D other )
    {
        if ( ( gun.hasCollided( other ) && gun.onCollision( other ) == -1 )
            || ( base.hasCollided( other ) && base.onCollision( other ) == -1 ) )
        {
            changeHitPoints();
        }
    }


    public void update()
    {
        double curTime = System.nanoTime();
        if ( curTime - lastFireTime > RELOADTIME )
        {
            canFire = true;
        }
        if ( !data.isEmpty() )
        {
            SystemPacket temp = data.remove();
            if ( temp.calledFire() && canFire )
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
        gun.setBaseCenter( new Value3D(base.getX(), base.getY(), base.getZ()) );
        gun.translate();
        gun.updateCorners();
        base.updateCorners();
    }


    public TankPacket sendData()
    {
        TankPacket temp = new TankPacket( new Value3D( base.getX(), base.getY(), base.getZ() ),
            new Value3D( gun.getX(), gun.getY(), gun.getZ() ),
            base.getAngle(),
            gun.getAngle(),
            hitPoints,
            hasFired );
        hasFired = false;
        return temp;
    }
}