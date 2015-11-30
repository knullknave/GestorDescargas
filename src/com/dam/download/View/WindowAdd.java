package com.dam.download.View;

import javax.swing.*;

public class WindowAdd
{
    private JTextField textField1;
    private JPanel panel1;
    private JButton cancelButton;
    private JButton acceptButton;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("WindowAdd");
        frame.setContentPane(new WindowAdd().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
