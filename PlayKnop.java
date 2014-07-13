/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beatmaker;

/**
 *
 * @author WernerL
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class PlayKnop
{
    public boolean playing = true;
    private Graphics g;
    private int posX;
    private int posY;
    private Image stop;
    private Image play;

    //De play/pause knop constructor. Krijgt een graphics object en een positie mee
    public PlayKnop(Graphics graph, int x, int y)
    {
	g = graph;
	posX = x;
	posY = y;
	createImages();
    }

    //De knop tekenen
    public void draw()
    {
	if(playing == true)
	{
	    g.drawImage(stop, posX, posY, 0);
	}
	else
	{
	    g.drawImage(play, posX, posY, 0);
	}
    }

    //De afbeeldingen maken voor deze knop
    public void createImages()
    {
	try
	{
	    stop = Image.createImage("/images/buttons/pauze.png");
	    play = Image.createImage("/images/buttons/play.png");
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }

    //Controlleren of er op de knop gedrukt is
    public boolean clicked(int x, int y)
    {
	if(x > posX && x < posX + 70)
	{
	    if(y > posY && y < posY + 70)
	    {
		if(playing == false)
		{
		    playing = true;
		}
		else
		{
		    playing = false;
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
}
