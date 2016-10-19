package com.oleksii_ka64m.MultithreadedServer;

import com.oleksii_ka64m.Commons.OptionsReaders.ServerOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Oleksii on 13.09.2016.
 */
public class ServerListener implements Runnable {
    Boolean working=true;
    Integer port;
    ServerSocket serversocket=null;
    public ServerListener(Integer port){
        this.port=port;
    }
    @Override
    public void run() {

        try {
            serversocket= new ServerSocket(port);
            logger.error("Server started on:" +serversocket.getLocalPort());
            while (isWorking()) {
                Socket socket = serversocket.accept();
                logger.error("Client " +socket.getRemoteSocketAddress().toString() +" accepted");
                new Thread(new Worker(socket)).start();
            }
            logger.error("Serverprocessing finished");
        } catch (Throwable e) {
            logger.error(e.toString());
        }
    }

    private synchronized boolean isWorking() {
        return this.working;
    }
    public synchronized void stop() {
        logger.error("Server stopped");
        this.working=false;
        try {
            serversocket.close();
        }
        catch(IOException e){}
    }



    Logger logger = LogManager.getLogger();
}
