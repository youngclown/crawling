package com.jsoup.crawling;

import com.google.gson.Gson;
import com.jsoup.crawling.model.Store;

import java.io.*;
import java.util.ArrayList;

public class JsonParser {
    public static void main(String[] args) throws IOException {
        Gson googleJson = new Gson();
        File file = new File("C:\\home\\text2.txt");
        BufferedReader br  =  new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        ArrayList<Store> list = new ArrayList<>();
        while ((line=br.readLine()) != null){
            String[] temp = line.split("\t");
            try {

                Store store = new Store();
                store.setStoreName(temp[0]);
                store.setLocation(temp[1]);
                try {
                    store.setCategory(temp[2]);
                } catch (Exception e) {
                    store.setCategory("-");
                }
                try {
                    store.setTelephone(temp[3]);
                } catch (Exception e) {
                    store.setTelephone("-");
                }
                list.add(store);
            } catch (Exception e) {
                System.out.println(line);
                throw  e;
            }
        }

        String gJson = googleJson.toJson(list, ArrayList.class);
        System.out.println(gJson);
    }
}
