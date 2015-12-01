package com.dam.download.View;

import com.dam.download.Controller.ControllerMain;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class WindowMain
{
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
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Description for menu");
        menuBar.add(menu);

        menuItem = new JMenuItem("Save");
        menuItem.getAccessibleContext().setAccessibleDescription("Description for first item");

        menuItem2 = new JMenuItem("Save as");
        menuItem2.getAccessibleContext().setAccessibleDescription("Description for second item");

        menuItem3 = new JMenuItem("Import");
        menuItem3.getAccessibleContext().setAccessibleDescription("Description for third item");

        menuItem4 = new JMenuItem("Export");
        menuItem4.getAccessibleContext().setAccessibleDescription("Description for fourth item");

        menuItem5 = new JMenuItem("Change File Path");
        menuItem5.getAccessibleContext().setAccessibleDescription("Description for fifth item");

        menuItem6 = new JMenuItem("Exit");
        menuItem6.getAccessibleContext().setAccessibleDescription("Description for sixth item");

        menu.add(menuItem);
        //menu.addSeparator();

        menu.add(menuItem2);
        //menu.addSeparator();

        menu.add(menuItem3);
        //menu.addSeparator();

        menu.add(menuItem4);
        //menu.addSeparator();

        menu.add(menuItem5);
        menu.addSeparator();

        menu.add(menuItem6);

        continueButton.setEnabled(false);
        stopButton.setEnabled(false);
        //TODO IF TABLA.SIZE() > 0 ENABLE == TRUE; ELSE ENABLE == FALSE
        stopAllButton.setEnabled(false);

        ControllerMain c = new ControllerMain(this);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Gestor de Descargas V0");
        frame.setContentPane(new WindowMain().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
