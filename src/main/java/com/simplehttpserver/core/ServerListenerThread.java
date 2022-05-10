package com.simplehttpserver.core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListenerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    //to allow serving on multiple ports and handle multiple webroots
    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {


        try {
            //while loop to have multiple browsers connect to server
            while(serverSocket.isBound() && !serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();

                LOGGER.info("Connection Accepted" + socket.getInetAddress());

                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();


            }
           // serverSocket.close();


        } catch (IOException e) {
            LOGGER.error("Problem with setting socket", e);
        }finally {
            if(serverSocket!= null){
                try{
                    serverSocket.close();
                }catch(IOException e){}
            }
        }
    }
}

