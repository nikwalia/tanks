package graphics;

import processing.core.PApplet;


public class RunnerWindow extends PApplet
{
    GraphicsTank enemy;

    CameraTank same;


    public RunnerWindow( GraphicsTank e, CameraTank s )
    {
        enemy = e;
        same = s;
    }


    public static void main( String[] args )
    {
        PApplet.main( "graphics.RunnerWindow" );
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
