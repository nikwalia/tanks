package requirementsM;

import processing.core.PApplet;


public class ProcessingMain extends PApplet
{
    public static void main( String[] args )
    {
        PApplet.main( "Using Processing" );
    }


    public void appletsettings()
    {
        size( 300, 300 );
    }


    public void setup()
    {
        fill( 120, 50, 240 );
    }


    public void drawS()
    {
        ellipse( width / 2, height / 2, second(), second() );
    }
}
