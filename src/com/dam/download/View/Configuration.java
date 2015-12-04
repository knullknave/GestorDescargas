package com.dam.download.View;

import com.dam.download.Model.Model;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class Configuration extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton searchButton;
    private Model m;

    public Configuration(Model m)
    {
        setContentPane(contentPane);
        pack();
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(530,150);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);

        this.m = m;

        String[] datos = m.readConfigureFile();
        textField1.setText(datos[0]);
        textField2.setText(datos[1]);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });

        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(m.readConfigureFile()[0]));
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    File f = fc.getSelectedFile();
                    if(f.getAbsolutePath().endsWith("\\"))
                        textField1.setText(f.getAbsolutePath());
                    else
                        textField1.setText(f.getAbsolutePath() + "\\");

                }
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK()
    {
        if(Integer.parseInt(textField2.getText()) > 0)
        {
            m.saveConfigureFile(textField1.getText(), textField2.getText());
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please, introduce more than 0 downloads", "Warning", JOptionPane.WARNING_MESSAGE);
            textField2.setText(m.readConfigureFile()[1]);
        }
    }

    private void onCancel()
    {
        dispose();
    }

    public void mostrar()
    {
        setVisible(true);
    }
}
