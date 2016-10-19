package com.oleksii_ka64m.MultithreadedServer;

import com.oleksii_ka64m.Commons.OptionsReaders.ServerOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

/**
 * Created by Oleksii on 15.10.2016.
 */
public class StopServerListener extends Thread {
    private ServerSocket serverSocket;
    Logger logger=LogManager.getLogger();
    public StopServerListener(int port) {
        setDaemon(true);
        setName("StopMonitor");
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
       logger.error("stop monitor on port: "+serverSocket.getLocalPort());
        Socket socket;
        try {
            socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s1=reader.readLine();

            String m="Server stopped";
            OutputStream s=socket.getOutputStream();
            showlandingpage("/byebye.jpg",s);
            s.write(m.getBytes());
            s.flush();
            socket.close();
            serverSocket.close();


        } catch (Throwable e) {
//            throw new RuntimeException(e);
        }
    }
    private void writeResponse(String s,OutputStream os) throws Throwable {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: RomankoServer/2016\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + s.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + s;
        os.write(result.getBytes());
        os.flush();
    }
    private void showlandingpage(String s,OutputStream os) throws Throwable{
        InputStream inp=Worker.class.getResourceAsStream(s);

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inp.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.flush();
    };
    private static String getRequest(String header) throws ParseException {
        final String host = "GET ";
        final String normalEnd = " ";
        final String msEnd = "H";

        int s = header.indexOf(host, 0);
        if (s < 0) {
            return "error";
        }
        s += host.length();
        int e = header.indexOf(normalEnd, s);
        e = (e > 0) ? e : header.indexOf(msEnd, s);
        if (e < 0) {
            throw new ParseException(
                    "В заголовке запроса не найдено " +
                            "закрывающих символов после пункта Host.",
                    0);
        }
        String res = header.substring(s, e).trim();
        return res;
    }
}
