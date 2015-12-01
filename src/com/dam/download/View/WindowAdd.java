package com.dam.download.View;

import com.dam.download.Controller.ControllerMain;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class WindowAdd extends JDialog
{

    private JPanel contentPane;
    private JTextField textField1;
    private JButton acceptButton;
    private JButton cancelButton;
    private ControllerMain c;

    public WindowAdd(ControllerMain c)
    {
        setContentPane(contentPane);
        pack();
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(520,100);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);

        this.c = c;

        acceptButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    onOK();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() throws IOException
    {
        //TODO EJECUTAR UNA DESCARGA
        dispose();
        WindowDownload w = new WindowDownload(textField1.getText(), this.c);

        w.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {
                if(evt.getPropertyName().equals("progress"))
                {
                    w.progressBar1.setValue((Integer)evt.getNewValue());
                }
            }
        });

        w.mostrarDialogo();
    }

    private void onCancel()
    {
        dispose();
    }

    public void mostrarDialogo()
    {
        setVisible(true);
    }
}
