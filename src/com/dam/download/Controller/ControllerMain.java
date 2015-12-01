package com.dam.download.Controller;

import com.dam.download.View.WindowAdd;
import com.dam.download.View.WindowDownload;
import com.dam.download.View.WindowMain;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControllerMain implements ActionListener
{
    WindowMain w;
    public ArrayList<WindowDownload> wd;
    public int i;
    private DefaultTableModel model;

    public ControllerMain(WindowMain w)
    {
        this.w = w;

        i = 1;

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

        //TODO COMPROBAR QUE EXISTA UN FICHERO CON UN ARRAY DE DESCARGAS. SINO:
        wd = new ArrayList<WindowDownload>();

        String[] columnNames = {"File name", "Size", "State", "Velocity"};

        String[] data = {"Prueba1", "20 KB", "Downloading", "10 KB/S"};

        model = new DefaultTableModel();
        w.table1.setModel(model);
        model.addColumn(columnNames[0]);
        model.addColumn(columnNames[1]);
        model.addColumn(columnNames[2]);
        model.addColumn(columnNames[3]);
        //model.addRow(data);
        //TODO CREAR UN ARCHIVO DE CONFIGURACION
    }

    public void recargar()
    {
/*
        for (int i = 0; i < model.getRowCount() ; i++)
        {
            model.removeRow(i);
        }*/

        String[] columnNames = {"File name", "Size", "State", "Velocity"};

        model = new DefaultTableModel();
        w.table1.setModel(model);
        model.addColumn(columnNames[0]);
        model.addColumn(columnNames[1]);
        model.addColumn(columnNames[2]);
        model.addColumn(columnNames[3]);

        for (WindowDownload windown : this.wd)
        {
            String[] column = {windown.name, windown.lblSize.getText(), windown.lblState.getText(), windown.lblVelocity.getText()};
            model.addRow(column);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source.getClass() == JButton.class)
        {
            if(source == w.addUrlButton)
            {
                WindowAdd w = new WindowAdd(this);
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
                //TODO ADD CON DIA, TIEMPO, ETC PARA DESCARGAR
            }
            if(source == w.optionsButton)
            {
                //TODO VENTANA CON OPCIONES
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
