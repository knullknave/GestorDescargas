package com.dam.download.View;

import javax.swing.*;
import javax.swing.text.StringContent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WindowDownload extends SwingWorker<Void,Void> implements ActionListener
{
    private JFrame frame;
    private JTabbedPane tabbedPane1;
    public JProgressBar progressBar1;
    private JButton stopButton;
    private JButton startButton;
    private JPanel panel1;
    private JLabel lblUrl;
    private JLabel lblState;
    private JLabel lblSize;
    private JLabel lblDownloaded;
    private JLabel lblVelocity;

    private String url;
    private boolean stop;
    private FileOutputStream fos;
    String size;

    WindowDownload(String url)
    {
        frame = new JFrame("Descarga");
        frame.pack();
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        stop = false;

        this.url = url;
        lblUrl.setText(url);

        URL url2 = null;
        try
        {
            url2 = new URL(this.url);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        URLConnection conexion = null;
        try
        {
            conexion = url2.openConnection();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // Obtiene el tamaño del fichero en bytes
        int tamanoFichero = conexion.getContentLength();

        size = String.valueOf(tamanoFichero/1024.0) + " KB";

        lblSize.setText(size);
        //TODO RETURN THE STATE
        lblState.setText("Waiting");
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source.getClass() == JButton.class)
        {
            String actionCommand = ((JButton)source).getActionCommand();
            //TODO MIRAR COMO CONTINUAR UNA DESCARGA
            switch (actionCommand)
            {
                case "Start":
                    lblState.setText("Downloading...");
                    startButton.setText("Pause");
                    this.execute();
                    break;
                case "Pause":
                    lblState.setText("Paused");
                    startButton.setText("Start");
                    break;
                case "Stop":
                    stop = true;
                    startButton.setText("Start");
                    lblState.setText("Stoped");
                    break;
            }
        }
    }

    @Override
    protected Void doInBackground() throws Exception
    {
        URL url = new URL(this.url);
        URLConnection conexion = url.openConnection();
        // Obtiene el tamaño del fichero en bytes
        int tamanoFichero = conexion.getContentLength();

        InputStream is = url.openStream();
        //TODO PENSAR DONDE DESCARGAR ETC
        File f = new File("fileDescargado.pdf");
        int i= 1;
        while(f.exists())
        {
            f = new File("fileDescargado" + i + ".pdf");
            i++;
        }
        fos = new FileOutputStream(f.getAbsolutePath());

        byte[] bytes = new byte[2048];
        int longitud = 0;
        int progresoDescarga = 0;

        while ((longitud = is.read(bytes)) != -1)
        {
            fos.write(bytes, 0, longitud);

            progresoDescarga += longitud;
            setProgress((int) (progresoDescarga * 100 / tamanoFichero));

            if(stop == true)
                break;
        }

        is.close();
        fos.close();

        setProgress(100);

        return null;
    }

    public void mostrarDialogo()
    {
        frame.setVisible(true);
    }
}
