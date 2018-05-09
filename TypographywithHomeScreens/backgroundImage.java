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
        background( background );
        textSize( 109 );
        text( "T-34", width / 6, 155 );
        fill( 11, 57, 198 );
        text( "        Tank Destroyers", width / 6, 155 );
        fill( 56, 114, 5 );
        PFont font;
        // String[] fontList = PFont.list();
        // printArray( fontList );
        font = createFont( "Verdana Bold", 196 );
        textFont( font );
        // font = createFont("LeagueGothic-Regular.otf",30);
        // text( "word", 10, 60 );
        // fill( 0, 102, 153, 51 );
        // text( "word", 10, 90 );]
    }

    // public void textColor()
    // {
    // empty
    // }

    /**
     *
     * 
     * 
     * TEST THIS SECTION ONCE FINISHED WITH SCREEN INTEGRATION TEST THIS SECTION
     * ONCE FINISHED WITH SCREEN INTEGRATION TEST THIS SECTION ONCE FINISHED
     * WITH SCREEN INTEGRATION
     * 
     * 
     * 
     */

    // need to test once done with screen integration
    // int x;

    // need to test once done with screen integration
    // boolean[] keys = new boolean[128];

    // need to test once done with screen integration
    // public void pressedKey()
    // {
    // if ( key == ' ' )
    // {
    // x++;
    // }
    // }

    // need to test once done with screen integration
    // public void keyPressed()
    // {
    // keys[key] = true;
    // }

    // public void keyReleased()
    // {
    // keys[key] = false;
    //
    // }
}
