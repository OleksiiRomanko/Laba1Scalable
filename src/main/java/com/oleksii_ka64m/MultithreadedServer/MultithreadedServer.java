package com.oleksii_ka64m.MultithreadedServer;

import com.oleksii_ka64m.Commons.OptionsReaders.ServerOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Oleksii on 15.10.2016.
 */
public class MultithreadedServer {
    Logger logger= LogManager.getLogger();
    Integer serverport=Integer.valueOf(ServerOptions.PARSER.getValue("listenerport"));
    Integer stopserver=Integer.valueOf(ServerOptions.PARSER.getValue("shutdownport"));
    public void start(){
        ServerListener server=new ServerListener(serverport);
        new Thread(server).start();
        try{
            Thread stopport=new StopServerListener(stopserver);
            stopport.start();
            stopport.join();
        }

        catch(InterruptedException e){
            logger.error(e.toString());
        }
        logger.error("Stopping server");
        server.stop();
    }
}
