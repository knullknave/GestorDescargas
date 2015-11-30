package com.dam.download.Controller;

import com.dam.download.View.WindowAdd2;
import com.dam.download.View.WindowMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMain implements ActionListener
{
    WindowMain w;

    public ControllerMain(WindowMain w)
    {
        this.w = w;

        ImageIcon icono = new ImageIcon("addIcon.png");
        ImageIcon icono2 = new ImageIcon("resume.png");
        ImageIcon icono3 = new ImageIcon("stop.png");
        ImageIcon icono4 = new ImageIcon("stopAll.png");
        ImageIcon icono5 = new ImageIcon("clock.png");
        ImageIcon icono6 = new ImageIcon("options.png");

        w.addUrlButton.setIcon(icono);
        w.continueButton.setIcon(icono2);
        w.stopButton.setIcon(icono3);
        w.stopAllButton.setIcon(icono4);
        w.programmButton.setIcon(icono5);
        w.optionsButton.setIcon(icono6);

        w.addUrlButton.addActionListener(this);
        w.continueButton.addActionListener(this);
        w.stopButton.addActionListener(this);
        w.stopAllButton.addActionListener(this);
        w.programmButton.addActionListener(this);
        w.optionsButton.addActionListener(this);

        w.menuItem.addActionListener(this);
        w.menuItem2.addActionListener(this);
        w.menuItem3.addActionListener(this);
        w.menuItem4.addActionListener(this);
        w.menuItem5.addActionListener(this);
        w.menuItem6.addActionListener(this);

        //TODO CREAR UN ARCHIVO DE CONFIGURACION
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source.getClass() == JButton.class)
        {
            if(source == w.addUrlButton)
            {
                WindowAdd2 w = new WindowAdd2();
                w.mostrarDialogo();
            }
            if(source == w.continueButton)
            {

            }
            if(source == w.stopButton)
            {

            }
            if(source == w.stopAllButton)
            {

            }
            if(source == w.programmButton)
            {

            }
            if(source == w.optionsButton)
            {

            }
        }
        else
        {
            if(source.getClass() == JMenuItem.class)
            {
                String actionCommand = ((JMenuItem) e.getSource()).getActionCommand();

                switch (actionCommand)
                {

                }
            }
        }

    }
}
