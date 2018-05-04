/**
 * 
 */
/**
 * TODO Write a one-sentence summary of your class here. TODO Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author mvats
 * @version May 4, 2018
 * @author Period: TODO
 * @author Assignment: Tanks
 *
 * @author Sources: TODO
 */
package TypographywithHomeScreens;

import processing.core.PApplet;
import processing.core.PFont;


public class typography extends PApplet
{

    public static void main( String[] args )
    {
        PApplet.main( "TypographywithHomeScreens.typography" );
    }

    PFont font;


    public void setupClass()
    {
        size( 1000, 1000);
        printArray( PFont.list() );
        font = createFont( "SourceCodePro-Regular.ttf", 24 );
        textFont( font );
    }


    public void drawBackground()
    {
        background( 0 );
        textAlign( RIGHT );
        drawTextAndAlign( (float)( width * 0.25 ) );
        textAlign( CENTER );
        drawTextAndAlign( (float)( width * 0.5 ) );
        textAlign( LEFT );
        drawTextAndAlign( (float)( width * 0.75 ) );
    }


    public void drawTextAndAlign( float y )
    {
        line( y, 0, y, 65 );
        line( y, 220, y, height );
        fill( 0 );
        text( "tank", y, 95 );
        fill( 51 );
        text( "tanks", y, 130 );
        fill( 204 );
        text( "tanksss", y, 165 );
        fill( 255 );
    }
}