package com.oleksii_ka64m;

import com.oleksii_ka64m.Commons.OptionsReaders.ServerOptions;
import com.oleksii_ka64m.MultithreadedServer.MultithreadedServer;

/**
 * Created by Oleksii on 13.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        MultithreadedServer myserver=new MultithreadedServer();
        myserver.start();
    }

//    public static void main(String[] args) throws Throwable {
//        ServerSocket ss = new ServerSocket(8085);
//        while (true) {
//            Socket s = ss.accept();
//            S.out.println("Client accepted");
//            new Thread(new SocketProcessor(s)).start();
//        }
//    }

//    private static class SocketProcessor implements Runnable {
//
//        private Socket s;
//        private InputStream is;
//        private OutputStream os;
//
//        private SocketProcessor(Socket s) throws Throwable {
//            this.s = s;
//            this.is = s.getInputStream();
//            this.os = s.getOutputStream();
//        }

//        public void run() {
//            try {
//                readInputHeaders();
//                writeResponse("<html><body><h1>Hello from Habrahabr</h1></body></html>");
//            } catch (Throwable t) {
//                /*do nothing*/
//            } finally {
//                try {
//                    s.close();
//                } catch (Throwable t) {
//                    /*do nothing*/
//                }
//            }
//            System.err.println("Client processing finished");
//        }

//        private void writeResponse(String s) throws Throwable {
//            String response = "HTTP/1.1 200 OK\r\n" +
//                    "Server: YarServer/2009-09-09\r\n" +
//                    "Content-Type: text/html\r\n" +
//                    "Content-Length: " + s.length() + "\r\n" +
//                    "Connection: close\r\n\r\n";
//            String result = response + s;
//            os.write(result.getBytes());
//            os.flush();
//        }

//        private void readInputHeaders() throws Throwable {
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            while(true) {
//                String s = br.readLine();
//                if(s == null || s.trim().length() == 0) {
//                    break;
//                }
//            }
//        }
//    }
}
