package beatmaker;
/**
 *
 * @author WernerL
 */

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.IOException;

public class InstrumentButton
{
    public int posX;
    public int posY;
    private Graphics g;
    public boolean active = false;
    private Image activebtn;
    private Image inactivebtn;
    public String button;

    //xPos en yPos zijn voor de positie, graphics om te kunnen tekenen, en btn om aan te geven welke afbeelding
    //moet worden gebruikt voor deze knop
    public InstrumentButton(int xPos, int yPos, Graphics graphics, String btn)
    {
	posX = xPos;
	posY = yPos;
	g = graphics;
	button = btn;

	createImages();
    }

    //De knop tekenen
    public void drawButton()
    {
	if(active == false)
	{
	    g.drawImage(inactivebtn, posX, posY, 0);
	}
	
	if(active == true)
	{
	    g.drawImage(activebtn, posX, posY, 0);
	}
    }

    //De knop op inactief zetten
    public void turnOff()
    {
	active = false;
    }

    //de knop op actief zetten
    public void turnOn()
    {
	active = true;
    }

    //Controlleren of er op deze knop gedrukt is
    public boolean clicked(int x, int y)
    {
	if(x > posX && x < posX + 60)
	{
	    if(y > posY && y < posY + 100)
	    {
		if(active == false)
		{
		    active = true;
		}
		return true;
	    }
	    else
	    {
		return false;
	    }
	}
	else
	{
	    return false;
	}
    }

    //De afbeeldingen maken
    public void createImages()
    {
	try
	{
	    activebtn = Image.createImage("/images/buttons/" + button + "_active.png");
	    inactivebtn = Image.createImage("/images/buttons/" + button + ".png");
	}
	catch (IOException e) {
            System.out.println("Images not found");
        }
    }
}
