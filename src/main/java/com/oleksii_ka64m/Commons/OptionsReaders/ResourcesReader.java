package com.oleksii_ka64m.Commons.OptionsReaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Oleksii on 15.10.2016.
 */
public class ResourcesReader {
    private static final String propertiesPath="noinit";
    Logger logger = LogManager.getLogger(this);
    public static final ResourcesReader PARSER=new ResourcesReader();
    protected String getPropertiesPath(){
        return propertiesPath;
    }
    public  String getValue(String Key){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Properties prop = new Properties();
            InputStream input=ResourcesReader.class.getResourceAsStream(getPropertiesPath());
            prop.load(input);

            return prop.getProperty(Key);
        } catch (Exception e){
            logger.error("Error with reading file application.properties " + e.toString());
            return null;
        }
    }
}
