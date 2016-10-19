package com.oleksii_ka64m.MultithreadedServer;

/**
 * Created by Oleksii on 15.10.2016.
 */
import com.oleksii_ka64m.Commons.OptionsReaders.ServerOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.IOUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Worker implements Runnable {

    private Socket s;
    private InputStream is;
    private OutputStream os;

    public Worker(Socket s) throws Throwable {
        this.s = s;
        this.is = s.getInputStream();
        this.os = s.getOutputStream();
    }

    public void run() {
        Logger logger = LogManager.getLogger();
        try {
            String line = "Client processing run!" + (System.currentTimeMillis()/1000/60/60);
            logger.error(line);
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String result=reader.readLine();
            showlandingpage("/main.htm");
//            InputStream sin = this.s.getInputStream();
//            StringWriter writer = new StringWriter();
//            String result=new Scanner(sin,"UTF-8").useDelimiter("\\A").next();


//            logger.error("request is:"+result);

//            this.writeResponse("Server says hello!");



        } catch (Throwable t) {
            logger.error("error");
        } finally {
            try {
                s.close();
            } catch (Throwable t) {
                    /*do nothing*/
            }
        }
        logger.error("Client processing finished");
    }

    private void writeResponse(String s) throws Throwable {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: RomankoServer/2016\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + s.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + s;
        os.write(result.getBytes());
        os.flush();
    }
    private void showlandingpage(String s) throws Throwable{
        InputStream inp=Worker.class.getResourceAsStream(s);

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inp.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.flush();
    };
//    private void readInputHeaders() throws Throwable {
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        while (true) {
//            String s = br.readLine();
//            if (s == null || s.trim().length() == 0) {
//                break;
//            }
//        }
//    }
}