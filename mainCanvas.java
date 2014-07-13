package beatmaker;

import javax.microedition.lcdui.game.*;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.IOException;

/**
 *
 * @author WernerL
 */

public class mainCanvas extends GameCanvas implements Runnable
{
    private TouchActions action;
    private Graphics g;
    private Instrument[] instruments;
    private int count = 0;
    private InstrumentButton piano;
    private InstrumentButton gitaar;
    private InstrumentButton drums;
    private Image bgimage;
    private Image positie;
    private PlayKnop play;
    private Thread gitaarThread;
    private SoundTimerTask task;

    public mainCanvas()
    {
	super(true);
	g = getGraphics();
    }
    public synchronized void start()
    {
	//Afbeeldingen maken voor de achtergrond en het pijltje wat aangeeft op welke positie
	//de beatmaker is
	try
	{
	    positie = Image.createImage("/images/pijl.png");
	    bgimage = Image.createImage("/background.jpg");
	}
	catch (IOException e) {
            System.out.println("Images not found");
        }

	//Knoppen om van instrument te wissen aanmaken
	piano = new InstrumentButton(30,4, g, "piano");
	gitaar = new InstrumentButton(30,130, g, "gitaar");
	drums = new InstrumentButton(30, 260, g, "overig");
	play = new PlayKnop(g, 30, 570);

	//standaard krijg je de piano voor je neus dus deze zetten we aan
	piano.turnOn();

	//DIt is om te controlleren op welke pad we geklikt hebben
	action = new TouchActions();

	createInstruments();
	draw();
	playLoop();
    }

    public void stop() {}

    public void run() {}

    //De loop starten. Hier maken we een nieuwe thread voor aan
    public void playLoop()
    {
	task = new SoundTimerTask();
	gitaarThread = new Thread(task);
	gitaarThread.start();
    }

    //In deze functie worden de instrumenten gemaakt.
    private void createInstruments()
    {
	String[] samples = {
	"/piano/piano1.wav",
	"/piano/piano2.wav",
	"/piano/piano3.wav",
	"/piano/piano4.wav",
	"/piano/piano5.wav",
	"/gitaar/gitaar1.wav",
	"/gitaar/gitaar2.wav",
	"/gitaar/gitaar3.wav",
	"/gitaar/gitaar4.wav",
	"/gitaar/gitaar5.wav",
	"/drums/conga.wav",
	"/drums/conga2.wav",
	"/drums/conga3.wav",
	"/drums/bel.wav",
	"/drums/bel2.wav"
	};

	int j = 0;
	instruments = new Instrument[15];
	for(int i = 0; i < 15; i++)
	{
	    if(j == 5)
	    {
		j = 0;
	    }
	    
	    instruments[i] = new Instrument(samples[i], (j+2) * 67, g);
	    j++;
	}
    }

    //Alles op het scherm zetten.
    private void draw()
    {
	//De achtergrond tekenen
	g.drawImage(bgimage, 0, 0, 0);

	//De pijl die positie aangeeft tekenen
	g.drawImage(positie, 105, count * 87, 0);

	//Als piano open staat tekenen we de pads voor de piano
	if(piano.active)
	{
	    for(int i = 0; i < 5; i++)
	    {
		instruments[i].draw();
	    }
	}

	//Als gitaar open staat de pads voor gitaar tekenen
	if(gitaar.active)
	{
	    for(int i = 5; i < 10; i++)
	    {
		instruments[i].draw();
	    }
	}

	//Als overig open staat tekenen we daar de pads voor
	if(drums.active)
	{
	    for(int i = 10; i < 15; i++)
	    {
		instruments[i].draw();
	    }
	}

	piano.drawButton();
	gitaar.drawButton();
	drums.drawButton();

	play.draw();

        //Send all graphics from Buffer to Screen
        flushGraphics();
    }

    //Deze functie wordt uitgevoerd als er op het scherm gedrukt wordt.
    public void pointerPressed (int x, int y)
    {
	//VOlgende if/else statement is voor de knoppen onderaan. De gene die
	//aangeklikt wordt moet groen worden, de rest blauw (dat is uit)
	if(piano.clicked(x, y))
	{
	    gitaar.turnOff();
	    drums.turnOff();
	}
	else if(gitaar.clicked(x, y))
	{
	    piano.turnOff();
	    drums.turnOff();
	}
	else if(drums.clicked(x, y))
	{
	    gitaar.turnOff();
	    piano.turnOff();
	}

	//Als er op play of pauze gedrukt wordt zorgt deze functie dat playing op true of false komt te staan.
	play.clicked(x, y);

	//adhv het actieve instrument gaan controlleren op welke pad is gedrukt
	if(piano.active)
	{
	    for(int i = 0; i < 5; i++)
	    {
		action.setPads(instruments[i].pads);
		if(action.touchCheck(x, y))
		{
		    instruments[i].pads[action.padClicked].clicked();
		}
	    }
	}
	else if(gitaar.active)
	{
	    for(int i = 5; i < 10; i++)
	    {
		action.setPads(instruments[i].pads);
		if(action.touchCheck(x, y))
		{
		    instruments[i].pads[action.padClicked].clicked();
		}
	    }
	}
	else if(drums.active)
	{
	    for(int i = 10; i < 15; i++)
	    {
		action.setPads(instruments[i].pads);
		if(action.touchCheck(x, y))
		{
		    instruments[i].pads[action.padClicked].clicked();
		}
	    }
	}
	draw();
    }

    //De thread welke om de 230 milliseconde uitgevoerd wordt
    private class SoundTimerTask implements Runnable
    {
	private int counter = 0;
	public final void run()
	{
	    while(true)
	    {
		//Alleen als playing op true staat gaan we hier iets doen
		if(play.playing == true)
		{
		    animate();
		    try
		    {
			Thread.sleep(230);
		    }
		    catch(Exception e)
		    {
			System.out.println(e.toString());
		    }
		}
	    }
	}

	//Deze zorgt ervoor dat er geluid komt. :-)
	public void animate()
	{

	    //15 verschillende toonsoorten (van alle 3 de instrumenten) doorlopen
	    for(int i = 0; i < 15; i++)
	    {
		//De huidige colom op true zetten zodat deze een glow krijgt en het pijltje weet 7
		//wat zijn nieuwe positie moet zijn
		instruments[i].pads[counter].currentBeat = true;
		//Vorige kolom weer op false zetten
		if(counter == 0)
		{
		    instruments[i].pads[7].currentBeat = false;
		}
		else
		{
		    instruments[i].pads[counter - 1].currentBeat = false;
		}

		//Als pad in huidige kolom aan staat spelen we het geluidje af
		if(instruments[i].pads[counter].activated == true)
		{
		    instruments[i].playSample();
		}
	    }

	    //En alles naar het scherm tekenen
	    draw();
	    counter++;
	    if(counter >= 8)
	    {
		counter = 0;
	    }

	    //Deze count var wordt in de hoofd thread gebruikt voor de positie van het pijltje
	    count = counter;
	}
    }
}