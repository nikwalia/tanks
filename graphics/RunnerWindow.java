package graphics;

import io.TankPacket;
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

    int playerNumber;


    public void init( Tank s, Tank e, TankPacket initSame, TankPacket initEnemy, int playerNumber )
    {
        same = s;
        enemy = e;
        initE = initEnemy;
        initS = initSame;
        initCalled = true;
        this.playerNumber = playerNumber;
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
    }


    public void settings()
    {
        size( 500, 500, P3D );
    }


    public void draw()
    {
        background( 0 );
        if ( initCalled && setupCalled && System.nanoTime() / 1e+9 - initTime > 20 )
        {
            enemyGraphics.display();
            sameCamera.display();
        }
        else
        {
            textSize( 50 );
            text( "Player " + playerNumber, width / 2 - 50, height / 2 );
            text( (int)( 20 - ( System.nanoTime() / 1e+9 - initTime ) ),
                width / 2,
                height / 2 + 50 );
        }
    }


    public void close()
    {
        dispose();
    }
}
