package beatmaker;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 *
 * @author WernerL
 */
public class App extends MIDlet
{
    Display display;
    mainCanvas beatmaker;

    public App()
    {
	 System.out.println("App called...");
	 beatmaker = new mainCanvas();
	 display  = Display.getDisplay(this);
	 display.setCurrent(beatmaker);
	 beatmaker.start();
    }

    protected void startApp()
    {
	
    }

    protected void pauseApp()
    {
	System.out.println("pauseApp called...");
    }

    protected void destroyApp(boolean unconditional)
    {
	System.out.println("destroyApp called...");
    }
}
