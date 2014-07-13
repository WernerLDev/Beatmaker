/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beatmaker;

/**
 *
 * @author WernerL
 */
public class TouchActions
{

    private Pad[] pads;
    public int padClicked;

    public TouchActions()
    {
    }

    //De array van pads waarop we moeten controlleren of er op geklikt is
    public void setPads(Pad[] data)
    {
	pads = data;
    }

    //In deze functie wordt gecontrolleerd of er op een pad is geklikt
    public boolean touchCheck(int x, int y)
    {
	for(int i = 0; i < pads.length; i++)
	{
	    if(x > pads[i].posX && x < pads[i].posX + 70)
	    {

		if(y > pads[i].posY && y < pads[i].posY + 90)
		{
		    padClicked = i;
		    return true;
		}
	    }
	}
	return false;
    }
}
