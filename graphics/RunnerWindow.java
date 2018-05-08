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
    
    TankPacket sameCurData;
    
    TankPacket enemyCurData;


    public RunnerWindow( Tank s, Tank e )
    {
        same = s;
        enemy = e;
        enemyGraphics = new GraphicsTank(enemy, this);
        sameCamera = new CameraTank(same, this);
    }


    public static void main( String[] args )
    {
        PApplet.main( "graphics.RunnerWindow" );
    }
    
    public void update(TankPacket p)
    {
        
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
    }
}
