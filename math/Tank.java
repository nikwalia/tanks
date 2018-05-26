package math;

import java.util.LinkedList;
import java.util.Queue;

import io.*;


/**
 * Class that mathematically represents an entire tank
 * 
 * @author Nikash Walia
 * @version 5/17/18
 * @author Period: 2
 * @author Assignment- Tanks
 * 
 * @author Sources: None
 *
 */
public class Tank
{
    protected Gun gun;

    protected Base base;

    private int hitPoints;

    protected Queue<SystemPacket> data;

    private boolean canFire;

    private double lastFireTime;

    private final static double RELOADTIME = 5;

    private boolean hasFired;

    public int baseLength;

    public int baseWidth;

    public int baseHeight;

    public int gunLength;

    public int gunWidth;

    public int gunHeight;

    public final int originalHP;

    public final Value3D originalPosition;


    /**
     * Constructor for Tank
     * 
     * @param x
     *            starting x-coordinate of base
     * @param y
     *            starting y-coordinate of base
     * @param z
     *            starting z-coordinate of base
     * @param angle
     *            starting angle of base and gun
     * @param gunLength
     *            length of gun
     * @param gunWidth
     *            width of gun
     * @param gunHeight
     *            height of gun
     * @param baseLength
     *            length of base
     * @param baseWidth
     *            width of base
     * @param baseHeight
     *            height of base
     * @param hp
     *            hitpoints of base
     */
    public Tank(
        int x,
        int y,
        int z,
        double angle,
        int gunLength,
        int gunWidth,
        int gunHeight,
        int baseLength,
        int baseWidth,
        int baseHeight,
        int hp )
    {
        gun = new Gun( angle, gunLength, gunWidth, gunHeight, new Value3D( x, y, z ), baseHeight );
        base = new Base( x, y, z, angle, baseLength, baseWidth, baseHeight );
        hitPoints = hp;
        data = new LinkedList<SystemPacket>();
        lastFireTime = System.nanoTime() / 1e+9;
        canFire = false;
        hasFired = false;
        this.baseLength = baseLength;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
        this.gunLength = gunLength;
        this.gunWidth = gunWidth;
        this.gunHeight = gunHeight;
        originalHP = hp;
        originalPosition = new Value3D( x, y, z );
    }


    /**
     * 
     * Returns the remaining hitpoints of the tank
     * 
     * @return hitpoints
     */
    protected int getHitPoints()
    {
        return hitPoints;
    }


    /**
     * 
     * Reduces the number of hitpoints of the tank
     */
    public void changeHitPoints()
    {
        hitPoints -= 250;
    }


    /**
     * 
     * Checks if the tank has lost all hitpoints
     * 
     * @return whether or not the tank is dead
     */
    protected boolean isDead()
    {
        return hitPoints <= 0;
    }


    /**
     * 
     * Adds data from the system regarding user input
     * 
     * @param p
     *            SystemPacket containing user input
     */
    public void receiveData( SystemPacket p )
    {
        data.add( p );
    }


    /**
     * 
     * Checks if the tank has collided with a bullet
     * 
     * @param other
     *            Bullet to check against
     * @return whether collision has happened
     */
    public boolean hasCollided( Bullet other )
    {
        return gun.hasCollided( other ) >= 0 || base.hasCollided( other ) == 10;
    }


    /**
     * 
     * Checks if the tank has collided with another tank
     * 
     * @param other
     *            Tank to check against
     * @return whether collision has happened
     */
    public boolean hasCollided( Tank other )
    {
        return gun.hasCollided( other.gun ) >= 0 || base.hasCollided( other.base ) >= 0;
    }


    /**
     * 
     * Changes appropriate behavior of the tank based on what it has collided
     * with
     * 
     * @param other
     *            Structure3D to interact with
     */
    public void onCollision( Object other )
    {
        if ( other instanceof Bullet )
        {
            if ( hasCollided( (Bullet)other ) )
            {
                changeHitPoints();
                ( (Bullet)other ).onCollision( base );
            }
        }
        else if ( other instanceof Tank )
        {
            if ( base.hasCollided( ( (Tank)other ).base ) >= 0 )
            {
                base.hasCollidedWithTank = true;
                if ( gun.hasCollided( ( (Tank)other ).gun ) >= 0 )
                {
                    gun.hasCollidedWithGun = true;
                    base.hasCollidedWithGun = true;
                    base.gunCollisionSide = gun.collisionSide( ( (Tank)other ).base,
                        gun.hasCollided( ( (Tank)other ).gun ) );
                }
                else
                {
                    gun.hasCollidedWithGun = false;
                    base.hasCollidedWithGun = false;
                }
            }
            else
            {
                base.hasCollidedWithTank = false;
                if ( gun.hasCollided( ( (Tank)other ).gun ) >= 0 )
                {
                    gun.hasCollidedWithGun = true;
                    base.hasCollidedWithGun = true;
                    base.gunCollisionSide = gun.collisionSide( ( (Tank)other ).base,
                        gun.hasCollided( ( (Tank)other ).gun ) );
                }
                else
                {
                    base.hasCollidedWithGun = false;
                    gun.hasCollidedWithGun = false;
                }
            }
        }
    }


    /**
     * 
     * Updates the position and data of the tank
     */
    public void update()
    {
        double curTime = System.nanoTime() / 1e+9;
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
        gun.setBaseCenter( new Value3D( base.getX(), base.getY(), base.getZ() ) );
        gun.translate();
        gun.updateCorners();
        base.updateCorners();
    }


    /**
     * 
     * On-demand method, called by the system to update information on the
     * tank's position
     * 
     * @return TankPacket containing data
     */
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


    public void reset()
    {
        hitPoints = originalHP;
        base.setX( originalPosition.getX() );
        base.setY( originalPosition.getY() );
        base.setZ( originalPosition.getZ() );
        base.changeAngle( -base.getAngle() );
        base.setVelocity( 0 );
        base.setAngularVelocity( 0 );

        base.setMoveDirection( 0 );
        base.setTurnDirection( 0 );

        gun.setAngularVelocity( 0 );
        gun.setTurnDirection( 0 );

        gun.changeAngle( -base.getAngle() );
    }


    public void resume()
    {
        gun.updateTime();
        base.updateTime();
    }
}
