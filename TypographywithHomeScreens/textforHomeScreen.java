package TypographywithHomeScreens;

import processing.core.PApplet;


public class textforHomeScreen extends PApplet
{

    public static void main( String[] args )
    {
        PApplet.main( "TypographywithHomeScreens.textforHomeScreen" );
    }


    public void settings()
    {
        size( 1920, 1080 );
    }


    public void draw()
    {
        background( 0 );
        textSize( 20 );
        textAlign( CENTER, BOTTOM );
        text( "T-34 Tank Destroyers", 50, 30 );
    }
}
