package requirementsM;

import processing.core.PApplet;


/**
 * 
 * description of class TODO
 *
 * @author Maithreyee Vatsan
 * @author Group members: Nikash Walia, Roopak Phatak
 * @version Apr 25, 2018
 * @author Period: 2
 * @author Assignment: APCS Project - Tanks
 *
 * @author Sources: None
 */
public class MovableClassRequirements extends PApplet
{
    float x;

    float y;

    float z;


    public void setup()
    {
        size( 200, 200, P3D );
        background( 100 );
        rectMode( CENTER );
        fill( 51 );
        stroke( 255 );
    }


    public void draw()
    {
        translate( 100, 100, 0 );
        rotateZ( PI / 8 );
        rect( 0, 0, 100, 100 );
    }


    public void primitives3D()
    {
        size( 640, 360, P3D );
        background( 0 );
        lights();
        noStroke();
        pushMatrix();
        translate( 130, height / 2, 0 );
    }
}
