package com.simplehttpserver.core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;
    public HttpConnectionWorkerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            File index = new File("C:\\Users\\eziko\\Downloads\\simplehttpproject\\index.html");

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());// Make a writer for the output stream to
            // the client
            BufferedReader reader = new BufferedReader(new FileReader(index));// grab a file and put it into the buffer
            // print HTTP headers
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type: text/html");
            printWriter.println("Content-Length: " + index.length());
            printWriter.println("\r\n");
            String line = reader.readLine();
            while (line != null)
            {
                printWriter.println(line);
                line = reader.readLine();
            }
            reader.close();

            printWriter.close();
            inputStream.close();

        }catch (IOException e){
            LOGGER.error("Problem with Communication", e);
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {}

            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {}

            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {}

            }


        }
        LOGGER.info("Connection Processing Finished.");

    }
}
