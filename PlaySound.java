package beatmaker;

/**
 * @author WernerL
 */

import javax.microedition.media.*;
import java.io.*;

public class PlaySound
{
    private String file;
    private Player p;
    private InputStream is;

    //De constructor krijgt alleen de naam van een wav file als parameter mee
    public PlaySound(String fileName)
    {
	//Player object maken en alles vast pre-fetchen
	file = fileName;
	try
	{
	    is = getClass().getResourceAsStream(file);

	    p = Manager.createPlayer(is, "audio/x-wav");
	    p.realize();
	    p.prefetch();

 	} catch (Exception e)
	{
	    e.printStackTrace();
 	}
    }

    //De sample afspelen
    public void play()
    {
	try
	{
	    p.start();
	}
	catch (Exception e)
	{
	    e.printStackTrace();
 	}
    }

    //De sample stoppen
    public void stop()
    {
	try
	{
	    p.stop();
	}
	catch (Exception e)
	{
	    e.printStackTrace();
 	}
    }
}