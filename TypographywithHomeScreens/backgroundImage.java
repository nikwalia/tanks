package TypographywithHomeScreens;

import processing.core.PApplet;
import processing.core.PImage;

public class backgroundImage extends PApplet
{

    public static void main( String[] args )
    {
        PApplet.main( "TypographywithHomeScreens.backgroundImage");

    }

    PImage background;
    int back; 
    public void settings()
    {
        size(1920, 1080);
    }
    public void setup()
    {
      background = loadImage("C:\\Users\\mvats\\tanksactualforproject.jpg");
    }
    public void draw()
    {
        background(background);
        
    }
}
