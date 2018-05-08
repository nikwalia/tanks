package TypographywithHomeScreens;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class backgroundImage extends PApplet
{

    public static void main( String[] args )
    {
        PApplet.main( "TypographywithHomeScreens.backgroundImage" );

    }

    PImage background;

    // int background1;


    public void settings()
    {
        size( 1920, 1080 );
    }


    public void setup()
    {
        // PImage image;
        background = loadImage( "C:\\Users\\mvats\\tanksactualforproject.jpg" );
        noTint();
    }


    public void draw()
    {
        // PFont font;
        background( background );
        textSize( 109 );
        text( "T-34 Tank Destroyers", width / 4, 160 );
        fill( 0 );
        // font =
        // createFont("C:\\Users\\mvats\\Downloads\\League-Gothic.zip\\LeagueGothic-Regular.otf")
        // text( "word", 10, 60 );
        // fill( 0, 102, 153, 51 );
        // text( "word", 10, 90 );

    }
}
