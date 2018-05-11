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

    public RunnerWindow( Tank s, Tank e, TankPacket initSame, TankPacket initEnemy )
    {
        same = s;
        enemy = e;
        enemyGraphics = new GraphicsTank(enemy, this, initEnemy);
        sameCamera = new CameraTank(same, this, initSame);
    }


    public static void main( String[] args )
    {
        PApplet.main( "graphics.RunnerWindow" );
    }
    
    public void update(TankPacket same, TankPacket enemy)
    {
        sameCamera.update( same );
        enemyGraphics.update( enemy );
    }


    public void setup()
    {

    }


    public void settings()
    {
        size( 500, 500 );
    }


    public void draw()
    {
        background( 0 );
        enemyGraphics.display();
        sameCamera.display();
    }
}
