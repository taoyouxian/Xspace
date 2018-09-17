package cn.edu.ruc.iir.xpace.datascience;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFactory
{
    private static ConfigFactory instance = null;

    private ConfigFactory()
    {
        prop = new Properties();
        String dsHome = System.getenv("ds_HOME");
        InputStream in = null;
        if (dsHome == null)
        {
            in = this.getClass().getResourceAsStream("/ds.properties");
        } else
        {
            if (!(dsHome.endsWith("/") || dsHome.endsWith("\\")))
            {
                dsHome += "/";
            }
            prop.setProperty("ds.home", dsHome);
            try
            {
                in = new FileInputStream(dsHome + "ds.properties");
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            if (in != null)
            {
                prop.load(in);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ConfigFactory Instance()
    {
        if (instance == null)
        {
            instance = new ConfigFactory();
        }
        return instance;
    }

    private Properties prop = null;

    public void loadProperties(String propFilePath)
    {
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(propFilePath);
            this.prop.load(in);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addProperty(String key, String value)
    {
        this.prop.setProperty(key, value);
    }

    public String getProperty(String key)
    {
        return this.prop.getProperty(key);
    }
}
