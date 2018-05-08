package TypographywithHomeScreens;

import processing.core.PApplet;// papplet import
import processing.core.PFont;// pfont import
import processing.core.PImage;// pimage import


public class backgroundImage extends PApplet
{
    /**
     * 
     * Tester for BackgroundImage
     * 
     * @param args
     *            tester
     */
    public static void main( String[] args )
    {
        PApplet.main( "TypographywithHomeScreens.backgroundImage" );

    }

    PImage background; // background image variable


    // int background1;

    /**
     * size of the screen 16:9 ratio (1920 pixels * 1080 pixels)
     */
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
        // font = createFont("LeagueGothic-Regular.otf",
        // text( "word", 10, 60 );
        // fill( 0, 102, 153, 51 );
        // text( "word", 10, 90 );

    }
}
