package gui;
import javax.swing.SwingUtilities;

import java.io.IOException;

import javax.swing.JFrame;

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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}