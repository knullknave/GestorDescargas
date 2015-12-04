package com.dam.download.View;

import com.dam.download.Controller.ControllerMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class WindowDownload extends SwingWorker<Void,Void> implements ActionListener
{
    private JFrame frame;
    private JTabbedPane tabbedPane1;
    public JProgressBar progressBar1;
    private JButton stopButton;
    private JButton startButton;
    private JPanel panel1;
    public JLabel lblUrl;
    public JLabel lblState;
    public JLabel lblSize;
    private JLabel lblDownloaded;
    public JLabel lblVelocity;

    private String url;
    private boolean stop;
    private File f;
    private FileOutputStream fos;
    public String size;
    private String folder;
    private long tamanoFichero;
    private ControllerMain c;
    public String name;
    private boolean paused;

    private static final DecimalFormat df = new DecimalFormat("#.##");

    public WindowDownload()
    {

    }

    public WindowDownload(String url, String path, ControllerMain c) throws IOException
    {
        frame = new JFrame("Descarga");
        frame.pack();
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        stop = false;
        paused = false;

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
        this.tamanoFichero = conexion.getContentLength();

        size = df.format(tamanoFichero / 1024.0) + " KB";

        lblSize.setText(size);
        lblState.setText("Waiting");
        lblVelocity.setText("0 KB/s");
        lblDownloaded.setText("0 %");
        startButton.addActionListener(this);
        stopButton.addActionListener(this);

        if(path.endsWith("\\"))
        {
            if(lblUrl.getText().substring(lblUrl.getText().length()-4, lblUrl.getText().length()-3).equals("."))
                name = path + lblUrl.getText().substring(lblUrl.getText().length() - 8, lblUrl.getText().length());
            else
                name = path + lblUrl.getText().substring(lblUrl.getText().length() - 8, lblUrl.getText().length()) + ".pdf";

            f = new File(name);
            name = f.getAbsolutePath();
        }
        else
        {
            name = path + lblUrl.getText().substring(lblUrl.getText().length() - 8, lblUrl.getText().length()) + ".pdf";
            File f = new File(name);
            name = f.getAbsolutePath();
        }


        this.f = new File(name);
        while(f.exists())
        {
            f = new File(name.substring(0, name.length()-4) + c.i + name.substring(name.length()-4, name.length()));
            c.i++;
        }
        f.createNewFile();

        folder = f.getAbsolutePath();
        name = f.getName();

        this.c = c;
        c.wd.add(this);

        c.recargar();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source.getClass() == JButton.class)
        {
            String actionCommand = ((JButton)source).getActionCommand();

            switch (actionCommand)
            {
                case "Start":
                    lblState.setText("Downloading...");
                    startButton.setText("Pause");
                    this.execute();
                    c.m.writeFile(c.wd);
                    c.recargar();
                    break;
                case "Pause":
                    lblState.setText("Paused");
                    startButton.setText("Continue");
                    pause();
                    c.m.writeFile(c.wd);
                    c.recargar();
                    break;
                case "Continue":
                    lblState.setText("Downloading...");
                    startButton.setText("Pause");
                    resume();
                    c.m.writeFile(c.wd);
                    c.recargar();
                    break;
                case "Stop":
                    stop = true;
                    startButton.setText("Start");
                    lblState.setText("Stoped");
                    frame.dispose();
                    c.m.writeFile(c.wd);
                    c.recargar();
                    break;
            }
        }
    }

    public void resume()
    {
        paused = false;

        synchronized (this)
        {
            notify();
        }

    }

    public void pause()
    {
        paused = true;
    }

    @Override
    protected Void doInBackground() throws Exception
    {
        URL url = new URL(this.url);
        URLConnection conexion = url.openConnection();

        int tamanoFichero = conexion.getContentLength();

        InputStream is = url.openStream();

        fos = new FileOutputStream(folder);

        byte[] bytes = new byte[2048];
        int longitud = 0;
        int progresoDescarga = 0;

        double porcentage = 0.0;

        while ((longitud = is.read(bytes)) != -1)
        {
            long i = System.nanoTime();

            fos.write(bytes, 0, longitud);

            long f = System.nanoTime() - i;

            double kb = (double) longitud / 1024.0;
            porcentage = porcentage + ((kb * 100000.0) / tamanoFichero);
            double seg = (double) f / 10000;

            lblVelocity.setText(df.format(kb / seg) + " KB/s");
            lblDownloaded.setText(df.format(porcentage) + "%");

            progresoDescarga += longitud;
            setProgress((int) (progresoDescarga * 100 / tamanoFichero));

            if (stop == true)
            {
                break;
            }
            if(paused == true)
            {
                try
                {
                    synchronized (this)
                    {
                        wait();
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("Runner away!");
                }
            }
            c.recargar();
        }

        is.close();
        fos.close();

        if(stop == false)
        {
            setProgress(100);

            lblState.setText("Finished");
            c.m.writeFile(c.wd);
            c.recargar();
            frame.dispose();
            WindowFinished wf = new WindowFinished(this.url, f.getAbsolutePath(), this.tamanoFichero);
            wf.mostrar();
        }
        else
        {
            setProgress(0);

            f.delete();
            frame.dispose();
        }

        if(lblState.getText().equals("Downloading...") || lblState.getText().equals("Paused") )
        {
            lblState.setText("Failure");
            c.m.writeFile(c.wd);
            c.recargar();
        }

        c.numDescargas--;

        return null;
    }

    public void mostrarDialogo()
    {
        frame.setVisible(true);
    }
}