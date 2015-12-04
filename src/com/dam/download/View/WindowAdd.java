package com.dam.download.View;

import com.dam.download.Controller.ControllerMain;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class WindowAdd extends JDialog
{

    private JPanel contentPane;
    private JTextField textField1;
    private JButton acceptButton;
    private JButton cancelButton;
    private JTextField textField2;
    private JButton pathButton;
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

        textField2.setText(c.m.readConfigureFile()[0]);

        pathButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                search();
            }
        });

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

    public void search()
    {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(c.m.readConfigureFile()[0]));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fc.getSelectedFile();
            if(selectedFile.getAbsolutePath().endsWith("\\"))
                c.datos[0] = selectedFile.getAbsolutePath();
            else
                c.datos[0] = selectedFile.getAbsolutePath() + "\\";
        }
        textField2.setText(c.datos[0]);
    }

    private void onOK() throws IOException
    {
        if(textField1.getText().equals("") | textField2.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please, fill each options", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(c.numDescargas < Integer.parseInt(c.m.readConfigureFile()[1]))
            {
                dispose();
                c.numDescargas ++;
                WindowDownload w = new WindowDownload(textField1.getText(), textField2.getText(), this.c);
                c.m.writeFile(c.wd);
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
            else
            {
                JOptionPane.showMessageDialog(null, "You have too many downloads right now", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        }
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
