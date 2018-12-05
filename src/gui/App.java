package gui;
import javax.swing.SwingUtilities;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * @author gradylynn, Robert
 * @version Dec 4, 2018
 * Honestly this class is pretty lame.
 */
public class App
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                try
                {
                    new MesonetFrame();
                } catch (IOException e)
                {
                   
                    e.printStackTrace();
                }
            }
        });
    }
}