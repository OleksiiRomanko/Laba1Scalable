package com.oleksii_ka64m.Commons.OptionsReaders;

import com.oleksii_ka64m.Commons.OptionsReaders.ResourcesReader;

/**
 * Created by Oleksii on 15.10.2016.
 */
public class ServerOptions extends ResourcesReader{
    public static final ResourcesReader PARSER=new ServerOptions();

    @Override
    public String getPropertiesPath(){
        return "/config/server.conf";
    }

//    final static String propertiesPath=ResourcesReader.setPath("/config/server.conf");
}
