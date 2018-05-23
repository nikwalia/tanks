package experiments;

import processing.core.PApplet;


public class fuccintest extends PApplet
{
    float inc = 1;

    float angle = (float)Math.PI / 2;

    float xpos = width / 2;

    float zpos = 1000;

    private int[] playerOneData = new int[4];


    public static void main( String[] args )
    {
        PApplet.main( "experiments.fuccintest" );
    }


    public void settings()
    {
        size( 500, 500 );
    }


    public void draw()
    {
        background( 0 );

        // fill( 1000 );
        // ellipseMode( CENTER );
        // ellipse( width / 8, 7 * ( height / 8 ), 69, 69 );
        pushMatrix();
        translate( width / 2, height / 2 );
        fill( 150 );
        rotate( radians( 45 ) );
        rectMode( CENTER );
        rect( 0, 0, 40, 40 );
        fill( 10 );
        // // rotate((float)data.peek().getAngle());rectMode( CENTER );
        // line( width / 8 - width / 1024,
        // 7 * ( height / 8 ) - height / 24,
        // width / 8 - width / 1024,
        // 7 * ( height / 8 ) );
        // fill( 0 );
        // line( width / 8 - width / 1024,
        // 7 * ( height / 8 ) - height / 24,
        // width / 8 - width / 1024,
        // 7 * ( height / 8 ) );
        //
        popMatrix();

        // rotate((float)data.peek().getGunAngle());
    }
    // ellipseMode( CENTER );
    // fill(120,50,240);
    // ellipse( 0, 0, 2, 69 );
    //// rectMode( CENTER );
    //// rect( 4, 20, 6, 6 );
    //// rotate((float)data.peek().getAngle());
    //// rectMode( CENTER );
    //// rect( 4, 21, 6, 1 );
    //// rotate((float)data.peek().getGunAngle());
    // }
}
