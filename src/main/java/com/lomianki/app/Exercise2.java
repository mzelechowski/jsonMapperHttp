package com.lomianki.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Exercise2 {
    public static void main(String[] args) {
        /*Przykład pobiernia JSON'a wystawionego przez Mockoon'a na adresie http://localhost:3002/persons
         * w postaci
         * {
         *  "data": {
         *    "name": "Maciej",
         *    "email": "maciej@gmail.com",
         *    "age": 30
         *  }
         *}
         * Pobierz email przy pomocy objectMapper.readTree
         */
        String baseURL = "http://localhost:3002/persons";
        String jsonData = getData(baseURL);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
           String email=jsonNode.get("data").get("email").toString();
            System.out.println(email);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getData(String baseURL) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(baseURL);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Sprawdz czy działa server lub Mokoon :)");
            System.exit(0);
        }
        return stringBuilder.toString();
    }
}
