package com.simplehttpserver;
import com.simplehttpserver.Configuration.configManager;
import com.simplehttpserver.Configuration.configuration;
import com.simplehttpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);


    public static void main(String[] args) {


                LOGGER.info("Server starting...");

                configManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
                configuration conf = configManager.getInstance().getCurrentConfiguration();

                LOGGER.info("Using Port: " + conf.getPort());
                LOGGER.info("Using webRoot: " + conf.getWebroot());

                ServerListenerThread serverListenerThread = null;
                try {
                    serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
                    serverListenerThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
