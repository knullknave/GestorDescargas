package com.dam.download.View;

import javax.swing.*;

public class SplashScreen
{
    private JFrame frame;
    private JPanel panel1;
    private JLabel lblSplash;

    SplashScreen()
    {
        ImageIcon icon = new ImageIcon("ArmarioCasas.png");
        lblSplash.setIcon(icon);

        frame = new JFrame();
        frame.getContentPane().add(panel1);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void mostrar()
    {
        frame.setVisible(true);
    }

    public void ocultar()
    {
        frame.setVisible(false);
    }
}
