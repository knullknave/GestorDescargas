package com.dam.download.View;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WindowAdd2 extends JDialog
{

    private JPanel contentPane;
    private JTextField textField1;
    private JButton acceptButton;
    private JButton cancelButton;

    public WindowAdd2()
    {
        setContentPane(contentPane);
        pack();
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);

        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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

    private void onOK()
    {
        //TODO EJECUTAR UNA DESCARGA
        dispose();
        WindowDownload w = new WindowDownload(textField1.getText());

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
