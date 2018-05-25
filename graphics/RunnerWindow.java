package graphics;

import io.TankPacket;
import math.Bullet;
import math.Tank;
import processing.core.PApplet;


public class RunnerWindow extends PApplet
{
    Tank same;

    Tank enemy;

    GraphicsTank enemyGraphics;

    CameraTank sameCamera;

    TankPacket initE;

    TankPacket initS;

    public boolean initCalled = false;

    public boolean setupCalled = false;

    public double initTime = System.nanoTime() / 1e+9;

    private int playerNumber;

    private int gameMode;

    Bullet sameBullet;

    Bullet enemyBullet;


    public void init( Tank s, Tank e, TankPacket initSame, TankPacket initEnemy, int playerNumber )
    {
        same = s;
        enemy = e;
        initE = initEnemy;
        initS = initSame;
        initCalled = true;
        this.playerNumber = playerNumber;
        gameMode = 0;
    }


    public static void main( String[] args )
    {
        PApplet.main( "graphics.RunnerWindow" );
    }


    public void update( TankPacket same, TankPacket enemy )
    {
        sameCamera.update( same );
        enemyGraphics.update( enemy );
    }


    public void setup()
    {
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
        background( 0 );
        if ( gameMode != 0 )
        {
            if ( initCalled && setupCalled 
//                            && System.nanoTime() / 1e+9 - initTime > 20 
                            )
            {
                stroke(10);
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
                lights();
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
