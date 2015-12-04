package com.dam.download.Controller;

import com.dam.download.Model.Model;
import com.dam.download.View.Configuration;
import com.dam.download.View.WindowAdd;
import com.dam.download.View.WindowDownload;
import com.dam.download.View.WindowMain;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ControllerMain implements ActionListener
{
    public WindowMain w;
    public Model m = new Model();
    public String[] datos;
    public ArrayList<WindowDownload> wd;
    public int i;
    private DefaultTableModel model;
    public int numDescargas;

    public ControllerMain(WindowMain w)
    {
        this.w = w;

        numDescargas = 0;
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

        String[] columnNames = {"File name", "Size", "State", "Velocity"};

        model = new DefaultTableModel();
        w.table1.setModel(model);
        model.addColumn(columnNames[0]);
        model.addColumn(columnNames[1]);
        model.addColumn(columnNames[2]);
        model.addColumn(columnNames[3]);

        m = new Model();
        if(!new File("configuracion.props").exists())
            m.saveConfigureFile();
        datos = m.readConfigureFile();

        File f = new File("Gestor.dat");
        if(f.exists())
        {
            wd = m.readFile();
            recargar();
        }
        else
        {
            wd = new ArrayList<WindowDownload>();
        }
    }

    public void recargar()
    {
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
                /**
                 * @Deprecated
                 */
            }
            if(source == w.stopButton)
            {
                /**
                 * @Deprecated
                 */
            }
            if(source == w.stopAllButton)
            {
                /**
                 * @Deprecated
                 */
            }
            if(source == w.programmButton)
            {
                /**
                 * @Deprecated
                 */
            }
            if(source == w.optionsButton)
            {
                Configuration c = new Configuration(m);
                c.mostrar();
            }
        }
        else
        {
            if(source.getClass() == JMenuItem.class)
            {
                String actionCommand = ((JMenuItem) e.getSource()).getActionCommand();

                switch (actionCommand)
                {
                    case "Download Files":
                        JFileChooser fc = new JFileChooser();
                        fc.setCurrentDirectory(new File(System.getProperty("user.home") + File.separator));
                        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                        {
                            ArrayList<String> descargas = m.readFile(fc.getSelectedFile().getAbsolutePath());

                            String path = m.readConfigureFile()[0];

                            for(int i=0; i<descargas.size();i++)
                            {
                                try
                                {
                                    WindowDownload w = new WindowDownload(descargas.get(i), path, this);
                                    w.mostrarDialogo();
                                    w.execute();
                                }
                                catch (IOException ioex)
                                {
                                    ioex.printStackTrace();
                                }
                            }
                        }

                        break;
                    case "Configuration":
                        Configuration c = new Configuration(m);
                        c.mostrar();
                        break;
                    case "Exit":
                        System.exit(0);
                        break;
                }
            }
        }
    }
}
