package com.dam.download.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class WindowFinished extends JDialog implements ActionListener
{
    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JButton openButton;
    private JButton openFolderButton;
    private JButton closeButton;
    private JLabel lblSize;

    private String url;
    private String folder;
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public WindowFinished(String url, String folder, long size)
    {
        //TODO PERMITIR SOLO COPIAR DEL TEXTFIELD
        setTitle("Descarga Completada");
        setContentPane(contentPane);
        setSize(500, 300);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(null);

        openButton.addActionListener(this);
        openFolderButton.addActionListener(this);
        closeButton.addActionListener(this);

        textField1.setText(url);
        textField2.setText(folder);
        textField1.setEnabled(false);
        textField2.setEnabled(false);

        this.folder = folder;
        this.url = url;

        lblSize.setText("Descargado " + df.format(size / 1024.0) + " KB ( " + size + " Bytes)");

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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source.getClass() == JButton.class)
        {
            String actionCommand = ((JButton) source).getActionCommand();
            switch (actionCommand)
            {
                case "Open":
                    dispose();
                    File f = new File(this.folder);
                    try
                    {
                        Desktop.getDesktop().open(f);
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    break;
                case "Open Folder":
                    JFileChooser fc = new JFileChooser();
                    f = new File(this.folder);
                    fc.setCurrentDirectory(f);
                    fc.setSelectedFile(f);
                    int result = fc.showOpenDialog(null);
                    if(result == JFileChooser.APPROVE_OPTION)
                    {
                        try
                        {
                            File file = fc.getSelectedFile();
                            Desktop.getDesktop().open(file);
                        }
                        catch (IOException e1)
                        {
                            e1.printStackTrace();
                        }
                    }
                    dispose();
                    break;
                case "Close":
                    onCancel();
                    break;
            }
        }
    }

    public void mostrar()
    {
        setVisible(true);
    }

    public void onCancel()
    {
        dispose();
    }
}