package com.dam.download.View;

import com.dam.download.Controller.ControllerMain;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class WindowMain
{
    public static JFrame frame;
    public JPanel panel1;
    public JButton addUrlButton;
    public JButton continueButton;
    public JButton stopButton;
    public JButton stopAllButton;
    public JButton programmButton;
    public JButton optionsButton;
    public JTable table1;
    private JScrollPane scrollPane;

    public static JMenuBar menuBar;
    public static JMenu menu;
    public static JMenuItem menuItem, menuItem2, menuItem3, menuItem4, menuItem5, menuItem6;

    WindowMain()
    {
        Thread hiloSplash = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                SplashScreen splash = new SplashScreen();
                splash.mostrar();
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
                splash.ocultar();
            }
        });
        hiloSplash.start();

        Thread hiloMain = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
                frame.setVisible(true);
            }
        });
        hiloMain.start();

        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Description for menu");
        menuBar.add(menu);

        menuItem = new JMenuItem("Download Files");
        menuItem.getAccessibleContext().setAccessibleDescription("Description for first item");

        menuItem2 = new JMenuItem("Configuration");
        menuItem2.getAccessibleContext().setAccessibleDescription("Description for second item");

        menuItem3 = new JMenuItem("Exit");
        menuItem3.getAccessibleContext().setAccessibleDescription("Description for third item");

        menu.add(menuItem);
        //menu.addSeparator();

        menu.add(menuItem2);
        //menu.addSeparator();

        menu.add(menuItem3);

        continueButton.setEnabled(false);
        stopButton.setEnabled(false);
        stopAllButton.setEnabled(false);
        programmButton.setEnabled(false);

        ControllerMain c = new ControllerMain(this);
    }

    public static void main(String[] args)
    {
        frame = new JFrame("Gestor de Descargas V0");
        frame.setContentPane(new WindowMain().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }
}