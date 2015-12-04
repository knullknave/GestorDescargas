package com.dam.download.Model;

import com.dam.download.View.WindowDownload;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Model
{
    public void saveConfigureFile()
    {
        File f = new File("configuracion.props");
        if(!f.exists())
        {
            String path = System.getProperty("user.home") + File.separator;
            String numMax = String.valueOf(3);
            Properties prop = new Properties();
            prop.setProperty("path", path);
            prop.setProperty("NumMax", numMax);

            try
            {
                prop.store(new FileOutputStream("configuracion.props"), " Fichero de configuracion ");
            }
            catch ( FileNotFoundException fnfe )
            {
                fnfe.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void saveConfigureFile(String s1, String s2)
    {
        Properties prop = new Properties();
        prop.setProperty("path", s1);
        prop.setProperty("NumMax", s2);

        try
        {
            prop.store(new FileOutputStream("configuracion.props"), " Fichero de configuracion ");
        }
        catch ( FileNotFoundException fnfe )
        {
            fnfe.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String[] readConfigureFile()
    {
        Properties p = new Properties();
        String[] datos = new String[2];
        try
        {
            p.load(new FileInputStream("configuracion.props"));
            datos[0] = p.getProperty("path");
            datos[1] = p.getProperty("NumMax");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

        return datos;
    }

    public void writeFile(ArrayList<WindowDownload> wd)
    {
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try
        {
            fichero = new FileWriter("Gestor.dat");
            escritor = new PrintWriter(fichero);

            for (WindowDownload w1 : wd)
            {
                escritor.println(w1.name);
                escritor.println(w1.lblSize.getText());
                escritor.println(w1.lblState.getText());
                escritor.println(w1.lblVelocity.getText());
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            if (fichero != null)
            {
                try
                {
                    fichero.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
    }

    public ArrayList<WindowDownload> readFile()
    {
        ArrayList<WindowDownload> wd = new ArrayList<WindowDownload>();

        File fichero = null ;
        FileReader lector = null ;
        BufferedReader buffer = null ;
        try
        {
            buffer = new BufferedReader(new FileReader( new File("Gestor.dat")));
            String linea = null ;
            ArrayList<String> lineas = new ArrayList<String>();
            while ((linea = buffer.readLine()) != null)
            {
                lineas.add(linea);
            }

            for(int i=0; i< lineas.size(); i++)
            {
                wd.add(new WindowDownload());
                wd.get(wd.size()-1).name = lineas.get(i);
                i++;
                wd.get(wd.size()-1).lblSize.setText(lineas.get(i));
                i++;
                wd.get(wd.size()-1).lblState.setText(lineas.get(i));
                i++;
                wd.get(wd.size()-1).lblVelocity.setText(lineas.get(i));
            }
        }
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            if (buffer != null)
            {
                try
                {
                    buffer.close ();
                }
                catch(IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
        return wd;
    }

    public ArrayList<String> readFile(String path)
    {
        ArrayList<String> lineas = null;
        File fichero = null ;
        FileReader lector = null ;
        BufferedReader buffer = null ;
        try
        {
            buffer = new BufferedReader(new FileReader( new File(path)));
            String linea = null ;
            lineas = new ArrayList<String>();
            while ((linea = buffer.readLine()) != null)
            {
                lineas.add(linea);
            }
        }
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            if (buffer != null)
            {
                try
                {
                    buffer.close ();
                }
                catch(IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
        return lineas;
    }
}
