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


    public void init( Tank s, Tank e, TankPacket initSame, TankPacket initEnemy )
    {
        same = s;
        enemy = e;
        initE = initEnemy;
        initS = initSame;
        initCalled = true;
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
        size( 500, 500 );
    }


    public void draw()
    {
        background( 0 );
        if ( initCalled )
        {
            enemyGraphics.display();
            sameCamera.display();
        }
    }
}
