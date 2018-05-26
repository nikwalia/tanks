package graphics;

import io.TankPacket;
import math.Building;
import math.Bullet;
import math.Tank;
import processing.core.PApplet;
import processing.core.PImage;


public class RunnerWindow extends PApplet
{
    PImage background;

    Tank same;

    Tank enemy;

    GraphicsTank enemyGraphics;

    CameraTank sameCamera;

    TankPacket initE;

    TankPacket initS;

    public boolean initCalled = false;

    public boolean setupCalled = false;

    boolean runningCounter = true;

    public double initTime = System.nanoTime() / 1e+9;

    private int playerNumber;

    private int gameMode;

    Bullet sameBullet;

    Bullet enemyBullet;

    double worldCenterX;

    double worldCenterZ;

    double worldAngle;

    private Building[] buildings;


    public void init(
        Tank s,
        Tank e,
        TankPacket initSame,
        TankPacket initEnemy,
        int playerNumber,
        Building[] b )
    {
        same = s;
        enemy = e;
        initE = initEnemy;
        initS = initSame;
        initCalled = true;
        this.playerNumber = playerNumber;
        gameMode = 0;
        worldCenterX = initSame.getLoc().getX();
        worldCenterZ = initSame.getLoc().getZ();
        worldAngle = initSame.getGunAngle();
        buildings = b;
    }


    public static void main( String[] args )
    {
        PApplet.main( "graphics.RunnerWindow" );
    }


    public void update( TankPacket same, TankPacket enemy )
    {
        sameCamera.update( same );
        enemyGraphics.update( enemy );
        worldCenterX = same.getLoc().getX();
        worldCenterZ = same.getLoc().getZ();
        worldAngle = same.getGunAngle();
    }


    public void setup()
    {
        background = loadImage( "background.png" );
        enemyGraphics = new GraphicsTank( enemy, this, initE );
        sameCamera = new CameraTank( same, this, initE );
        setupCalled = true;
        surface.setTitle( "PLAYER " + playerNumber );
    }


    public void settings()
    {
        size( 500, 500, P3D );
    }


    public void draw()
    {
        // background( background );
        background( 135, 206, 235 );
        if ( gameMode != 0 )
        {
            if ( initCalled && setupCalled
            // && System.nanoTime() / 1e+9 - initTime > 20
            )
            {
                pushMatrix();
                rectMode( CENTER );
                translate( -100 + (float)worldCenterX, 425, -100 + (float)worldCenterZ );
                fill( 139, 69, 19 );
                noStroke();
                rotateX( (float)Math.PI / 2 );
                rotateZ( (float)worldAngle );
                rect( 0, 0, 1000000, 1000000 );
                popMatrix();
                stroke( 10 );
                enemyGraphics.display();
                sameCamera.display();
                noStroke();
                if ( sameBullet != null )
                {
                    pushMatrix();
                    translate( (float)sameBullet.getX(),
                        (float)sameBullet.getY(),
                        (float)sameBullet.getZ() );
                    sphere( 10 );
                    fill( 255 );
                    popMatrix();
                }
                if ( enemyBullet != null )
                {
                    pushMatrix();
                    translate( (float)enemyBullet.getX(),
                        (float)enemyBullet.getY(),
                        (float)enemyBullet.getZ() );
                    sphere( 10 );
                    fill( 255 );
                    popMatrix();
                }

                for ( int i = 0; i < buildings.length; i++ )
                {
                    pushMatrix();
                    translate( (float)buildings[i].getX(),
                        (float)buildings[i].getY(),
                        (float)buildings[i].getZ() );
                    rotateY( -(float)buildings[i].getAngle() );
                    fill( 255 );
                    stroke( 10 );
                    box( (float)buildings[i].getLength(),
                        (float)buildings[i].getHeight(),
                        (float)buildings[i].getWidth() );
                    popMatrix();
                }
            }
            else
            {
                textSize( 50 );
                text( "Player " + playerNumber, width / 2 - 50, height / 2, 0 );
                text( (int)( 20 - ( System.nanoTime() / 1e+9 - initTime ) ),
                    width / 2,
                    height / 2 + 50,
                    0 );
            }
        }
        else
        {
            text( "Player " + playerNumber, width / 2 - 50, height / 2, 0 );
            initTime = System.nanoTime() / 1e+9;
        }
    }


    public void setGameMode( int g )
    {
        if ( g == 1 && gameMode == 0 )
        {
            initTime = System.nanoTime() / 1e+9;
        }
        gameMode = g;
    }


    public void close()
    {
        dispose();
    }
}
