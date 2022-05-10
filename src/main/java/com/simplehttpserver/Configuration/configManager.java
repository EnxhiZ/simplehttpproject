package com.simplehttpserver.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.simplehttpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class configManager {
    private static configManager myconfigManager;
    private static configuration myCurrentConfiguration;

    private configManager() {
    }

    public static configManager getInstance(){
        if (myconfigManager==null)
            myconfigManager = new configManager();
        return myconfigManager;
    }

    //method to load configuration file from the path provided
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        while(true){
            try {
                if (!((i = fileReader.read()) != -1)) break;
            } catch (IOException e) {
                throw new HttpConfigurationException(e);
            }
            sb.append((char)i);
        }
        JsonNode config = null;
        try {
            config = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing Configuration file", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(config, configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing Configuration file, internal", e);
        }


    }
    //returns the current loaded configuration
    public configuration getCurrentConfiguration(){
        if(myCurrentConfiguration == null){
            throw new HttpConfigurationException("No Current Configuration Set");

        }
        return myCurrentConfiguration;
    }


}
