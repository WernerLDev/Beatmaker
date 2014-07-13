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

public class Instrument
{
    private String sampleFile;
    private PlaySound playr;
    public Pad[] pads;
    private Graphics g;
    private int posX;

    //De construct krijgt een sample file mee, een wav file welke afgespeeld moet worden als
    //1 van de pads actief staat. int x is voor de positie en graphics om alles naar het scherm te kunnen tekenen
    public Instrument(String sample, int x, Graphics graphics)
    {
	sampleFile = sample;
	g = graphics;
	playr = new PlaySound(sampleFile);
	posX = x;
	createPads();
    }

    //De pads tekenen voor dit instrument. Dit is dus een rij, niet een kolom
    public void createPads()
    {
	pads = new Pad[8];
	for(int i = 0; i < 8; i++)
	{
	    pads[i] = new Pad(posX , i * 87 , g);
	}
    }

    //Alles op het scherm tekenen
    public void draw()
    {
	for(int i = 0; i < pads.length; i++)
	{
	    pads[i].draw();
	}
    }

    //Sample afspelen
    public void playSample()
    {
	playr.play();
    }

    //sample stoppen
    public void stopSample()
    {
	playr.stop();
    }
}