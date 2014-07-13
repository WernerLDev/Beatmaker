/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beatmaker;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.IOException;

/**
 *
 * @author WernerL
 */
public class Pad {

    public int posX;
    public int posY;
    private int color;
    public boolean activated;
    private Graphics g;
    private Image active;
    private Image inactive;
    private Image overin;
    private Image overout;
    public boolean currentBeat = false;


    //De constructor. krijgt een x en y positie mee als parameter en graphics om te kunnen tekenen
    public Pad(int xPos, int yPos, Graphics graphics)
    {
	posX = xPos;
	posY = yPos;
	color = 0xff00ff;
	activated = false;
	g = graphics;

	createImages();

    }

    //De pad tekenen
    public void draw()
    {
	//Als deze knop actief is maar de positie van de beatmaker is NIET deze pad
	//dan deze afbeelding tekenen
	if(activated == true && currentBeat == false)
	{
	    g.drawImage(active, posX, posY, 0);
	}
	else if(currentBeat == true)
	{
	    //Als positie wel bij deze pad is dan gaat hij de afbeelding met glow tekenen
	    //2 verschillende afbeeldingen, ligt eraan of de pad op actief staat of niet
	    if(activated == true)
	    {
		System.out.println("sdfsdf");
		g.drawImage(overin, posX, posY, 0);
	    }
	    else
	    {
		g.drawImage(overout, posX, posY, 0);
	    }

	}
	else if(currentBeat == false)
	{
	    //Deze afbeelding wordt getekend als huidige positie NIET deze pad is, en deze pad niet actief is.
	    g.drawImage(inactive, posX, posY, 0);
	}
    }

    //Controlleren of er op deze knop gedrukt is
    public void clicked()
    {
	if(activated == false)
	{
	    activated = true;
	}
	else
	{
	    activated = false;
	}

	draw();
    }

    //De afbeeldingen maken
    public void createImages()
    {
	try
	{
	    active = Image.createImage("/images/button_in(red).png");
	    inactive = Image.createImage("/images/button(red).png");
	    overin = Image.createImage("/images/button_in_over(red).png");
	    overout = Image.createImage("/images/button_over(red).png");
	}
	catch (IOException e) {
            System.out.println("Images not found");
        }
    }
}