package experiments;

import processing.core.PApplet;


public class Experiment extends PApplet
{
    float inc = 1;


    // Usage: PApplet [options] <class name> [sketch args]not able to run - need
    // to fix in class
    public static void main( String[] args )
    {
        PApplet.main( "experiments.Experiment" );
    }


    public void settings()
    {
        size( 1000, 1000, P3D );
    }


    public void setup()
    {
        noFill();
    }


    public void draw()
    {
        inc += 0.01;
        background( 0 );
        // param 1- always keep 0
        // param 2- always keep height / 2 to ensure eye level
        // param 3- useful for distance away
        // param 4- useful for sideways movement
        // param 5- always keep height / 2 to ensure eye level
        // param 6- always keep 0
        // param 7- set to 1 for top view
        // param 8- set to 1 for front view
        // param 9- always keep 0
        camera( width / 2, height / 2, 600, inc * 100, height / 2, 0, 0, 1, 0 );
        pushMatrix();
        translate( width / 2, height / 2 );
        rotateY( inc );
        box( 150, 100, 100 );
        fill( 255 );
        popMatrix();
        // inc += 0.01;
        // background(0);
        // pushMatrix();
        // if (inc * 100 < width / 2)
        // {
        // translate(inc * 100, height / 4);
        // }
        // else
        // {
        // translate(width / 2, height / 4);
        // }
        // rotateY(inc);
        // rotateX(inc);
        // rotateZ(inc);
        // box(150);
        // fill(255);
        // popMatrix();
        //
        // pushMatrix();
        // if (width - inc * 100 > width / 2)
        // {
        // translate(width - inc * 100, 3 * height / 4);
        // }
        // else
        // {
        // translate(width / 2, 3 * height / 4);
        // }
        // rotateY(inc);
        // rotateX(inc);
        // rotateZ(inc);
        // box(150);
        // fill(255);
        // popMatrix();
    }
}