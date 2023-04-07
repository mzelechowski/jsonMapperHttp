package com.lomianki.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lomianki.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        /**Przykład pobiernia JSON'a wystawionego przez Mockoon'a na adresie http://localhost:3001/persons
         * w postaci
         * {
         *   "name": "Maciej",
         *   "email": "maciej@gmail.com",
         *   "age": 30
         * }
         */
        String baseURL = "http://localhost:3001/persons";
        String jsonData=getData(baseURL);
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            Person person=objectMapper.readValue(jsonData, Person.class);
            System.out.println(person.getEmail());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getData(String baseURL) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {

            URL url = new URL(baseURL);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            while((line=bufferedReader.readLine())!=null){
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
